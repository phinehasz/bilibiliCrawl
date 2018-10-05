package com.zhhiyp.facade;

import com.zhhiyp.avcrawl.core.TagProcessor;
import com.zhhiyp.avcrawl.core.VideoProcessor;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * @author zhiyp
 * @date 2018/10/4 0004 20:25
 */
public class Starter {
	private static final Logger LOGGER = Logger.getLogger(Starter.class);
	//FACADE TO START
	public static void main(String[] args) {
		if(args.length == 1 && "start".equals(args[0])){
			new VideoProcessor().run();
			new TagProcessor().run();
		}else {
			LOGGER.error("can't resolve args: "+Arrays.toString(args));
		}
	}
}
