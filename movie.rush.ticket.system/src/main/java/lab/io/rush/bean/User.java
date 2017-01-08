package lab.io.rush.bean;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *类名称 ：User
 *类描述 ：用户实体类
 *sql:CREATE TABLE `user` (
		`username` VARCHAR(50) NULL DEFAULT NULL COMMENT '用户名',
		`password` VARCHAR(50) NULL DEFAULT NULL COMMENT '密码',
		`email` VARCHAR(50) NULL DEFAULT NULL COMMENT '电子邮箱',
		`regist_time` VARCHAR(50) NOT NULL COMMENT '注册时间',
		`user_id` INT(11) NOT NULL COMMENT '用户编号',
		PRIMARY KEY (`user_id`)
		)
		COMMENT='用户表'
		ENGINE=InnoDB
		;
 *创建人 ：黄耿嘉
 *创建时间 ：2016年12月29日下午10:08:51
 */
@PersistenceCapable(table="user")
public class User {
    @Column(length = 50)
	private String username;//用户名
    
	@PrimaryKey
	@Persistent(name="user_id",valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String userId;//用户编号
	
    @Column(length = 50)
	private String password;//密码
    
    @Column(length = 50,allowsNull = "false")
	private String email;//电子邮箱
    
    @Column(name = "regist_time",allowsNull = "false")
    private Date registTime;//注册时间

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", userId=" + userId + ", password=" + password + ", email=" + email + ", registTime=" + registTime
				+ "]";
	}

	
    
}
