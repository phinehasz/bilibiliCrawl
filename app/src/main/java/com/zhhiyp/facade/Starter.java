package com.zhhiyp.facade;

import com.zhhiyp.avcrawl.core.TagProcessor;
import com.zhhiyp.avcrawl.core.VideoProcessor;

/**
 * @author zhiyp
 * @date 2018/10/4 0004 20:25
 */
public class Starter {
	//FACADE TO START
	public static void main(String[] args) {
		new VideoProcessor().run();
		new TagProcessor().run();
	}
}
