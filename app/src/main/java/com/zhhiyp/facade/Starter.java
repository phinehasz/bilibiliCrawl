package com.zhhiyp.facade;

import com.zhhiyp.avcrawl.core.AbstractProcessor;
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
		try{
			if(args.length ==0 ){
				System.out.println("use like this:java -jar xxx.jar start video[or tag] -th 50 begin 10000");
			}
			if(args.length >= 1 && "start".equals(args[0])){
				AbstractProcessor.setAid(Integer.parseInt(args[5]));
				if("video".equals(args[1])){
					new VideoProcessor().run(Integer.parseInt(args[3]));
				}
				if("tag".equals(args[1])){
					new TagProcessor().run(Integer.parseInt(args[3]));
				}
			}else {
				LOGGER.error("can't resolve args: "+Arrays.toString(args));
			}
		} catch (Throwable e) {
			LOGGER.error("error catch!",e);
		}

	}
}
