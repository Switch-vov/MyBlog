package com.myblog.web.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.myblog.domain.Article;
import com.myblog.domain.Bloginfo;
import com.myblog.domain.User;
import com.myblog.service.inter.ArticleServiceInter;
import com.myblog.service.inter.BlogInfoServiceInter;
import com.myblog.service.inter.CritiqueServiceInter;
import com.myblog.service.inter.UserServiceInter;

public class UserBlogAction extends DispatchAction {
	@Resource
	UserServiceInter userService;
	@Resource
	BlogInfoServiceInter blogInfoService;
	@Resource
	ArticleServiceInter articleService;
	@Resource
	CritiqueServiceInter critiqueService;
	
	public void setCritiqueService(CritiqueServiceInter critiqueService) {
		this.critiqueService = critiqueService;
	}

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}
	
	public void setBlogInfoService(BlogInfoServiceInter blogInfoService) {
		this.blogInfoService = blogInfoService;
	}

	public void setArticleService(ArticleServiceInter articleService) {
		this.articleService = articleService;
	}

	public ActionForward gotoUserUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// get blog username from query string
		String userName = request.getParameter("userName");
		// User loginUserInfo = (User) request.getSession().getAttribute("loginUserInfo");
		String userId = request.getParameter("userId");
		if (userId != null && userId.length() >= 0) {
			User user = (User) userService.findById(User.class, Integer.parseInt(userId));
			userName = user.getUserName();
		}
		
		if(prepareShowVisitInfo(request, userName, 1, 30)){
			return mapping.findForward("gotoUserUI");
		} else {
			return mapping.findForward("opererr");
		}
	}
	
	public ActionForward page(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageNowString = request.getParameter("pageNow");
		String userName = request.getParameter("userName");
		int pageNow = 1;
		int pageSize = 30;
		try {
			pageNow = Integer.parseInt(pageNowString);
		} catch (Exception e) {
			if(prepareShowVisitInfo(request, userName, 1, pageSize)){
				return mapping.findForward("gotoUserUI");
			} else {
				return mapping.findForward("opererr");
			}
		}
		if(pageNow <= 0 || pageNow > articleService.getArticlePageCount(userService.getUserByUserName(userName), pageSize)) {
			if(prepareShowVisitInfo(request, userName, 1, pageSize)){
				return mapping.findForward("gotoUserUI");
			} else {
				return mapping.findForward("opererr");
			}
		} else {
			if(prepareShowVisitInfo(request, userName, pageNow, pageSize)){
				return mapping.findForward("gotoUserUI");
			} else {
				return mapping.findForward("opererr");
			}
		}
	}

	private boolean prepareShowVisitInfo(HttpServletRequest request,
			String userName, int pageNow, int pageSize) {
		// prepare user info
		User visitUserInfo = userService.getUserByUserName(userName);
		if(visitUserInfo == null) {
			return false;
		}
		// test get user info (ok)
		// System.out.println(visitUserInfo.getUserName() + " " + visitUserInfo.getPassword());
		request.setAttribute("visitUserInfo", visitUserInfo);
		
		// prepare blog info
		Bloginfo visitBlogInfo = blogInfoService.getBlogInfoByUser(visitUserInfo);
		// test get blog info (ok)
		// System.out.println(visitBlogInfo.getBlogtitle() + " " + visitBlogInfo.getIdiograph());
		request.setAttribute("visitBlogInfo", visitBlogInfo);
			
		// prepare article count
		Integer visitArticleCount = articleService.getArticleCountByUser(visitUserInfo);
		// test get article count  (ok)
		// System.out.println(visitArticleCount);
		request.setAttribute("visitArticleCount", visitArticleCount);
		
		// prepare article click total count
		Integer visitClickCount = articleService.getClickTotalCountByUser(visitUserInfo);
		// test get article click total count  (ok)
		// System.out.println(visitClickCount);
		request.setAttribute("visitClickCount", visitClickCount);
		
		// prepare article critique total count
		Integer visitCritiqueCount = articleService.getCritiqueTotalCountByUser(visitUserInfo);
		// test get article critique total count  (ok)
		// System.out.println(visitCritiqueCount);
		request.setAttribute("visitCritiqueCount", visitCritiqueCount);
		
		// prepare article info
		List<Article> visitArticleInfo = articleService.getArticlesByUserByPageOrderByTime(visitUserInfo, pageNow, pageSize);
		request.setAttribute("visitArticleInfo", visitArticleInfo);
		
		// prepare article page count
		Integer pageCount = articleService.getArticlePageCount(visitUserInfo, pageSize);
		request.setAttribute("pageCount", pageCount);
		
		// prepare article page now
		request.setAttribute("pageNow", pageNow);
		
		return true;
	}
	
	public ActionForward gotoAllBlogUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		prepareAllBlogInfo(request, 1, 20);
		
		return mapping.findForward("gotoAllBlogUI");
	}
	
	public ActionForward pageBlog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageNowString = request.getParameter("pageNow");
		int pageNow = 1;
		int pageSize = 20;
		try {
			pageNow = Integer.parseInt(pageNowString);
		} catch (Exception e) {
			prepareAllBlogInfo(request, 1, pageSize);
			return mapping.findForward("gotoAllBlogUI");
		}
		if(pageNow <= 0 || pageNow > userService.getWriterBlogUserPageCount(pageSize)) {
			prepareAllBlogInfo(request, 1, pageSize);
			return mapping.findForward("gotoAllBlogUI");
		} else {
			prepareAllBlogInfo(request, pageNow, pageSize);
			return mapping.findForward("gotoAllBlogUI");
		}
	}
	

	private void prepareAllBlogInfo(HttpServletRequest request,int pageNow, int pageSize) {
		List<User> tmpUsers =  userService.getAllUserByPageOrderByTime(pageNow, pageSize);
		List<User> allUser = new ArrayList<User>();
		for (User user : tmpUsers) {
			User userTmp =  new User();
			userTmp = userService.checkUserNotEncrypt(user);
			Integer userClick = new Integer(0);
		 	userClick = articleService.getClickTotalCountByUser(userTmp);
		 	request.setAttribute("userClick" + userTmp.getUserName(), userClick);
		 	Integer userCritique = new Integer(0);
		 	userCritique = articleService.getCritiqueTotalCountByUser(userTmp);
		 	request.setAttribute("userCritique" + userTmp.getUserName(), userCritique);
		 	Date lastUpdateTime = new Date();
		 	lastUpdateTime = articleService.getLastestUpdateDateByUser(userTmp);
		 	request.setAttribute("lastUpdateTime" + userTmp.getUserName(), lastUpdateTime);
			allUser.add(userTmp);
			// test encapsulate (ok)
			// System.out.println(userTmp.getUserId() + " " + userTmp.getNickName() + " " +  userTmp.getArticles().size());
		}
		request.setAttribute("allUser", allUser);
		
		Integer pageCount = userService.getWriterBlogUserPageCount(pageSize);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("pageNow", pageNow);
	}
}