package com.sankai.inside.crm.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.core.mail.SpringMailSender;
import com.sankai.inside.crm.core.utils.IdUtils;
import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.IMessageDAO;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.Message;
import com.sankai.inside.crm.web.core.MyRealm;
import com.sankai.inside.crm.web.core.UserState;

@Component
public class MessageService {

	private static Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private IMessageDAO messageDAO;

	@Autowired
	private IAccountDAO accountDAO;

	@Autowired
	private SpringMailSender mailSender;

	public Page<Message> findMessageReceiveBy(int state, String content, int page, int pageSize, int type) {

		Integer loginId = UserState.getLoginId();

		PageHelper.startPage(page, pageSize, true);

		Page<Message> list = (Page<Message>) messageDAO.selectMessageReceiveBy(state, content, loginId, type);

		return list;

	}

	public Page<Message> findMyMessageBy(String content, int page, int pageSize, int type) {

		Integer loginId = UserState.getLoginId();

		PageHelper.startPage(page, pageSize, true);

		Page<Message> list = (Page<Message>) messageDAO.selectMessageBy(type, loginId, content);

		return list;

	}

	@Transactional(rollbackFor = Exception.class)
	public void sendMessage(String title, String content, Integer[] ids, int type) {

		int loginId = UserState.getLoginId();

		Message message = new Message();
		message.setId(IdUtils.uniqueId());
		message.setContent(content);
		message.setTitle(title);
		message.setCreateTime(new Date());
		message.setCreateId(loginId);
		message.setType(type);
		message.setState(0);

		messageDAO.insert(message);
		messageDAO.insertReceives(ids, message.getId());
		/*Account thisAccount = accountDAO.selectAccountInfo(loginId);
		for (int id : ids) {
			// 发送邮件
			try {
				Account account = accountDAO.selectAccountInfo(id);
				mailSender.sendMail(thisAccount.getName(),account.getEmail(),
						title, content);
			} catch (Exception ex) {
				logger.error("邮件发送失败", ex);
			}
		}*/
	}
	
	

	public int getUnreadCount() {

		int loginId = UserState.getLoginId();

		return messageDAO.selectCount(loginId);
	}

	public void remove(BigInteger messageId) {

		int loginId = UserState.getLoginId();

		Message msg = messageDAO.selectInfo(messageId);

		// 本人创建
		if (msg.getCreateId() == loginId) {

			messageDAO.delete(messageId);

		} else {
			messageDAO.deleteReceive(messageId, loginId);
		}

	}

	public Message show(BigInteger id) {
		Message msg = messageDAO.selectInfo(id);
		return msg;
	}

	public void setReaded(BigInteger id) {
		int loginId = UserState.getLoginId();
		messageDAO.updateState(id, loginId);
	}
	@Transactional(rollbackFor = Exception.class)
	public void sendMessage(String title, String content,Integer createId, Integer[] ids, int type) {

		Message message = new Message();
		message.setId(IdUtils.uniqueId());
		message.setContent(content);
		message.setTitle(title);
		message.setCreateTime(new Date());
		message.setCreateId(createId);
		message.setType(type);
		message.setState(0);

		messageDAO.insert(message);
		messageDAO.insertReceives(ids, message.getId());
	}
	
	public int removeAllById(List<String> ids){
		return messageDAO.removeAllById(ids);
	}
}
