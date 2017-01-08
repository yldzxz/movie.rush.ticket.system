package lab.io.rush.bean;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *类名称 ：MovieTicket
 *类描述 ：电影票实体类
 *sql:CREATE TABLE `movie_ticket` (
		`ticket_id` INT(11) NOT NULL COMMENT '电影票编号',
		`ticket_num` INT(11) NULL DEFAULT NULL COMMENT '电影票数目',
		`movie_name` INT(11) NULL DEFAULT NULL COMMENT '电影名称',
		`start_time` DATETIME NOT NULL COMMENT '开始时间',
		`end_time` DATETIME NOT NULL COMMENT '结束时间',
		`sale_time` DATETIME NOT NULL COMMENT '添加起售时间',
		PRIMARY KEY (`ticket_id`)
		)
		COMMENT='电影票'
		COLLATE='utf8_general_ci'
		ENGINE=InnoDB
		;
 *创建人 ：黄耿嘉
 *创建时间 ：2016年12月29日下午10:56:02
 */
@PersistenceCapable(table="movie_ticket")
public class MovieTicket {
    @PrimaryKey
    @Persistent(name = "ticket_id",valueStrategy = IdGeneratorStrategy.IDENTITY)
	private int ticketId;//电影票编号
	
    @Column(name = "movie_name",length = 50)
	private String movieName;//电影名称
	
    @Column(name = "ticket_num")
	private int ticketNum;//电影票数目
	
    @Column(name = "begin_time",allowsNull = "false")
	private Date beginTime;//开始时间
	
    @Column(name = "end_time",allowsNull = "false")
	private Date endTime;//结束时间
	
    @Column(name = "sale_time",allowsNull = "false")
	private Date saleTime;//添加起售时间

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	@Override
	public String toString() {
		return "MovieTicket [ticketId=" + ticketId + ", movieName=" + movieName + ", ticketNum=" + ticketNum + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", saleTime=" + saleTime + "]";
	}
    
}
