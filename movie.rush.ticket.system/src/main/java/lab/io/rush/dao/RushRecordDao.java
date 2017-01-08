package lab.io.rush.dao;

import java.util.List;

import lab.io.rush.bean.RushRecord;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 *类名称 ：RushRecordDao
 *类描述 ：抢购记录接口
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午4:28:40
 */
public interface RushRecordDao {
	
	/**
	 * 添加抢购记录
	 * @param rushRecord
	 * @return
	 */
	 public int addRushRecord(RushRecord rushRecord);
	 
	/**
	 * 更新抢购记录
	 * @param rushRecord
	 * @return
	 */
	 @Caching(evict={@CacheEvict(value  = "RushRecord",key = "'RushRecord-id:'+#rushRecord.id",beforeInvocation = true),
			         @CacheEvict(value = "RushRecord",key = "'RushRecord-userId-ticketId:'+#rushRecord.userId+'-'+#rushRecord.ticketId",beforeInvocation = true)})
	 public boolean modifyRushRecord(RushRecord rushRecord);
	 
	 
	/**
	 * 根据抢购记录Id查询抢购记录
	 * @param id 抢购记录Id
	 * @return 抢购记录
	 */
	 @Cacheable(value = "RushRecord",key = "'RushRecord-id'+#id")
	 public RushRecord findRecordById(int id);
	 
    /**
	 * @param userId 用户Id
	 * @param ticketId 电影票Id
	 * @return 抢购记录
	 */
	 @Cacheable(value = "RushRecord",key  = "'RushRecord-userId-ticketId'+#userId+'-'+#ticketId")
	 public RushRecord findRecordByUidAndTicketId(int userId,int ticketId);
	 
    /**
	 * @param userId 用户Id
	 * @return 该用户的所有抢购记录
	 */
	 public List<RushRecord> findRecordsByUserId(int userId);
	 
	 /**
	 * @param ticketId
	 * @return 该电影票的所有抢购记录
	 */
	 public List<RushRecord> findRecordsByTicketId(int ticketId);
}
