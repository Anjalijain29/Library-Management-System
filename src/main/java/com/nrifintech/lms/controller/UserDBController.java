package com.nrifintech.lms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.entity.IssueHistory;
import com.nrifintech.lms.entity.IssueRequest;
import com.nrifintech.lms.entity.Member;
import com.nrifintech.lms.entity.User;
import com.nrifintech.lms.request.RegisterRequest;
import com.nrifintech.lms.service.BookService;
import com.nrifintech.lms.service.IssueHistoryService;
import com.nrifintech.lms.service.IssueRequestService;
import com.nrifintech.lms.service.MemberService;
import com.nrifintech.lms.service.UserService;
import com.nrifintech.lms.util.UserActiveRequest;
import com.nrifintech.lms.util.UserBorrowHistory;
import com.nrifintech.lms.util.UserRejectedAndPendingRequest;

@Controller
public class UserDBController {

	@Autowired
	BookService bookService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	IssueRequestService issueRequestService;	
	
	@Autowired
	UserService userService;
	
	@Autowired
	IssueHistoryService issueHistoryService;

	
	@RequestMapping("/PendingRequest")
	public String viewIssueRequests(Model model) {

		
		return "redirect:/userDB/1?sortField=request_id&sortDirection=desc&pane=pending";
	}
	@RequestMapping("/ActiveRequest")
	public String viewReturnRequests(Model model) {

//		model.addAttribute("initial", "initial");
		return "redirect:/userDB/1?sortField=issue_id&sortDirection=desc&pane=active";
	}
	
	@RequestMapping("/IssueHistory")
	public String issueHistory(Model model) {

//		model.addAttribute("initial", "initial");
		return "redirect:/userDB/1?sortField=issue_id&sortDirection=desc&pane=history";
	}
	
	@RequestMapping("/RejectedRequest")
	public String viewRjectedRequests(Model model) {

		model.addAttribute("rejected", "rejected");
		return "redirect:/userDB/1?sortField=request_id&sortDirection=desc&pane=rejected";
	}
	
	@RequestMapping("/ViewProfile")
	public String viewProfile(Model model) {

		model.addAttribute("MyProfile", "MyProfile");
		return "redirect:/userDB/1?pane=ViewProfile";
	}
	
