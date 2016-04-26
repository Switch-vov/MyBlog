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
import com.myblog.service.inter.ArticleServiceInter;
import com.myblog.web.struts.form.SearchForm;

public class SearchAction extends DispatchAction {
	@Resource
	ArticleServiceInter articleService;
	
	public void setArticleService(ArticleServiceInter articleService) {
		this.articleService = articleService;
	}

	public ActionForward gotoSearchArticleUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchForm searchForm = (SearchForm) form;
		String searchKey  = searchForm.getSearchKey();
		if (searchKey.length() < 2 || searchKey.length() > 20) {
			return mapping.findForward("opererr");
		}
		int pageNow = 1;
		int pageSize = 20;
		
		prepareShowSearchInfo(request, searchKey, pageNow, pageSize);
		
		return mapping.findForward("gotoSearchArticleUI");
	}

	private void prepareShowSearchInfo(HttpServletRequest request,
			String searchKey, int pageNow, int pageSize) {
		List<Article> searchArticles = articleService.getArticleBySearchKeyByPage(searchKey, pageNow, pageSize);
		
		request.setAttribute("searchArticles", searchArticles);
		
		int pageCount = articleService.getArticlePageCountBySearchKey(searchKey, pageSize);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("pageNow", pageNow);
		
		request.setAttribute("searchKey", searchKey);
	}
	
	public ActionForward pageArticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageNowString = request.getParameter("pageNow");
		String searchKey = request.getParameter("searchKey");
		if (searchKey.length() < 2 || searchKey.length() > 20) {
			return mapping.findForward("opererr");
		}
		int pageSize = 20;
		Integer pageNow = null;
		try {
			pageNow = Integer.parseInt(pageNowString);
		} catch (Exception e) {
			prepareShowSearchInfo(request, searchKey, 1, pageSize);
			return mapping.findForward("gotoSearchArticleUI");
		}
		if(pageNow <= 0 || pageNow > articleService.getArticlePageCountBySearchKey(searchKey, pageSize)) {
			prepareShowSearchInfo(request, searchKey, 1, pageSize);
			return mapping.findForward("gotoSearchArticleUI");
		} else {
			prepareShowSearchInfo(request, searchKey, pageNow, pageSize);
			return mapping.findForward("gotoSearchArticleUI");
		}
	}
}