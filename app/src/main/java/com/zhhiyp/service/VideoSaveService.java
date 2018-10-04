package com.zhhiyp.service;

import com.zhhiyp.bean.OwnerDO;
import com.zhhiyp.bean.StatDO;
import com.zhhiyp.bean.VideoDO;
import com.zhhiyp.dao.TagDao;
import com.zhhiyp.dao.VideoDao;
import com.zhhiyp.util.StringUtil;

import java.util.List;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class VideoSaveService {

	public static void saveVideoDo(VideoDO videoDO, OwnerDO ownerDO, StatDO statDO){
		videoDO.setPubdate(StringUtil.convert2String(1000*Long.parseLong(videoDO.getPubdate())));
		VideoDao.insertVideoDO(videoDO);
		TagDao.register(videoDO.getAid());
		VideoDao.insertOwnerDO(ownerDO);
		VideoDao.insertStatDO(statDO);
	}

	public static void saveTags(String aid, List<String> tags){
		TagDao.saveTags(aid,tags);
	}
}
