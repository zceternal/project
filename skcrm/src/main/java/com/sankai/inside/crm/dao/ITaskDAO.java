package com.sankai.inside.crm.dao;

import com.sankai.inside.crm.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITaskDAO {

    /**
     *
     * @mbggenerated 2020-08-01
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2020-08-01
     */
    int insert(Task record);

    /**
     *
     * @mbggenerated 2020-08-01
     */
    int insertSelective(Task record);


    /**
     *
     * @mbggenerated 2020-08-01
     */
    Task selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2020-08-01
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     *
     * @mbggenerated 2020-08-01
     */
    int updateByPrimaryKey(Task record);
}