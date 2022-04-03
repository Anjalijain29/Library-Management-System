package com.nrifintech.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nrifintech.lms.entity.IssueRequest;

public interface IssueRequestService {
	List<IssueRequest> findAll();
	void save(IssueRequest issue);
	List<IssueRequest> findByMemberId(int userId);
	void delete(IssueRequest issueRequest);
	public IssueRequest findById(int id);
	List<IssueRequest> findAllI();
	List<IssueRequest> findByMemberIdA(int userId);

	IssueRequest findByMemberAndBook(int memberId, int bookId);
	
	int findPBooksByMember(int userId);
	Page<IssueRequest> findPaginatedPending(int pageNo, int pageSize, int userId, String sortField,
			String sortDirection);
	Page<IssueRequest> findAllI(int pageNo, int pageSize, String sortField,
			String sortDirection);
	

	Page<Object[]> fetchUserPendingRequest(int pageNo, int pageSize, int userId, String sortField,
			String sortDirection);
	Page<Object[]> fetchAdminIssueRequests(int pageNo, int pageSize, String sortField, String sortDirection);
	
	
	
}
