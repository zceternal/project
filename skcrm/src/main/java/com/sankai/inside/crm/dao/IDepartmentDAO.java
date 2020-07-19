package com.sankai.inside.crm.dao;

import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.DeptAndManagerDTO;
import com.sankai.inside.crm.entity.UpDownDTO;


public interface IDepartmentDAO {
	
	/*
	 * 查询
	 * */
	public List<Department> selectBy(String val);
	
	/*
	 * 刪除部門之前先確認是否有子節點
	 * 
	 * */
	public Integer selectNumByPid(Integer id);
	
	/*
	 * 删除
	 * */
	public int delete(int id);
	
	/*
	 * 新增
	 * */
	public Integer insert(Department department);
	
	/*
	 * 检查是否有重复部门名称*/
	public Boolean existsByDepartmentName(String val);
	
	/*
	 * 更新*/
	public Integer update(Department department);
	
	/*
	 * 数据实体*/
	public Department selectById(int id);
	
	/*
	 * 获取全部部门
	 * */
	public List<Department> selectAll();
	
	/*
	 * 树形菜单  部门
	 * */
	public List<Department> selectAllTree(String deptIds);
	
	/*
	 * 树形菜单  部门员工 
	 * */
	public List<Department> selectAllUserTree(Integer deptId);
	
	public String returnName(String pid);
	/*
	 * 查询order
	 * */
	public int selectOrder(String pid);
	/*
	 * up 上移
	 * 根据id查出order
	 * */
	public int selectOrderById(int id);
	/*
	 * up 上移
	 * 
	 * */
	public int up(UpDownDTO dto);
	public int upT(UpDownDTO dto);
	
	public int down(UpDownDTO dto);
	public int downT(UpDownDTO dto);
	
	/*
	 * 检查是否移动到最低端或者最顶端
	 * */
	public int exectOrder(UpDownDTO dto);
	
	/**
	 * 获取name,state根据id
	 * @param id
	 * @return
	 */
	public Department findDeptById(int id);
	/**
	 * 查到小于自身ORDER的其他所有目标(用于删除)
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<UpDownDTO>  findDeptForDelete(UpDownDTO dto) throws Exception;
	/**
	 * 修改order (用于删除)
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer updateOrderForDelete(UpDownDTO dto) throws Exception;

	public List<String> findAuthById(int id);
	
	/**
	 * 获取部门和部门领导人信息
	 * @return
	 */
	public List<DeptAndManagerDTO> getDeptAndManager();
}
