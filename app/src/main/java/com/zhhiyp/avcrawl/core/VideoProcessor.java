package com.zhhiyp.avcrawl.core;

import com.zhhiyp.bean.OwnerDO;
import com.zhhiyp.bean.StatDO;
import com.zhhiyp.bean.VideoDO;
import com.zhhiyp.service.VideoSaveService;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class VideoProcessor extends AbstractProcessor {
	private static final Logger LOGGER = Logger.getLogger(VideoProcessor.class);

	private static final String defineUrl = "https://api.bilibili.com/x/web-interface/view?aid=";//32858614

	private static AtomicInteger queneMax = new AtomicInteger(0);
	private static AtomicInteger current = new AtomicInteger(0);
	@Override
	protected void addHost() {
		//TODO b站视频单体页面HOST
		site.addHeader("Host", "api.bilibili.com");
		//TODO b站单体视频信息
		//https://api.bilibili.com/x/web-interface/view?aid=32858614
		//空是: {"code":-404,"message":"啥都木有","ttl":1,"data":null}
		//data:aid 视频号;videos:视频个数;tid:分区id;tname:分区名;title:视频名;pubdate:发布时间;
		//desc:视频描述;duration:视频总长s;owner.mid:作者ID,owner.name:作者名;
		//stat:view观看数;danmaku:弹幕数;reply:回复数;favorite:收藏;coin:硬币;分享;like:点赞;
	}

	public void process(Page page) {
		//原操作造成队列堵塞 单纯+1 又没有使用到其他线程,降低效率
		while(current.get() < queneMax.get()){
			aid.getAndIncrement();
			page.addTargetRequest(defineUrl + aid);
			current.getAndIncrement();
		}

		getVideo(page);
		current.getAndDecrement();
	}

	private void getVideo(Page page) {
		String data = page.getJson().jsonPath("$.data").get();
		if (data == null) {
			LOGGER.info("data is null,skip it!");
			return;
		}

		String aid = page.getJson().jsonPath("$.data.aid").get();
		String videoNum = page.getJson().jsonPath("$.data.videos").get();
		String tName = page.getJson().jsonPath("$.data.tname").get();
		String title = page.getJson().jsonPath("$.data.title").get();
		String pubdate = page.getJson().jsonPath("$.data.pubdate").get();
		String desc = page.getJson().jsonPath("$.data.desc").get();
		String duration = page.getJson().jsonPath("$.data.duration").get();
		String ownerId = page.getJson().jsonPath("$.data.owner.mid").get();
		String name = page.getJson().jsonPath("$.data.owner.name").get();

		String view = page.getJson().jsonPath("$.data.stat.view").get();
		String danmaku = page.getJson().jsonPath("$.data.stat.danmaku").get();
		String reply = page.getJson().jsonPath("$.data.stat.reply").get();
		String favorite = page.getJson().jsonPath("$.data.stat.favorite").get();
		String coin = page.getJson().jsonPath("$.data.stat.coin").get();

		OwnerDO ownerDO = new OwnerDO(ownerId, name);
		VideoDO videoDO = new VideoDO(aid, videoNum, tName, title, pubdate, desc, duration, ownerId);

		StatDO statDO = new StatDO(aid, view, danmaku, reply, favorite, coin);

		VideoSaveService.saveVideoDo(videoDO, ownerDO, statDO);
		ownerDO = null;
		videoDO = null;
		statDO = null;
	}

	@Override
	public void run(int threadNum) {
		addHost();
		queneMax.set(threadNum*2);
		Spider.create(this)
				//.addUrl(urls)
				.addUrl(defineUrl + aid)
				.addPipeline(new ConsolePipeline())
				.thread(threadNum)
				.run();
	}
}
