package com.myblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.myblog.basic.BasicService;
import com.myblog.domain.Article;
import com.myblog.domain.User;
import com.myblog.service.inter.ArticleServiceInter;

public class ArticleService extends BasicService implements ArticleServiceInter {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Set<Article> getArticlesByUser(User user) {
		// test get article info (ok)
		// for (Article article : articleInfo) {
		//	System.out.println(article.getTitle() + " " + article.getHasread());
		// }
		
		return user.getArticles();
	}

	@Override
	public Integer getArticleCountByUser(User user) {
		return user.getArticles().size();
	}

	@Override
	public Integer getClickTotalCountByUser(User user) {
		Set<Article> articles = this.getArticlesByUser(user);
		Integer count = 0;
		for (Article article : articles) {
			count += article.getClicks().size();
		}
		return count;
	}

	@Override
	public Integer getCritiqueTotalCountByUser(User user) {
		Set<Article> articles = this.getArticlesByUser(user);
		Integer count = 0;
		for (Article article : articles) {
			count += article.getCritiques().size();
		}
		return count;
	}

	@Override
	public List<Article> getArticlesByUserByPageOrderByTime(User user, int pageNow, int pageSize) {
		String hql = "from Article a where a.user.userName =? order by date desc";
		String[] parameters = {user.getUserName()};
		return this.executeQueryByPage(hql, parameters, pageNow, pageSize);
	}

	@Override
	public int getArticlePageCount(User user, int pageSize) {
		String hql = "select count(*) from Article a where a.user.userName=?";
		String[] parameters = {user.getUserName()};
		return this.queryPageCount(hql, parameters, pageSize);
	}

	@Override
	public Date getLastestUpdateDateByUser(User user) {
		String hql = "select max(date) from Article a where a.user.userName=?";
		String[] parameters = {user.getUserName()};
		return (Date) this.executeUniqueQuery(hql, parameters);
	}
	
	
}
