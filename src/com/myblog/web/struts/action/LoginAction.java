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
import static com.myblog.tools.RegularVerify.verifyOnlyLetterAndNumber;

public class LoginAction extends DispatchAction {
	// 提供用户服务
	@Resource
	private UserServiceInter userService;
	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}



	private void saveUserToSessionAndCookie(HttpServletRequest request,
			HttpServletResponse response, User loginUserInfo) {
		// 创建HttpSession对象
		HttpSession session = request.getSession();
		
		// 保存信息至Cookie中
		Cookie jSessionId = new Cookie("JSESSIONID", session.getId());
		jSessionId.setMaxAge(3600 * 24);
		response.addCookie(jSessionId);
		
		Cookie userName = new Cookie("userName", loginUserInfo.getUserName());
		userName.setMaxAge(3600 * 24);
		response.addCookie(userName);
		
		// Cookie password = new Cookie("password", loginUserInfo.getPassword());
		// password.setMaxAge(3600 * 24);
		// response.addCookie(password);
		
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
			request.setAttribute("errinfo", "账号名或密码输入有误");
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
		
		if(!checkUserForm(request, userForm)){
			return mapping.findForward("registererr");
		}
		
		// 
		
		// 构建一个User对象
		User user = new User();
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		user.setNickName(userForm.getNickName());
		
		String question = userForm.getQuestion();
		if ("mother".equals(question)) {
			user.setQuestion("您母亲的名字是？");
		} else if ("father".equals(question)) {
			user.setQuestion("您父亲的名字是？");
		} else if ("lover".equals(question)) {
			user.setQuestion("您爱人的名字是？");
		} else if ("name".equals(question)) {
			user.setQuestion("您的名字是？");
		} else if ("birth".equals(question)) {
			user.setQuestion("您的出生年月是？");
		}
		
		user.setAnswer(userForm.getAnswer());
		
		// 测试表单数据是否填入表单对象(成功)
		// System.out.println(userForm.getUserName() + " " + userForm.getPassword());
		if(userService.register(user)) {
			saveUserToSessionAndCookie(request, response, user);
			return mapping.findForward("gotoUserUI");
		} else {
			request.setAttribute("errinfo", "用户名已存在");
			return mapping.findForward("registererr");
		}
	}



	private boolean checkUserForm(HttpServletRequest request, UserForm userForm) {
		// userName length verify
		if (userForm.getUserName().length() < 1 || userForm.getUserName().length() > 16) { 
			request.setAttribute("errinfo", "用户名长度必须为1到16位之间");
			return false;
		}
		// userName and password only letter and number
		if (!verifyOnlyLetterAndNumber(userForm.getUserName())
				|| !verifyOnlyLetterAndNumber(userForm.getPassword())) {
			request.setAttribute("errinfo", "用户名或密码只能为数字、字母、下划线且不能以下划线开头");
			return false;
		}
		// password length verify
		if (userForm.getPassword().length() < 6 || userForm.getPassword().length() > 16) {
			request.setAttribute("errinfo", "密码长度必须为6到16位之间");
			return false;
		}
		// repassword verity
		if (!userForm.getPassword().equals(userForm.getRepassword())) {
			request.setAttribute("errinfo", "两次输入密码密码不一致");
			return false;
		}
		// nickName length verify
		if (userForm.getNickName().length() < 1 || userForm.getNickName().length() > 20) {
			request.setAttribute("errinfo", "昵称长度必须为1到20位之间");
			return false;
		}
		// answer length verify
		if (userForm.getAnswer().length() < 2 || userForm.getAnswer().length() > 30) {
			request.setAttribute("errinfo", "密保答案长度必须为2到30位之间");
			return false;
		}
		return true;
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.removeUserFromSessionAndCookie(request, response);
		return mapping.findForward("gotoMainUI");
	}
}