package com.nrifintech.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrifintech.lms.entity.Book;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query(value = "select * from book where title=?1 ", nativeQuery = true)
	List<Book> findByTitle(String title);
	
	List<Book> findByTitleContaining(String title);
	
	public Page<Book>  searchByTitleLikeIgnoreCase(String title,Pageable perPage);
	
	public Page<Book>  searchByAuthorLikeIgnoreCase(String author,Pageable perPage);
	
	public Page<Book>  searchByCategoryLikeIgnoreCase(String category,Pageable perPage);
	
	public Page<Book>  searchByPrintYearIgnoreCase( String printYear,Pageable perPage);
	
	@Query(value = "select b.bookId, b.title, b.author, b.count from Book b order by b.bookId")
	public Page<Object[]> getBooks(Pageable pageable);
}
