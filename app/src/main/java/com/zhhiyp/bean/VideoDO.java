package com.zhhiyp.bean;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class VideoDO {
	private String aid;
	private String videoNum;
	private String tName;
	private String title;
	private String pubdate;
	private String desc;
	private String duration;
	private String ownerId;
	//tagA#tagB#tagC#
	private String tags;

	public VideoDO(String aid, String videoNum, String tName, String title, String pubdate, String desc, String duration, String ownerId) {
		this.aid = aid;
		this.videoNum = videoNum;
		this.tName = tName;
		this.title = title;
		this.pubdate = pubdate;
		this.desc = desc;
		this.duration = duration;
		this.ownerId = ownerId;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(String videoNum) {
		this.videoNum = videoNum;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
