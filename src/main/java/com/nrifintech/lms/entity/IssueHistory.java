package com.nrifintech.lms.entity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="issue_history")
@org.hibernate.annotations.DynamicInsert(value=true)
@org.hibernate.annotations.DynamicUpdate(value=true)
public class IssueHistory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="issue_id")
	private int issueId;
	
	@ManyToOne
	@JoinColumn(name="member_id", referencedColumnName="member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="book_id", referencedColumnName="book_id")
	private Book book;
	
	@Column(name="issue_date")
	private Date issueDate;
	
	@Column(name="request_id")
	private int requestId;
	
	@Column(name="return_date")
	private Date returnDate;
	
	@Column(name="request_date")
	private Date requestDate;
	
	
	@Column(name="status")
	private String status;
	
	public IssueHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public IssueHistory(Member member, Book book, String status) {
//		super();
//		this.member = member;
//		this.book = book;
//		this.status = status;
//	}
	
	public IssueHistory(Member member, Book book, String status, int requestId) {
		super();
		this.member = member;
		this.book = book;
		this.status = status;
		this.requestId = requestId;
	}

	@Column(name="fine")
	private int fine;

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
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

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getFine() {
		return this.fine;
	}
	

	
	

	public void setFine(int fine) {
		this.fine = fine;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	
	public Date getDueDate() {
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Calendar c = Calendar.getInstance();
//		int day = issueDate.getDay();
//		issueDate.getMonth()
//		c.setTime(this.issueDate);
		
		Date dueDate = new Date(this.issueDate.getTime() + TimeUnit.DAYS.toMillis(14));

		return dueDate;
	
	}
	
	
	public int calculateFine() {
		if(this.status.equals("C"))
		{
			long duration = this.returnDate.getTime() - this.issueDate.getTime();
			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration)-14;
			
			return (int) Math.max(diffInDays, 0);
		}
		if(this.requestDate.getTime()>this.issueDate.getTime()) {
		long duration = this.requestDate.getTime() - this.issueDate.getTime();
		//long duration = this.requestDate.getTime() - this.issueDate.getTime();
		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration)-14;
		
		return (int) Math.max(diffInDays, 0);
		}
		else {
			long duration = new Date().getTime() - this.issueDate.getTime();
			//long duration = this.requestDate.getTime() - this.issueDate.getTime();
			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration)-14;
			
			return (int) Math.max(diffInDays, 0);
		}
		
	}
}
