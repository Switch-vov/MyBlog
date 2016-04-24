package com.myblog.web.struts.action;

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
import com.myblog.domain.Critique;
import com.myblog.domain.User;
import com.myblog.service.inter.ArticleServiceInter;
import com.myblog.service.inter.BlogInfoServiceInter;
import com.myblog.service.inter.ClickServiceInter;
import com.myblog.service.inter.CritiqueServiceInter;
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
	@Resource
	CritiqueServiceInter critiqueService;
	
	public void setCritiqueService(CritiqueServiceInter critiqueService) {
		this.critiqueService = critiqueService;
	}

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
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId = request.getParameter("articleId");
		prepareArticleContent(request, articleId);
		return mapping.findForward("gotoArticleContentPage");
	}

	private void prepareArticleContent(HttpServletRequest request,
			String articleId) throws Exception{
		// add the click record to Click Class
		String ip =  getIpAddr(request);
		// test click IP (ok)
		// System.out.println(ip);
		clickService.saveClickRecordByArticleIdAndIPAddress(articleId, ip);
		
		User visitUserInfo = articleService.getUserByArticleId(articleId);
		visitUserInfo = userService.checkUserNotEncrypt(visitUserInfo);
		request.setAttribute("visitUserInfo", visitUserInfo);
		// test get visitUserInfo
		// System.out.println(visitUserInfo.getUserName() + " " + visitUserInfo.getArticles().size());
		
		Article visitSingleArticleInfo = (Article) articleService.findById(Article.class, Integer.parseInt(articleId));
		request.setAttribute("visitSingleArticleInfo", visitSingleArticleInfo);
		
		Bloginfo visitBlogInfo = blogInfoService.getBlogInfoByUser(visitUserInfo);
		request.setAttribute("visitBlogInfo", visitBlogInfo);
		
		List<Critique> visitCritiquesInfo = critiqueService.getAllCritiqueByArticleId(articleId);
		request.setAttribute("visitCritiquesInfo", visitCritiquesInfo);
		
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

		prepareArticleContent(request, articleId);
		return mapping.findForward("gotoArticleContentPage");
	}
	
	public ActionForward gotoUpdateBlogUI(ActionMapping mapping, ActionForm form,
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
			
			String articleId = request.getParameter("articleId");
			request.setAttribute("articleId", articleId);
			Article article = (Article) articleService.findById(Article.class, Integer.parseInt(articleId));
			String title = article.getTitle();
			request.setAttribute("title", title);
			String content = article.getContent();
			request.setAttribute("content", content);
			return mapping.findForward("gotoModifyUI");
		}
	}
	
	public ActionForward updateArticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArticleForm articleForm = (ArticleForm) form;
		String title = articleForm.getTitle();
		String content = articleForm.getContent();
		if (title.length() > 45 || content.length() < 50) {
			request.setAttribute("errInfo", "博客内容不符合要求");
			return mapping.findForward("gotoUpdateBlogUI");
		}
		String articleId = articleForm.getArticleId();
		Article article = (Article) this.articleService.findById(Article.class, Integer.parseInt(articleId));
		article.setTitle(title);
		article.setContent(content);
		this.articleService.update(article);
		prepareArticleContent(request, articleId);
		return mapping.findForward("gotoArticleContentPage");
	}
	
	public ActionForward comment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String articleId = request.getParameter("articleId");
		String content = request.getParameter("content");
		User user = (User) userService.findById(User.class, Integer.parseInt(userId));
		Article article = (Article) articleService.findById(Article.class, Integer.parseInt(articleId));
		Critique critique = new Critique(user, article, content);
		critiqueService.add(critique);
		prepareArticleContent(request, articleId);
		return mapping.findForward("gotoArticleContentPage");
	}
}