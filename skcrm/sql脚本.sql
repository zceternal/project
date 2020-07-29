-- ----------[20200726]-----------
-- 1、数据字典新增 “产品及服务”
INSERT INTO sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (9, '0', '产品及服务', 0, 2, 53, sysdate(), 0, '产品及服务', NULL, NULL);
-- 2、数据字典新增 “客户出钱性质”
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (10, '0', '客户出钱性质', 0, 2, 53, sysdate(), 0, '客户出钱性质', NULL, NULL);
-- 3、销售推进状态
INSERT INTO `sys_dict`(`id`, `value`, `name`, `parent_id`, `order`, `create_id`, `create_time`, `state`, `remark`, `pycode`, `pyname`) VALUES (11, '0', '销售推进状态', 0, 2, 53, sysdate(), 0, '销售推进状态', NULL, NULL);
-- 4、客户表新增字段
ALTER TABLE `sys_customer`
ADD COLUMN `buy_service` varchar(20) NULL COMMENT '产品及服务' AFTER `recommender`,
ADD COLUMN `cus_source` varchar(20) NULL COMMENT '客户来源' AFTER `type`,
ADD COLUMN `cus_source_type` varchar(20) NULL COMMENT '客户来源类型：1渠道；2直销' AFTER `cus_source`,
ADD COLUMN `synopsis` varchar(500) NULL COMMENT '客户简介' AFTER `cus_source_type`,
ADD COLUMN `business` varchar(20) NULL COMMENT '客户行业' AFTER `synopsis`,
ADD COLUMN `pay_nature` varchar(20) NULL COMMENT '客户出钱性质' AFTER `business`,
ADD COLUMN `follow_state` varchar(20) NULL COMMENT '销售推进状态' AFTER `pay_nature`,
ADD COLUMN `follow_state_details` varchar(100) NULL COMMENT '销售推进状态-明细（多个之间用 逗号 分割）' AFTER `follow_state`;
-- 5、客户-人际关系图表
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

