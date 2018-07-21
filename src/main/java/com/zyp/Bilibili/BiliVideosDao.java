package com.zyp.Bilibili;

import com.zyp.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zhiyp
 * @date 2018/5/24 0024
 */
public class BiliVideosDao {
	private static QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	private static int duplicateCount = 0;
	public static void save(String keyword ,String author,String title,String srcLinks,String duration,String watchNum,String uploadTime,
							String id,String tag,String favorite,String description){

		try {
			String sql="insert into bilivideos (keyword,author,title,srcLinks,duration,watchNum,uploadTime," +
					"id,tag,favorite,description) " +
					"values (?,?,?,?,?,?,?,?,?,?,?) ";
			Object[] params = {keyword,author,title,srcLinks,duration,watchNum,uploadTime,id,tag,favorite,description};
			qr.update(sql,params);
		} catch (SQLException e) {
			duplicateCount++;
			if(duplicateCount>50){
				throw new RuntimeException(e);
			}
		}
	}


	public static void main(String[] args) {
		String sql = "SELECT keyword,DATE_FORMAT(uploadTime,'%Y-%c') month FROM bilivideos ORDER BY uploadTime";
		List<Map<String, Object>> query = null;
		try {
			query = qr.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(query);
	}
}
