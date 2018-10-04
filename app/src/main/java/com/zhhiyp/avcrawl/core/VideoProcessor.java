package com.zhhiyp.avcrawl.core;

import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class VideoProcessor extends AbstractProcessor {
	private static final Logger LOGGER = Logger.getLogger(VideoProcessor.class);

	private static final String defineUrl = "https://api.bilibili.com/x/web-interface/view?aid=1";//32858614
	@Override
	protected void addHost() {
		//TODO b站视频单体页面HOST
		site.addHeader("Host", "api.bilibili.com");
		//TODO b站单体视频TAG
		//https://api.bilibili.com/x/tag/archive/tags?aid=32858614
		//空是: {"code":0,"message":"0","ttl":1,"data":[]}
		//data.0.tag_id:tagId; data.0.tag_name:tagName;
		//TODO b站单体视频信息
		//https://api.bilibili.com/x/web-interface/view?aid=32858614
		//空是: {"code":-404,"message":"啥都木有","ttl":1,"data":null}
		//data:aid 视频号;videos:视频个数;tid:tagId;tname:分区名;title:视频名;pubdate:发布时间;
		//desc:视频描述;duration:视频总长s;owner.mid:作者ID,owner.name:作者名;
		//stat:view观看数;danmaku:弹幕数;reply:回复数;favorite:收藏;coin:硬币;分享;like:点赞;
	}

	@Override
	public void process(Page page) {
		String data = page.getJson().jsonPath("$.data").get();
		if(data == null){
			LOGGER.info("data is null,skip it!");
			return;
		}
		String aid = page.getJson().jsonPath("$.data.aid").get();
		String videoNums = page.getJson().jsonPath("$.data.videos").get();
		String tagId = page.getJson().jsonPath("$.data.tid").get();
		String tagNames = page.getJson().jsonPath("$.data.tname").get();
		String upnames = page.getJson().jsonPath("$.data.owner.name").get();
	}

	@Override
	public void run() {
		addHost();
		Spider.create(this)
				//.addUrl(urls)
				.addUrl(defineUrl)
				.addPipeline(new ConsolePipeline())
				.thread(5)
				.run();
	}
}
