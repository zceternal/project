-- ----------[20200726]-----------
-- 1、新增数据字典
-- 1.1数据字典新增 “产品及服务”
INSERT INTO sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (9, '0', '产品及服务', 0, 2, 53, sysdate(), 0, '产品及服务', NULL, NULL);
-- 1.2数据字典新增 “客户出钱性质”
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (10, '0', '客户出钱性质', 0, 2, 53, sysdate(), 0, '客户出钱性质', NULL, NULL);
-- 1.3销售推进状态
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (11, '0', '销售推进状态', 0, 2, 53, sysdate(), 0, '销售推进状态', NULL, NULL);
-- 1.4联系人来源
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (12, '0', '联系人来源', 0, 2, 53, sysdate(), 0, '联系人来源', NULL, NULL);
-- 1.5任务象限
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (13, '0', '任务象限', 0, 2, 53, sysdate(), 0, '任务象限', NULL, NULL);
-- 1.6任务状态
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (14, '0', '任务状态', 0, 2, 53, sysdate(), 0, '任务状态', NULL, NULL);
-- 1.7任务性质
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (15, '0', '任务性质', 0, 2, 53, sysdate(), 0, '任务性质', NULL, NULL);

-- 2、客户表新增字段
ALTER TABLE `sys_customer`
ADD COLUMN `buy_service` varchar(20) NULL COMMENT '产品及服务' AFTER `recommender`,
ADD COLUMN `cus_source` varchar(20) NULL COMMENT '客户来源' AFTER `type`,
ADD COLUMN `cus_source_type` varchar(20) NULL COMMENT '客户来源类型：1渠道；2直销' AFTER `cus_source`,
ADD COLUMN `synopsis` varchar(500) NULL COMMENT '客户简介' AFTER `cus_source_type`,
ADD COLUMN `business` varchar(20) NULL COMMENT '客户行业' AFTER `synopsis`,
ADD COLUMN `pay_nature` varchar(20) NULL COMMENT '客户出钱性质' AFTER `business`,
ADD COLUMN `follow_state` varchar(20) NULL COMMENT '销售推进状态' AFTER `pay_nature`,
ADD COLUMN `follow_state_details` varchar(100) NULL COMMENT '销售推进状态-明细（多个之间用 逗号 分割）' AFTER `follow_state`;
-- 3、客户-人际关系图表
CREATE TABLE `sys_customer_relations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `channel_partner` varchar(20) DEFAULT NULL COMMENT '渠道伙伴',
  `trust_person` varchar(20) DEFAULT NULL COMMENT '客户信任人',
  `decision_person` varchar(20) DEFAULT NULL COMMENT '客户决策人',
  `manage_person` varchar(20) DEFAULT NULL COMMENT '客户管理人',
  `handle_person` varchar(20) DEFAULT NULL COMMENT '客户办事人',
  `professional_person` varchar(20) DEFAULT NULL COMMENT '客户业务人',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户-人际关系图表';
-- 4、联系人表新增字段
ALTER TABLE `sys_contact`
ADD COLUMN `source` varchar(10) NULL COMMENT '联系人来源' ,
ADD COLUMN `referrer_person` varchar(20) NULL COMMENT '推荐人名称',
ADD COLUMN `buy_service` varchar(20) NULL COMMENT '产品及服务';
-- 5、新增表结构-任务表
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_type` varchar(10) DEFAULT NULL COMMENT '客户类型',
  `customer_id` int(11) DEFAULT NULL COMMENT '现有客户id',
  `company_interior` varchar(20) DEFAULT NULL COMMENT '公司内部',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供应商',
  `next_plan` varchar(500) DEFAULT NULL COMMENT '下一步工作计划',
  `plan_standard` varchar(200) DEFAULT NULL COMMENT '计划标准',
  `plan_executor_user` varchar(30) DEFAULT NULL COMMENT '计划执行人（内部员工），多个用逗号分隔',
  `plan_executor_contact` varchar(30) DEFAULT NULL COMMENT '计划执行人（联系人），多个用逗号分隔',
  `execute_way` varchar(20) DEFAULT NULL COMMENT '告知执行人方式，多个用逗号分隔',
  `quadrant` varchar(20) DEFAULT NULL COMMENT '任务象限',
  `status` varchar(20) DEFAULT NULL COMMENT '任务状态',
  `source` int(2) DEFAULT NULL COMMENT '任务来源：1我指派的；2被指派的',
  `back_time` timestamp NULL DEFAULT NULL COMMENT '计划反馈时间',
  `back_way` int(2) DEFAULT NULL COMMENT '反馈方式',
  `top` int(2) DEFAULT '0' COMMENT '是否置顶：0否；1是',
  `task_nature` varchar(20) DEFAULT NULL COMMENT '任务性质',
  `assign_person` varchar(20) DEFAULT NULL COMMENT '指派者',
  `assign_time` timestamp NULL DEFAULT NULL COMMENT '指派时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `modify_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='任务表';
-- 6、新增表结构-任务反馈表
CREATE TABLE `task_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL COMMENT '任务id',
  `content` varchar(500) DEFAULT NULL COMMENT '反馈内容',
  `summary` varchar(200) DEFAULT NULL COMMENT '小结',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务反馈表';
-- 7、新增表结构-任务反馈附件表
CREATE TABLE `task_feedback_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_feedback_id` int(11) DEFAULT NULL COMMENT '任务反馈id',
  `file_name` varchar(150) DEFAULT NULL COMMENT '附件名称',
  `file_path` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标记：0正常；-1删除；',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务反馈记录附件表';
-- 8、任务共享表
CREATE TABLE `task_share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL COMMENT '任务id',
  `account_id` int(11) DEFAULT NULL COMMENT '分享人id',
  `allow_account_id` int(11) DEFAULT NULL COMMENT '允许人id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标记：0正常，-1删除；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务共享表';


