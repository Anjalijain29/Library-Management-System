package com.nrifintech.lms.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.entity.IssueHistory;
import com.nrifintech.lms.entity.IssueRequest;
import com.nrifintech.lms.request.BookRequest;
import com.nrifintech.lms.request.ReportParams;
import com.nrifintech.lms.service.BookService;
import com.nrifintech.lms.service.IssueHistoryService;
import com.nrifintech.lms.service.IssueRequestService;
import com.nrifintech.lms.service.MemberService;
import com.nrifintech.lms.service.UserService;
import com.nrifintech.lms.util.AdminIssueHistory;
import com.nrifintech.lms.util.AdminIssueRequests;
import com.nrifintech.lms.util.AdminReturnRequests;
import com.nrifintech.lms.util.ExcelGenerateReport;
import com.nrifintech.lms.util.PdfGenerateReport;

@Controller
public class AdminDBController {
	

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
	
	public AdminDBController(BookService bookService, MemberService memberService,
			IssueRequestService issueRequestService, UserService userService, IssueHistoryService issueHistoryService) {
		super();
		this.bookService = bookService;
		this.memberService = memberService;
		this.issueRequestService = issueRequestService;
		this.userService = userService;
		this.issueHistoryService = issueHistoryService;
	}
	@GetMapping("/viewIssueRequests")
	public String viewIssueRequests(Model model) {
		return "redirect:/adminDB/1?sortField=request_id&sortDirection=desc&pane=issueRequests";
	}
	@GetMapping("/viewReturnRequests")
	public String viewReturnRequests(Model model) {
		return "redirect:/adminDB/1?sortField=request_id&sortDirection=desc&pane=returnRequests";
	}
	@GetMapping("/viewIssueHistory")
	public String viewIssueHistory(Model model) {
		
		return "redirect:/adminDB/1?sortField=issue_id&sortDirection=desc&pane=issueHistory";
	}
	@GetMapping("/generateReport")
	public String generateReport(Model model) {
		model.addAttribute("generateReport", "generateReport");
		model.addAttribute("initial", "initial");
		return "redirect:/adminDB/1?pane=generateReport";
	}
	
	
	@RequestMapping(value="/adminDB/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminDB(@PathVariable("pageNo") int pageNo,@ModelAttribute("dataTimeSpan") @Valid ReportParams dataTS,BindingResult result,Model model, @RequestParam(required = false, name = "type", defaultValue = "") String type, @RequestParam(required = false, name = "startDate", defaultValue = "") String strtDate, @RequestParam(required = false, name = "endDate", defaultValue = "") String ndDate , @RequestParam(name="issueRequestAccepted", defaultValue = "", required = false) String issueRequestAccepted, 	@RequestParam(name="returnRequestAccepted", defaultValue = "", required = false) String returnRequestAccepted, @RequestParam(name="sortField", defaultValue = "book_id", required = false) String sortField, @RequestParam(name="sortDirection", defaultValue = "", required = false) String sortDirection, HttpServletRequest request) {
		int pageSize = 5;
		BookRequest req = new BookRequest();
		model.addAttribute("req", req);
		model.addAttribute("accept", new AdminIssueRequests());
		model.addAttribute("reject", new AdminIssueRequests());
		model.addAttribute("acceptRT", new IssueHistory());
		model.addAttribute("rejectRT", new IssueHistory());
		model.addAttribute("return", new IssueRequest());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("revSortDirection", sortDirection.equalsIgnoreCase("asc")?"desc":"asc");		
		model.addAttribute("dataTimeSpan", new ReportParams());
		model.addAttribute("dataAcceptanceRate", new ReportParams());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortOrder", sortDirection);
		if(issueRequestAccepted.equals("true")) {
			List<IssueRequest> issueRequests = issueRequestService.findAllI();
			model.addAttribute("issueRequests", issueRequests);
			model.addAttribute("acceptedIssueRequest", "acceptedIssueRequest");
			model.addAttribute("viewIssueRequests", "viewIssueRequests");
			model.addAttribute("initial", "initial");
		}
		
		else if(issueRequestAccepted.equals("false")) {
			
			List<IssueRequest> issueRequests = issueRequestService.findAllI();
			model.addAttribute("issueRequests", issueRequests);
			model.addAttribute("rejectedIssueRequest", "rejectedIssueRequest");
			model.addAttribute("viewIssueRequests", "viewIssueRequests");
			model.addAttribute("initial", "initial");
		}
		
		if(returnRequestAccepted.equals("true")) {
			List<IssueRequest> issueRequests = issueRequestService.findAllI();
			model.addAttribute("issueRequests", issueRequests);
			model.addAttribute("acceptedReturnRequest", "acceptedReturnRequest");
			model.addAttribute("viewReturnRequests", "viewReturnRequests");
			model.addAttribute("initial", "initial");
		}

		else if(returnRequestAccepted.equals("false")) {
			model.addAttribute("rejectedReturnRequest", "rejectedReturnRequest");
			model.addAttribute("viewReturnRequests", "viewReturnRequests");
			model.addAttribute("initial", "initial");
		}

		if(request.getParameter("pane").equals("issueRequests")) {
			Page<Object[]> issueRequestPage = issueRequestService.fetchAdminIssueRequests(pageNo, pageSize,sortField, sortDirection);
			List<Object[]> issueRequestsadm = issueRequestPage.getContent();
			List<AdminIssueRequests> issueRequests = new ArrayList<AdminIssueRequests>();
			for (Object[] row: issueRequestsadm) {
				String[] strRow = new String[6];
				strRow[0] = (row[0].toString());
				strRow[1] = (row[1]).toString();
				strRow[2] = (row[2]).toString();
				strRow[3] = (row[3]).toString();
				strRow[4] = (row[4]).toString();
				strRow[5] = row[5].toString();				
				try {
					
					 int Memfine =issueHistoryService.findPFineByMember(Integer.parseInt(strRow[1]));

					Date requestdate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[5]);
					issueRequests.add(new AdminIssueRequests(Integer.parseInt(strRow[0]), Integer.parseInt(strRow[1]), strRow[2], Integer.parseInt(strRow[3]), strRow[4], requestdate, Memfine));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				
			}			
			
			int n= (int) Math.ceil((double)pageNo/5);
			int beg = (n-1)*5+1;
			int end = Math.min((n-1)*5+5,issueRequestPage.getTotalPages());
			model.addAttribute("beg", beg);
			model.addAttribute("end", end);
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("lastPage", issueRequestPage.getTotalPages());
			model.addAttribute("issueRequests", issueRequests);
			model.addAttribute("viewIssueRequests", "viewIssueRequests");
			model.addAttribute("initial", "initial");
		}
		
		else if(request.getParameter("pane").equals("returnRequests")) {
				Page<Object[]> returnRequestPage = issueHistoryService.fetchAdminRequestRequests(pageNo, pageSize,sortField, sortDirection);
				List<Object[]> returnRequestsadm = returnRequestPage.getContent();
				List<AdminReturnRequests> returnRequests = new ArrayList<AdminReturnRequests>();
				
				for (Object[] row: returnRequestsadm) {
					String[] strRow = new String[11];
					strRow[0] = (row[0].toString());
					strRow[1] = (row[1]).toString();
					strRow[2] = (row[2]).toString();
					strRow[3] = (row[3]).toString();
					strRow[4] = (row[4]).toString();
					strRow[5] = row[5].toString();
					strRow[6] = row[6].toString();
					strRow[7] = row[7].toString();
					strRow[8] = row[8].toString();
					strRow[9] = row[9].toString();
					strRow[10] = row[10].toString();
					try {
						Date issuedate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[2]);
						Date returndate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[3]);
						Date requestdate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[5]);
						returnRequests.add(new AdminReturnRequests(Integer.parseInt(strRow[0]),Integer.parseInt(strRow[1]), Integer.parseInt(strRow[6]), strRow[7],Integer.parseInt(strRow[8]),strRow[9],requestdate,issuedate,returndate,Integer.parseInt(strRow[4]),strRow[10]));
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
				}
				
