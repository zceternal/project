package com.sankai.inside.crm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;



import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;
import com.sankai.inside.crm.core.utils.MathUtil;
import com.sankai.inside.crm.core.utils.TransformResult;
import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.IAuthorityDAO;
import com.sankai.inside.crm.dao.IDepartmentDAO;
import com.sankai.inside.crm.entity.Authority;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.DeptAndManagerDTO;
import com.sankai.inside.crm.entity.Module;
import com.sankai.inside.crm.entity.ModuleItem;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.UpDownDTO;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.DepartmentForm;
import com.sun.mail.imap.protocol.Item;

@Service
public class DepartmentService {
	public static Logger logger = LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private IDepartmentDAO departmentDAO;

	@Autowired
	private IAuthorityDAO authorityDAO;
	
	@Autowired
	private IAccountDAO accountDAO;

	public ServiceResult<Page<Department>> getDepartmentsByPage(String content, int page, int pageSize) {
		
		Page<Department> list = new Page<Department>();
		PageHelper.startPage(page, pageSize, true);
		/*if(loginId<=0)
		{*/
			list = (Page<Department>) departmentDAO.selectBy(content);
		/*}
		else
		{
			list = (Page<Department>) departmentDAO.selectBy(deptId.toString());
		}*/
		return new ServiceResult<Page<Department>>(list);
	}

