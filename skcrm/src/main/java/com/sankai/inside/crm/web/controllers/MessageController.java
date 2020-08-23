package com.sankai.inside.crm.web.controllers;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.entity.Message;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.MessageService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.FormPage;
import com.sankai.inside.crm.web.model.MessageSearchForm;
import com.sankai.inside.crm.web.model.MessageSendForm;

@Controller
@RequestMapping("message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AccountService accountService;
	
	////@RequiresAuthentication
	////@RequiresPermissions(value="message_list")
	@RequestMapping(path="index",method=RequestMethod.GET)
	public String index(Model model,FormPage page){
		
		Page<Message> result = messageService.findMessageReceiveBy(0, null, page.getPage(), page.getPageSize(),0); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		
		return "message/message_index";
	}
	
	////@RequiresAuthentication
	////@RequiresPermissions(value="message_list")
	@RequestMapping(path="manage",method=RequestMethod.GET)
	public String manage(Model model,FormPage page){
		
		Page<Message> result = messageService.findMessageReceiveBy(0, null, page.getPage(), page.getPageSize(),0); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		
		return "message/_message";
	}
	
	////@RequiresAuthentication
	////@RequiresPermissions(value="message_list")
	@RequestMapping(path="manage",method=RequestMethod.POST)
	public String manage(MessageSearchForm form,Model model){
		
		Page<Message> result = messageService.findMessageReceiveBy(form.getState(),form.getContent(), form.getPage(), form.getPageSize(),0); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		
		return "message/_message_list";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="notice",method=RequestMethod.GET)
	public String notice(Model model,FormPage page){
		
		Page<Message> result = messageService.findMessageReceiveBy(0, null, page.getPage(), page.getPageSize(),1); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		return "message/_notice";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="notice",method=RequestMethod.POST)
	public String notice(MessageSearchForm form,Model model){
		
		Page<Message> result = messageService.findMessageReceiveBy(form.getState(),form.getContent(), form.getPage(), form.getPageSize(),1); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		
		return "message/_notice_list";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="mymessage",method=RequestMethod.GET)
	public String myMessage(Model model,FormPage page){
		Page<Message> result = messageService.findMyMessageBy(null,page.getPage(),page.getPageSize(),0); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		return "message/_mymessage";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="mymessage",method=RequestMethod.POST)
	public String myMessage(MessageSearchForm form,Model model){
		Page<Message> result = messageService.findMyMessageBy(form.getContent(),form.getPage(),form.getPageSize(),0); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		return "message/_mymessage_list";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="mynotice",method=RequestMethod.GET)
	public String myNotice(Model model,FormPage page){
		Page<Message> result = messageService.findMyMessageBy(null,page.getPage(),page.getPageSize(),1); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		return "message/_mynotice";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="mynotice",method=RequestMethod.POST)
	public String myNotice(MessageSearchForm form,Model model){
		Page<Message> result = messageService.findMyMessageBy(form.getContent(),form.getPage(),form.getPageSize(),1); 
		model.addAttribute("message", result);
		model.addAttribute("pager", new PageInfo<>(result));
		return "message/_mynotice_list";
	}
	
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_send")
	@RequestMapping(path="send",method=RequestMethod.GET)
	public String send(Model model){
		int loginId = UserState.getLoginId();
		model.addAttribute("users", accountService.findIdAndNameAll(loginId));
		return "message/message_send";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_send")
	@RequestMapping(path="send",method=RequestMethod.POST)
	@ResponseBody
	public  ServiceResultBool send(MessageSendForm form){
		Integer[] ids = StringToInt(form.getReceives().split(","));
		messageService.sendMessage(form.getTitle(), form.getContent(), ids, 0);
		return new ServiceResultBool();
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="delete.ajax",method=RequestMethod.POST)
	@ResponseBody
	public  ServiceResultBool send(@RequestParam("id")BigInteger id){
		messageService.remove(id);
		return new ServiceResultBool();
	}
	
	private Integer[] StringToInt(String[] arrs){
		Integer[] ints = new Integer[arrs.length];
		for(Integer i=0;i<arrs.length;i++){
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="show",method=RequestMethod.GET)
	public  String show(@RequestParam("id")BigInteger id,Model model){
		
		
		model.addAttribute("model", messageService.show(id));
		
		return "message/show";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="message_list")
	@RequestMapping(path="setread.ajax",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResult<Integer> setReaded(@RequestParam("id")BigInteger id){
		
		messageService.setReaded(id);
		Integer result = messageService.getUnreadCount();
		return new ServiceResult<Integer>(result);
		
	}
	
	@RequestMapping(path="unreadCount.ajax",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResult<Integer> readCount(){
		Integer result = messageService.getUnreadCount();
		return new ServiceResult<Integer>(result);
	}
	
	@RequestMapping(path="remove.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> removeAll(@RequestParam(value="id",required=true)String id){
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isEmpty(id)){
			map.put("error", 1);
			map.put("msg", "请选择！");
			return map;
		}
		String[] ids=id.split(",");
		List<String> lists=new ArrayList<String>();
		for(String s:ids){
			lists.add(s);
		}
		int  a=messageService.removeAllById(lists);
		if(a>0){
			map.put("error", 0);
			map.put("msg", "请选择！");
		}else{
			map.put("error", 1);
			map.put("msg", "删除失败！");
		}
		return map;
	}
}
