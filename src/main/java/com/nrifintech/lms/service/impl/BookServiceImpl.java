package com.nrifintech.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.repository.BookRepository;
import com.nrifintech.lms.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);	
	}

	@Override
	public Book findById(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		return optionalBook.get();
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	@Override
	public Page<Book> getSearchSortedPage(PageRequest pageable, int pageSize, int pageNo,String searchCriteria, String searchField, String sortField, String sortOrder){
		Page<Book> bookPageable;
		if(sortOrder.equalsIgnoreCase("asc")) {
			if(searchCriteria.equalsIgnoreCase("title")) {
				searchField = "%" + searchField+ "%";
				System.out.println("Inside");
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
				bookPageable = bookRepository.searchByTitleLikeIgnoreCase(searchField, pageable);
				System.out.println(bookPageable.toString());
			}
			else if(searchCriteria.equalsIgnoreCase("author")) {
				searchField = "%" + searchField+ "%";
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
				bookPageable = bookRepository.searchByAuthorLikeIgnoreCase(searchField, pageable);
			}
			else if(searchCriteria.equalsIgnoreCase("category")) {
				searchField = "%" + searchField+ "%";
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
				bookPageable = bookRepository.searchByCategoryLikeIgnoreCase(searchField, pageable);
			}
			else{
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
				bookPageable = bookRepository.searchByPrintYearIgnoreCase(searchField, pageable);
			}

		}
		else {
			if(searchCriteria.equalsIgnoreCase("title")) {
				searchField = "%" + searchField+ "%";
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
				bookPageable = bookRepository.searchByTitleLikeIgnoreCase(searchField, pageable);
			}
			else if(searchCriteria.equalsIgnoreCase("author")) {
				searchField = "%" + searchField+ "%";
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
				bookPageable = bookRepository.searchByAuthorLikeIgnoreCase(searchField, pageable);
			}
			else if(searchCriteria.equalsIgnoreCase("category")) {
				searchField = "%" + searchField+ "%";
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
				bookPageable = bookRepository.searchByCategoryLikeIgnoreCase(searchField, pageable);
			}
			else{
				pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
				bookPageable = bookRepository.searchByPrintYearIgnoreCase(searchField, pageable);
			}
		}
		return bookPageable;
	}

	@Override
	public int count() {
		long count = bookRepository.count();
		return (int) count;
	}

	@Override
	public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.bookRepository.findAll(pageable);
	}
	
	@Override
	public Page<Object[]> getBooks(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return bookRepository.getBooks(pageable);
	}
	
}
