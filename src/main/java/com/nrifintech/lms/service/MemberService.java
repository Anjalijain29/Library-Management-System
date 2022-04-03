package com.nrifintech.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.nrifintech.lms.entity.Member;

public interface MemberService {
	void save(Member member);
	
	List<Object[]> getCountMemberIssueHistoryParts(Date startDate, Date endDate);
	Long getIHCountStatus(Date startDate, Date endDate, Integer memberId, String status);
	Page<Object[]> getCountMemberIssueHistoryParts(Date startDate, Date endDate, int pageSize, int pageNo);
	List<Object[]> getIHReturn(Date startDate, Date endDate, Integer memberId);
}
