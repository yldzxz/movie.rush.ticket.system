package lab.io.rush.bean;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *类名称 ：RushRecord
 *类描述 ：抢购记录
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午4:31:46
 */
@PersistenceCapable(table = "rush_record")
public class RushRecord {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private int id;//抢购Id
	
	@Column(name = "uid")
	private int userId;//用户Id
	
	@Column(name = "tid")
	private int ticketId;//电影票Id
	
	@Column(name = "num")
	private int ticketNum;//电影票数目
	
	@Column(name= "rush_time",allowsNull = "false")
	private Date rushTime;//抢购时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Date getRushTime() {
		return rushTime;
	}

	public void setRushTime(Date rushTime) {
		this.rushTime = rushTime;
	}

	@Override
	public String toString() {
		return "RushRecord [id=" + id + ", userId=" + userId + ", ticketId=" + ticketId + ", ticketNum=" + ticketNum + ", rushTime=" + rushTime + "]";
	}
	

}
