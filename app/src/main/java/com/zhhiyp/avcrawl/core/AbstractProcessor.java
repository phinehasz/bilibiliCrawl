package com.zhhiyp.avcrawl.core;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public abstract class AbstractProcessor implements PageProcessor {
	protected static int aid = 1;
	//Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36
	protected Site site = Site.me().setUserAgent("Mozilla/5.0 (Windows NT 10.0; …e/59.0.3071.109 Safari/537.36")
			.setRetryTimes(3)
			.setTimeOut(30000)
			.setSleepTime(1800)
			.setCycleRetryTimes(3)
			.setUseGzip(true)
			.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			.addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
			.addHeader("Accept-Encoding","gzip, deflate, br");

	public Site getSite() {
		return site;
	}

	public static void setAid(int start){
		aid = start;
	}

	protected abstract void addHost();

	public abstract void run(int threadNum);
}
