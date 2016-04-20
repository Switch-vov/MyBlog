package com.myblog.web.struts.action;

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
import com.myblog.service.inter.UserServiceInter;

public class UserArticleAction extends DispatchAction {
	@Resource
	ArticleServiceInter articleService;
	@Resource
	UserServiceInter userService;
	@Resource
	BlogInfoServiceInter blogInfoService;
	
	public void setBlogInfoService(BlogInfoServiceInter blogInfoService) {
		this.blogInfoService = blogInfoService;
	}

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}

	public void setArticleService(ArticleServiceInter articleService) {
		this.articleService = articleService;
	}



	public ActionForward gotoArticleContentPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String articleId = request.getParameter("articleId");
		User visitUserInfo = articleService.getUserByArticleId(articleId);
		visitUserInfo = userService.checkUserNotEncrypt(visitUserInfo);
		request.setAttribute("visitUserInfo", visitUserInfo);
		// test get visitUserInfo
		// System.out.println(visitUserInfo.getUserName() + " " + visitUserInfo.getArticles().size());
		
		Article visitArticleInfo = (Article) articleService.findById(Article.class, Integer.parseInt(articleId));
		request.setAttribute("visitArticleInfo", visitArticleInfo);
		
		Bloginfo visitBlogInfo = blogInfoService.getBlogInfoByUser(visitUserInfo);
		request.setAttribute("visitBlogInfo", visitBlogInfo);
		
		Integer visitArticleCount = articleService.getArticleCountByUser(visitUserInfo);
		request.setAttribute("visitArticleCount", visitArticleCount);
		
		Integer visitClickCount = articleService.getClickTotalCountByUser(visitUserInfo);
		request.setAttribute("visitClickCount", visitClickCount);
		
		Integer visitCritiqueCount = articleService.getCritiqueTotalCountByUser(visitUserInfo);
		request.setAttribute("visitCritiqueCount", visitCritiqueCount);
		
		return mapping.findForward("gotoArticleContentPage");
	}
}