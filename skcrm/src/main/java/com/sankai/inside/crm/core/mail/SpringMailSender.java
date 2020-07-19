package com.sankai.inside.crm.core.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SpringMailSender  {
	
	private JavaMailSenderImpl mailSender;
	
	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.userName}")
	private String userName;
	
	@Value("${mail.password}")
	private String password;
	
	public void initConfig(){
		
		mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost(host);
		mailSender.setUsername(userName);
		mailSender.setPassword(password);
		
	}
	
	/**
	 * 发送简单邮件
	 * @param mailAdd  收件人地址
	 * @param title
	 * @param content
	 */
	public void sendSimpleMail(String mailAdd,String title,String content){
		
		if(mailSender == null) initConfig();
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(mailSender.getUsername());
	    smm.setTo(mailAdd);
	    smm.setSubject(title);
	    smm.setText(content);
	    
	    mailSender.send(smm);
	    
	}
	
	/**
	 * 
	 * @param personal 发送人名称
	 * @param mailAdd  收件人地址
	 * @param title
	 * @param content
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	
public void sendMail(String personal,String mailAdd,String title,String content) throws MessagingException, UnsupportedEncodingException{
		
		if(mailSender == null) initConfig();
		
		MimeMessage mailMessage = mailSender.createMimeMessage();  
        MimeMessageHelper smm = new MimeMessageHelper(mailMessage,false, "utf-8"); 
        
        smm.setTo(mailAdd);
	    smm.setSubject(title);
	    smm.setText("<html><head></head><body>" + content + "</body></html>",true);
	    	    
	    smm.setFrom(userName, personal + "(三开 CMS)");
	    
	    mailSender.send(mailMessage);
	}
}
