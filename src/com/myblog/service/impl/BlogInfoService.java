package com.myblog.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.myblog.basic.BasicService;
import com.myblog.domain.Bloginfo;
import com.myblog.domain.User;
import com.myblog.service.inter.BlogInfoServiceInter;

public class BlogInfoService extends BasicService implements BlogInfoServiceInter {
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Bloginfo getBlogInfoByUser(User user) {
		Set<Bloginfo> blogInfos = user.getBloginfos();
		Iterator<Bloginfo> iterator = blogInfos.iterator();
		Bloginfo bloginfo = null;
		if(iterator.hasNext()) {
			bloginfo = iterator.next();
		}
		// test get blog info (ok)
		// for (Bloginfo bloginfo2 : blogInfo) {
		//	System.out.println(bloginfo2.getBlogtitle() + " " + bloginfo2.getIdiograph());
		// }
		return bloginfo;
	}

	@Override
	public Set<Bloginfo> initBlogInfoByUser(User user) {
		Bloginfo bloginfo = new Bloginfo(user);
		this.add(bloginfo);
		Set<Bloginfo> bloginfos = new HashSet<Bloginfo>();
		bloginfos.add(bloginfo);
		return bloginfos;
	}

}
