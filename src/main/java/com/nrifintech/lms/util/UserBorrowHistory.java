package com.nrifintech.lms.util;



import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserBorrowHistory {
	int issueId;

	String bookTitle;
	Date issueDate;
	Date returnDate;

	int fine;
	String status;
	
	public UserBorrowHistory(int issueId,String bookTitle, Date issueDate, Date returnDate,
			int fine, String status) {
		super();
		this.issueId = issueId;

		this.bookTitle = bookTitle;
		this.issueDate = issueDate;
		this.returnDate = returnDate;

		this.fine = fine;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

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
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
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

