package lab.io.rush.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 *类名称 ：MailConfig
 *类描述 ：管理config
 *创建人 ：黄耿嘉
 *创建时间 ：2017年1月5日上午6:42:25
 */
public class MailConfig {
	private static Properties properties = null;
	static{
		properties = loadPropertyFile("mail.properties");
	}
	
	/**
	 *  获取发送邮件配置
	 *  @return Properties
	 */
	public static Properties getEmailProperties()
	{
		Properties prop = new Properties();
	try{
		prop.put("mail.enable", properties.get("mail.enable"));
		prop.put("mail.user", properties.get("mail.user"));
		prop.put("mail.pass", properties.get("mail.pass"));
		prop.put("mail.smtp.from", properties.get("mail.smtp.from"));	//增加发送人的邮件地址
		prop.put("mail.smtp.host", properties.get("mail.smtp.host"));
		prop.put("mail.protocal", properties.get("mail.protocal"));
	}catch(Exception e){
		System.out.println("error!!! 请检查您的mail.properties数据库配置文件！");
		e.printStackTrace();
	}
		return prop;
	}
	
	/**
	 *  读取properties文件
	 *  @return Properties
	 */
	public static Properties loadPropertyFile(String fullFile) {
		if (null == fullFile || fullFile.equals(""))
			throw new IllegalArgumentException(
					"Properties file path can not be null : " + fullFile);
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = MailConfig.class.getClassLoader().getResourceAsStream(fullFile);
			p = new Properties();
			p.load(inputStream);
		} catch (Exception e) {
			System.out.println("Error !!!! Properties file can not be loading: " + fullFile);
		} finally {
			try {
				if (inputStream != null){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
}

