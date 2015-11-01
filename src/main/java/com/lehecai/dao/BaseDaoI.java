package com.lehecai.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {
	public java.io.Serializable save(T o);
	
	public void update(T o);
	
	public void saveOrUpdate(T o);
	
	/**
	 * 查询
	 * @Description: TODO:
	 * @param hql
	 * @return:
	 * @return List<T>:  
	 * @author sunqp
	 */
	public List<T> find(String hql);
	
	public List<T> find(String hql,Map<String,Object> params);
	
	/**
	 * 翻页查询，全字段查询
	 * @Description: TODO:
	 * @param hql
	 * @param page
	 * @param rows
	 * @return:
	 * @return List<T>:  
	 * @author sunqp
	 */
	public List<T> find(String hql,int page,int rows);
	/**
	 * 翻页查询，且传参
	 * @Description: TODO:
	 * @param hql
	 * @param params
	 * @param page 页数
	 * @param rows 查询的条数
	 * @return:
	 * @return List<T>:  
	 * @author sunqp
	 */
	public List<T> find(String hql,Map<String,Object> params,int page,int rows);
	/**
	 * 删除
	 * @Description: TODO:
	 * @param o:
	 * @return void:  
	 * @author sunqp
	 */
	public void delete(T o);
	
	public T get(String hql);
	
	/**
	 * 本项目暂时不用这个方法了，有下面的替换了
	 * @Description: TODO:
	 * @param hql
	 * @param params
	 * @return:
	 * @return T:  
	 * @author sunqp
	 */
	public T get(String hql,Object[] params);
	
	/**
	 * 获取数据库字段
	 * @Description: TODO:
	 * @param hql
	 * @param map
	 * @return:
	 * @return T:  
	 * @author sunqp
	 */
	public T get(String hql,Map<String,Object> map);
	
	/**
	 * 通过ID来获取数据库实体类对象
	 * @Description: TODO:
	 * @param id
	 * @param c
	 * @return:
	 * @return T:  
	 * @author sunqp
	 */
	public T get(Class<T> c,Serializable id);
	/**
	 * 计算查询的条数
	 * @Description: TODO:
	 * @param hql
	 * @return:
	 * @return Long:  
	 * @author sunqp
	 */
	public Long count(String hql);
	
	/**
	 * 计算查询的条数-含参数
	 * @Description: TODO:
	 * @param hql
	 * @return:
	 * @return Long:  
	 * @author sunqp
	 */
	public Long count(String hql,Map<String,Object> params);
	
	/**
	 * 直接执行hql语句，而不先去查询等操作
	 * @Description: TODO:
	 * @param hql
	 * @return:
	 * @return int:  
	 * @author sunqp
	 */
	public int executeHql(String hql);
}