	@RequestMapping("/userDB/{pageNo}")
	public String userDB(@PathVariable("pageNo") int pageNo,HttpServletRequest request, Model model, @RequestParam(name="requestedReturn", defaultValue = "", required = false) String requestedReturn, @RequestParam(name = "sortField", defaultValue = "request_id") String sortField, @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
		int pageSize = 5;
		
		
		
		try {
			if(request.getParameter("form").equals("pw")) {
				model.addAttribute("password", "password");
			}
			else if(request.getParameter("form").equals("notreg")) {
				model.addAttribute("notRegistered", "notRegistered");
			}
		} catch (Exception e) {
			
		}
		Object attribute = request.getSession().getAttribute("validUser");
		RegisterRequest req = RegisterRequest.class.cast(attribute);
		User user = userService.findByPhone(req.getPhone());
	
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("revSortDirection", sortDirection.equalsIgnoreCase("asc")?"desc":"asc");
		
		model.addAttribute("return", new IssueHistory());
		RegisterRequest newReq = new RegisterRequest();
		newReq.setFirstName(user.getFirstName());
		newReq.setLastName(user.getLastName());
		newReq.setPhone(user.getPhone());
		model.addAttribute("req", newReq);
		model.addAttribute("newReq", new RegisterRequest());
		model.addAttribute("user", user);
		model.addAttribute("userFine", issueHistoryService.findFineByMember(user.getUserId()));
		model.addAttribute("userPFine", issueHistoryService.findPFineByMember(user.getUserId()));
		model.addAttribute("userBBooks", issueHistoryService.findBBooksByMember(user.getUserId()));
		model.addAttribute("userCBooks", issueHistoryService.findCBooksByMember(user.getUserId()));
		model.addAttribute("userPBooks", issueRequestService.findPBooksByMember(user.getUserId()));
		model.addAttribute("userRBooks", issueHistoryService.findRBooksByMember(user.getUserId()));
		
		try {
			if(request.getParameter("pane").equals("active")) {
				model.addAttribute("initial", "initial");
//				Page<IssueHistory> pageMyBooks = issueHistoryService.findPaginatedMyBooks(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
//				List<IssueHistory> issueHistory = pageMyBooks.getContent();
				
				Page<Object[]> pageMyBooks = issueHistoryService.fetchUserActiveRequest(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
				List<Object[]> issueHistoryUser = pageMyBooks.getContent();
				List<UserActiveRequest> issueHistory = new ArrayList<UserActiveRequest>();
				
				for (Object[] row: issueHistoryUser ) {
					String[] strRow = new String[10];
					strRow[0] = (row[0].toString());
					strRow[1] = (row[1]).toString();
					strRow[2] = (row[2]).toString();
					strRow[3] = (row[3]).toString();
					strRow[4] = (row[4]).toString();
					strRow[5] = (row[5]).toString();
					strRow[6] = (row[6]).toString();
					strRow[7] = (row[7]).toString();
					strRow[8] = (row[8]).toString();
					strRow[9] = (row[9]).toString();
			
					
					try {
						Date issuedate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[1]);
						Date duedate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[2]);
						Date returndate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[8]);
						UserActiveRequest uih =new UserActiveRequest(strRow[0],issuedate, duedate,Integer.parseInt(strRow[3]),
								Integer.parseInt(strRow[4]),Integer.parseInt(strRow[5]),Integer.parseInt(strRow[6]),strRow[7],returndate,Integer.parseInt(strRow[9]));
						
	
						IssueHistory ih = issueHistoryService.findById(Integer.parseInt(strRow[4]));
						ih.setFine(uih.calculateFine());
						issueHistoryService.save(ih);
						uih.setFine(uih.calculateFine());
						issueHistory.add(uih);
					
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				int n= (int) Math.ceil((double)pageNo/5);
				int beg = (n-1)*5+1;
				int end = Math.min((n-1)*5+5,pageMyBooks.getTotalPages());
				model.addAttribute("beg", beg);
				model.addAttribute("end", end);
				
				model.addAttribute("issueHistory", issueHistory);
				model.addAttribute("totalPagesMyBooks", pageMyBooks.getTotalPages());
				model.addAttribute("totalItemsMyBooks", pageMyBooks.getTotalElements());
			}
			else if(request.getParameter("pane").equals("rejected")) {
				model.addAttribute("rejected", "rejected");
//				Page<IssueHistory> pageIssueRejected = issueHistoryService.findPaginatedRejected(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
//				List<IssueHistory> rejectRequestss = pageIssueRejected.getContent();
				
				Page<Object[]> pageIssueRejected = issueHistoryService.fetchUserRejectedRequest(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
				List<Object[]> rejectRequests = pageIssueRejected.getContent();
				List<UserRejectedAndPendingRequest> rejectRequestss = new ArrayList<UserRejectedAndPendingRequest>();
				
				for (Object[] row: rejectRequests) {
					String[] strRow = new String[4];
					strRow[0] = (row[0].toString());
					strRow[1] = (row[1]).toString();
					strRow[2] = (row[2]).toString();
					strRow[3] = (row[3]).toString();
					try {
						Date requestdate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[1]);
						
				
						rejectRequestss.add(new UserRejectedAndPendingRequest(Integer.parseInt(strRow[0]),strRow[3], requestdate,strRow[2]));
						
					} catch (ParseException e) {						
						e.printStackTrace();
					}
				}
				int n= (int) Math.ceil((double)pageNo/5);
				int beg = (n-1)*5+1;
				int end = Math.min((n-1)*5+5,pageIssueRejected.getTotalPages());
				model.addAttribute("beg", beg);
				model.addAttribute("end", end);
				
				model.addAttribute("rejectRequestss", rejectRequestss);
				model.addAttribute("totalPagesRejected", pageIssueRejected.getTotalPages());
				model.addAttribute("totalItemsRejected", pageIssueRejected.getTotalElements());
			}
			else if(request.getParameter("pane").equals("ViewProfile")) {
				model.addAttribute("ViewProfile", "ViewProfile");
			}
			else if(request.getParameter("pane").equals("history")) {
				model.addAttribute("MyHistory", "MyHistory");
//				Page<IssueHistory> pageIssueHistory = issueHistoryService.findPaginated(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
//				List<IssueHistory> myHistory = pageIssueHistory.getContent();
				
				Page<Object[]> borrowHistoryPage = issueHistoryService.fetchUserBorrowHistory(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
				List<Object[]> myHistoryUser = borrowHistoryPage.getContent();
				List<UserBorrowHistory> myHistory = new ArrayList<UserBorrowHistory>();
				
				for (Object[] row: myHistoryUser) {
					String[] strRow = new String[6];
					strRow[0] = (row[0].toString());
					strRow[1] = (row[1]).toString();
					strRow[2] = (row[2]).toString();
					strRow[3] = (row[3]).toString();
					strRow[4] = (row[4]).toString();
					strRow[5] = (row[5]).toString();
			
					
					try {
						Date issuedate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[1]);
						Date returndate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[2]);
						myHistory.add(new UserBorrowHistory(Integer.parseInt(strRow[0]),strRow[5], issuedate, returndate,Integer.parseInt(strRow[3]),strRow[4]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				int n= (int) Math.ceil((double)pageNo/5);
				int beg = (n-1)*5+1;
				int end = Math.min((n-1)*5+5,borrowHistoryPage.getTotalPages());
				model.addAttribute("beg", beg);
				model.addAttribute("end", end);
				
				model.addAttribute("myHistory", myHistory);
				model.addAttribute("totalPagesIssueHistory", borrowHistoryPage.getTotalPages());
				model.addAttribute("totalItemsIssueHistory", borrowHistoryPage.getTotalElements());
			}
			else {
				Page<Object[]> pageIssueRequest = issueRequestService.fetchUserPendingRequest(pageNo, pageSize, user.getUserId(), sortField, sortDirection);
				List<Object[]> pageIssueRequestUser = pageIssueRequest.getContent();
				List<UserRejectedAndPendingRequest>issueRequests = new ArrayList<UserRejectedAndPendingRequest>();
							
				for (Object[] row: pageIssueRequestUser) {
					String[] strRow = new String[8];
					strRow[0] = (row[0].toString());
					strRow[1] = (row[1]).toString();
					strRow[2] = (row[2]).toString();
					strRow[3] = (row[3]).toString();
					strRow[4] = (row[4]).toString();
					strRow[5] = (row[5]).toString();
					strRow[6] = (row[6]).toString();
					strRow[7] = (row[7]).toString();
					try {
						Date requestdate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[4]);
						Date issuedate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[0]);
						Date returndate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[1]);
				
						issueRequests.add(new UserRejectedAndPendingRequest(Integer.parseInt(strRow[3]),issuedate,returndate,Integer.parseInt(strRow[2]),strRow[6],requestdate,strRow[5],Integer.parseInt(strRow[7])));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				
				int n= (int) Math.ceil((double)pageNo/5);
				int beg = (n-1)*5+1;
				int end = Math.min((n-1)*5+5,pageIssueRequest.getTotalPages());
				model.addAttribute("beg", beg);
				model.addAttribute("end", end);
				
				model.addAttribute("issueRequests", issueRequests);
				model.addAttribute("totalPagesPending", pageIssueRequest.getTotalPages());
				model.addAttribute("totalItemsPending", pageIssueRequest.getTotalElements());
			}
			
		} catch (Exception e) {
			model.addAttribute("ViewProfile", "ViewProfile");
		}
		
		if(requestedReturn.equals("true")) {
			model.addAttribute("requestedToReturn", "requestedToReturn");
			model.addAttribute("initial", "initial");
		}
		return "userDB";
	}

	@PostMapping("/requestReturn")
	public String returnRequest( HttpServletRequest request, @ModelAttribute("return") IssueHistory returnRequest, Model model) {
		Book book = bookService.findById(returnRequest.getBook().getBookId());
		Object attribute = request.getSession().getAttribute("validUser");
		RegisterRequest req = RegisterRequest.class.cast(attribute);
		User user = userService.findByPhone(req.getPhone());
		Member member = user.getMember();
		IssueRequest rtrn = new IssueRequest(member, book, "R");
		IssueHistory issueHistory = issueHistoryService.findById(returnRequest.getIssueId());
		issueRequestService.save(rtrn);
		
		issueHistory.setRequestId(rtrn.getRequestId());
		
		try {
		 Date date = new Date();
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	     String str = formatter.format(date);
	     Date date1=formatter.parse(str);
	     issueHistory.setRequestDate(date1);
		}
		catch(Exception e)
		{
			
		}
		
		issueHistory.setFine(issueHistory.calculateFine());
		issueHistoryService.save(issueHistory);
		return "redirect:/userDB/1?requestedReturn=true&pane=active&sortField=issue_id&sortDirection=desc";
	}
	
}
