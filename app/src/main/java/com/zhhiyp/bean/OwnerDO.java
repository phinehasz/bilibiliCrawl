package com.zhhiyp.bean;

/**
 * @author zhiyp
 * @date 2018/10/4 0004
 */
public class OwnerDO {
	private String ownerId;
	private String name;

	public OwnerDO(String ownerId, String name) {
		this.ownerId = ownerId;
		this.name = name;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
