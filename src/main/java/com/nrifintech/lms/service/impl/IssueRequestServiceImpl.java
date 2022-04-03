package com.nrifintech.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.IssueRequest;
import com.nrifintech.lms.repository.IssueRequestRepository;
import com.nrifintech.lms.service.IssueRequestService;
@Service
public class IssueRequestServiceImpl implements IssueRequestService {

	@Autowired
	IssueRequestRepository issueRequestRepository;
	@Override
	public List<IssueRequest> findAll() {
		List<IssueRequest> findAll = issueRequestRepository.findAll();
		return findAll;
	}
	@Override
	public void save(IssueRequest issue) {
		issueRequestRepository.save(issue);
	}
	@Override
	public List<IssueRequest> findByMemberId(int userId) {
		return issueRequestRepository.findByMemberId(userId);
	}
	@Override
	public void delete(IssueRequest issueRequest) {
		issueRequestRepository.delete(issueRequest);
	}
	public IssueRequest findById(int id) {
		return issueRequestRepository.findById(id).get();
	}
	@Override
	public List<IssueRequest> findByMemberIdA(int userId) {
		// TODO Auto-generated method stub
		return issueRequestRepository.findByMemberIdA(userId);
	}
	@Override
	public List<IssueRequest> findAllI() {
		// TODO Auto-generated method stub
		return issueRequestRepository.findAllI();
	}
	@Override
	public IssueRequest findByMemberAndBook(int memberId, int bookId) {
		return issueRequestRepository.findByMemberAndBook(memberId, bookId);
	}
	@Override
	public int findPBooksByMember(int memberId) {
		// TODO Auto-generated method stub
		return issueRequestRepository.findPBooksByMember(memberId);
	}
	@Override
	public Page<IssueRequest> findPaginatedPending(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueRequestRepository.findByMemberId(pageable, memberId);
	}
	@Override
	public Page<IssueRequest> findAllI(int pageNo, int pageSize,String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
		return this.issueRequestRepository.findAllI(pageable);
	}
	@Override
	public Page<Object[]> fetchAdminIssueRequests(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
		return this.issueRequestRepository.fetchAdminIssueRequests(pageable);
	}
	@Override
	public Page<Object[]> fetchUserPendingRequest(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueRequestRepository.fetchUserPendingRequest(pageable, memberId);
	}
	

	

}
