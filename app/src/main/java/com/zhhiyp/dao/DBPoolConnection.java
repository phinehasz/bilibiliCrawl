package com.zhhiyp.dao;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 要实现单例模式，保证全局只有一个数据库连接池
 */
public class DBPoolConnection {
	private static final Logger LOGGER = Logger.getLogger(DBPoolConnection.class);
	private static DBPoolConnection dbPoolConnection = new DBPoolConnection();
	private static DruidDataSource druidDataSource = null;
	static {
		Properties properties = loadPropertiesFile("db_server.properties");
		try {
			druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			LOGGER.error("CANNOT GET SETTINGS");
		}
	}
	/**
	 * 数据库连接池单例
	 * @return
	 */
	public static DBPoolConnection getInstance(){
		return dbPoolConnection;
	}

	public static DruidDataSource getDruidDataSource(){
		return druidDataSource;
	}

	/**
	 * 返回druid数据库连接
	 * @return
	 * @throws SQLException
	 */
	public DruidPooledConnection getConnection() throws SQLException{
		return druidDataSource.getConnection();
	}

	/**
	 *
	 * @param fullFile
	 * @return
	 */
	private static Properties loadPropertiesFile(String fullFile) {
		String classpath = null;
		if (null == fullFile || fullFile.equals("")){
			throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
		}
		classpath = DBPoolConnection.class.getClassLoader().getResource("").getPath();
		InputStream inputStream = null;
		Properties p =null;
		try {
			inputStream = new FileInputStream(new File(classpath + File.separator + fullFile));
			p = new Properties();
			p.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream){
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return p;
	}
}
