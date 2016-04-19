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
import com.myblog.domain.User;
import com.myblog.service.inter.ArticleServiceInter;
import com.myblog.service.inter.BlogInfoServiceInter;
import com.myblog.service.inter.UserServiceInter;

public class ModifyBlogInfoAction extends DispatchAction {
	@Resource
	private UserServiceInter userService;
	@Resource
	private BlogInfoServiceInter blogInfoService;
	@Resource
	private ArticleServiceInter articleService;
	
	public void setBlogInfoService(BlogInfoServiceInter blogInfoService) {
		this.blogInfoService = blogInfoService;
	}

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}

	public void setArticleService(ArticleServiceInter articleService) {
		this.articleService = articleService;
	}

	public ActionForward gotoModifyBlogInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("gotoModifyInfo");
	}
	
	public ActionForward modifyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nickName = request.getParameter("nickName");
		String idiograph = request.getParameter("idiograph");
		// get parameter (ok)
		// System.out.println(nickName + " " + idiograph);
		// nickName length verify
		if (nickName.length() < 1 || nickName.length() > 20) {
			request.setAttribute("errinfo", "昵称长度必须为1到20位之间");
			return mapping.findForward("gotoModifyInfo");
		}
		// idiograph length verify 
		if (idiograph.length() < 1 ||  idiograph.length() > 50) {
			request.setAttribute("errinfo", "个性签名长度必须为1到50位之间");
			return mapping.findForward("gotoModifyInfo");
		}
		
		User loginUserInfo = (User) request.getSession().getAttribute("loginUserInfo");
		loginUserInfo.setNickName(nickName);
		Bloginfo bloginfo =  blogInfoService.getBlogInfoByUser(loginUserInfo);
		bloginfo.setIdiograph(idiograph);
		userService.update(loginUserInfo);
		
		prepareShowVisitInfo(request, loginUserInfo.getUserName(), 1, 50);
		
		return mapping.findForward("modifyInfo");
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
}