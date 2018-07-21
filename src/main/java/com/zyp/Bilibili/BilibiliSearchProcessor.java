package com.zyp.Bilibili;

import com.zyp.util.SimpleUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author zhiyp
 * @date 2018/5/23 0023
 */
public class BilibiliSearchProcessor implements PageProcessor{
	private Site site = Site.me().setUserAgent("Mozilla/5.0 (Windows NT 10.0; …e/59.0.3071.109 Safari/537.36")
			.setRetryTimes(3)
			.setTimeOut(30000)
			.setSleepTime(1800)
			.setCycleRetryTimes(3)
			.setUseGzip(true)
			.addHeader("Host","search.bilibili.com")
			.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			.addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
			.addHeader("Accept-Encoding","gzip, deflate, br")
	;
	private static final String keyword = "指弹吉他";
	private static final String defineUrl = "https://search.bilibili.com/api/search?search_type=video&keyword="+keyword+"&from_source=banner_search&order=pubdate&duration=0&tids=0";
	//"井草圣二","伍伍慧","押尾光太郎","岸部真明","松井佑贵","小松原俊","郑成河","depapepe","Pierre Bensusan","TOMMY EMMANUEL","Daniel Padim","andy mckee"};
	public void process(Page page) {
		int numPage = Integer.parseInt(page.getJson().jsonPath("$.numPages").get());
		for (int i=0;i<numPage;i++) {
			page.addTargetRequest(defineUrl+"&page="+(i));
		}
		//up主
		List<String> ups = page.getJson().jsonPath("$..author").all();
		page.putField("author", ups);
		//标题
		List<String> titles = page.getJson().jsonPath("$..title").all();
		page.putField("title", titles);
		//链接
		List<String> srcLinks = page.getJson().jsonPath("$..arcurl").all();
		page.putField("srcLinks", srcLinks);
		//时长
		List<String> durations = page.getJson().jsonPath("$..duration").all();
		page.putField("duration", durations);
		//观看数
		List<String> watchNums = page.getJson().jsonPath("$..play").all();
		page.putField("watchNum", watchNums);
		//上传时间  2017-08-09  1502222633
		// 2016-09-28  1475053142
		//2018-05-18 1526650568
		List<String> uploadTimes = page.getJson().jsonPath("$..pubdate").all();
		page.putField("uploadTime", uploadTimes);
		//review
		List<String> ids = page.getJson().jsonPath("$..id").all();
		//video_review
		List<String> tags = page.getJson().jsonPath("$..tag").all();
		//favorite
		List<String> favorites = page.getJson().jsonPath("$..favorites").all();
		//视频说明
		List<String> description = page.getJson().jsonPath("$..description").all();
		page.putField("description", description);
		for (int i=0;i<ups.size();i++) {
			BiliVideosDao.save(keyword,ups.get(i),SimpleUtil.cutHtml(titles.get(i)),srcLinks.get(i),durations.get(i),watchNums.get(i)
			, SimpleUtil.convert2String(1000*Long.parseLong(uploadTimes.get(i))),
					ids.get(i),tags.get(i),favorites.get(i),description.get(i));
		}
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new BilibiliSearchProcessor())
				//.addUrl(urls)
				.addUrl(defineUrl)
				.addPipeline(new ConsolePipeline())
				.thread(5)
				.run();
	}
}
