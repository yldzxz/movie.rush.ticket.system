package lab.io.rush.util;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import lab.io.rush.bean.Email;

/**
 *类名称 ：MailManagerUtils
 *类描述 ：邮件发送接口
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午6:41:43
 */
public class MailManagerUtils {

	//发送邮件
	public static boolean sendMail(Email email) {
		String subject = email.getSubject();
		String content = email.getContent();
		String[] recievers = email.getRecievers();
		String[] copyto = email.getCopyto();
		String attbody = email.getAttbody();
		if(recievers == null || recievers.length <=0) {
			return false;
		}
		try {
			Properties props = MailConfig.getEmailProperties();
			// 创建一个程序与邮件服务器的通信
			Session mailConnection = Session.getInstance(props, null);
			Message msg = new MimeMessage(mailConnection);

			// 设置发送人和接受人
			Address sender = new InternetAddress(props.getProperty("mail.smtp.from"));
			// 多个接收人
			msg.setFrom(sender);

			Set<InternetAddress> toUserSet = new HashSet<InternetAddress>();
			// 邮箱有效性较验
			for (int i = 0; i < recievers.length; i++) {
				if (recievers[i].trim().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)+$")) {
					toUserSet.add(new InternetAddress(recievers[i].trim()));
				}
			}
			msg.setRecipients(Message.RecipientType.TO, toUserSet.toArray(new InternetAddress[0]));
			// 设置抄送
			if (copyto != null) {
				Set<InternetAddress> copyToUserSet = new HashSet<InternetAddress>();
				// 邮箱有效性较验
				for (int i = 0; i < copyto.length; i++) {
					if (copyto[i].trim().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)+$")) {
						copyToUserSet.add(new InternetAddress(copyto[i].trim()));
					}
				}
				//	msg.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(copyto));
				msg.setRecipients(Message.RecipientType.CC, copyToUserSet.toArray(new InternetAddress[0]));
			}
			// 设置邮件主题
			msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B")); // 中文乱码问题

			// 设置邮件内容
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(content, "text/html; charset=UTF-8"); // 中文
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);

			/********************** 发送附件 ************************/
			if (attbody != null) {
				String[] filePath = attbody.split(";");
				for (String filepath : filePath) {
					//设置信件的附件(用本地机上的文件作为附件)
					BodyPart mdp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(filepath);
					DataHandler dh = new DataHandler(fds);
					mdp.setFileName(MimeUtility.encodeText(fds.getName()));
					mdp.setDataHandler(dh);
					multipart.addBodyPart(mdp);
				}
				//把mtp作为消息对象的内容
				msg.setContent(multipart);
			}
			;
			/********************** 发送附件结束 ************************/

			// 先进行存储邮件
			msg.saveChanges();
			System.out.println("正在发送邮件....");
			Transport trans = mailConnection.getTransport(props.getProperty("mail.protocal"));
			// 邮件服务器名,用户名，密码
			trans.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.user"), props.getProperty("mail.pass"));
			trans.sendMessage(msg, msg.getAllRecipients());
			System.out.println("发送邮件成功！");

			// 关闭通道
			if (trans.isConnected()) {
				trans.close();
			}
			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		} finally {
		}
	}
	
	

	// 发信人，收信人，回执人邮件中有中文处理乱码,res为获取的地址
	// http默认的编码方式为ISO8859_1
	// 对含有中文的发送地址，使用MimeUtility.decodeTex方法
	// 对其他则把地址从ISO8859_1编码转换成gbk编码
	public static String getChineseFrom(String res) {
		String from = res;
		try {
			if (from.startsWith("=?GB") || from.startsWith("=?gb") || from.startsWith("=?UTF")) {
				from = MimeUtility.decodeText(from);
			} else {
				from = new String(from.getBytes("ISO8859_1"), "GBK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return from;
	}

	// 转换为GBK编码
	public static String toChinese(String strvalue) {
		try {
			if (strvalue == null)
				return null;
			else {
				strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
				return strvalue;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
