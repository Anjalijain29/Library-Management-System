package com.nrifintech.lms.interceptor;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

@Component
public class Interceptor implements AsyncHandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String resourcePath = new UrlPathHelper().getPathWithinApplication(request);
		HttpSession session = request.getSession();
		if(!isUserAuthenticated(request) && !canPassAuthentication(resourcePath)) {
			response.sendRedirect(request.getContextPath() + "/notAuthorizedUser");
			return false;
		}
		if(resourcePath.equalsIgnoreCase("/Profile") && session.getAttribute("validUser").equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/unAuthorized");
			return false;
		}
		String [] adminOnly = {"/addbook", "/updatebookcount", "/enabledisable", "/viewissuerequests", "/viewreturnrequests", "/viewissuehistory", "/admindb"};
		if(isUserAuthenticated(request)) {
			System.out.println("User Logged IN");
		}
		if(!isUserAuthenticated(request) && canPassAuthentication(resourcePath)) {
			System.out.println("not loggedin but can view");
		}
		Object validUser = session.getAttribute("validUser");
		if(isUserAuthenticated(request)) {
			if(validUser.equals("admin") == false) {
				if(Arrays.asList(adminOnly).contains(resourcePath.toLowerCase()) || resourcePath.contains("adminDB")) {
					response.sendRedirect(request.getContextPath() + "/unAuthorized");
					return false;
				}
			}
		}
		return true;
	}
	private boolean isUserAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return Objects.nonNull(session.getAttribute("validUser"));
	}

	private boolean canPassAuthentication(String resourcePath) {
		
		return resourcePath.equalsIgnoreCase("/")
				|| resourcePath.equalsIgnoreCase("/lms/")
				|| resourcePath.equalsIgnoreCase("/login")
				|| resourcePath.equalsIgnoreCase("/register")
				|| resourcePath.equalsIgnoreCase("/logout")
				|| resourcePath.equalsIgnoreCase("/profile")
				|| resourcePath.equalsIgnoreCase("/addUser")
				|| resourcePath.equalsIgnoreCase("/userLogin")
				|| resourcePath.equalsIgnoreCase("/catalogue")
				|| resourcePath.equalsIgnoreCase("/catalogue/**")
				|| resourcePath.equalsIgnoreCase("/notAuthorizedUser")
				|| Pattern.matches("^.*\\.(?!css|js|font|png|jpeg)[a-z0-9]+$", resourcePath);
	}
}
