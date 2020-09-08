package com.sankai.inside.crm.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sankai.inside.crm.core.utils.PropertiesUtil;
import com.sankai.inside.crm.entity.DictOrderDTO;
import com.sankai.inside.crm.entity.MonitorSysinfo;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.SysDict;
import com.sankai.inside.crm.entity.SysDictListDTO;
import com.sankai.inside.crm.entity.SysDictSearchDTO;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.service.SystemInfoService;
import com.sankai.inside.crm.web.core.UserState;

@Controller
@RequestMapping("setting")

public class SettingController {
	
	@Autowired
	private SystemInfoService systemInfoService;
	@Resource
	private ISysDictService sysDictService;
	
	@Resource
	private AccountService accountService;
	
	/**
	 * 维护平台规则
	 */
	@RequestMapping(value="plat_rule",method=RequestMethod.GET)
	public ModelAndView platRule() {
		ModelAndView mav=new ModelAndView();
		PropertiesUtil.KEY_NAME="PLATFORM_RULE";
		try {
			String value = PropertiesUtil.getValueByKey();
			mav.addObject("content",value);
		} catch (Exception e) {
			mav.addObject("content", "读取数据失败，请联系系统管理员！");
		}
		mav.setViewName("setting/plat_rule");
		return mav;
	}
	/**
	 * 维护平台规则
	 */
	@RequestMapping(value="plat_rule",method=RequestMethod.POST)
	public ModelAndView platRule(String content) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("setting/plat_rule_content");
		mav.addObject("content", content);
		
		PropertiesUtil.KEY_NAME="PLATFORM_RULE";
		PropertiesUtil.modifyValueByKey(content);
		return mav;
	}
	
	@RequestMapping(value = "index",method = RequestMethod.GET)
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	public String index(HttpServletRequest request, Model model){
		List<MonitorSysinfo> result = systemInfoService.getInfo(request);
		
		try {
			//获取所有的1级字典
			List<SysDictListDTO> dictParentList=this.sysDictService.listAllByPidSearch(0, null);
			//列出每个父级字典下的所有字典列表
			Iterator<SysDictListDTO> iter=dictParentList.iterator();
			//List<List<SysDictListDTO>> allDicts=new ArrayList<List<SysDictListDTO>>();
			List<Map<String,Object>> allDictMap=new ArrayList<Map<String,Object>>();
			while(iter.hasNext()){
				Map<String,Object> map=new HashMap<String,Object>();
				SysDictListDTO vo=iter.next();
				int pid=vo.getId();
				List<SysDictListDTO> dictList=this.sysDictService.listAllByPidSearch(pid, null);
				map.put("pid", pid);
				map.put("dictList", dictList);
				allDictMap.add(map);
				
			}
			model.addAttribute("dictParentList", dictParentList);//父级
			model.addAttribute("allDictsMap", allDictMap);//子级
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("resultList", result);
		return "setting/index";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	@RequestMapping(value="searchDict",method=RequestMethod.POST)
	public ModelAndView searchDict(SysDictSearchDTO dto) {
		ModelAndView mav=new ModelAndView();
		Integer pid=dto.getPid();
		String keyWord=dto.getKeyWord();
		//获取所有的1级字典
		try {
			List<SysDictListDTO> dictList=this.sysDictService.listAllByPidSearch(pid, keyWord);
			
			mav.addObject("allDicts", dictList);
			mav.addObject("pid", pid);
			mav.setViewName("setting/dict_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView addPre(SysDict vo) {
		ModelAndView mav=new ModelAndView();
		Integer pid=vo.getParentId();
		mav.addObject("parentId", pid);
		mav.setViewName("setting/add_dict");
		
		return mav;
	}
	/**
	 * 增加字段动作
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	public ServiceResult<SysDict> add(SysDict vo) throws Exception {
		ServiceResult<SysDict> result=new ServiceResult<SysDict>();
	
			boolean checkExists=this.sysDictService.checkExists(vo.getId(), vo.getParentId(), vo.getName());
			if(checkExists){
				result.setMsg("名称已存在");
				result.setSuccess(false);
				return result;
			}
	
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		vo.setCreateTimeStr(sdf.format(date));
		vo.setCreateTime(date);
		String loginName=null;
		try {
			loginName=this.accountService.getLoginNameX(UserState.getLoginId());
			vo.setCreateName(loginName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(vo.getName()==null||vo.getName().equals("")){
			result.setMsg("名称不能为空");
			result.setSuccess(false);
			return result;
		}
		try {
			if(this.sysDictService.Insert(vo)){
				result.setSuccess(true);
				result.setMsg("新增成功");
				result.setData(vo);
				
			}else{
				result.setSuccess(false);
				result.setMsg("新增失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("e160312");
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	/**
	 * 打开修改框
	 * @param vo
	 * @return
	 */
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	@RequestMapping(value="updateDict",method=RequestMethod.GET)
	public ModelAndView updateDictPre(SysDict vo) {
		ModelAndView mav=new ModelAndView();
		Integer id=vo.getId();
		try {
			vo=this.sysDictService.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("dict", vo);
		mav.addObject("id", id);
			mav.setViewName("setting/update_dict");
		
		return mav;
	}
	/**
	 * 执行修改动作
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateDict",method=RequestMethod.POST)
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	public ServiceResult<SysDict> updateDict(SysDict vo) {
		ServiceResult<SysDict> result=new ServiceResult<SysDict>();
		try {
			boolean checkExists=this.sysDictService.checkExists(vo.getId(), vo.getParentId(), vo.getName());
			if(checkExists){
				result.setMsg("名称已存在");
				result.setSuccess(false);
				return result;
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			result.setMsg("error0603141407");
			return result;
		}
		if(vo.getName()==null||vo.getName().equals("")){
			result.setMsg("名称不能为空");
			result.setSuccess(false);
			return result;
		}
		if(vo.getValue()==null){
			result.setMsg("值不能为空");
			result.setSuccess(false);
			return result;
		}
		try {
			if(this.sysDictService.updateDict(vo)){
				result.setMsg("修改成功");
				result.setSuccess(true);
				result.setData(vo);
			}else{
				result.setMsg("修改失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("error160314");
			result.setSuccess(false);
			e.printStackTrace();
			return result;
		}
		
		
		return result;
	}
	/**
	 * 执行删除动作
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteDict",method=RequestMethod.POST)
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	public ServiceResult<SysDict> deleteDict(SysDict vo) {
		ServiceResult<SysDict> result=new ServiceResult<SysDict>();
		Integer id=vo.getId();
		try {
			if(this.sysDictService.doRemove(id, vo.getOrder(), vo.getParentId())){
				result.setMsg("删除成功");
				result.setSuccess(true);
				result.setData(vo);
			}else{
				result.setMsg("修改失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("error1603141158");
			result.setSuccess(false);
			e.printStackTrace();
			return result;
		}
		return result;
	}
	/**
	 * 上移/下移
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="move",method=RequestMethod.POST)
	//@RequiresAuthentication
	//@RequiresPermissions(value="system_mamage")
	public ServiceResultBool up(DictOrderDTO vo) {
		ServiceResultBool result=new ServiceResultBool();
		
		Integer id=vo.getId();
		try {
			if(this.sysDictService.updateOrder(id, vo.getiOrder(), vo.getPid(), vo.getMove())){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				if(vo.getMove()>0){
					result.setMsg("已到最底层");
				}else{
					result.setMsg("已到最顶层");
					
				}
			}
			
		} catch (Exception e) {
			result.setMsg("操作失败");
			result.setSuccess(false);
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	
	
	
}
