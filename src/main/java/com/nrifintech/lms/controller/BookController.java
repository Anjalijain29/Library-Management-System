package com.nrifintech.lms.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.entity.IssueHistory;
import com.nrifintech.lms.entity.IssueRequest;
import com.nrifintech.lms.entity.Member;
import com.nrifintech.lms.entity.Publisher;
import com.nrifintech.lms.entity.User;
import com.nrifintech.lms.repository.BookRepository;
import com.nrifintech.lms.repository.PublisherRepository;
import com.nrifintech.lms.request.AddBook;
import com.nrifintech.lms.request.BookRequest;
import com.nrifintech.lms.request.RegisterRequest;
import com.nrifintech.lms.request.ReportParams;
import com.nrifintech.lms.service.BookService;
import com.nrifintech.lms.service.IssueHistoryService;
import com.nrifintech.lms.service.IssueRequestService;
import com.nrifintech.lms.service.PublisherService;
import com.nrifintech.lms.service.UserService;
import com.nrifintech.lms.service.impl.BookServiceImpl;
import com.nrifintech.lms.util.AdminIssueHistory;
import com.nrifintech.lms.util.AdminIssueRequests;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	@Autowired
	PublisherService publisherService;
	@Autowired
	UserService userService;
	@Autowired
	IssueRequestService issueRequestService;
	@Autowired
	IssueHistoryService issueHistoryService;
	
	
	
	
	public BookController(BookService bookService, PublisherService publisherService, UserService userService,
			IssueRequestService issueRequestService, IssueHistoryService issueHistoryService) {
		super();
		this.bookService = bookService;
		this.publisherService = publisherService;
		this.userService = userService;
		this.issueRequestService = issueRequestService;
		this.issueHistoryService = issueHistoryService;
	}
	@PostMapping("/addBook")
	public ModelAndView addBook(@ModelAttribute("req") @Valid BookRequest req2, BindingResult result, Model model ) {
		ModelAndView modelAndView = new ModelAndView();
		
		
		
		
		model.addAttribute("accept", new AdminIssueRequests());
		model.addAttribute("reject", new AdminIssueRequests());
		model.addAttribute("acceptRT", new IssueHistory());
		model.addAttribute("rejectRT", new IssueHistory());
		model.addAttribute("return", new IssueRequest());
		model.addAttribute("currentPage", 1);
////		model.addAttribute("revSortDirection", sortDirection.equalsIgnoreCase("asc")?"desc":"asc");		
		model.addAttribute("dataTimeSpan", new ReportParams());
		model.addAttribute("dataAcceptanceRate", new ReportParams());
		model.addAttribute("sortField","book_id");
		model.addAttribute("sortOrder", "asc");

	
		Page<Object[]> issueHistoryPage = issueHistoryService.fetchAdminIssueHistory(1, 5, "book_id", "asc");
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
		
		int n= (int) Math.ceil((double)1/5);
		int beg = (n-1)*5+1;
		int end = Math.min((n-1)*5+5,issueHistoryPage.getTotalPages());
		model.addAttribute("beg", beg);
		model.addAttribute("end", end);

		model.addAttribute("lastPage", issueHistoryPage.getTotalPages());

		model.addAttribute("issueHistory", issueHistory);
		
		
		if(result.hasErrors()) {
			model.addAttribute("error", "error");
		
			model.addAttribute("initial", "initial");
			model.addAttribute("viewIssueHistory", "viewIssueHistory");
			modelAndView.setViewName("adminDB");
			return modelAndView;		
		}
		
		
		System.out.println("outside try for add book");
		try 
		{
			System.out.println("inside try for add book");
		String printYear = req2.getPrintYear();
	
	
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        String strDate = dateFormat.format( new Date());  
       
		int d1 = Integer.parseInt(printYear);
		int d2=Integer.parseInt(strDate.substring(0,4));
		System.out.println("date 1 "+d1);
		System.out.println("date 2 "+d2);
		if(d1>d2) {
			System.out.println("error2 occured");
			model.addAttribute("error", "error");
			model.addAttribute("year", "year");
			model.addAttribute("initial", "initial");
			model.addAttribute("viewIssueHistory", "viewIssueHistory");
			modelAndView.setViewName("adminDB");
			return modelAndView;
		}
		
		}

		catch(Exception e){
			System.out.println("inside catch for add book");
		}
		
		
		
		int count = Integer.parseInt(req2.getCount());
		System.out.println("add book 1");
		Book book = AddBook.makeBook(req2);
		Publisher publisher = publisherService.findById(req2.getPublisher().getPubId());
		System.out.println("add book 2");
		book.setPublisher(publisher);
		book.setCount(count);
		List<Book> currBook = bookService.findByTitle(book.getTitle());
		if(Objects.nonNull(currBook)) {
			for (Book current : currBook) {
				if(Objects.nonNull(current))
				{
					if(current.getTitle().equals(book.getTitle()) && current.getAuthor().equals(book.getAuthor()) && current.getCategory().equals(book.getCategory()) && current.getPublisher().equals(book.getPublisher())) {
			
					model.addAttribute("exists", currBook);
					model.addAttribute("initial", "initial");
					model.addAttribute("viewIssueHistory", "viewIssueHistory");
					modelAndView.setViewName("adminDB");
					return modelAndView;
					}
				}
			}
			System.out.println("add book 3");
			bookService.save(book);
			}
		else 
		{ 	System.out.println("add book 4");
				bookService.save(book);
		}
		
		
		BookRequest req = new BookRequest();
		model.addAttribute("req", req);
		
		
				modelAndView.setViewName("adminDB");
				model.addAttribute("saved", "saved");
				model.addAttribute("initial", "initial");
				model.addAttribute("viewIssueHistory", "viewIssueHistory");
				model.addAttribute("book", new Book());
				return modelAndView;
	}
	@PostMapping("/updateBookCount")
	public String updateCount(@Valid @ModelAttribute("updateBook") Book updateBook,BindingResult result, Model model ) {
		
		if(result.hasErrors()) {
			
			return "redirect:/catalogue/0?countUpdated=failure";
		}

		
		Book bookToUpdate = bookService.findById(updateBook.getBookId());
		int prevCount = bookToUpdate.getCount();
		int updatedCount = updateBook.getCount();
		
	
		
		if(updatedCount<1 || updatedCount>999) {
			return "redirect:/catalogue/0?countUpdated=failure";
		}
		bookToUpdate.setCount(updateBook.getCount());
		if(bookToUpdate.getStatus().equals("D") && prevCount==0 && updatedCount>0) {
			bookToUpdate.setStatus("E");
		}
		
		bookService.save(bookToUpdate);
		return "redirect:/catalogue/0?countUpdated=success";
	}
	@RequestMapping("/enableDisable")
	public String enableDisable(@ModelAttribute("enableDisable") Book enableDisable, HttpServletRequest request ) {
		Book book = bookService.findById(enableDisable.getBookId());
		boolean isIssued = issueHistoryService.isIssuedCurrently(book.getBookId());
		if(isIssued && book.getStatus().equals("E")) {
			return "redirect:/catalogue/0?isIssued=true";
		}
		if(book.getStatus().equals("E") ) {
			book.setStatus("D");
		}
		else if(book.getCount() == 0 && book.getStatus().equals("D")) {
			return "redirect:/catalogue/0?countZero=true";
		}
		else if(book.getStatus().equals("D") && book.getCount() !=0){
			book.setStatus("E");
		}
		bookService.save(book);
		if(book.getStatus().equals("E")) {
			return "redirect:/catalogue/0?enabled=true";}
		else {
			return "redirect:/catalogue/0?disabled=true";
		}
	}
	
	
	@RequestMapping("/borrow")
	public String borrow(@ModelAttribute("borrow") Book borrow, HttpServletRequest request ) {
		Book book = bookService.findById(borrow.getBookId());
		Object attribute = request.getSession().getAttribute("validUser");
		RegisterRequest req = RegisterRequest.class.cast(attribute);
		User user = userService.findByPhone(req.getPhone());
		Member member = user.getMember();
		IssueRequest issueRequestExists = issueRequestService.findByMemberAndBook(user.getUserId(), book.getBookId());
		if(Objects.nonNull(issueRequestExists)) {
			return "redirect:catalogue/0?alreadyRequested=success";
		}
		IssueHistory issueHistoryExists = issueHistoryService.findByMemberAndBookBB(user.getUserId(), book.getBookId());
		if(Objects.nonNull(issueHistoryExists)) {
			return "redirect:catalogue/0?alreadyIssued=success";
		}
		IssueRequest issue = new IssueRequest(member, book, "I");
		issueRequestService.save(issue);
		book.setCount(book.getCount()-1);
		bookService.save(book);
		return "redirect:/catalogue/0?borrowed=success";
	}	
}
