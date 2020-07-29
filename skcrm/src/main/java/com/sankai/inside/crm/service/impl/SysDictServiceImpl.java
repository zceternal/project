package com.sankai.inside.crm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.core.utils.PropertiesUtil;
import com.sankai.inside.crm.dao.IDictDAO;
import com.sankai.inside.crm.dao.ISysDictDAO;
import com.sankai.inside.crm.entity.CheckDictExistsDTO;
import com.sankai.inside.crm.entity.Dict;
import com.sankai.inside.crm.entity.DictEasy;
import com.sankai.inside.crm.entity.DictOrderDTO;
import com.sankai.inside.crm.entity.SysDict;
import com.sankai.inside.crm.entity.SysDictListByRecordTypeDTO;
import com.sankai.inside.crm.entity.SysDictListDTO;
import com.sankai.inside.crm.entity.SysDictSearchDTO;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.web.core.UserState;
import org.springframework.util.CollectionUtils;

@Service
public class SysDictServiceImpl implements ISysDictService {
	@Resource
	private ISysDictDAO sysDictDAO;
	@Resource
	private IDictDAO dictDAO;

	/** 字典集合-查询下一级 */
	@Override
	public List<SysDict> findAllByPid(Integer pid) throws Exception {
		return this.sysDictDAO.findAllByPid(pid);
	}

	/** 字典集合-级联查询子父级 */
	@Override
	public List<SysDict> findAllByPid2(Integer pid) throws Exception {
		List<SysDict> result = new ArrayList<>();
		SysDictSearchDTO dto=new SysDictSearchDTO();
		dto.setPid(pid);
		List<SysDictListDTO> dictList = this.sysDictDAO.findAllByPidSearch2(dto);
		if (CollectionUtils.isEmpty(dictList)) {
			return result;
		}
		List<SysDict> tempList = new ArrayList<>();
		for (SysDictListDTO sysDictListDTO : dictList) {
			SysDict dict = new SysDict();
			BeanUtils.copyProperties(sysDictListDTO, dict);
			tempList.add(dict);
		}

		for (SysDict dict : tempList) {
			if (Objects.equals(dict.getParentId(), pid)) {
				dict.setChildList(getChilds(tempList,dict.getId()));
				result.add(dict);
			}
		}
		return result;
	}

	/**
	 * 级联遍历
	 * @param allList
	 * @param id
	 * @return
	 */
	private List<SysDict> getChilds(List<SysDict> allList,int id) {
		List<SysDict> collect = allList.stream().filter(x -> x.getParentId().equals(id)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(collect)) {
			return new ArrayList<>();
		}
		for (SysDict dict : collect) {
			dict.setChildList(getChilds(allList,dict.getId()));
		}
		return collect;
	}

