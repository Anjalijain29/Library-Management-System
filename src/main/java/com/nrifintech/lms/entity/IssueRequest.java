package com.nrifintech.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="issue_request")
@org.hibernate.annotations.DynamicInsert(value=true)
@org.hibernate.annotations.DynamicUpdate(value=true)
public class IssueRequest {
	public IssueRequest(int requestId, Member member, Book book, Date requestDate, String type) {
		super();
		this.requestId = requestId;
		this.member = member;
		this.book = book;
		this.requestDate = requestDate;
		this.type = type;
	}

	public IssueRequest(String type) {
		super();
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_id")
	private int requestId;
	
	@ManyToOne
	@JoinColumn(name="member_id", referencedColumnName="member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="book_id", referencedColumnName="book_id")
	private Book book;
	
	@Column(name="request_date")
	private Date requestDate;
	
	@Column(name="type")
	private String type;

	public IssueRequest() {
		super();
	
	}

	public IssueRequest(Member member, Book book) {
		super();
		this.member = member;
		this.book = book;
	}
	
	public IssueRequest(Member member, Book book,String type) {
		super();
		this.type=type;
		this.member = member;
		this.book = book;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
