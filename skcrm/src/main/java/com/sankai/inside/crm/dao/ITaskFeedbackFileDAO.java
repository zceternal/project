package com.sankai.inside.crm.dao;

import com.sankai.inside.crm.entity.TaskFeedbackFile;

public interface ITaskFeedbackFileDAO {

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int insert(TaskFeedbackFile record);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int insertSelective(TaskFeedbackFile record);


    /**
     *
     * @mbggenerated 2020-08-02
     */
    TaskFeedbackFile selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int updateByPrimaryKeySelective(TaskFeedbackFile record);

    /**
     *
     * @mbggenerated 2020-08-02
     */
    int updateByPrimaryKey(TaskFeedbackFile record);
}