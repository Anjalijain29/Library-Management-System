package com.nrifintech.lms.request;

import com.nrifintech.lms.entity.Book;

public class AddBook {	
	public static Book makeBook(BookRequest req) {
		String title = req.getTitle();
		String author = req.getAuthor();
		String year = req.getPrintYear();
		String category = req.getCategory();
		Book book = new Book(title, category, year, author);
		return book;
	}
}