	public ServiceResultBool remove(int id) {

		if (departmentDAO.selectNumByPid(id) > 0) {

			return new ServiceResultBool("删除失败,请先删除下属部门");
		}
		Department dept = this.departmentDAO.selectById(id);

		UpDownDTO dto = new UpDownDTO();
		dto.setOrder(dept.getOrder());
		dto.setPid(dept.getPid());
		try {
			List<UpDownDTO> targetList = this.departmentDAO.findDeptForDelete(dto);
			Iterator<UpDownDTO> iter = targetList.iterator();
			while (iter.hasNext()) {
				UpDownDTO deleteTarget = iter.next();
				deleteTarget.setOrder(deleteTarget.getOrder() - 1);
				this.departmentDAO.updateOrderForDelete(deleteTarget);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = departmentDAO.delete(id);
		if (result == 0)
			return new ServiceResultBool("删除失败，请需要删除的数据已不存在");
		return new ServiceResultBool();
	}

	public ServiceResult add(DepartmentForm form, List<String> authKey)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ServiceResult result = new ServiceResult();

		if (departmentDAO.existsByDepartmentName(form.getName()))
			return new ServiceResult("部门已存在");

		Department department = new Department();
		PropertyUtils.copyProperties(department, form);

		department.setName(form.getName());
		department.setRemark(form.getRemark());
		department.setCreateId(UserState.getLoginId());// 以后完善
		department.setCreateTime(new Date());
		department.setPid(form.getPid());
		// int order=departmentDAO.selectOrder(String.valueOf(form.getPid()));
		department.setOrder((departmentDAO.selectOrder(String.valueOf(form.getPid()))) + 1);
		Integer i = departmentDAO.insert(department);
		List<Authority> insertList = new ArrayList<>();
		if (i > 0) {
			// 返回值用於js取值用
			DepartmentForm dept = new DepartmentForm();
			dept.setId(department.getId());
			dept.setName(form.getName());
			for (String key : authKey) {
				insertList.add(new Authority(1, department.getId(), key));
			}
			if (insertList.size() != 0) {
				this.authorityDAO.insertBatch(insertList);
			}

			result.setData(dept);
			result.setSuccess(true);// 返回值

		} else {
			result.setSuccess(false);
		}

		return result;
	}

	public ServiceResult<Department> getDepartment(int id) {

		Department department = departmentDAO.selectById(id);

		if (department == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");

		return new ServiceResult<Department>(department);

	}

	@CacheEvict(value = "departmentServiceGetDeptById", key = "#model.getId()")
	public ServiceResult save(DepartmentForm model)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ServiceResult result = new ServiceResult();
		Department entity = departmentDAO.selectById(model.getId());
		if (!(model.getName().equals(entity.getName())) && departmentDAO.existsByDepartmentName(model.getName())) {
			return new ServiceResult("部门已存在");
		}
		Department department = new Department();

		PropertyUtils.copyProperties(department, model);
		department.setName(model.getName());
		department.setRemark(model.getRemark());
		Integer i = departmentDAO.update(department);
		if (i > 0) {
			DepartmentForm d = new DepartmentForm();
			d.setId(department.getId());
			d.setName(model.getName());
			result.setData(d);
			result.setSuccess(true);
		}
		return result;
	}

	/*
	 * 树形菜单 部门
	 */
	public List<Department> selectAllTree() {
		String deptIds = UtilService.getDeptChilds();
		if (deptIds.equals("-1")) {
			return departmentDAO.selectAll();
		}
		List<Department> list = departmentDAO.selectAllTree(deptIds);
		return list;
	}
	
	/*
	 * 树形菜单 部门  员工
	 */
	public List<Department> selectUserAllTree() {
		String deptIds = UtilService.getDeptChilds();
		List<Department> depts = null;
		List<Department> account = null; //把用户当做部门来查
		if (deptIds.equals("-1")) {
			depts = departmentDAO.selectAll();
		}else{
			depts = departmentDAO.selectAllTree(deptIds);
		};
		
		
		
		Department[] ite =  depts.toArray(new Department[depts.size()]);
		for (Department depit : ite) {
			account=departmentDAO.selectAllUserTree(depit.getId());//根据部门Id，把用户当做部门来查
			//Department[] user =  account.toArray(new Department[depts.size()]);
			for (Department department : account) {
				Department dep = new Department();
				dep.setId(department.getId() * 100000);//用户Id
				dep.setName(department.getName());//用户名字
				dep.setPid(department.getPid());//用户部门
				dep.setUser(true);
				depts.add(dep);
			}
			
		}

		
		return depts;
	}

	/*
	 * 获取全部部门
	 */
	public List<Department> selectAll() {
		List<Department> list = departmentDAO.selectAll();
		return list;
	}

	public String returnName(String pid) {
		String name;
		if (pid.equals("0")) {
			name = "部门管理";
		} else {
			name = departmentDAO.returnName(pid);
		}
		return name;
	}

	/*
	 * type 1是上移 2是下移
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ServiceResult<Integer> updateOrder(int id, int pid, int type) {
		// ServiceResultBool result=new ServiceResultBool();

		int order = departmentDAO.selectOrderById(id);

		int exectOrder;

		if (type == 1) {
			// 上移的时候如果order==0，则在顶端\
			UpDownDTO par = new UpDownDTO();
			par.setPid(pid);
			par.setOrder(order - 1);
			exectOrder = departmentDAO.exectOrder(par);
			if (exectOrder <= 0)
				return new ServiceResult<>("已经上移最顶级");

			UpDownDTO dto = new UpDownDTO();
			dto.setPid(pid);
			dto.setOrder(order);
			dto.setId(id);

			int data = departmentDAO.upT(dto);
			if (data > 0) {
				int da = departmentDAO.up(dto);

			}
		} else if (type == 2) {
			UpDownDTO par = new UpDownDTO();
			par.setPid(pid);
			par.setOrder(order + 1);
			exectOrder = departmentDAO.exectOrder(par);
			if (exectOrder <= 0)
				return new ServiceResult<>("已经上移最后一级");

			UpDownDTO dto = new UpDownDTO();
			dto.setPid(pid);
			dto.setOrder(order);
			dto.setId(id);

			int data = departmentDAO.downT(dto);
			if (data > 0) {
				int da = departmentDAO.down(dto);

			}
		}
		return new ServiceResult<>(id);
	}

	public Module[] getModuleByDept(int deptId, Module[] result) throws IOException {

		
		List<Authority> checkedAuth = authorityDAO.selectByDept(deptId);

		for (Module module : result) {

			int index = 0;

			for (ModuleItem item : module.getItems()) {

				if (checkedAuth.stream().filter(x -> x.getModuleKey().equals(item.getKey())).count() > 0) {

					item.setChecked(true);
					index++;
				}
			}
			if (module.getItems().size() > 0 && module.getItems().size() == index) {

				module.setChecked(true);

			}
		}
		return result;
	}

	@Cacheable(value = "departmentServiceGetDeptById")
	public Department getDeptById(int id) {

		return this.departmentDAO.findDeptById(id);

	}
	
	public List<String> getAuthByDeptId(int id){
		List<String> result = new ArrayList<>();
		result = departmentDAO.findAuthById(id);
		logger.info("id:" + id + "getAuthByDeptIdCount：" + (result != null ? result.size() : 0));
		return result;
	}

	public ServiceResultBool saveAuth(List<String> authKey, int deptId) {

		List<String> source = this.authorityDAO.selectByDept(deptId).stream().map(x -> x.getModuleKey())
				.collect(Collectors.toList());

		TransformResult result = MathUtil.Transform(source, authKey);

		if (result.getNewList().size() > 0) {
			List<Authority> insertList = new ArrayList<>();
			for (String val : result.getNewList()) {

				Authority m = new Authority();
				m.setType(1);
				m.setQuoteId(deptId);
				m.setModuleKey(val);
				insertList.add(m);
			}

			this.authorityDAO.insertBatch(insertList);
		}

		if (result.getDeleList().size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("quoteId", deptId);
			map.put("list", result.getDeleList());
			map.put("type", 1);
			this.authorityDAO.deleteBatch(map);
		}
		return new ServiceResultBool();
	}
	

	/*
	 * 获取所有部门
	 */
	public List<Department> getAll() {
		return departmentDAO.selectAll();
	}
	/**
	 * 获取部门和部门领导人信息
	 * @return
	 */
	public List<DeptAndManagerDTO> getDeptAndManagerList(){
		return departmentDAO.getDeptAndManager();
	}
}
