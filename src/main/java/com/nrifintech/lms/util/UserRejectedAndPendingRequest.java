package com.nrifintech.lms.util;

import java.util.Date;

public class UserRejectedAndPendingRequest {
	int requestId;
	Date issueDate;
	Date returnDate;
	int issueId;
	String bookTitle;
	Date requestDate;
	String status;
	int fine;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	

	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	
	
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public UserRejectedAndPendingRequest(int requestId, String bookTitle, Date requestDate, String status) {
		super();
		this.requestId = requestId;
		this.bookTitle = bookTitle;
		this.requestDate = requestDate;
		this.status = status;
	}
	public UserRejectedAndPendingRequest(int requestId, Date issueDate, Date returnDate, int issueId, String bookTitle,
			Date requestDate, String status, int fine) {
		super();
		this.requestId = requestId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.issueId = issueId;
		this.bookTitle = bookTitle;
		this.requestDate = requestDate;
		this.status = status;
		this.fine=fine;
	}	
}
