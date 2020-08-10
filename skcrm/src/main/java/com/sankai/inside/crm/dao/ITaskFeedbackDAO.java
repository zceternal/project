package com.sankai.inside.crm.dao;

import com.sankai.inside.crm.entity.TaskFeedback;

public interface ITaskFeedbackDAO {

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int insert(TaskFeedback record);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int insertSelective(TaskFeedback record);


    /**
     *
     * @mbggenerated 2020-08-02
     */
    TaskFeedback selectByPrimaryKey(Integer id);


    /**
     *
     * @mbggenerated 2020-08-02
     */
    int updateByPrimaryKeySelective(TaskFeedback record);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int updateByPrimaryKey(TaskFeedback record);
}