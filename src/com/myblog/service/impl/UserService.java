package com.myblog.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.myblog.basic.BasicService;
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
	
}