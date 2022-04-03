package com.nrifintech.lms.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserActiveRequest {
	

	String bookTitle;
	Date issueDate;
	Date dueDate;

	int fine;
	int issueId;
	int memberId;
	int bookId;
	String status;
	Date returnDate;
	int requestId;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}
	
	

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMember_id(int memberId) {
		this.memberId = memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBook_id(int bookId) {
		this.bookId = bookId;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	

	public int getRequestId() {
		return requestId;
	}

	public void setRequest_id(int requestId) {
		this.requestId = requestId;
	}

	public UserActiveRequest(String bookTitle, Date issueDate, Date dueDate, int fine, int issueId, int memberId, int bookId,String status,Date returnDate, int requestId) {
		super();
		this.bookTitle = bookTitle;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.fine = fine;
		this.issueId=issueId;
		this.memberId=memberId;
		this.bookId=bookId;
		this.status=status;
		this.returnDate= returnDate;
		this.requestId=requestId;
	}
	
	
	public int calculateFine() {
		if(this.status.equals("C"))
		{
			long duration = this.returnDate.getTime() - this.issueDate.getTime();
			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration)-14;
			
			return (int) Math.max(diffInDays, 0);
		}
		long duration = new Date().getTime() - this.issueDate.getTime();
		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration)-14;
		
		return (int) Math.max(diffInDays, 0);
	}

	
	
	

}
