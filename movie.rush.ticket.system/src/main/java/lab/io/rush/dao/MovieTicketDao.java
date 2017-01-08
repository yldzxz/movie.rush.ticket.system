package lab.io.rush.dao;

import java.util.List;

import lab.io.rush.bean.MovieTicket;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 *类名称 ：MovieTicketDao
 *类描述 ：电影票接口
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午2:53:45
 */
public interface MovieTicketDao {

	/**
	 * 添加电影票
	 * @param movieTicket 电影票
	 * @return 电影票Id
	 */
	public int addTicket(MovieTicket movieTicket);
	
	/**
	 * 更新电影票
	 * @param movieTicket 电影票
	 * @return
	 */
	@Caching(evict = {@CacheEvict(value = "MovieTicket",key = "'MovieTicket-id:'+#movieTicket.id",beforeInvocation  = true)})
	public boolean modifyMovieTicket(MovieTicket movieTicket);
	
	/**
	 * 根据电影票编号查询电影票
	 * @param id 电影票Id
	 * @return 电影票
	 */
	@Cacheable(value = "MovieTicket",key  = "'MovieTicket-id'+#id")
	public MovieTicket findMovieTicketById(int id);
	
	/**
	 * 根据电影票Id获取电影票数量
	 * @param id 电影票Id
	 * @return 电影票数量
	 */
	@Cacheable(value = "MovieTicketNum",key = "'MovieTicketNum-id'+#id")
	public int getNumOfMovieTicket(int id);
	
	/**
	 * @return 所有电影票
	 */
	public List<MovieTicket> findAllTicket();
}
