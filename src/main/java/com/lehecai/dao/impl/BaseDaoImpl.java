package com.lehecai.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lehecai.dao.BaseDaoI;

public class BaseDaoImpl<T> implements BaseDaoI<T> {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> ls = q.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<T> ls = q.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> map) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				q.setParameter(key, map.get(key));
			}
			/*
			 * q.setParameter("name", map.get("name")); q.setParameter("pwd", map.get("pwd"));
			 */
		}
		List<T> ls = q.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public T get(Class<T> c,Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();// 从第几条开始查，该公式为=(当前页-1)*每页显示记录数，每次查询多少条
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();// 从第几条开始查，该公式为=(当前页-1)*每页显示记录数，每次查询多少条
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

}
