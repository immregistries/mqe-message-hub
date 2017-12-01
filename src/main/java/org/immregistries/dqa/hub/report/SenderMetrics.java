package org.immregistries.dqa.hub.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TemporalType;

@Entity
@Table(name="SENDER_METRICS")
public class SenderMetrics {
	@Id
	@SequenceGenerator(name="SENDER_METRICS_GENERATOR", sequenceName="SENDER_METRICS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SENDER_METRICS_GENERATOR")
	@Column(name="SENDER_METRICS_ID")
	private long id;
	
	private String sender;

	@javax.persistence.Temporal(TemporalType.DATE)
	private Date metricsDate;
	
	private int patientCount;
	
	private int vaccinationCount;
	
	private int score;
	
	@OneToMany(mappedBy="senderMetrics", cascade = CascadeType.ALL)
	private List<SenderCodeMetrics> codes = new ArrayList<>();
	
	@OneToMany(mappedBy="senderMetrics", cascade = CascadeType.ALL)
	private List<SenderAttributeMetrics> attributes = new ArrayList<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public int getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}
	public int getVaccinationCount() {
		return vaccinationCount;
	}
	public void setVaccinationCount(int vaccinationCount) {
		this.vaccinationCount = vaccinationCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<SenderCodeMetrics> getCodes() {
		return codes;
	}
	public void setCodes(List<SenderCodeMetrics> codes) {
		this.codes = codes;
	}
	public List<SenderAttributeMetrics> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<SenderAttributeMetrics> attributes) {
		this.attributes = attributes;
	}
	public Date getMetricsDate() {
		return metricsDate;
	}
	public void setMetricsDate(Date metricsDate) {
		this.metricsDate = metricsDate;
	}
	
}