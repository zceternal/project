package com.sankai.inside.crm.entity;

/**
 * 点赞后响应
 * @author cgq
 *
 */
public class LikeItReponseDTO {
	private Integer likeQty;//新的点赞数量
	private boolean isLiked;//是否已经赞过
	private boolean isSuccess;//是否点赞成功
	private String msg;//响应消息
	public Integer getLikeQty() {
		return likeQty;
	}
	public void setLikeQty(Integer likeQty) {
		this.likeQty = likeQty;
	}
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "LikeItReponseDTO [likeQty=" + likeQty + ", isLiked=" + isLiked + ", isSuccess=" + isSuccess + ", msg="
				+ msg + "]";
	}

}
