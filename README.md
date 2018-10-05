# bilibiliCrawl
基于WebMagic的b站视频爬虫  
以关键词爬取数据,分析内容,统计  
新计划正在开发中...  
核心如下:  
avcrawl\core
		--BilibiliSearchProcessor -- b站搜索host爬虫
		--TagProcessor --b站视频tag爬虫  
		--VideoProcessor --b站视频爬虫,基于av number  
dao
	--DBPoolConnection --使用druid连接池  
facade
	--Starter --执行入口  
