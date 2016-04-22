package com.myblog.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.myblog.basic.BasicService;
import com.myblog.domain.Article;
import com.myblog.domain.Click;
import com.myblog.service.inter.ClickServiceInter;

public class ClickService extends BasicService implements ClickServiceInter{
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Serializable saveClickRecordByArticleIdAndIPAddress(
			String articleId, String ip) {
		String sql = "insert into click(articleId,ip) values(?,?)";
		String[] parameters = {articleId, ip};
		return this.executeSQLUpdate(sql, parameters);
	}
	
	
}
