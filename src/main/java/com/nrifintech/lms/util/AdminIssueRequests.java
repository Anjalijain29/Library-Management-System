package com.nrifintech.lms.util;

import java.util.Date;

public class AdminIssueRequests {
	int requestId;
	int memberId;
	String name;
	int bookId;
	String title;
	Date requestDate;
	int fine;

	
	
	public AdminIssueRequests(int requestId, int memberId, String name, int bookId, String title, Date requestDate,int fine) {
		super();
		this.requestId = requestId;
		this.memberId = memberId;
		this.name = name;
		this.bookId = bookId;
		this.title = title;
		this.requestDate = requestDate;
		this.fine = fine;
	}
	
	
	public AdminIssueRequests() {
		// TODO Auto-generated constructor stub
	}


	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


	public int getFine() {
		return fine;
	}


	public void setFine(int fine) {
		this.fine = fine;
	}
	
	
	
	
	
	
	
}
