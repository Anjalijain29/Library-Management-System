package com.nrifintech.lms.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.entity.IssueHistory;
import com.nrifintech.lms.entity.IssueRequest;
import com.nrifintech.lms.entity.Member;
import com.nrifintech.lms.entity.ReturnRequest;
import com.nrifintech.lms.entity.User;
import com.nrifintech.lms.repository.IssueRequestRepository;
import com.nrifintech.lms.request.BookRequest;
import com.nrifintech.lms.request.RegisterRequest;
import com.nrifintech.lms.request.ReportParams;
import com.nrifintech.lms.service.BookService;
import com.nrifintech.lms.service.IssueHistoryService;
import com.nrifintech.lms.service.IssueRequestService;
import com.nrifintech.lms.service.MemberService;
import com.nrifintech.lms.service.UserService;
import com.nrifintech.lms.util.ExcelGenerateReport;
import com.nrifintech.lms.util.PdfGenerateReport;
import com.nrifintech.lms.util.UserActiveRequest;
import com.nrifintech.lms.util.UserBorrowHistory;
import com.nrifintech.lms.util.UserRejectedAndPendingRequest;
import com.nrifintech.lms.util.AdminIssueHistory;
import com.nrifintech.lms.util.AdminIssueRequests;
import com.nrifintech.lms.util.AdminReturnRequests;

@Controller
@RequestMapping("/")
public class MainController {

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


	public MainController(BookService bookService, IssueRequestService issueRequestService, UserService userService,
			IssueHistoryService issueHistoryService) {
		super();
		this.bookService = bookService;
		this.issueRequestService = issueRequestService;
		this.userService = userService;
		this.issueHistoryService = issueHistoryService;
	}
	@RequestMapping("")
	public String home(HttpServletRequest request, Model model) {
		
		model.addAttribute("session", request.getSession().getAttribute("validUser"));
		
		return "index";
	}
	@RequestMapping("/notAuthorizedUser")
	public String errorPage() {
		return "errorU";
	}
	@RequestMapping("/unAuthorized")
	public String unAuthorized() {
		return "notAuthorized";
	}
	
	@RequestMapping(value = "catalogue", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView catalog(HttpServletRequest request, @RequestParam(required = false, name = "searchCriteria", defaultValue = "title")String searchCriteria, @RequestParam(required = false, name="searchField", defaultValue = "") String searchField, @RequestParam(required = false, name="sortField", defaultValue = "title") String sortField, @RequestParam(required = false, name="sortOrder", defaultValue = "asc") String sortOrder) {
		ModelAndView modelAndView = new ModelAndView("catalogue");
		List<Book> books = bookService.findAll();
		modelAndView.addObject("books", books);
		Book updateBook = new Book();
		modelAndView.addObject("updateBook", updateBook);
		modelAndView.addObject("enableDisable", new Book());
		modelAndView.addObject("borrow", new Book());
		modelAndView.addObject("session", request.getSession().getAttribute("validUser"));
		int lastPageNo = books.size()%5;
		if(books.size()%5 != 0)
			lastPageNo++;
		modelAndView.addObject("lastPageNo", lastPageNo);
		
		return modelAndView;
	}
	@GetMapping("/catalogue/{pageNo}")
	public ModelAndView findPaginated(@PathVariable("pageNo") int pageNo, @RequestParam(required = false, name = "searchCriteria", defaultValue = "title")String searchCriteria, @RequestParam(required = false, name="searchField", defaultValue = "") String searchField, @RequestParam(required = false, name="sortField", defaultValue = "title") String sortField, @RequestParam(required = false, name="sortOrder", defaultValue = "asc") String sortOrder, @RequestParam(name = "enabled", required = false, defaultValue = "") String enabled, @RequestParam(name = "disabled", required = false, defaultValue = "") String disabled, @RequestParam(name = "countUpdated", required = false, defaultValue = "") String countUpdated, @RequestParam(name = "borrowed", required = false, defaultValue = "") String borrowed, @RequestParam(name = "alreadyRequested", required = false, defaultValue = "") String alreadyRequested, @RequestParam(name = "alreadyIssued", defaultValue = "", required = false) String alreadyIssued, @RequestParam(name = "isIssued", defaultValue = "", required = false) String isIssued, @RequestParam(name = "countZero", defaultValue = "", required = false) String countZero, HttpServletRequest request) {
		
		int pageSize = 5;
		PageRequest pageable = null;
		ModelAndView modelAndView = new ModelAndView("catalogue");
		Page<Book> booksPage = bookService.getSearchSortedPage(pageable, pageSize, pageNo, searchCriteria, searchField, sortField, sortOrder);
		List<Book> books =booksPage.getContent();
		
		for (Book book : books) {
			if(book.getCount()==0) {
				book.setStatus("D");
				bookService.save(book);
			}
			
		}
		modelAndView.addObject("books", books);
		Book updateBook = new Book();
		modelAndView.addObject("updateBook", updateBook);
		modelAndView.addObject("enableDisable", new Book());
		modelAndView.addObject("borrow", new Book());
		modelAndView.addObject("session", request.getSession().getAttribute("validUser"));
		int lastPageNo = booksPage.getTotalPages();
		modelAndView.addObject("lastPageNo", lastPageNo);
		modelAndView.addObject("pageNumber", pageNo);
		modelAndView.addObject("searchField", searchField);
		modelAndView.addObject("searchCriteria", searchCriteria);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		
		
		int n= (int) Math.ceil((double)(pageNo+1)/5);
		int beg = (n-1)*5+1;
		int end = Math.min((n-1)*5+5,lastPageNo);
		modelAndView.addObject("beg", beg);
		modelAndView.addObject("end", end);
		
		if(countZero.equals("true")) {
			modelAndView.addObject("countZero", "countZero");
		}
		if(enabled.equals("") == false) {
			modelAndView.addObject("bookToggled", "enabled");
		}
		else if(disabled.equals("") == false) {
			modelAndView.addObject("bookToggled", "disabled");
		}
		if(countUpdated.equals("success")) {
			modelAndView.addObject("countUpdated", "success");
		}
		else if(countUpdated.equals("failure")) {
			modelAndView.addObject("invalidCount", "failure");
		}
		if(alreadyRequested.equals("success")) {
			modelAndView.addObject("alreadyRequested", "alreadyRequested");
		}
		if(alreadyIssued.equals("success")) {
			modelAndView.addObject("alreadyIssued", "alreadyIssued");
		}
		if(borrowed.equals("success")) {
			modelAndView.addObject("borrowSuccess", "success");
		}
		if(isIssued.equals("true")) {
			modelAndView.addObject("isIssued", "isIssued");
		}
	
		return modelAndView;
	}
	

	
	
	
	

	
	

	

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
