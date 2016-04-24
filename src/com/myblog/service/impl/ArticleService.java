package com.myblog.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.type.NullableType;

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

	@Override
	public User getUserByArticleId(String articleId) {
		String sql = "select u.* from user u,article a where u.userId = a.userId and a.articleId=?";
		String[] parameters = {articleId};
		String[] alias = {"userId", "userName", "password", "nickName", "question", "answer"};
		NullableType[] types = {Hibernate.INTEGER, Hibernate.STRING, Hibernate.STRING, 
				Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};
		Class<User> clazz = User.class;
		List<User> users = this.executeSQLQueryAndTransformer(sql, parameters, alias, types, clazz);
		User user = new User();
		if(users != null) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public String saveArticleByUserIdAndTitleAndContent(String userId,
			String title, String content) throws Exception{
		String sql = "insert into article(userId,title,content) values(?,?,?)";
		String[] parameters = {userId, title, content};
		this.executeSQLUpdate(sql, parameters);
		sql = "select max(articleId) from article";
		List list = this.executeSQLQuery(sql, null);
		return list.get(0).toString();
	}

	@Override
	public List<Article> getArticleBySearchKeyByPage(String searchKey, int pageNow, int pageSize) {
		String hql = "from Article a where a.title like '%" + searchKey + "%' order by date desc";
		//String[] parameters = {searchKey};
		return this.executeQueryByPage(hql, null, pageNow, pageSize);
	}

	@Override
	public int getArticlePageCountBySearchKey(String searchKey, int pageSize) {
		String hql = "select count(*) from Article a where a.title like '%" + searchKey + "%'";
		// Object[] parameters = {searchKey};
		return this.queryPageCount(hql, null, pageSize);
	}
	
}