				int n= (int) Math.ceil((double)pageNo/5);
				int beg = (n-1)*5+1;
				int end = Math.min((n-1)*5+5,returnRequestPage.getTotalPages());
				model.addAttribute("beg", beg);
				model.addAttribute("end", end);
				
				
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("lastPage", returnRequestPage.getTotalPages());
				model.addAttribute("returnRequests", returnRequests);
				model.addAttribute("viewReturnRequests", "viewReturnRequests");
				model.addAttribute("initial", "initial");
			}

		else if(request.getParameter("pane").equals("issueHistory")) {
			Page<Object[]> issueHistoryPage = issueHistoryService.fetchAdminIssueHistory(pageNo, pageSize, sortField, sortDirection);
			List<Object[]> issueHistoryAdm = issueHistoryPage.getContent();
			List<AdminIssueHistory> issueHistory = new ArrayList<AdminIssueHistory>();
			
			for (Object[] row: issueHistoryAdm) {
				String[] strRow = new String[11];
				strRow[0] = (row[0].toString());
				strRow[1] = (row[1]).toString();
				strRow[2] = (row[2]).toString();
				strRow[3] = (row[3]).toString();
				strRow[4] = (row[4]).toString();
				strRow[5] = row[5].toString();
				strRow[6] = row[6].toString();
				strRow[7] = row[7].toString();
				strRow[8] = row[8].toString();
				strRow[9] = row[9].toString();
				strRow[10] = row[10].toString();				
			try {
					Date issuedate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[2]);
					Date returndate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[3]);
					Date requestdate = new SimpleDateFormat("yyyy-MM-dd").parse(strRow[5]);
					AdminIssueHistory cAdIsHs = new AdminIssueHistory(Integer.parseInt(strRow[0]),Integer.parseInt(strRow[1]), Integer.parseInt(strRow[6]), strRow[7],Integer.parseInt(strRow[8]),strRow[9],requestdate,issuedate,returndate,Integer.parseInt(strRow[4]),strRow[10]);
					int fne=cAdIsHs.calculateFine();
					cAdIsHs.setFine(fne);
					IssueHistory issHst = issueHistoryService.findById(Integer.parseInt(strRow[0]));
					issHst.setFine(fne);
					issueHistoryService.save(issHst);
					issueHistory.add(cAdIsHs);
					
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
				
			}
			
			//model.addAttribute("eloop", n);
			int n= (int) Math.ceil((double)pageNo/5);
			int beg = (n-1)*5+1;
			int end = Math.min((n-1)*5+5,issueHistoryPage.getTotalPages());
			model.addAttribute("beg", beg);
			model.addAttribute("end", end);
			//System.out.println("LOOOOOOOOP"+n);
			
			
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("lastPage", issueHistoryPage.getTotalPages());
			
			model.addAttribute("viewIssueHistory", "viewIssueHistory");
			model.addAttribute("initial", "initial");
			model.addAttribute("issueHistory", issueHistory);
			
		}

		else if(request.getParameter("pane").equals("generateReport")) {
			model.addAttribute("generateReport", "generateReport");
			model.addAttribute("initial", "initial");
		}

		else if(request.getParameter("pane").equals("Report")) 
		{
				model.addAttribute("generateReport", "generateReport");
				model.addAttribute("initial", "initial");
				Date startDate;
				Date endDate;
				
				System.out.println("inside generate repo part 1");
				System.out.println(strtDate);
				System.out.println(ndDate);
				System.out.println(type);
		String reportType = (!type.equals("")) ? type : dataTS.getType();
		try {
			if(!strtDate.equals("")) 
			{

				System.out.println("inside generate repo part 2");
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(strtDate);
				endDate = new SimpleDateFormat("yyyy-MM-dd").parse(ndDate);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				Date currDate =  new SimpleDateFormat("yyyy-MM-dd").parse(formatter.format(date)); 
				
				System.out.println("inside generate repo 4");
				
				if(startDate.compareTo(endDate) >0 || endDate.compareTo(currDate)>0 || startDate.compareTo(currDate)>0) {
					

					System.out.println("error in date");
					
					model.addAttribute("dateError","dateError");
					return "adminDB";
				}
				
			}
			else {

				System.out.println("inside generate repo part 5");
				
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataTS.getStartDate());
				endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataTS.getEndDate());
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				Date currDate =  new SimpleDateFormat("yyyy-MM-dd").parse(formatter.format(date)); 
				
				System.out.println("inside generate repo 6");
				
				if(startDate.compareTo(endDate) >0 || endDate.compareTo(currDate)>0 || startDate.compareTo(currDate)>0) {
					

					System.out.println("error in date");
					
					model.addAttribute("dateError","dateError");
					return "adminDB";
				}
			}
				

			System.out.println("inside generate repo part 5");
				
				List<String[]> data = new ArrayList<String[]>();
				String[] headers = new String[4];
				if(reportType.equals("2")) {
					Page<Object[]> __rows = memberService.getCountMemberIssueHistoryParts(startDate, endDate, pageSize, pageNo);
					model.addAttribute("lastPage", __rows.getTotalPages());
					
					
					int n= (int) Math.ceil((double)pageNo/5);
					int beg = (n-1)*5+1;
					int end = Math.min((n-1)*5+5,__rows.getTotalPages());
					model.addAttribute("beg", beg);
					model.addAttribute("end", end);
					
					List<Object[]> rows = __rows.getContent();
					String colNames[] = { "Member ID", "First Name", "Last Name", "Acceptance Rate%" };
					headers=colNames;
					model.addAttribute("colNames",colNames);
					
					for (Object[] row: rows) {
						String[] strRow = new String[colNames.length];
						strRow[0] = ((Integer) row[0]).toString();
						strRow[1] = ((String) row[1]).toString();
						strRow[2] = ((String) row[2]).toString();
						
						Integer tokenId = (Integer) row[0];
						Long a = memberService.getIHCountStatus(startDate, endDate, tokenId, "A");
						Long c = memberService.getIHCountStatus(startDate, endDate, tokenId, "C");
						Long r = memberService.getIHCountStatus(startDate, endDate, tokenId, "R");
						if (a + c + r == 0) {
							strRow[3] = "NA";
						} else {
							Long d = a + c + r;
							Double rate = (double) (100 * (a + c)) / d;
							strRow[3] = String.format("%.2f", rate);
						}
						
						data.add(strRow);
					}
					
					
					
				}
				else if(reportType.equals("1")) {
					Page<Object[]> __rows = issueHistoryService.getPopularData(startDate, endDate, pageSize, pageNo);
					model.addAttribute("lastPage", __rows.getTotalPages());
					
					
					int n= (int) Math.ceil((double)pageNo/5);
					int beg = (n-1)*5+1;
					int end = Math.min((n-1)*5+5,__rows.getTotalPages());
					model.addAttribute("beg", beg);
					model.addAttribute("end", end);
					
					List<Object[]> rows = __rows.getContent();
					String[] colNames = { "Book ID", "Title", "Author", "Count" };
					headers=colNames;
					model.addAttribute("colNames",colNames);	
					for (Object[] row: rows) {
						String[] strRow = new String[colNames.length];
						strRow[0] = ((Integer) row[0]).toString();
						strRow[1] = ((String) row[1]).toString();
						strRow[2] = ((String) row[2]).toString();
						strRow[3] = ((Long) row[3]).toString();
						data.add(strRow);
					}
				}
				else if (reportType.equals("4")) {
					Page<Object[]> __rows = bookService.getBooks(pageNo, pageSize);
					model.addAttribute("lastPage", __rows.getTotalPages());
					int n = (int) Math.ceil((double) pageNo / 5);
					int beg = (n - 1) * 5 + 1;
					int end = Math.min((n-1)*5+5,__rows.getTotalPages());
					model.addAttribute("beg", beg);
					model.addAttribute("end", end);
					
					List<Object[]> rows = __rows.getContent();
					String[] colNames = { "Book ID", "Title", "Author", "Net Quantity" };
					headers=colNames;
					model.addAttribute("colNames",colNames);
					for (Object[] row : rows) {
						String strRow[] = new String[colNames.length];
						strRow[0] = ((Integer) row[0]).toString();
						strRow[1] = ((String) row[1]).toString();
						strRow[2] = ((String) row[2]).toString();
						Integer netQty = ((Integer) row[3]) + issueHistoryService.getBookActiveIssues((Integer) row[0]).intValue();
						strRow[3] = netQty.toString();
						data.add(strRow);
					}
				}
				
				else {
					
					Page<Object[]> __rows = memberService.getCountMemberIssueHistoryParts(startDate, endDate, pageSize, pageNo);
					model.addAttribute("lastPage", __rows.getTotalPages());
					
					
					int n= (int) Math.ceil((double)pageNo/5);
					int beg = (n-1)*5+1;
					int end = Math.min((n-1)*5+5,__rows.getTotalPages());
					model.addAttribute("beg", beg);
					model.addAttribute("end", end);
					
					List<Object[]> rows = __rows.getContent();
					String colNames[] = { "Member ID", "First Name", "Last Name", "Returned In Time%" };
					headers=colNames;
					model.addAttribute("colNames",colNames);
					
					for (Object[] row: rows) {
						String[] strRow = new String[colNames.length];
						strRow[0] = ((Integer) row[0]).toString();
						strRow[1] = ((String) row[1]).toString();
						strRow[2] = ((String) row[2]).toString();
						
						Integer tokenId = (Integer) row[0];
						Object values[] = memberService.getIHReturn(startDate, endDate, tokenId).get(0);
						Long f = (Long) values[0];
						
						Long t = (Long) values[1];
						if (t == 0) {
							strRow[3] = "NA";
						} else {
							Double rate = (double) (100 * f) / t;
							strRow[3] = String.format("%.2f", rate);
						}
						data.add(strRow);
					}
				}
				
				try {
					if (data.size() > 0)
						ExcelGenerateReport.acceptIt(headers,data);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					if (data.size() > 0)
					PdfGenerateReport.pdfGen(headers,data);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				model.addAttribute("popBooks", data);
				model.addAttribute("sDate",dataTS.getStartDate());
				model.addAttribute("eDate",dataTS.getEndDate());
				model.addAttribute("rType",reportType);
		
			}
		catch (ParseException e)
		{
			System.out.println("error in date");
			
			model.addAttribute("dateError","dateError");
			return "adminDB";
		}


}
		return "adminDB";
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
		Path path = Paths.get("src\\main\\resources\\static\\documents\\" + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("multipart/form-data"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PostMapping("/acceptRequest")
	public String acceptRequest( @ModelAttribute("accept") AdminIssueRequests issueRequest, Model model) {
		
		IssueRequest actualRequest = issueRequestService.findById(issueRequest.getRequestId());
		IssueHistory issueHistory = new IssueHistory(actualRequest.getMember(), actualRequest.getBook(), "A", issueRequest.getRequestId() );     
		issueHistoryService.save(issueHistory);
		issueHistory.setRequestDate(actualRequest.getRequestDate());
		issueRequestService.delete(actualRequest);
		return "redirect:adminDB/1?issueRequestAccepted=true&pane=issueRequests&sortField=request_id&sortDirection=desc";
	}
	@PostMapping("/rejectRequest")
	public String rejectRequest( @ModelAttribute("reject") AdminIssueRequests issueRequest, Model model) {
		IssueRequest actualRequest = issueRequestService.findById(issueRequest.getRequestId());
		Book book = actualRequest.getBook();
		if(book.getCount()==0)book.setStatus("E");
		book.setCount(book.getCount()+1);
		bookService.save(book);
		issueRequestService.delete(actualRequest);
		IssueHistory issueHistory = new IssueHistory(actualRequest.getMember(), actualRequest.getBook(), "R", issueRequest.getRequestId());     //R Issue Rejected
		issueHistory.setRequestDate(actualRequest.getRequestDate());
		issueHistoryService.save(issueHistory);
		return "redirect:/adminDB/1?issueRequestAccepted=false&pane=issueRequests&sortField=request_id&sortDirection=desc";
	}
	
	
	@PostMapping("/acceptReturn")
	public String acceptReturn( @ModelAttribute("acceptRT") IssueHistory returnRequest, Model model) {
		IssueRequest actualRequest = issueRequestService.findById(returnRequest.getRequestId());
		IssueHistory issueHistory = issueHistoryService.findById(returnRequest.getIssueId());
		issueHistory.setStatus("C");
		issueHistory.setRequestId(actualRequest.getRequestId());
		issueHistory.setRequestDate(actualRequest.getRequestDate());
		issueHistory.setReturnDate(issueHistory.getRequestDate());
		issueHistory.setFine(issueHistory.calculateFine());
		issueHistoryService.save(issueHistory);		 //saving to issue history page
		issueRequestService.delete(actualRequest);  //deleting from request page
		Book bookToUpdate = bookService.findById(returnRequest.getBook().getBookId());
		if(bookToUpdate.getCount()==0)
			bookToUpdate.setStatus("E");
		bookToUpdate.setCount(bookToUpdate.getCount()+1);
		bookService.save(bookToUpdate);
		return "redirect:/adminDB/1?returnRequestAccepted=true&pane=returnRequests&sortField=request_id&sortDirection=desc";
	}
	
	@PostMapping("/rejectReturn")
	public String rejectReturn( @ModelAttribute("rejectRT") IssueHistory returnRequest, Model model) {
		IssueRequest actualRequest = issueRequestService.findById(returnRequest.getRequestId());
		issueRequestService.delete(actualRequest);      //
		IssueHistory issueHistory = issueHistoryService.findById(returnRequest.getIssueId());
		issueHistory.setRequestId(actualRequest.getRequestId());
		issueHistory.setStatus("X");
		issueHistoryService.save(issueHistory);
		issueHistory.setRequestDate(actualRequest.getRequestDate());
		
	
		return "redirect:/adminDB/1?returnRequestAccepted=false&pane=returnRequests&sortField=request_id&sortDirection=asc";
	}
	
}
