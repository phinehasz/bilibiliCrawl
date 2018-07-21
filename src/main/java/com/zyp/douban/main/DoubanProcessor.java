package com.zyp.douban.main;

import com.zyp.douban.dao.DoubanListDao;
import com.zyp.douban.pojo.DoubanList;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author zhiyp
 * @date 2018/5/18 0018
 */
public class DoubanProcessor implements PageProcessor{
	private Site site = Site.me().setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0").setRetryTimes(3).setSleepTime(1000);

	public void process(Page page) {
		List<String> titles = page.getHtml().xpath("//div[@class='title']/a/text()").all();
		List<String> movieUrls = page.getHtml().xpath("//div[@class='post']/a").links().all();
		List<String> ratings = page.getHtml().xpath("//div[@class='rating']/span[@class='rating_nums']/text()").all();
		List<String> attrFields = page.getHtml().xpath("//div[@class='abstract']").all();
		for (int i = 0; i < titles.size(); i++) {
			DoubanList doubanList=new DoubanList();
			doubanList.setTitle(titles.get(i));
			doubanList.setMovieUrl(movieUrls.get(i));
			doubanList.setRating(ratings.get(i));
			String[] movieAttrs = attrFields.get(i).split("<br>");
			if(movieAttrs.length>4) {
				doubanList.setDirector(movieAttrs[0].substring(23).trim());
				doubanList.setActor(movieAttrs[1].trim());
				doubanList.setType(movieAttrs[2].trim());
				doubanList.setTime(movieAttrs[4].substring(0, 9).trim());
			}else {
				doubanList.setDirector(attrFields.get(i));
			}
			DoubanListDao.save(doubanList);
		}
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new DoubanProcessor())
				.addUrl("https://www.douban.com/doulist/3907668/")
				.addPipeline(new ConsolePipeline())
				.thread(5)
				.run();
	}
}
