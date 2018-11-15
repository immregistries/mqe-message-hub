package org.immregistries.mqe.hub.report.viewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MESSAGE_METADATA")
public class MessageMetadata {

  @Id
  @SequenceGenerator(name = "MESSAGE_METADATA_GENERATOR", sequenceName = "MESSAGE_METADATA_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_METADATA_GENERATOR")
  @Column(name = "MESSAGE_METADATA_ID")
  private long id;
  @Column(columnDefinition = "CLOB")
  @Lob
  private String message;
  @Column(columnDefinition = "CLOB")
  @Lob
  private String response;

  @javax.persistence.Temporal(TemporalType.TIMESTAMP)
  private Date inputTime;

  @NotNull
  private String provider;

  @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="messageMetadata")
  public List<MessageDetection> detections = new ArrayList<>();

  @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="messageMetadata")
  public List<MessageCode> codes = new ArrayList<>();

  public List<MessageCode> getCodes() {
    return codes;
  }

  public void setCodes(List<MessageCode> codes) {
    this.codes = codes;
  }

  public List<MessageDetection> getDetections() {
    return detections;
  }

  public void setDetections(
      List<MessageDetection> detections) {
    this.detections = detections;
  }

  public Date getInputTime() {
    return inputTime;
  }

  public void setInputTime(Date inputTime) {
    this.inputTime = inputTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }


}