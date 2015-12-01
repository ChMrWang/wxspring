package com.entityDao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;   
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * @author Mr Wang
 * dao层实现类，提供增删改查操作
 */
public class EntityDaoImpl extends HibernateDaoSupport implements EntityDao{

	/* 
	 * @see com.entityDao.EntityDao#insert(java.lang.Object)
	 * 调用hibernate插入一个对象
	 */
	@Override
	public Object insert(Object model) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.save(model);
				return null;
			}
		});
	}

	/*
	 * @see com.entityDao.EntityDao#delete(java.lang.Object)
	 * 从数据库删除一个对象实体
	 */
	@Override
	public void delete(Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.delete(model);
				return null;
			}
		});
	}

	/* 
	 * @see com.entityDao.EntityDao#update(java.lang.Object)
	 * 修改一条记录，根据主键
	 */
	@Override
	public void update(Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.update(model);
				return null;
			}
		});
	}

	/* 
	 * @see com.entityDao.EntityDao#select(java.lang.String)
	 * 根据给定的HQL语句进行查询，返回List<Object>
	 */
	@Override
	public List<?> select(String hql) {
		return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}
	
}
