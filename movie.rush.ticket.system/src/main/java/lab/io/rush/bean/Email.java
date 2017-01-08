package lab.io.rush.bean;
/**
 *类名称 ：Email
 *类描述 ： 发送邮件实体类
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午6:44:01
 */
public class Email {
	private String fromUser;//发送用户邮箱
	private String subject;//主题
	private String[] recievers;//收件人
	private String[] copyto;//抄送人
	private String content;//内容
	private String attbody;//附件路径

	public Email(String fromUser, String subject, String[] recievers, String[] copyto, String content, String attbody) {
		super();
		this.fromUser = fromUser;
		this.subject = subject;
		this.recievers = recievers;
		this.copyto = copyto;
		this.content = content;
		this.attbody = attbody;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getRecievers() {
		return recievers;
	}

	public void setRecievers(String[] recievers) {
		this.recievers = recievers;
	}

	public String[] getCopyto() {
		return copyto;
	}

	public void setCopyto(String[] copyto) {
		this.copyto = copyto;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttbody() {
		return attbody;
	}

	public void setAttbody(String attbody) {
		this.attbody = attbody;
	}

}
