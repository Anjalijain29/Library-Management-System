package com.nrifintech.lms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.Member;
import com.nrifintech.lms.repository.MemberRepository;
import com.nrifintech.lms.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	@Override
	public void save(Member member) {
		memberRepository.save(member);
	}
	@Override
	public List<Object[]> getCountMemberIssueHistoryParts(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return memberRepository.getCountMemberIssueHistoryParts(startDate, endDate);
	}
	@Override
	public Page<Object[]> getCountMemberIssueHistoryParts(Date startDate, Date endDate, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return memberRepository.getCountMemberIssueHistoryParts(startDate, endDate, pageable);
	}
	
	@Override
	public Long getIHCountStatus(Date startDate, Date endDate, Integer memberId, String status) {
		return memberRepository.getIHCountStatus(startDate, endDate, memberId, status);
	}
	
	@Override
	public List<Object[]> getIHReturn(Date startDate, Date endDate, Integer memberId) {
		return memberRepository.getIHReturn(startDate, endDate, memberId);
	}
}
