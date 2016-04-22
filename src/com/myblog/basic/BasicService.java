package com.myblog.basic;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.NullableType;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.domain.User;

//配置注解@Transactional用处是让spring的事务管理器接管该Service的事务
@Transactional
public abstract class BasicService implements BasicServiceInter {
	// 当给某个属性增加了@Resource后，spring会启用byName的方式注入属性值
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public Object executeUniqueQuery(String hql, Object[] parameters) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		
		// 给?赋值
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return query.uniqueResult();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object findById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().load(clazz, id);
	}

	@Override
	public List executeQuery(String hql, Object[] parameters) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		
		// 注入?
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		
		return query.list();
	}

	@Override
	public List executeQueryByPage(String hql, Object[] parameters,
			int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		
		// 注入?
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		
		// 分页
		// 设置起始记录
		query.setFirstResult((pageNow - 1) * pageSize);
		// 设置每页记录数
		query.setMaxResults(pageSize);
		
		return query.list();
	}

	@Override
	public Serializable add(Object object) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void executeUpdate(String hql, Object[] parameters) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		// 注入?
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
	}
	
	@Override
	public int queryPageCount(String hql, Object[] parameters, int pageSize) {
		// 获取rowCount
		int pageRow = Integer.parseInt(this.executeUniqueQuery(hql, parameters).toString());
		// 分页总页数算法，pageSize - 1相当于最大余数
		return (pageRow + pageSize - 1) / pageSize;
	}

	@Override
	public void deleteById(Class clazz,Serializable id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(this.findById(clazz, id));
	}

	@Override
	public void update(Object object) {
		this.sessionFactory.getCurrentSession().update(object);
	}
	
	@Override
	public <T> List<T> getAll(String tname) {
		return this.executeQuery("from " + tname, null);
	}

	@Override
	public List executeSQLQuery(String sql, Object[] parameters) {
		SQLQuery query =  this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		// 注入?
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
				}
			}
		return query.list();
	}

	@Override
	public <T> List<T> executeSQLQueryAndTransformer(String sql,
			Object[] parameters, String[] alias, NullableType[] types,
			Class<T> clazz) {
		SQLQuery query =  this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		// 注入?
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
				}
			}
		if (alias != null && alias.length > 0 && types!= null && types.length > 0) {
			for (int i = 0; i < alias.length; i++) {
				query.addScalar(alias[i], types[i]);
			}
		}
		query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}
	
	@Override
	public int executeSQLUpdate(String sql, Object[] parameters){
		SQLQuery query =  this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		// 注入?
		if(parameters != null && parameters.length > 0) {
			for(int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
				}
			}
		return query.executeUpdate();
	}
	
}
