package com.myblog.web.struts.action;

import java.io.Serializable;

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
import com.myblog.service.inter.ClickServiceInter;
import com.myblog.service.inter.UserServiceInter;
import com.myblog.web.struts.form.ArticleForm;

import static com.myblog.tools.IPAddress.getIpAddr;

public class UserArticleAction extends DispatchAction {
	@Resource
	ArticleServiceInter articleService;
	@Resource
	UserServiceInter userService;
	@Resource
	BlogInfoServiceInter blogInfoService;
	@Resource
	ClickServiceInter clickService;
	
	public void setClickService(ClickServiceInter clickService) {
		this.clickService = clickService;
	}

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
		prepareArticleContent(request, articleId, true);
		return mapping.findForward("gotoArticleContentPage");
	}

	private void prepareArticleContent(HttpServletRequest request,
			String articleId, boolean isCalcClick) {
		if (isCalcClick) {
			// add the click record to Click Class
			String ip =  getIpAddr(request);
			// test click IP (ok)
			// System.out.println(ip);
			clickService.saveClickRecordByArticleIdAndIPAddress(articleId, ip);
		}
		
		User visitUserInfo = articleService.getUserByArticleId(articleId);
		visitUserInfo = userService.checkUserNotEncrypt(visitUserInfo);
		request.setAttribute("visitUserInfo", visitUserInfo);
		// test get visitUserInfo
		// System.out.println(visitUserInfo.getUserName() + " " + visitUserInfo.getArticles().size());
		
		Article visitSingleArticleInfo = (Article) articleService.findById(Article.class, Integer.parseInt(articleId));
		request.setAttribute("visitSingleArticleInfo", visitSingleArticleInfo);
		
		Bloginfo visitBlogInfo = blogInfoService.getBlogInfoByUser(visitUserInfo);
		request.setAttribute("visitBlogInfo", visitBlogInfo);
		
		Integer visitArticleCount = articleService.getArticleCountByUser(visitUserInfo);
		request.setAttribute("visitArticleCount", visitArticleCount);
		
		Integer visitClickCount = articleService.getClickTotalCountByUser(visitUserInfo);
		request.setAttribute("visitClickCount", visitClickCount);
		
		Integer visitCritiqueCount = articleService.getCritiqueTotalCountByUser(visitUserInfo);
		request.setAttribute("visitCritiqueCount", visitCritiqueCount);
	}
	
	public ActionForward gotoWriteBlogUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userIdStr = request.getParameter("userId");
		User loginUserInfo = (User) request.getSession().getAttribute("loginUserInfo");
		if (loginUserInfo == null) {
			return mapping.findForward("opererr");
		}
		
		Integer userId = null;
		try {
			userId = Integer.parseInt(userIdStr);
		} catch (Exception e) {
			return mapping.findForward("opererr");
		}
		
		if (!userId.equals(loginUserInfo.getUserId())) {
			return mapping.findForward("opererr");
		} else {
			return mapping.findForward("gotoWriteBlogUI");
		}
	}
	
	public ActionForward saveArticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArticleForm articleForm = (ArticleForm) form;
		String userId = articleForm.getUserId();
		String title = articleForm.getTitle();
		String content = articleForm.getContent();
		if (title.length() > 45 || content.length() < 50) {
			request.setAttribute("errInfo", "博客内容不符合要求");
			return mapping.findForward("gotoWriteBlogUI");
		}
		
		
		String articleId = null;
		try {
			articleId = articleService.saveArticleByUserIdAndTitleAndContent(userId, title, content);
		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("opererr");
		}
		prepareArticleContent(request, articleId, false);
		return mapping.findForward("gotoArticleContentPage");
	}
}