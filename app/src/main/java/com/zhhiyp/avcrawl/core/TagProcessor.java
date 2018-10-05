package com.zhhiyp.avcrawl.core;

import com.zhhiyp.service.VideoSaveService;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.List;

/**
 * @author zhiyp
 * @date 2018/10/4 0004 18:50
 */
public class TagProcessor extends AbstractProcessor {
	private static final Logger LOGGER = Logger.getLogger(TagProcessor.class);
	private static final String defineUrl = "https://api.bilibili.com/x/tag/archive/tags?aid=";
	//private static boolean added = false;
	//private static final int total = 1000000;

	protected void addHost() {
		site.addHeader("Host", "api.bilibili.com");
		//TODO b站单体视频TAG
		//https://api.bilibili.com/x/tag/archive/tags?aid=32858614
		//空是: {"code":0,"message":"0","ttl":1,"data":[]}
		//data.0.tag_id:tagId; data.0.tag_name:tagName;
	}

	public void process(Page page) {
//		if (!added) {
//			for (int i = 0; i < total; i++) {
//				page.addTargetRequest(defineUrl + i);
//			}
//			added = true;
//		}
		for (int i = 0; i < step; i++) {
			page.addTargetRequest(defineUrl + aid);
			aid++;
		}
		if(aid%10000 == 0 ){
			System.gc();
		}
		getTag(page);
	}

	private void getTag(Page page){

		String data = page.getJson().jsonPath("$.data").get();
		if(data == null){
			LOGGER.info("data is null,skip it!");
			return;
		}
		List<String> tagNames = page.getJson().jsonPath("$..tag_name").all();
		String url = page.getRequest().getUrl();
		VideoSaveService.saveTags(cut2Aid(url),tagNames);

	}

	private String cut2Aid(String url) {
		return url.substring(url.lastIndexOf("=")+1);
	}

	public static void main(String[] args) {
		new TagProcessor().run(50);
	}
	public void run(int threadNum) {
		addHost();
		step = threadNum;
		Spider.create(this)
				//.addUrl(urls)
				.addUrl(defineUrl+1)
				.addPipeline(new ConsolePipeline())
				.thread(threadNum)
				.run();
	}
}
