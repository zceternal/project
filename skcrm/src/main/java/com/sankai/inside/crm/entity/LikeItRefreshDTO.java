package com.sankai.inside.crm.entity;

/**
 * 点赞后刷新点赞状态
 * @author cgq
 *
 */
public class LikeItRefreshDTO {
	private Integer likeQty;
	private Integer liked;
	public Integer getLikeQty() {
		return likeQty;
	}
	public void setLikeQty(Integer likeQty) {
		this.likeQty = likeQty;
	}
	public Integer getLiked() {
		return liked;
	}
	public void setLiked(Integer liked) {
		this.liked = liked;
	}
	
}
