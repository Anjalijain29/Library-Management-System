package com.nrifintech.lms.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.IssueHistory;
import com.nrifintech.lms.repository.IssueHistoryRepository;
import com.nrifintech.lms.service.IssueHistoryService;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {

	@Autowired
	IssueHistoryRepository issueHistoryRepository;
	
	@Override
	public void save(IssueHistory issueHistory) {
		issueHistoryRepository.save(issueHistory);
	}

	@Override
	public List<IssueHistory> findAll() {
		return issueHistoryRepository.findAll();
		
	}

	@Override
	public List<IssueHistory> findByMemberId(int userId) {
		return issueHistoryRepository.findByMemberId(userId);
		
	}

	@Override
	public List<IssueHistory> findByMemberIdA(int memberId) {
		return issueHistoryRepository.findByMemberIdA(memberId);
	}

	@Override
	public IssueHistory findById(int issueId) {
		Optional<IssueHistory> ish = issueHistoryRepository.findById(issueId);
		return ish.get();
	}

	@Override
	public List<IssueHistory> findAllR() {
		return issueHistoryRepository.findAllR();
	}

	@Override
	public IssueHistory findByMemberAndBook(int memberId, int bookId) {
		return issueHistoryRepository.findByMemberAndBook(memberId, bookId);
	}

	@Override
	public boolean isIssuedCurrently(int bookId) {
		return issueHistoryRepository.isIssuedCurrently(bookId) != 0;
	}

	@Override
	public List<IssueHistory> findByMemberIdRX(int memberId) {
		// TODO Auto-generated method stub
		return issueHistoryRepository.findByMemberIdRX(memberId);
	}

	@Override
	public int findFineByMember(int memberId) {
		try {
		return issueHistoryRepository.findFineByMember(memberId);
		}
		catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int findBBooksByMember(int memberId) {
		try {
		return issueHistoryRepository.findBBooksByMember(memberId);
		}
		catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int findCBooksByMember(int memberId) {
		try
		{
			return issueHistoryRepository.findCBooksByMember(memberId);
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	@Override
	public int findPFineByMember(int memberId) {
		try {
			return issueHistoryRepository.findPFineByMember(memberId);
			}
			catch(Exception e) {
				return 0;
			}
	}
	
	@Override
	public int findRBooksByMember(int memberId) {
		try {
			return issueHistoryRepository.findRBooksByMember(memberId);
			}
			catch(Exception e) {
				return 0;
			}
	}



	@Override
	public List<IssueHistory> findByMemberIdC(int memberId) {
		return issueHistoryRepository.findByMemberIdC(memberId);
	}

	@Override
	public Page<IssueHistory> findPaginated(int pageNo, int pageSize, int memberId, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.findByMemberIdC(pageable, memberId);
	}
	
	public List<Object[]> getPopularData(Date startDate, Date endDate) {
		return issueHistoryRepository.getPopularData(startDate, endDate);
	}

	@Override
	public Page<IssueHistory> findPaginatedMyBooks(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.findByMemberIdA(pageable, memberId);
	}

	@Override
	public Page<IssueHistory> findPaginatedRejected(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.findByMemberIdRX(pageable, memberId);
	}

	@Override
	public IssueHistory findByMemberAndBookBB(int memberId, int bookId) {
		return issueHistoryRepository.findByMemberAndBookBB(memberId, bookId);
	}

	@Override
	public Page<IssueHistory> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.findAll(pageable);
	}

	@Override
	public Page<IssueHistory> findAllR(int pageNo, int pageSize,  String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
		return this.issueHistoryRepository.findAllR(pageable);
	}

	@Override
	public Page<Object[]> fetchAdminRequestRequests(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
		return this.issueHistoryRepository.fetchAdminReturnRequests(pageable);
	}

	@Override
	public Page<Object[]> fetchAdminIssueHistory(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
		return this.issueHistoryRepository.fetchAdminIssueHistory(pageable);
	}

	@Override
	public Page<Object[]> fetchUserBorrowHistory(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.fetchUserBorrowHistory(pageable, memberId);
	}

	@Override
	public Page<Object[]> fetchUserRejectedRequest(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.fetchUserRejectedRequest(pageable, memberId);
	}

	@Override
	public Page<Object[]> fetchUserActiveRequest(int pageNo, int pageSize, int memberId, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return this.issueHistoryRepository.fetchUserActiveRequest(pageable, memberId);
	}

	@Override
	public Page<Object[]> getPopularData(Date startDate, Date endDate, int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return issueHistoryRepository.getPopularData(startDate, endDate, pageable);
	}
	
	@Override
	public Long getBookActiveIssues(Integer bookId) {
		return issueHistoryRepository.getBookActiveIssues(bookId);
	}
}
