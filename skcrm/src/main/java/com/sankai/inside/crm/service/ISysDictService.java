package com.sankai.inside.crm.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.sankai.inside.crm.entity.Dict;
import com.sankai.inside.crm.entity.DictEasy;
import com.sankai.inside.crm.entity.SysDict;
import com.sankai.inside.crm.entity.SysDictListByRecordTypeDTO;
import com.sankai.inside.crm.entity.SysDictListDTO;

public interface ISysDictService {
	/**
	 * 根据pid获取所有的内容
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<SysDict> findAllByPid(Integer pid) throws Exception;
	public List<SysDict> findAllByPid2(Integer pid) throws Exception;

	/**
	 * 根据pid列表+查询
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<SysDictListDTO> listAllByPidSearch(int pid,String keyWord) throws Exception;
	/**
	 * 增加
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean Insert(SysDict vo) throws Exception;
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
	@CacheEvict(value="dictCache",key="#vo.getId()")
	public boolean updateDict(SysDict vo) throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@CacheEvict(value="dictCache",key="#id")
	public boolean doRemove(Integer id,Integer iOrder,Integer pid) throws Exception;

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
	@CacheEvict(value="dictCache",key="#id")
	public boolean deleteDict(int id) throws Exception;
	/**
	 * 查看是否存在 用于修改删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean checkExists(Integer id,Integer pid,String name) throws Exception;

	/**
	 * 上下移动
	 * @param iOrder
	 * @param pid
	 * @param move
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrder(Integer id,Integer iOrder,Integer pid,Integer move) throws Exception;



	public List<Dict> getDictByType(int type);


	@Cacheable(value="dictCache")
	public Dict getDictById(int id);

	/**
	 * 根据父级Id 获取所有子项集合
	 * @return
	 */
	public List<DictEasy> getDictByPid(int parentId);
}
