package com.myblog.web.struts.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.myblog.domain.User;
import com.myblog.service.inter.UserServiceInter;
import com.myblog.web.struts.form.UserForm;

import static com.myblog.tools.MD5.createMD5;

public class ForgetPasswordAction extends DispatchAction {
	@Resource
	UserServiceInter userService;
	
	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}

	public ActionForward gotoForgetPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("gotoForgetPassword");
	}
	
	public ActionForward gotoFindPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		User user = null;
		if ((user = userService.getUserByUserName(userForm.getUserName())) != null) {
			request.getSession().setAttribute("forgetUser", user);
			return mapping.findForward("gotoFindPassword");
		} else {
			request.setAttribute("errinfo", "账号名输入有误");
			return mapping.findForward("gotoForgetPassword");
		}
	}
	
	public ActionForward gotoFindMyPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("gotoFindPassword");
	}
	
	public ActionForward gotoModifyPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		User forgetUser = (User) request.getSession().getAttribute("forgetUser");
		User loginUserInfo = (User) request.getSession().getAttribute("loginUserInfo");
		if (loginUserInfo != null) {
			forgetUser = loginUserInfo;
		}
		if (userForm.getAnswer().equals(forgetUser.getAnswer())
				&& userForm.getPassword().equals(userForm.getRepassword())) {
			forgetUser.setPassword(createMD5(userForm.getPassword()));
			userService.update(forgetUser);
			request.getSession().removeAttribute("forgetUser");
			return mapping.findForward("modifyPasswordOk");
		} else {
			request.setAttribute("errinfo", "密保问题输入有误或两次密码不一致");
			return mapping.findForward("gotoFindPassword");
		}
	}
}