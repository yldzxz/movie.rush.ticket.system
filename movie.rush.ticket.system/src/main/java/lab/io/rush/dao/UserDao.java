package lab.io.rush.dao;

import lab.io.rush.bean.User;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 *类名称 ：UserDao
 *类描述 ：用户接口
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午2:53:29
 */
public interface UserDao {
	
	/**
	 * 新增用户
	 * @param user 用户
	 * @return 用户id
	 */
	public int addUser(User user);
	
	
	/**
	 * 更新用户
	 * @param user 用户
	 * @return 
	 */
	@Caching(evict = {@CacheEvict(value = "User", key = "'User-id:'+#user.id", beforeInvocation = true),
	@CacheEvict(value = "User",key = "'User-username:'+#user.username",beforeInvocation = true),
	@CacheEvict(value = "User",key = "'User-email:'+#user.email",beforeInvocation = true)
	})
	public boolean modifyUser(User user);
	
	/**
	 * 根据id查询用户
	 * @param id 用户id
	 * @return 用户
	 */
	@Cacheable(value = "User",key = "'User-id:'+#id")
	public User findUserById(int id);
	
	/**
	 * 根据邮箱查询用户
	 * @param email 用户邮箱，默认qq邮箱
	 * @return 用户
	 */
	@Cacheable(value = "User",key = "'User-email'+#email")
	public User findUserByEmail(String email);
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	@Cacheable(value = "User", key = "'User-username'+#username")
	public User findUserByUsername(String username);
}
