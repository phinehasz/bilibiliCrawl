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
	private static int queneMax = 0;
	private static int current = 0;
	protected void addHost() {
		site.addHeader("Host", "api.bilibili.com");
		//TODO b站单体视频TAG
		//https://api.bilibili.com/x/tag/archive/tags?aid=32858614
		//空是: {"code":0,"message":"0","ttl":1,"data":[]}
		//data.0.tag_id:tagId; data.0.tag_name:tagName;
	}

	public void process(Page page) {
		while(current < queneMax){
			aid++;
			page.addTargetRequest(defineUrl + aid);
			current += 1;
		}

		if (aid % 10000 == 0) {
			System.gc();
		}
		getTag(page);
		current -= 1;
	}

	private void getTag(Page page) {
		if (page.getStatusCode() == 403) {
			LOGGER.error(page.getRequest().getUrl() + " code:403");
			return;
		}

		String data = page.getJson().jsonPath("$.data").get();
		if (data == null) {
			LOGGER.info("data is null,skip it!");
			return;
		}
		List<String> tagNames = page.getJson().jsonPath("$..tag_name").all();
		String url = page.getRequest().getUrl();
		VideoSaveService.saveTags(cut2Aid(url), tagNames);

	}

	private String cut2Aid(String url) {
		return url.substring(url.lastIndexOf("=") + 1);
	}

	public static void main(String[] args) {
		new TagProcessor().run(50);
	}

	public void run(int threadNum) {
		addHost();
		queneMax = threadNum*2;
		Spider.create(this)
				//.addUrl(urls)
				.addUrl(defineUrl + aid)
				.addPipeline(new ConsolePipeline())
				.thread(threadNum)
				.run();
	}
}
