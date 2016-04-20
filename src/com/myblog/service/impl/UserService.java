package com.myblog.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.myblog.basic.BasicService;
import com.myblog.domain.Article;
import com.myblog.domain.Bloginfo;
import com.myblog.domain.User;
import com.myblog.service.inter.BlogInfoServiceInter;
import com.myblog.service.inter.UserServiceInter;

import static com.myblog.tools.MD5.createMD5;;

public class UserService extends BasicService implements UserServiceInter{
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private BlogInfoServiceInter blogInfoService;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setBlogInfoService(BlogInfoServiceInter blogInfoService) {
		this.blogInfoService = blogInfoService;
	}

	@Override
	public User getUserById(Serializable id) {
		return (User) this.findById(User.class, id);
	}
	
	@Override
	public User checkUser(User user) {
		String hql = "from User where userName=? and password=?";
		Object[] parameters = {user.getUserName(), createMD5(user.getPassword())};
		List list = this.executeQuery(hql, parameters);
		if(list.size() <= 0 || list.size() >= 2) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}
	
	@Override
	public User checkUserNotEncrypt(User user) {
		String hql = "from User where userName=? and password=?";
		Object[] parameters = {user.getUserName(), user.getPassword()};
		List list = this.executeQuery(hql, parameters);
		if(list.size() <= 0 || list.size() >= 2) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}

	@Override
	public boolean register(User user) {
		List<User> users = this.getAllUser();
		for (User u : users) {
			if(u.getUserName().equals(user.getUserName())) {
				return false;
			}
		}
		// 使用MD5加密密码
		user.setPassword(createMD5(user.getPassword()));
		this.add(user);
		Set<Bloginfo> bloginfos =  blogInfoService.initBlogInfoByUser(user);
		user.setBloginfos(bloginfos);
		// 测试获取ID是否成功(成功)
		// System.out.println(id);			
		return true;
	}
	
	@Override
	public List<User> getAllUser() {
		return this.getAll("User");
	}

	@Override
	public User getUserByUserName(String userName) {
		String hql = "from User where userName=?";
		String[] parameters = {userName};
		return (User) this.executeUniqueQuery(hql, parameters);
	}
	
	@Override
	public List<User> getAllUserByPageOrderByTime(int pageNow, int pageSize) {
		String sql = "select userId,userName,password,nickName,question,answer from (select u.*,max(date) maxdate from user u,article a where u.userId = a.userId GROUP BY a.userId ORDER BY userId asc) temp ORDER BY temp.maxdate desc";
		SQLQuery sqlQuery =  sessionFactory.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("userId", Hibernate.INTEGER);
		sqlQuery.addScalar("userName", Hibernate.STRING);
		sqlQuery.addScalar("password", Hibernate.STRING);
		sqlQuery.addScalar("nickName", Hibernate.STRING);
		sqlQuery.addScalar("question", Hibernate.STRING);
		sqlQuery.addScalar("answer", Hibernate.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(User.class));
		
		// 分页
		// 设置起始记录
		sqlQuery.setFirstResult((pageNow - 1) * pageSize);
		// 设置每页记录数
		sqlQuery.setMaxResults(pageSize);
		return sqlQuery.list();
		
	}

	@Override
	public int getWriterBlogUserPageCount(int pageSize) {
		String sql = "select count(*) from (select u.*,max(date) maxdate from user u,article a where u.userId = a.userId GROUP BY a.userId ORDER BY userId asc) temp ORDER BY temp.maxdate desc;";
		List list = executeSQLQuery(sql, null);
		Integer pageCount = Integer.parseInt(list.get(0).toString());
		// 分页总页数算法，pageSize - 1相当于最大余数
		return (pageCount + pageSize - 1) / pageSize;
	}
	
}