package com.zhhiyp.dao;

import com.zhhiyp.bean.OwnerDO;
import com.zhhiyp.bean.StatDO;
import com.zhhiyp.bean.VideoDO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class VideoDao {
	private static final Logger LOGGER = Logger.getLogger(VideoDao.class);
	private static QueryRunner qr=new QueryRunner(DBPoolConnection.getDruidDataSource());

	public static void insertVideoDO(VideoDO videoDO){
		//String aid, String videoNum, String tName, String title, String pubdate, String desc, String duration, String ownerId,String tags
		try {
			String sql="insert into video (aid,videoNum,tName,title,pubdate,description,duration,ownerId,tags) " +
					"values (?,?,?,?,?,?,?,?,?) ";
			Object[] params = {videoDO.getAid(),videoDO.getVideoNum(),videoDO.gettName(),videoDO.getTitle(),
					videoDO.getPubdate(),videoDO.getDesc(),videoDO.getDuration(),videoDO.getOwnerId(),videoDO.getTags()};
			qr.update(sql,params);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	public static void insertOwnerDO(OwnerDO ownerDO){
		//String ownerId, String name
		try {
			String sql="insert into owner (ownerId,name) " +
					"values (?,?)";
			Object[] params = {ownerDO.getOwnerId(),ownerDO.getName()};
			qr.update(sql,params);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	public static void insertStatDO(StatDO statDO){
		//aid,view,danmaku,reply,favorite,coin
		try {
			String sql="insert into stat (aid,view,danmaku,reply,favorite,coin) " +
					"values (?,?,?,?,?,?)";
			Object[] params = {statDO.getAid(),statDO.getView(),statDO.getDanmaku(),statDO.getReply(),statDO.getFavorite(),
					statDO.getCoin()};
			qr.update(sql,params);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
