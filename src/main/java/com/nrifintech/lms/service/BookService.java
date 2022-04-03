package com.nrifintech.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nrifintech.lms.entity.Book;

public interface BookService {
	List<Book> findByTitle(String title);

	void save(Book book);

	Book findById(int bookId);

	List<Book> findAll();

	Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String SortOrder);
	
	int count();
	public Page<Book> getSearchSortedPage(PageRequest pageable, int pageSize, int pageNo,String searchCriteria, String searchField, String sortField, String sortOrder);
	public Page<Object[]> getBooks(int pageNo, int pageSize);
}
