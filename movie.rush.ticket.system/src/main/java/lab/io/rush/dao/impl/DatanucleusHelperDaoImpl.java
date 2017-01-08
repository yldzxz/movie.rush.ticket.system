package lab.io.rush.dao.impl;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import lab.io.rush.dao.DatanucleusHelperDao;

import org.springframework.beans.BeanUtils;

/**
 *类名称 ：DatanucleusHelperDaoImpl
 *类描述 ：Datanucleus接口实现类,根据事务处理http://www.datanucleus.org/products/accessplatform_2_1/guides/jdo/tutorial.html
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午6:49:10
 */
public class DatanucleusHelperDaoImpl implements DatanucleusHelperDao{

	private PersistenceManagerFactory pmf;
	@Override
	public boolean update(Object object) {
        boolean sign = false;
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        Object o;
        try{
        	tx.begin();
        	o = pm.getObjectById(object.getClass());
        	if(o.equals(null)){return false;}
        	BeanUtils.copyProperties(object, o);
        	tx.commit();
        	sign = true;
        }catch(JDOObjectNotFoundException e){
        	e.printStackTrace();
        }finally{
        	if(tx.isActive()){
        		tx.rollback();
        	}
        	pm.close();
        }
		return sign;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findByPrimaryKey(@SuppressWarnings("rawtypes") Class clazz, Object key) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Object o = null;
        try{
        	o = pm.getObjectById(clazz,key);
        }catch(JDOObjectNotFoundException e){
        	e.printStackTrace();
        }finally{
        	pm.close();
        }
		return o;
	}

	@Override
	public boolean deleteByPrimaryKey(Class clazz, Object key) {
		PersistenceManager pm = pmf.getPersistenceManager();
		boolean sign = false;
        Transaction tx = pm.currentTransaction();
		Object o = null;
        try{
        	tx.begin();
        	o = pm.getObjectById(clazz,key);
        	if(o.equals(null)){return false;}
        	pm.deletePersistent(o);
        	tx.commit();
        	sign = true;
        }catch(JDOObjectNotFoundException e){
        	e.printStackTrace();
        }finally{
        	if(tx.isActive()){
        		tx.rollback();
        	}
        	pm.close();
        }
		return sign;
	}

	@Override
	public Object insert(Object var) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        Object o = null;
        try{
        	tx.begin();
        	o = pm.getObjectById(var);
        	pm.makePersistent(var);
        	tx.commit();
        }catch(JDOObjectNotFoundException e){
        	e.printStackTrace();
        }finally{
        	if(tx.isActive()){
        		tx.rollback();
        	}
        	pm.close();
        }
		return o;
	}

	public List findAllByQuery(Class clazz, String query) {
        PersistenceManager pm = pmf.getPersistenceManager();
        List list = null;
        try{
        	javax.jdo.Query q = pm.newQuery(clazz,query);
        	list = (List) q.execute();
        }catch(JDOObjectNotFoundException e){
        	e.printStackTrace();
        }finally{
        	pm.close();
        }
		return list;
	}

}
