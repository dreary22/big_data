package com.tongtech.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 获得“src/sysParameter.properties”中的系统参数
 * 工具类
 * @author 董帅
 * 2013-01-05 
 *
 */
public class SysParamUtil {
	private static final Log logger = LogFactory.getLog(SysParamUtil.class);
	private static java.util.Properties param = null;

	static{
		File directory = new File(".");
		InputStream in = null;
		try {
			//String pathPro = System.getProperty("user.dir")+File.separator+"sysParameter.properties";
			//System.out.println(directory.getAbsolutePath());
			in = SysParamUtil.class.getResourceAsStream("/sysParameter.properties");
			//in = new FileInputStream(directory.getCanonicalPath().toString()+File.separator+"conf"+File.separator+"sysParameter.properties");
			//in = new FileInputStream(pathPro);
			Properties p = new Properties();
			p.load(in);
			param = p;
			logger.debug("加载src\\sysParameter.properties完成!");
		} catch (Exception e) {
			logger.error("加载src\\sysParameter.properties时出错！");
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
	
	
	/**
	 * 获得配置文件中某个参数的值
	 * @param paramName 参数名称
	 * @return	参数值
	 * 
	 * @author 董帅
	 * Aug 2, 2008  4:26:15 PM
	 */
	public static String getParam(String paramName){
		return param.getProperty(paramName);
	}
	
	public static void setParam(String paramName,String paramValue){
		String pathPro = "sysParameter.properties";
		param.setProperty(paramName, paramValue);
		try {
			OutputStream os = new FileOutputStream(pathPro);
			param.store(os, "");
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
