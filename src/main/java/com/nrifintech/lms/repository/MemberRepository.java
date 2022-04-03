package com.nrifintech.lms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrifintech.lms.entity.Member;
import com.nrifintech.lms.entity.User;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	
	@Query(value = "select u.userId, u.firstName, u.lastName, ( select count(ih1.issueId) from IssueHistory ih1 where ih1.member.user.userId = u.userId and ih1.status = 'A' and (ih1.requestDate between ?1 and ?2) ) as A, ( select count(ih2.issueId) from IssueHistory ih2 where ih2.member.user.userId = u.userId and ih2.status = 'C' and (ih2.requestDate between ?1 AND ?2) ) as C, ( select count(ih3.issueId) from IssueHistory ih3 where ih3.member.user.userId = u.userId and ih3.status = 'R' and (ih3.requestDate between ?1 and ?2) ) as R from Member m inner join m.user u")

	List<Object[]> getCountMemberIssueHistoryParts(Date startDate, Date endDate);
	
	@Query(value = "select u.userId, u.firstName, u.lastName from Member m inner join m.user u order by u.userId")
	Page<Object[]> getCountMemberIssueHistoryParts(Date startDate, Date endDate, Pageable pageable);
	
	@Query(value = "select count(ih1.issueId) from IssueHistory ih1 where ih1.requestDate between ?1 and ?2 and ih1.member.user.userId = ?3 and ih1.status = ?4")
	Long getIHCountStatus(Date startDate, Date endDate, Integer memberId, String status);

	@Query(value = "select ( select count(ih1.issueId) from IssueHistory ih1 where (ih1.member.user.userId = ?3) and (ih1.issueDate between ?1 and ?2) and (((ih1.status = 'A') and (current_date() <= ih1.returnDate)) or (ih1.status = 'C' and ih1.fine = 0)) ) as F, ( select count(ih2.issueId) from IssueHistory ih2 where (ih2.member.user.userId = ?3) and (ih2.issueDate between ?1 and ?2) and ((ih2.status = 'A') or (ih2.status = 'C')) ) as T from Member m inner join m.user u where u.userId = ?3")
	List<Object[]> getIHReturn(Date startDate, Date endDate, Integer memberId);
}
