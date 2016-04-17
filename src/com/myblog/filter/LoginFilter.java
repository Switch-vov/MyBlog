package com.myblog.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myblog.domain.User;

public class LoginFilter extends HttpServlet implements Filter {

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		// get userName and password from Cookies
		String userName = null;
		String password = null;
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("userName".equals(cookie.getName())) {
					userName = cookie.getValue();
				}
				if ("password".equals(cookie.getName())) {
					password = cookie.getValue();
				}
			}
		}
		// test information of userName and password
		// System.out.println("用户名：" + userName + " 密码：" + password);
		
		// get loginUserInfo from Session
		HttpSession session = httpServletRequest.getSession();
		User loginUserInfo = (User) session.getAttribute("loginUserInfo");
		// resource and login page(when not login) didn't filter
		String path = httpServletRequest.getRequestURI() + "?" + httpServletRequest.getQueryString();
		String prefixLogin = httpServletRequest.getContextPath() + "/login.do";
		String prefixResource = httpServletRequest.getContextPath() + "/resource";
		String prefixLogout = httpServletRequest.getContextPath() + "/login.do?type=logout";
		
		// test path and prefix
		// System.out.println(path);
		// System.out.println("---------"+ prefixLogin);
		
		// nologin only goto login
		if((path.startsWith(prefixLogin) || path.startsWith(prefixResource)) 
				&& password == null){
			chain.doFilter(request, response);
		} else {			
			// login judge use 
			if(loginUserInfo != null && userName != null && password != null 
					&& userName.equals(loginUserInfo.getUserName()) 
					&& password.equals(loginUserInfo.getPassword())
					&& (!path.startsWith(prefixLogin) || path.startsWith(prefixLogout))) {
				chain.doFilter(request, response);
			} else {
				httpServletRequest.getRequestDispatcher("/login.do?type=gotoMainUI").forward(request, response);;
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
