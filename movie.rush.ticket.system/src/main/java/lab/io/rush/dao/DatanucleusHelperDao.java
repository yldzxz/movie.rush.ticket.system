package lab.io.rush.dao;

import java.util.List;

/**
 *类名称 ：DatanucleusHelperDao
 *类描述 ：泛型CRUD通用，根据Datanucleus API设置参数
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午6:06:08
 */
public interface DatanucleusHelperDao<T> {

	public  boolean update(T object);
	
	public  T findByPrimaryKey(Class<T> clazz,Object key);
	
	public  boolean deleteByPrimaryKey(Class<T> clazz,Object key);
	
	public Object insert(T var);
	
	public List<T> findAllByQuery(Class<T> clazz,String query);
	
	
	
	
}
