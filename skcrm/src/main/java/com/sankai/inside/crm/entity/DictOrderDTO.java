package com.sankai.inside.crm.entity;
/**
 * 上下移动菜单
 * @author CGQ
 *
 */
public class DictOrderDTO {
	private Integer id;
	private Integer brotherId;
	private Integer pid;
	private Integer iOrder;//移动目标的order
	private Integer newOrder;//新的Order
	
	private Integer move;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getiOrder() {
		return iOrder;
	}
	public void setiOrder(Integer iOrder) {
		this.iOrder = iOrder;
	}
	public Integer getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(Integer newOrder) {
		this.newOrder = newOrder;
	}
	public Integer getBrotherId() {
		return brotherId;
	}
	public void setBrotherId(Integer brotherId) {
		this.brotherId = brotherId;
	}
	public Integer getMove() {
		return move;
	}
	public void setMove(Integer move) {
		this.move = move;
	}
	@Override
	public String toString() {
		return "DictOrderDTO [id=" + id + ", brotherId=" + brotherId + ", pid=" + pid + ", iOrder=" + iOrder
				+ ", newOrder=" + newOrder + ", move=" + move + "]";
	}
	
	
}
