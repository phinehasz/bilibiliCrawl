package com.zyp.douban.dao;

import com.zyp.douban.pojo.DoubanList;
import com.zyp.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @author zhiyp
 * @date 2018/5/20 0020
 */
public class DoubanListDao {
	public static void save(DoubanList doubanList){
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		try {
			String sql="insert into doubanlist (title,movieUrl,rating,director,actor,type,time) " +
					"values (?,?,?,?,?,?,?) ";
			Object[] params={doubanList.getTitle(),doubanList.getMovieUrl(),doubanList.getRating(),
					doubanList.getDirector(),doubanList.getActor(),doubanList.getType(),
					doubanList.getTime()};
			int row=qr.update(sql,params);
			if(row>0){
				System.out.println("succeed!");
			}else{
				System.out.println("failed!");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
