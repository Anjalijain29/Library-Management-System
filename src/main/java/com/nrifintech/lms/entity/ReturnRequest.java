package com.nrifintech.lms.entity;

import java.util.Date;


public class ReturnRequest {
	
	
	private int requestId;
	
	private Member member;

	private Book book;

	public ReturnRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReturnRequest(int requestId, Member member, Book book, Date requestDate) {
		super();
		this.requestId = requestId;
		this.member = member;
		this.book = book;
		this.requestDate = requestDate;
	}


	private Date requestDate;
	
	
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




}
