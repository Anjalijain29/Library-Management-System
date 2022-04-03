package com.nrifintech.lms.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AdminReturnRequests {
	int issueId;
	int requestId;
	int memberId;
	String name;
	int bookId;
	String title;

	Date requestDate;
	Date issueDate;
	Date returnDate;
	int Fine;
	String status;
	
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
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
		return Fine;
	}
	public void setFine(int fine) {
		Fine = fine;
	}
	public AdminReturnRequests() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AdminReturnRequests(int issueId,int requestId, int memberId, String name, int bookId, String title, Date requestDate,
			Date issueDate, Date returnDate, int fine,String status) {
		super();
		this.issueId = issueId;
		this.requestId = requestId;
		this.memberId = memberId;
		this.name = name;
		this.bookId = bookId;
		this.title = title;
		this.requestDate = requestDate;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		Fine = fine;
		this.status=status;
	}
//	
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
