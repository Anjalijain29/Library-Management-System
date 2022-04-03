package com.nrifintech.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.IssueHistory;

public interface IssueHistoryService {
	void save(IssueHistory issueHistory);

	List<IssueHistory> findAll();
	Page<IssueHistory> findAll(int pageNo, int pageSize, String sortField, String sortDirection);
	List<IssueHistory> findAllR();

	List<IssueHistory> findByMemberId(int userId);
	
	List<IssueHistory> findByMemberIdA(int memberId);
	
	IssueHistory findById(int issueId);
	IssueHistory findByMemberAndBook(int memberId, int bookId);

	boolean isIssuedCurrently(int bookId);

	List<IssueHistory> findByMemberIdRX(int userId);

	int findFineByMember(int userId);

	int findBBooksByMember(int userId);

	int findCBooksByMember(int userId);
	
	int findPFineByMember(int userId);
	
	int findRBooksByMember(int userId);
	
	
	List<IssueHistory> findByMemberIdC(int userId);
	Page<IssueHistory> findPaginated(int pageNo, int pageSize, int memberId, String sortField, String sortDirection);
	
	List<Object[]> getPopularData(Date startDate, Date endDate);

	Page<IssueHistory> findPaginatedMyBooks(int pageNo, int pageSize, int userId, String sortField,
			String sortDirection);

	Page<IssueHistory> findPaginatedRejected(int pageNo, int pageSize, int userId, String sortField,
			String sortDirection);

	IssueHistory findByMemberAndBookBB(int userId, int bookId);

	Page<IssueHistory> findAllR(int pageNo, int pageSize,String sortField,
			String sortDirection);

	Page<Object[]> fetchAdminRequestRequests(int pageNo, int pageSize, String sortField, String sortDirection);

	Page<Object[]> fetchAdminIssueHistory(int pageNo, int pageSize, String sortField, String sortDirection);

	Page<Object[]> fetchUserBorrowHistory(int pageNo, int pageSize, int userId, String sortField, String sortDirection);

	Page<Object[]> fetchUserRejectedRequest(int pageNo, int pageSize, int userId, String sortField,
			String sortDirection);

	Page<Object[]> fetchUserActiveRequest(int pageNo, int pageSize, int userId, String sortField, String sortDirection);

	Page<Object[]> getPopularData(Date startDate, Date endDate, int pageSize, int pageNo);

	Long getBookActiveIssues(Integer bookId);

	

	


	
}
