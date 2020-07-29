package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.CheckDictExistsDTO;
import com.sankai.inside.crm.entity.Dict;
import com.sankai.inside.crm.entity.DictOrderDTO;
import com.sankai.inside.crm.entity.SysDict;
import com.sankai.inside.crm.entity.SysDictListByRecordTypeDTO;
import com.sankai.inside.crm.entity.SysDictListDTO;
import com.sankai.inside.crm.entity.SysDictSearchDTO;

public interface ISysDictDAO {


	/**
	 * 根据字典类型查询数据
	 * @param type
	 * @return
	 */
	public List<Dict> findDictAllByType(int type);

	/**
	 * 根据pid获取所有的内容
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> findAllByPid(Integer pid) throws Exception;
	/**
	 * 根据pid列表+查询
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<SysDictListDTO> findAllByPidSearch(SysDictSearchDTO dto) throws Exception;
	public List<SysDictListDTO> findAllByPidSearch2(SysDictSearchDTO dto) throws Exception;
	/**
	 * 增加
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Integer insert(SysDict vo) throws Exception;
	/**
	 * 查询，用于修改
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysDict findById(Integer id) throws Exception;
	/**
	 * 查询，当前登录人的销售记录类型
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<SysDictListByRecordTypeDTO> findByRecordType(String recordType) throws Exception;
	/**
	 * 修改
	 * @param vo
	 * @return
	 * @throws Exception
	 */

	public Integer updateDict(SysDict vo) throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer doRemove(Integer id) throws Exception;
	/**
	 * 获取信的排序值
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public Integer getNewOrder(Integer pid) throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteDict(Integer id) throws Exception;
	/**
	 * 查看是否存在 用于修改删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer checkExists(CheckDictExistsDTO dto) throws Exception;
	/**
	 * 1. 获取移动目标的前一个/后一个目标的ID（移动）
	 * @param pid
	 * @param newOrder
	 * @return
	 * @throws Exception
	 */
	public Integer getBrotherId(DictOrderDTO dto ) throws Exception;
	/**
	 * 2. 修改前一个/后一个目标的order（移动）
	 * @param pid
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public Integer updateBrotherOrder(DictOrderDTO dto) throws Exception;

	/*
	 * 查询name,value,state,根据id
	 */
	public Dict findDictById(int id);
	/**
	 * 3. 修改自身的order（移动）
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer updateSelfOrder (DictOrderDTO dto) throws Exception;
	/**
	 * 查到小于自身ORDER的其他所有目标(用于删除)
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<DictOrderDTO> findDictForDelete (DictOrderDTO dto) throws Exception;
	/**
	 * 修改order (用于删除)
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer updateOrderForDelete (DictOrderDTO dto) throws Exception;

}
