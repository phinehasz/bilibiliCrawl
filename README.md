# bilibiliCrawl 1.2V
基于WebMagic的b站视频爬虫  
以关键词爬取数据,分析内容,统计  
目前生产环境运行中...  **欢迎反馈**

核心如下:  
```
avcrawl\core  
	--BilibiliSearchProcessor -- b站搜索host爬虫  
	--TagProcessor --b站视频tag爬虫  
	--VideoProcessor --b站视频爬虫,基于av number  
dao
	--DBPoolConnection --使用druid连接池  
facade
	--Starter --执行入口  
```
**使用方法:**  

将工程打包(内置shade插件),会打成一个fat jar  
`java -jar xxx.jar start [video/tag] -th [线程数] begin [开始av号]`  
第一次启动:  
`java -jar xxx.jar start [video/tag] -th [线程数] begin 1`即可  
begin这个参数 之前是为了解决溢出和临时暂停的问题.  

目前video和tag的爬取是分开的.  
推荐video线程 30到70  
tag线程我之前较多,结果被b站封ip和user-agent了.先建议7~20.  

---
UPDATE LOG:  
2018/10/9  
```
调整为循环控制队列总大小,解决溢出问题.  
之前测试中发现会溢出,结果发现是队列太多的缘故,现在不会溢出了. 
```

