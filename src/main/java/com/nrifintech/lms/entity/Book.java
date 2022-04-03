package com.nrifintech.lms.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="book")
@org.hibernate.annotations.DynamicInsert(value=true)
@org.hibernate.annotations.DynamicUpdate(value=true)
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int bookId;

	@Column(name="title")
	private String title;
	@Column(name="category")
	private String category;
	
	@Column(name="print_year")
	private String printYear;
	
	@Column(name="author")
	
	private String author;
	
	@Column(name="status")
	private String status;
	
	@Column(name="count")
	@NotNull
	private int count;
	
	@ManyToOne
	@JoinColumn(name="pub_id", referencedColumnName="pub_id")
	private Publisher publisher;
	
	@OneToMany(mappedBy="book")
	private List<IssueRequest> issueRequest;
	
	@OneToMany(mappedBy="book")
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrintYear() {
		return printYear;
	}

	public void setPrintYear(String printYear) {
		this.printYear = printYear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Book(String title,
			String category,
			String printYear, String author) {
		super();
		this.title = title;
		this.category = category;
		this.printYear = printYear;
		this.author = author;
	}
	public Book() {}
}
