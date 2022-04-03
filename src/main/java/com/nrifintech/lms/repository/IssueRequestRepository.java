package com.nrifintech.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.entity.IssueRequest;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface IssueRequestRepository extends JpaRepository<IssueRequest, Integer> 

{	
	@Query(value="select * from issue_request where member_id=?1", nativeQuery=true)
	List<IssueRequest> findByMemberId(int id);
	@Query(value="select * from issue_request where member_id=?1", nativeQuery=true)
	Page<IssueRequest> findByMemberId(Pageable pageable, int memberId);
	
	@Query(value="select issue_id as request_id, H.member_id, H.book_id, issue_date as request_date, type from issue_history H join issue_request R on H.member_id=R.member_id and H.book_id=R.book_id where H.member_id=?1 and status='A'", nativeQuery=true)//(value = "select request_id, R.member_id, R.book_id, request_date from issue_request R where R.member_id=?1 intersect select request_id, H.member_id, H.book_id, request_date from issue_history H ,issue_request R where H.member_id = R.member_id and H.book_id = R.book_id and H.member_id=?1", nativeQuery = true)
	List<IssueRequest> findByMemberIdA(int id);

	@Query(value="select * from issue_request where type='I'", nativeQuery=true)
	List<IssueRequest> findAllI();
	
	@Query(value="select * from issue_request where type='I'", nativeQuery=true)
	Page<IssueRequest> findAllI(Pageable pageable);
	
	
	
	
	@Query(value = "select * from issue_request where issue_request.member_id=?1 and issue_request.book_id=?2", nativeQuery = true)
	IssueRequest findByMemberAndBook(int memberId, int bookId);
	
	@Query(value="select count(*) from issue_request where member_id=?1 and type in ('R','I') ", nativeQuery=true)
	int findPBooksByMember(int member_id);
	
	@Query(value="select IR.request_id, U.user_id, U.first_name||' '||U.last_name as name, B.book_id, B.title as title, IR.request_date from issue_request IR, book B, users U where IR.book_id=B.book_id and IR.member_id=U.user_id and IR.type='I'", nativeQuery = true)
	Page<Object[]> fetchAdminIssueRequests(Pageable pageable);
	
	@Query(value="select coalesce(H.issue_date,to_date('19700101','YYYYMMDD')) as issue_date, coalesce(H.return_date,to_date('19700101','YYYYMMDD')) as return_date,coalesce(H.issue_id,0) as issue_id,R.request_id,R.request_date,R.type as status, B.title as bookTitle, coalesce(H.fine,0) as fine from issue_request R left outer join issue_history H on R.request_id=H.request_id join book B on R.book_id=B.book_id where R.member_id=?1 ", nativeQuery=true)
	Page<Object[]> fetchUserPendingRequest(Pageable pageable, int memberId);


	
	
	
}