	@Override
	public List<SysDictListDTO> listAllByPidSearch(int pid,String keyWord) throws Exception {
		SysDictSearchDTO dto=new SysDictSearchDTO();
		dto.setPid(pid);
		if(keyWord!=null){
			dto.setKeyWord(keyWord+"%");
		}
		List<SysDictListDTO> dictList = null;
		if (Objects.equals(dto.getPid(), 0)) {
			dictList = this.sysDictDAO.findAllByPidSearch(dto);
		}else{
			dictList = this.sysDictDAO.findAllByPidSearch2(dto);
		}
		return dictList;
	}
	@Override
	public boolean Insert(SysDict vo) throws Exception {
		vo.setCreateId(UserState.getLoginId());
		String pycode=Pinyin4jUtil.getPinYinHeadChar(vo.getName());
		String pyname=Pinyin4jUtil.getPinYin(vo.getName());
		Integer newOrder=this.sysDictDAO.getNewOrder(vo.getParentId());
		if(newOrder==null){
			newOrder=1;
		}
		vo.setOrder(newOrder);
		vo.setPycode(pycode);
		vo.setPyname(pyname);
		int i=this.sysDictDAO.insert(vo);
		return i>0;
	}
	@Override
	public SysDict findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.sysDictDAO.findById(id);
	}
	@Override
	public List<SysDictListByRecordTypeDTO> findByRecordType(String recordType) throws Exception {
		// TODO Auto-generated method stub
		return this.sysDictDAO.findByRecordType(recordType);
	}
	@Override
	public boolean updateDict(SysDict vo) throws Exception {
		String pycode=Pinyin4jUtil.getPinYinHeadChar(vo.getName());
		String pyname=Pinyin4jUtil.getPinYin(vo.getName());
		vo.setPycode(pycode);
		vo.setPyname(pyname);
		int i=this.sysDictDAO.updateDict(vo);
		//updateCustomerPoolProp(vo.getId(),vo.getValue());
		return i>0;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public boolean doRemove(Integer id,Integer iOrder,Integer pid) throws Exception {
		DictOrderDTO dto=new DictOrderDTO();
		dto.setiOrder(iOrder);
		dto.setPid(pid);
		List<DictOrderDTO> list=this.sysDictDAO.findDictForDelete(dto);
		Iterator<DictOrderDTO> iter=list.iterator();
		boolean flag1=true;
		boolean flag2=false;
		while(iter.hasNext()){
			DictOrderDTO vo=iter.next();
			vo.setNewOrder(vo.getiOrder()-1);
			if(this.sysDictDAO.updateOrderForDelete(vo)==0){
				flag1=false;
				break;
			}
		}
		int i=this.sysDictDAO.doRemove(id);
		flag2=i>0;
		return flag1&&flag2;
	}
	@Override
	public Integer getNewOrder(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		return this.sysDictDAO.getNewOrder(pid);
	}
	@Override
	public boolean deleteDict(int id) throws Exception {

		Integer i=this.sysDictDAO.deleteDict(id);
		return i>0;
	}
	@Override
	public boolean checkExists(Integer id,Integer pid,String name) throws Exception {
		CheckDictExistsDTO dto=new CheckDictExistsDTO();
		dto.setId(id);
		dto.setPid(pid);
		dto.setName(name);
		return this.sysDictDAO.checkExists(dto)>0;
	}


	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public boolean updateOrder(Integer id,Integer iOrder, Integer pid, Integer move) throws Exception {
		DictOrderDTO dto=new DictOrderDTO();
		boolean flag1=false;
		boolean flag2=false;

		dto.setId(id);
		dto.setNewOrder(iOrder+move);
		dto.setPid(pid);

		Integer brotherId=this.sysDictDAO.getBrotherId(dto);
		if(brotherId==null){
			return false;
		}
		dto.setiOrder(iOrder);
		dto.setBrotherId(brotherId);
		Integer i=this.sysDictDAO.updateBrotherOrder(dto);
		flag1=i>0;

		Integer ii=this.sysDictDAO.updateSelfOrder(dto);
		flag2=ii>0;

		return flag1&&flag2;
	}

	public List<Dict> getDictByType(int type){

		return this.sysDictDAO.findDictAllByType(type);
	}

	public Dict getDictById(int id){
		return this.sysDictDAO.findDictById(id);
	}

	/**
	 * 修改客户应用配置文件:
	 * /customer_pool.properties
	 * @throws Exception
	 */
	private void updateCustomerPoolProp(int id,String value) throws Exception{

		switch (id) {
			case 38:
				PropertiesUtil.KEY_NAME= "START_FOLLOW";
				break;
			case 39:
				PropertiesUtil.KEY_NAME= "FACE_TO_FACE";
				break;
			case 40:
				PropertiesUtil.KEY_NAME= "PURCHASE_INTENTION";
				break;
			case 41:
				PropertiesUtil.KEY_NAME= "QUOTED_CUS";
				break;
			case 42:
				PropertiesUtil.KEY_NAME= "NEGOTIATIONS_CUS";
				break;
			default:
				PropertiesUtil.KEY_NAME= "";
				return;
		}
		if(StringUtils.isNullOrEmpty(value))value = "7";//值为空：默认为
		PropertiesUtil.modifyValueByKey(value);
	}
	@Override
	public List<DictEasy> getDictByPid(int parentId) {
		return dictDAO.selectDictByPid(parentId);
	}

}
