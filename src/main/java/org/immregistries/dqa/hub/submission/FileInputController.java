package org.immregistries.dqa.hub.submission;

import org.immregistries.dqa.hub.rest.FileUploadData;
import org.immregistries.dqa.hub.rest.MessageInputController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RequestMapping(value = "/file")
@RestController public class FileInputController {

    private static final Logger logger = LoggerFactory.getLogger(FileInputController.class);

    @Autowired private MessageInputController messageController;

    private Map<String, FileUploadData> fileQueue = new HashMap<>();

    private static final String MSH_REGEX = "^MSH\\|.*";
    private static final String HL7_SEGMENT_REGEX = "^\\w\\w\\w\\|.*";

    @RequestMapping(value = "upload-messages", method = RequestMethod.POST)
    public FileUploadData urlEncodedHttpFormFilePost(@RequestParam("file") MultipartFile file,
        String facilityId) throws Exception {

        InputStream inputStream = file.getInputStream();

        String fileId = "file" + String.valueOf(new Date().getTime());
        FileUploadData fileUpload = new FileUploadData(facilityId, file.getOriginalFilename(), fileId);

        this.fileQueue.put(fileId, fileUpload);

        //These are to check if it's a zip file, and to use it yes:
        ZipInputStream zis = new ZipInputStream(inputStream);
        ZipEntry entry = zis.getNextEntry();

        if (entry == null) {
            //it's not a zip file.  process as text file.
            logger.info("Not a zip file!");
            fileUpload.getHl7Messages().addAll(this.getMessagesFromInputStream(file.getInputStream()));
        } else {
            //Process the first one.
            fileUpload.getHl7Messages().addAll(getMessagesFromInputStream(zis));
            //Then the rest.
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    fileUpload.getHl7Messages().addAll(getMessagesFromInputStream(zis));
                }
            }
        }

        logger.info("\nFilename: " + fileUpload.getFileName() + "\n" + "Number of messages: " + fileUpload.getNumberOfMessages() + "\n" + "Reported under: " + fileUpload);

        return fileUpload;
    }

    private List<String> getMessagesFromInputStream(InputStream inputStream) throws IOException {

        List<String> messages = new ArrayList<>();
        StringBuilder oneMessage = new StringBuilder();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        while ((line = bufferedReader.readLine()) != null) {
            logger.info("Line: " + line);
            if (line.matches(MSH_REGEX)) {
                if (oneMessage.length() <= 0) {
                    oneMessage.append(line);
                } else {
                    messages.add(oneMessage.toString());
                    oneMessage.setLength(0);
                    oneMessage.append(line);
                }
            } else {
                if (line.matches(HL7_SEGMENT_REGEX)) {
                    oneMessage.append("\r\n");
                    oneMessage.append(line);
                }
            }
        }

        if (oneMessage.length() > 0) {
            messages.add(oneMessage.toString());
        }

        return messages;
    }

    @RequestMapping(value = "report-file", method = RequestMethod.GET)
    public FileUploadData reportFile(@RequestParam("fileId") String fileId) {
        return this.fileQueue.get(fileId);
    }

    @RequestMapping(value = "stop-file", method = RequestMethod.POST) public void stopFile(@RequestParam("fileId") String fileId) {
        this.fileQueue.get(fileId).setStatus("Stop");
    }

    @RequestMapping(value = "process-file", method = RequestMethod.POST)
    public FileUploadData processFile(@RequestParam("fileId") String fileId) throws Exception {
        FileUploadData fileUpload = this.fileQueue.get(fileId);

        if ("started".equals(fileUpload.getStatus())) {
            return fileUpload;
        }

        fileUpload.setStatus("started");

        try {
            for (String message : fileUpload.getHl7Messages()) {

                if (!"started".equals(fileUpload.getStatus())) {
                    return fileUpload;
                } else {
                    logger.info("File " + fileId + " Stopped. Remaining Messages to process: " + fileUpload.getNumberUnProcessed());
                }

                String ackResult = messageController.urlEncodedHttpFormPost(message, null, null, fileUpload.getFacilityId());
                fileUpload.addAckMessage(ackResult);
            }
            fileUpload.setStatus("finished");
        } catch (Exception e) {
            logger.error("Exception processing messages: " + e.getMessage());
            e.printStackTrace();
            fileUpload.setStatus("exception");
        }

        return fileUpload;
    }
}
