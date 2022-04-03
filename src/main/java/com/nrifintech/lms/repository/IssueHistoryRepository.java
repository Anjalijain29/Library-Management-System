package com.nrifintech.lms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrifintech.lms.entity.IssueHistory;


@Repository
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Integer> {
	
	@Query(value = "select * from issue_history where issue_history.member_id=?1 and status='A' ", nativeQuery = true)
	List<IssueHistory> findByMemberId(int id);
	
	
	@Query(value = "select * from issue_history where member_id=?1 and status in ('A','X') and book_id not in (select book_id from issue_request where member_id=?1)", nativeQuery = true)
	List<IssueHistory> findByMemberIdA(int memberId);
	
	@Query(value = "select * from issue_history where member_id=?1 and status in ('A','X') and book_id not in (select book_id from issue_request where member_id=?1)", nativeQuery = true)
	Page<IssueHistory> findByMemberIdA(Pageable pageable,int memberId);
	
	
	@Query(value="select H.issue_id, H.request_date, H.member_id, H.book_id, H.issue_date, H.return_date, H.status, H.fine, H.request_id from issue_history H join issue_request R on H.book_id=R.book_id and H.member_id=R.member_id where H.status in ('A', 'X') and R.type='R'", nativeQuery=true)
	List<IssueHistory> findAllR();
	@Query(value="select H.issue_id, H.request_date, H.member_id, H.book_id, H.issue_date, H.return_date, H.status, H.fine, H.request_id from issue_history H join issue_request R on H.book_id=R.book_id and H.member_id=R.member_id where H.status in ('A', 'X') and R.type='R'", nativeQuery=true)
	Page<IssueHistory> findAllR(Pageable pageable);
	
	@Query(value = "select * from issue_history where issue_history.member_id=?1 and issue_history.book_id=?2 and issue_history.status='A'", nativeQuery = true)
	public IssueHistory findByMemberAndBook(int memberId, int bookId);
	
	@Query(value = "select count(*) from issue_history where book_id = ?1 and status='A'", nativeQuery = true)
	public int isIssuedCurrently(int bookId);
	
	
	@Query(value="select * from issue_history where member_id=?1 and status in ('R','X')", nativeQuery=true)
	List<IssueHistory> findByMemberIdRX(int member_id);
	@Query(value="select * from issue_history where member_id=?1 and status in ('R','X')", nativeQuery=true)
	Page<IssueHistory> findByMemberIdRX(Pageable pageable, int memberId);
	
	@Query(value="select count(*) from issue_history where member_id=?1 and status in ('A','X') and book_id not in (select book_id from issue_request where member_id=?1 and type='R') ", nativeQuery=true)
	int findCBooksByMember(int member_id);
	
	@Query(value="select count(*) from issue_history where member_id=?1 and status in ('A','C') ", nativeQuery=true)
	int findBBooksByMember(int member_id);
	
	@Query(value="select count(*) from issue_history where member_id=?1 and status='R' ", nativeQuery=true)
	int findRBooksByMember(int memberId);
	
	@Query(value="select sum(Fine) from issue_history where member_id=?1 and status='C' ", nativeQuery=true)
	int findFineByMember(int member_id);
	
	@Query(value="select sum(Fine) from issue_history where member_id=?1 and status='A' ", nativeQuery=true)
	int findPFineByMember(int member_id);

	@Query(value="select * from issue_history where member_id=?1 and status='C' ", nativeQuery=true)
	List<IssueHistory> findByMemberIdC(int memberId);
	
	@Query(value="select * from issue_history where member_id=?1 and status='C' ", nativeQuery=true)
	Page<IssueHistory> findByMemberIdC(Pageable pageable,int memberId);
	
	@Query(value = "select B.bookId, B.title, B.author, count(I.issueId) as reqs from IssueHistory I right join I.book B where (I.requestDate between ?1 and ?2) and (I.status != 'X') group by B.bookId, B.title order by count(I.issueId) desc")
	List<Object[]> getPopularData(Date startDate, Date endDate);

	@Query(value = "select * from issue_history where issue_history.member_id=?1 and issue_history.book_id=?2 and issue_history.status in ('A','X')", nativeQuery = true)
	public IssueHistory findByMemberAndBookBB(int memberId, int bookId);

	
	@Query(value="select H.issue_id,H.request_id,H.issue_date, H.return_date,H.fine,H.request_date, U.user_id, U.first_name||' '||U.last_name as name, B.book_id, B.title as title, H.status as status from issue_history H, book B, users U,issue_request R where H.request_id=R.request_id and H.book_id=B.book_id and H.member_id=U.user_id and H.status in ('A', 'X') and R.type='R'", nativeQuery = true)
	Page<Object[]> fetchAdminReturnRequests(Pageable pageable);

	@Query(value="select H.issue_id,H.request_id,H.issue_date, H.return_date,H.fine,H.request_date, H.member_id, U.first_name||' '||U.last_name as name,H.book_id, B.title as title, H.status as status from issue_history H, book B, users U where H.book_id=B.book_id and H.member_id=U.user_id ", nativeQuery = true)
	Page<Object[]> fetchAdminIssueHistory(Pageable pageable);

	
	@Query(value="select  H.issue_id,H.issue_date, H.return_date,H.fine,H.status as status, B.title as bookTitle from issue_history H, book B where member_id=?1 and H.status='C' and H.book_id=B.book_id", nativeQuery=true)
	Page<Object[]> fetchUserBorrowHistory(Pageable pageable, int memberId);


	@Query(value="select  H.request_id,H.request_date,H.status as status, B.title as bookTitle from issue_history H, book B where member_id=?1 and H.book_id=B.book_id and H.status in ('R','X')", nativeQuery=true)
	Page<Object[]> fetchUserRejectedRequest(Pageable pageable, int memberId);

	@Query(value = "select B.title as bookTitle,H.issue_date, H.request_date, H.fine,H.issue_id,H.member_id,H.book_id, H.status,H.return_date , H.request_id from issue_history H , book B where member_id=?1 and H.status in ('A','X') and H.book_id not in (select book_id from issue_request where member_id=?1) and H.book_id = b.book_id", nativeQuery = true)
	Page<Object[]> fetchUserActiveRequest(Pageable pageable, int memberId);

	@Query(value = "select B.bookId, B.title, B.author, count(I.issueId) as reqs from IssueHistory I right join I.book B where (I.requestDate between ?1 and ?2) and (I.status != 'X') group by B.bookId, B.title order by count(I.issueId) desc")
	Page<Object[]> getPopularData(Date startDate, Date endDate, Pageable pageable);
	
	@Query(value = "select count(ih.issueId) from IssueHistory ih where ih.book.bookId = ?1 and ih.status = 'A'")
	Long getBookActiveIssues(Integer bookId);

	



//	select H.issue_id, H.request_date, H.member_id, H.book_id, H.issue_date, H.return_date, H.status, H.fine, H.request_id from issue_history H join issue_request R on H.book_id=R.book_id and H.member_id=R.member_id where H.status in ('A', 'X') and R.type='R'"


	
}
