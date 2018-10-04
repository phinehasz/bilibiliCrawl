package com.zhhiyp.bean;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class StatDO {
	private String aid;
	private String view;
	private String danmaku;
	private String reply;
	private String favorite;
	private String coin;

	public StatDO(String aid, String view, String danmaku, String reply, String favorite, String coin) {
		this.aid = aid;
		this.view = view;
		this.danmaku = danmaku;
		this.reply = reply;
		this.favorite = favorite;
		this.coin = coin;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getDanmaku() {
		return danmaku;
	}

	public void setDanmaku(String danmaku) {
		this.danmaku = danmaku;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}
}
