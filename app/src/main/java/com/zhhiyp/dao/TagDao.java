package com.zhhiyp.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhiyp
 * @date 2018/10/4 0004 19:15
 */
public class TagDao {
	private static final Logger LOGGER = Logger.getLogger(TagDao.class);
	private static final Map<String,String> tagGraph = new HashMap<>();
	private static QueryRunner qr=new QueryRunner(DBPoolConnection.getDruidDataSource());

	public static void saveTags(String aid,List<String> tags){
		StringBuffer sb = new StringBuffer(7);
		tags.forEach((tag) ->{
			sb.append(tag+"#");
		});

		tagGraph.put(aid,sb.toString());
		//get aid
		if(tagGraph.containsKey(aid)){
			//can save
			save(aid);
		}
	}

	private static void save(String aid){
		//String aid, String videoNum, String tName, String title, String pubdate, String desc, String duration, String ownerId,String tags
		String tags = tagGraph.get(aid);
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

	public static void register(String aid){
		if(!tagGraph.containsKey(aid)){
			tagGraph.put(aid,null);
		}
		save(aid);
	}
}
