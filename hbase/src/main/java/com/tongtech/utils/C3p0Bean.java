package com.tongtech.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author dongshuai
 * 
 *         dbcp ʵ���࣬�ṩ��dbcp���ӣ�������̳У�
 * 
 *         ������Ҫ�и��ط�����ʼ�� DS ��ͨ������initDS
 *         ���������
 */
public final class C3p0Bean {
	private static final Log logger = LogFactory.getLog(C3p0Bean.class);
	/** ����Դ��static */
	public static DataSource DS;

	/**
	 * ������Դ���һ������
	 * 
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {

		try {
			return DS.getConnection();
		} catch (SQLException e) {
			logger.error("������ӳ���:" + e.getMessage());
			e.printStackTrace();
			throw e;
			// return null;
		}
	}

	/** ���캯������ʼ���� DS ��ָ�� ���ݿ� */
	// public DbcpBean(String connectURI) {
	// initDS(connectURI);
	// }

	/** ���캯������ʼ���� DS ��ָ�� ���в��� */
	// public DbcpBean(String connectURI, String username, String pswd, String
	// driverClass, int initialSize,
	// int maxActive, int maxIdle, int maxWait) {
	// initDS(connectURI, username, pswd, driverClass, initialSize, maxActive,
	// maxIdle, maxWait);
	// }

	/**
	 * ��������Դ���������ݿ��⣬��ʹ��Ӳ�����Ĭ�ϲ�����
	 * 
	 * @param connectURI
	 *            ���ݿ�
	 * @return
	 * @throws PropertyVetoException 
	 */
	// public static void initDS(String connectURI) {
	// initDS(connectURI, "scott", "tiger", "oracle.jdbc.driver.OracleDriver",
	// 5, 100, 30, 10000);
	// }

	public static void initDSByParam() throws PropertyVetoException {
		String connectURI = SysParamUtil.getParam("db.url");
		String username = SysParamUtil.getParam("db.username");
		String pwd = SysParamUtil.getParam("db.password");
		String driverClass = SysParamUtil.getParam("db.driver");
		int initialPoolSize = Integer.valueOf(SysParamUtil
				.getParam("db.initialPoolSize"));
		int maxPoolSize = Integer.valueOf(SysParamUtil.getParam("db.maxPoolSize"));
		int minPoolSize = Integer.valueOf(SysParamUtil.getParam("db.minPoolSize"));
		int maxIdleTime = Integer.valueOf(SysParamUtil.getParam("db.maxIdleTime"));
		int acquireIncrement = Integer.valueOf(SysParamUtil.getParam("db.acquireIncrement"));
		int acquireRetryAttempts = Integer.valueOf(SysParamUtil.getParam("db.acquireRetryAttempts"));
		int acquireRetryDelay = Integer.valueOf(SysParamUtil.getParam("db.acquireRetryDelay"));
		int checkoutTimeout = Integer.valueOf(SysParamUtil.getParam("db.checkoutTimeout"));
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl(connectURI);
		dataSource.setDriverClass(driverClass);
		dataSource.setInitialPoolSize(initialPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMaxIdleTime(maxIdleTime);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setAcquireRetryAttempts(acquireRetryAttempts);
		dataSource.setAcquireRetryDelay(acquireRetryDelay);
		dataSource.setCheckoutTimeout(checkoutTimeout);
		
		DS = dataSource;
	}


	/** �ر�����Դ */
	protected static void shutdownDataSource() throws SQLException {
		ComboPooledDataSource bds = (ComboPooledDataSource) DS;
		bds.close();
	}

	static {
		try {
			initDSByParam();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}// ��������Դ
	}

}
