package com.nrifintech.lms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.lms.entity.Book;
import com.nrifintech.lms.entity.IssueHistory;
import com.nrifintech.lms.entity.IssueRequest;
import com.nrifintech.lms.entity.Member;
import com.nrifintech.lms.entity.ReturnRequest;
import com.nrifintech.lms.entity.User;
import com.nrifintech.lms.repository.UserRepository;
import com.nrifintech.lms.request.AddUser;
import com.nrifintech.lms.request.HashPassword;
import com.nrifintech.lms.request.RegisterRequest;
import com.nrifintech.lms.service.BookService;
import com.nrifintech.lms.service.IssueHistoryService;
import com.nrifintech.lms.service.IssueRequestService;
import com.nrifintech.lms.service.MemberService;
import com.nrifintech.lms.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	MemberService memberService;
	@Autowired
	IssueHistoryService issueHistoryService;
	@Autowired
	IssueRequestService issueRequestService;
	
	@Autowired
	BookService bookService;
	

	public UserController(UserService userService, MemberService memberService, IssueHistoryService issueHistoryService,
			IssueRequestService issueRequestService) {
		super();
		this.userService = userService;
		this.memberService = memberService;
		this.issueHistoryService = issueHistoryService;
		this.issueRequestService = issueRequestService;
	}

	@RequestMapping("register")
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("register");
		RegisterRequest req = new RegisterRequest();
		modelAndView.addObject("session", request.getSession().getAttribute("validUser"));
		modelAndView.addObject("req", req);
		return modelAndView;
	}

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("login");
		RegisterRequest req = new RegisterRequest();
		modelAndView.addObject("session", request.getSession().getAttribute("validUser"));
		modelAndView.addObject("req", req);
		return modelAndView;
	}
	@PostMapping("/updateProfile")
	public ModelAndView changePassword(@ModelAttribute("newReq") @Valid RegisterRequest req, BindingResult result,
			HttpServletRequest request) 
	{
		ModelAndView modelAndView = new ModelAndView("userDB");
		Object attribute = request.getSession().getAttribute("validUser");
		RegisterRequest oldRequest = RegisterRequest.class.cast(attribute);
		User user = userService.findByPhone(req.getPhone());
		String password = req.getPassword();
		String confirmPassword = req.getConfirmPassword();

		modelAndView.addObject("userFine", issueHistoryService.findFineByMember(user.getUserId()));
		modelAndView.addObject("userPFine", issueHistoryService.findPFineByMember(user.getUserId()));
		modelAndView.addObject("userBBooks", issueHistoryService.findBBooksByMember(user.getUserId()));
		modelAndView.addObject("userCBooks", issueHistoryService.findCBooksByMember(user.getUserId()));
		modelAndView.addObject("userPBooks", issueRequestService.findPBooksByMember(user.getUserId()));
		modelAndView.addObject("userRBooks", issueHistoryService.findRBooksByMember(user.getUserId()));
		modelAndView.addObject("user", user);
		RegisterRequest req2 = new RegisterRequest();
		req2.setFirstName(user.getFirstName());
		req2.setLastName(user.getLastName());
		req2.setPhone(user.getPhone());
	
		modelAndView.addObject("ViewProfile", "ViewProfile");
		if (result.hasFieldErrors("firstName") || result.hasFieldErrors("lastName") || result.hasFieldErrors("password")) {
			modelAndView.addObject("req", req2);
			modelAndView.addObject("hasErrors", "hasErrors");
			return modelAndView;
		}
		if (password.equals(confirmPassword) == false) {
			modelAndView.addObject("req", req2);
			modelAndView.addObject("password", "password");
			return modelAndView;
		}
		modelAndView.addObject("req", req);
		password = HashPassword.hash(password);
		user.setFirstName(req.getFirstName());
		user.setLastName(req.getLastName());
		user.setPassword(password);
		userService.save(user);
//		modelAndView.addObject("req", req);
		modelAndView.addObject("profileUpdated", "profileUpdated");
		return modelAndView;	
	}

	
	
	
	@PostMapping("/addUser")
	public ModelAndView addUser(@ModelAttribute("req") @Valid RegisterRequest req, BindingResult result, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		String phone = req.getPhone();
		if (result.hasErrors()) {
			modelAndView.setViewName("register");
			return modelAndView;
		}
		User newUser = userService.findByPhone(phone);
		if (Objects.isNull(newUser) == false) {
			modelAndView.setViewName("register");
			modelAndView.addObject("phone", phone);
			return modelAndView;
		}
		String password = req.getPassword();
		String confirmPassword = req.getConfirmPassword();
		if (password.equals(confirmPassword) == false) {
			modelAndView.setViewName("register");
			modelAndView.addObject("password", password);
			return modelAndView;
		}
		req.setPassword(HashPassword.hash(req.getPassword()));
		User user = AddUser.makeUser(req);
		
		Member member = new Member();
		
		User userObj = userService.save(user);
		member.setUser( userObj);
		user.setMember(member);
		memberService.save(member);
		model.addAttribute("registered","registered");
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("/userLogin")
	public String loginUser(@ModelAttribute("req") @Valid RegisterRequest req, BindingResult result,
			HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasFieldErrors("phone") || result.hasFieldErrors("password")) {
			modelAndView.setViewName("login");
			return "login";
		}
		String phone = req.getPhone();
		String password = req.getPassword();
		password = HashPassword.hash(password);
		User currentUser = userService.findByPhone(phone);
		if (Objects.isNull(currentUser)) {
			model.addAttribute("notRegistered", "notRegistered");
			modelAndView.setViewName("login");
			return "login";
		}
		if (currentUser.getPassword().equals(password) == false) {
			modelAndView.addObject("password", password);
			modelAndView.setViewName("login");
			model.addAttribute("password", password);
			return "login";
		}
		if (currentUser.getIsAdmin().equals("N")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("validUser", req);
			return "redirect:/userDB/1";
		} else {
			HttpSession session = request.getSession(true);
			Book book = new Book();
			Book updateBook = new Book();
			session.setAttribute("validUser", "admin");
			session.setAttribute("adminReq", currentUser);
			model.addAttribute("book", book);
			model.addAttribute("return", new ReturnRequest());
			modelAndView.setViewName("adminDB");
			return "redirect:/adminDB/1?sortField=issue_id&sortDirection=asc&pane=issueHistory";
		}
	}
	
}