package com.sankai.inside.crm.dao;

import com.sankai.inside.crm.entity.*;
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

    /** 任务列表 */
    List<Task> selectList(TaskListSearch search);
    /** 任务是否已经分享过某人 */
    Integer checkExists(TaskShareExistsCheck ckeck);
    /** 批量新增任务共享 */
    Integer insertTaskShare(List<TaskShare> list);
    /**
     * 获取最近一条记录
     * @param customerId
     * @return
     */
    Task getLastTask(@Param("customerId") int customerId);
}