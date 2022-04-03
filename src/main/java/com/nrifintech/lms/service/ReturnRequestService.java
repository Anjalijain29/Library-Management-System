package com.nrifintech.lms.service;

import java.util.List;

import com.nrifintech.lms.entity.ReturnRequest;

public interface ReturnRequestService {
	
	List<ReturnRequest> findAll();

	void save(ReturnRequest issue);

	List<ReturnRequest> findByMemberId(int userId);

	void delete(ReturnRequest issueRequest);
	public ReturnRequest findById(int id);

}
