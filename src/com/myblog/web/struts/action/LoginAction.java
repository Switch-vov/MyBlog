package com.myblog.web.struts.action;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.myblog.domain.User;
import com.myblog.service.inter.UserServiceInter;
import com.myblog.web.struts.form.UserForm;

public class LoginAction extends DispatchAction {
	// 提供用户服务
	@Resource
	private UserServiceInter userService;
	public void setUserServiceInter(UserServiceInter userService) {
		this.userService = userService;
	}



	private void saveUserToSessionAndCookie(HttpServletRequest request,
			HttpServletResponse response, User loginUserInfo) {
		// 创建HttpSession对象
		HttpSession session = request.getSession();
		
		// 保存信息至Cookie中
		Cookie jSessionId = new Cookie("JSESSIONID", session.getId());
		jSessionId.setMaxAge(3600 * 24);
		Cookie userName = new Cookie("userName", loginUserInfo.getUserName());
		userName.setMaxAge(3600 * 24);
		Cookie password = new Cookie("password", loginUserInfo.getPassword());
		password.setMaxAge(3600 * 24);
		response.addCookie(jSessionId);
		response.addCookie(userName);
		response.addCookie(password);
		
		// 将用户数据加入Session中，以备后用
		session.setAttribute("loginUserInfo", loginUserInfo);
	}
	
	private void removeUserFromSessionAndCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		request.getSession().invalidate();
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserForm userForm = (UserForm) form;
		
		// 构建一个User对象
		User loginUserInfo = new User();
		loginUserInfo.setUserName(userForm.getUserName());
		loginUserInfo.setPassword(userForm.getPassword());
		
		// 测试表单数据是否填入表单对象(成功)
		// System.out.println(userForm.getUserName() + " " + userForm.getPassword());
		if((loginUserInfo = userService.checkUser(loginUserInfo)) != null) {
			// 测试domain对象是否被填入数据(成功)
			// System.out.println(loginUserInfo.getUserId() + " " + loginUserInfo.getUserName() + " " + loginUserInfo.getPassword());
			
			saveUserToSessionAndCookie(request, response, loginUserInfo);
			
			return mapping.findForward("gotoUserUI");
		} else {
			return mapping.findForward("loginerr");
		}
	}

	
	public ActionForward gotoLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("gotoLogin");
	}
	
	public ActionForward gotoMainUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("gotoMainUI");
	}
	
	public ActionForward gotoRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("gotoRegister");
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		if (!userForm.getPassword().equals(userForm.getRepassword())) {
			return mapping.findForward("registererr");
		}
		
		// 构建一个User对象
		User user = new User();
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		user.setNickName(userForm.getNickName());
		user.setQuestion(userForm.getQuestion());
		user.setAnswer(userForm.getAnswer());
		
		// 测试表单数据是否填入表单对象(成功)
		// System.out.println(userForm.getUserName() + " " + userForm.getPassword());
		if(userService.register(user)) {
			saveUserToSessionAndCookie(request, response, user);
			
			return mapping.findForward("gotoUserUI");
		} else {
			return mapping.findForward("registererr");
		}
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.removeUserFromSessionAndCookie(request, response);
		return mapping.findForward("gotoMainUI");
	}
}