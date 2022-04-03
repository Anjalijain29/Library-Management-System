package com.nrifintech.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="member")
@org.hibernate.annotations.DynamicInsert(value=true)
@org.hibernate.annotations.DynamicUpdate(value=true)
public class Member implements Serializable {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;
	

	@OneToOne
	@JoinColumn(name="member_id", referencedColumnName="user_id")
	private User user;
	
	@Column(name="status")
	private String status;
	
	@Column(name="warnings")
	private int warnings;
	
	@OneToMany(mappedBy="member")
	private List<IssueRequest> issueRequest;
	
	@OneToMany(mappedBy="member")
	private List<IssueHistory> issueHistory;

	public List<IssueRequest> getIssueRequest() {
		return issueRequest;
	}

	public void setIssueRequest(List<IssueRequest> issueRequest) {
		this.issueRequest = issueRequest;
	}

	public List<IssueHistory> getIssueHistory() {
		return issueHistory;
	}

	public void setIssueHistory(List<IssueHistory> issueHistory) {
		this.issueHistory = issueHistory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getWarnings() {
		return warnings;
	}

	public void setWarnings(int warnings) {
		this.warnings = warnings;
	}
	
	
}

