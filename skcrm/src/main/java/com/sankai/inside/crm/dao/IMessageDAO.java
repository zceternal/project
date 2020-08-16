package com.sankai.inside.crm.dao;

import java.math.BigInteger;
import java.util.List;

import com.sankai.inside.crm.entity.Message;

public interface IMessageDAO {

	public List<Message> selectMessageReceiveBy(int state,String content,int receiveId,int type);

	public List<Message> selectMessageBy(int type,int accountId,String content);
	
	public void insert(Message message);
	
	public void insertReceives(Integer[] receive,BigInteger sendId);
	
	public void delete(BigInteger id);
	
	public void deleteReceive(BigInteger messageId,int accountId);
	
	public Message selectInfo(BigInteger id);
	
	public void updateState(BigInteger id,int accountId);
	
	public int selectCount(int accountId);
	
	public int removeAllById(List<String> ids);
}
