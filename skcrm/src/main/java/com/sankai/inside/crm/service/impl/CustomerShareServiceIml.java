package com.sankai.inside.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.ICustomerShareDAO;
import com.sankai.inside.crm.dao.IDepartmentDAO;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.CustomerContact;
import com.sankai.inside.crm.entity.CustomerShareAccDTO;
import com.sankai.inside.crm.entity.CustomerShareCusDTO;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.CustomerShareExistsCheckDTO;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.DeptDTO;
import com.sankai.inside.crm.entity.DeptTable;
import com.sankai.inside.crm.entity.UpdateCustomerShare;
import com.sankai.inside.crm.service.ICustomerShareService;
import com.sankai.inside.crm.web.core.UserState;

@Service
public class CustomerShareServiceIml implements ICustomerShareService {
	@Resource
	private ICustomerShareDAO custoemrShareDAO;

	@Resource
	private IDepartmentDAO departDAO;

	@Resource
	private IAccountDAO accountDAO;

	@Override
	public CustomerShareCusDTO findCusById(Integer cusid) throws Exception {
		// TODO Auto-generated method stub
		return this.custoemrShareDAO.findCusById(cusid);
	}

	@Override
	public List<DeptTable> getDeptTable() throws Exception {
		List<DeptTable> listTable = new ArrayList<DeptTable>();
		DeptTable vo = new DeptTable();
		List<Department> depts = departDAO.selectAll();
		int loginId = UserState.getLoginId();
		List<Account> accounts = accountDAO.selectBy(null).stream().filter(x -> x.getId() != loginId)
				.collect(Collectors.toList());

		// 一级
		List<DeptDTO> list = this.custoemrShareDAO.getDeptsByPid(0);
		Iterator<DeptDTO> iter = list.iterator();
		while (iter.hasNext()) {
			DeptTable table = new DeptTable();
			DeptDTO dto = iter.next();
			// 二级
			List<DeptDTO> list2 = getDeptsByPid(dto.getDeptId(), depts);

			if (list2.size() > 0) {

				Iterator<DeptDTO> level2Iter = list2.iterator();
				while (level2Iter.hasNext()) {
					DeptTable table2 = new DeptTable();
					DeptDTO dtoLevel2 = level2Iter.next();
					table2.setIdLevel1(dto.getDeptId());
					table2.setNameLevel1(dto.getDeptName());

					table2.setIdLevel2(dtoLevel2.getDeptId());
					table2.setNameLevel2(dtoLevel2.getDeptName());

					String deptLevel2Childs = dtoLevel2.getDeptId()+","+getDeptLevel2Childs(dtoLevel2.getDeptId(), depts);
					deptLevel2Childs= deptLevel2Childs.replace(",,", ",");
					table2.setDeptLevel2ChildStr(deptLevel2Childs);

					String[] strArray = deptLevel2Childs.split(",");
					List<String> strList = Arrays.asList(strArray);
					List<CustomerShareAccDTO> users = getAccountsByDeptId(
							strList.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList()), accounts);

					table2.setUsers(users);
					listTable.add(table2);

				}
			} else {
				table.setIdLevel1(dto.getDeptId());
				table.setNameLevel1(dto.getDeptName());
				table.setIdLevel2(dto.getDeptId());
				table.setNameLevel2(dto.getDeptName());
				table.setDeptLevel2ChildStr(dto.getDeptId() + "");
				List<String> strList2 = new ArrayList<String>();

				strList2.add(dto.getDeptId() + "");
				List<CustomerShareAccDTO> users = getAccountsByDeptId(
						strList2.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList()), accounts);
				table.setUsers(users);
				listTable.add(table);

			}

		}
		return listTable;
	}

	private List<CustomerShareAccDTO> getAccountsByDeptId(List<Integer> ids, List<Account> accounts) {
		return accounts.stream().filter(x -> ids.contains(x.getDeptId()))
				.map(x -> new CustomerShareAccDTO(x.getId(), x.getName(), x.getDeptId())).collect(Collectors.toList());

	}

	private List<DeptDTO> getDeptsByPid(int id, List<Department> depts) {

		return depts.stream().filter(x -> x.getPid() == id).map(x -> new DeptDTO(x.getId(), x.getName()))
				.collect(Collectors.toList());

	}

	private String getDeptLevel2Childs(Integer level2Id, List<Department> depts) {
		String str = "";
		// 查找2级以下的级别
		List<DeptDTO> myList = getDeptsByPid(level2Id, depts);
		if (myList.size() > 0) {
			Iterator<DeptDTO> iter = myList.iterator();
			while (iter.hasNext()) {
				DeptDTO dto = iter.next();
				str += dto.getDeptId() + ",";
				str += getDeptLevel2Childs(dto.getDeptId(), depts) + ",";
			}
		} else {
			return level2Id + "";
		}
		return str;

	}

	@Override
	public Integer insertCustomerShare(List<CustomerShareDTO> list) throws Exception {
		Iterator<CustomerShareDTO> iter = list.iterator();
		Date date = new Date();
		//List<CustomerShareDTO> newList = new ArrayList<CustomerShareDTO>();
		while (iter.hasNext()) {
			CustomerShareDTO vo = iter.next();
			Integer maxOrder = this.custoemrShareDAO.getMaxOrderByAllowId(vo.getAllowAccountId());
			if (maxOrder == null) {
				maxOrder = 1;
			}else{
				maxOrder +=1;
			}
			vo.setCreateTime(date);
			vo.setIsOwn(0);
			vo.setOrder(maxOrder);
			vo.setState(0);
			vo.setAccountId(UserState.getLoginId());
			//newList.add(vo);
			this.custoemrShareDAO.insertShare(vo);
		}
			return list.size();
	}

	@Override
	public boolean checkExists(Integer allowAccountId, Integer customerId) throws Exception {
		CustomerShareExistsCheckDTO dto = new CustomerShareExistsCheckDTO();
		dto.setAllowAccountId(allowAccountId);
		dto.setCustomerId(customerId);
		Integer i = this.custoemrShareDAO.checkExists(dto);
		return i > 0;
	}

	@Override
	public List<CustomerContact> contactByCuId(Integer customerId) {
		List<CustomerContact> list = custoemrShareDAO.contactByCuId(customerId);
		return list;
	}

	@Override
	public boolean updateCusIsShare(UpdateCustomerShare share) {
		return custoemrShareDAO.updateCusIsShare(share);
	}

	@Override
	public List<Integer> getIdsByAccId(Integer accId) {
		return custoemrShareDAO.selectIdsByAccId(accId);
	}

	@Override
	public int updateBatchByIds(UpdateCustomerShare dto) {
		return custoemrShareDAO.updateBatchByIds(dto);
	}
}
