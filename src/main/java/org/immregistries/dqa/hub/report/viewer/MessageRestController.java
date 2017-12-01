package org.immregistries.dqa.hub.report.viewer;

import org.immregistries.dqa.hl7util.parser.MessageParser;
import org.immregistries.dqa.hl7util.parser.MessageParserHL7;
import org.immregistries.dqa.hl7util.parser.model.HL7MessagePart;
import org.immregistries.dqa.hl7util.parser.profile.generator.MessageProfileReader;
import org.immregistries.dqa.hl7util.parser.profile.generator.MessageProfileReaderNIST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller to get JSON objects related to the messages in the system.
 *
 * @author Josh
 */
@RestController
@RequestMapping(value = "/messages")
//@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class MessageRestController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MessageRestController.class);


    MessageParser parser = new MessageParserHL7();

    MessageProfileReader profileReader = new MessageProfileReaderNIST();

    @Autowired
    MessageMetadataJpaRepository messageRepo;

    @Autowired
    MessageRetrieverService messageRetreiver;

    @RequestMapping(method = RequestMethod.GET, value = "/{providerKey}/date/{date}/messages/{messages}/page/{page}")
    public MessageListContainer jsonMessagesGetter(HttpServletRequest request, @PathVariable("providerKey") String providerKey, @PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") Date date, @PathVariable("page") int pageNumber, @PathVariable("messages") int itemsCount, String filters) {
        LOGGER.info("jsonMessagesGetter - calling for messages.  ");
        MessageListContainer container = messageRetreiver.getMessages(providerKey, date, null, pageNumber, itemsCount);
        LOGGER.info("jsonMessagesGetter - Messages: " + container.getTotalMessages() + " pages: " + container.getTotalPages() + " current page: " + container.getPageNumber());
        return container;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public MessageDetailItem jsonMessage(HttpServletRequest request, @PathVariable("id") long messageQueueId) {

        LOGGER.info("id coming in from the call: " + messageQueueId);
        MessageMetadata mq = messageRepo.getOne(messageQueueId);
        List<HL7MessagePart> vxuParts = parser.getMessagePartList(mq.getMessage());

        List<HL7LocationValue> vxuLocs = prepareHL7LocationList(vxuParts);

        MessageListItem mli = messageRetreiver.getMessageListItemFor(mq);

        MessageDetailItem mdi = new MessageDetailItem();
        mdi.setVxuParts(vxuLocs);
        mdi.setMessageMetaData(mli);
        mdi.setProviderKey(mq.getProvider());

        String received = mq.getMessage().replaceAll("[\\r]+", "\n");
        mdi.setMessageReceived(received);
        mdi.setMessageResponse(mq.getResponse().replaceAll("[\\r]+", "\n"));

        return mdi;
    }

    List<HL7LocationValue> prepareHL7LocationList(List<HL7MessagePart> list) {
        List<HL7LocationValue> newList = new ArrayList<HL7LocationValue>();
        int x = 0;
        for (HL7MessagePart part : list) {
            HL7LocationValue value = partToLocation(part, x++);
            newList.add(value);
        }
        return newList;
    }

    HL7LocationValue partToLocation(HL7MessagePart part, int idx) {
        HL7LocationValue val = new HL7LocationValue();

        val.setValue(part.getValue());
        val.setValueIndex(idx);

        val.setFieldRepetition(part.getFieldRepetition());
        val.setSegmentIndex(part.getSegmentIndex());

        val.setLocation(part.getLocationCd());

        String locationDesc = profileReader.getFieldDescription(part.getLocationCd());
        val.setLocationDescription(locationDesc);

        return val;
    }
}