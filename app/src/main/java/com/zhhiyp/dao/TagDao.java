package com.zhhiyp.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zhiyp
 * @date 2018/10/4 0004 19:15
 */
public class TagDao {
	private static final Logger LOGGER = Logger.getLogger(TagDao.class);
	private static QueryRunner qr=new QueryRunner(DBPoolConnection.getDruidDataSource());

	public static void saveTags(String aid,List<String> tags){
		StringBuffer sb = new StringBuffer(7);
		tags.forEach((tag) ->{
			sb.append(tag+"#");
		});

		save(aid,sb.toString());
	}

	private static void save(String aid,String tags){
		//String aid, String videoNum, String tName, String title, String pubdate, String desc, String duration, String ownerId,String tags
		//video还未注册
		if(tags == null){
			return;
		}
		try {
			String sql="UPDATE video SET tags = ? WHERE aid = ?";
			Object[] params = {tags,aid};
			qr.update(sql,params);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

}
