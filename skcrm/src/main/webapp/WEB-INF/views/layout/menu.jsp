<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
		.left_slide{
		background: url(content/img/left_slide_bg.png) no-repeat;
		outline:none;
		border:none;
		position: fixed;
		top: 50%;
		margin-top: -50px;
		width: 13px;
		height: 103px;
		left: 166px;
		display: none;
	}
    .left_slide_show{
    	background:url(content/img/left_slide_on_bg.png) no-repeat !important;
    	background-position: 0px 0px;
    	left: 56px;
    }
    .left_menu:hover .left_slide{
    	display: block;
    }
</style>
<div>
	<ul>

		<!-- <li class="on center"><a href="javascript:;" data-id="main"
			data-link="home/content_welcome"><i class="icon_account"></i><span>我的主页</span></a></li> -->
		<li class="on center"><a href="javascript:;" data-id="main"
			data-link="home/content"><i class="icon_account"></i><span>我的主页</span></a></li>
			
	<%-- 	<shiro:hasPermission name="message_list">	
		<li class="center"><a href="javascript:;" data-id="message"
				data-link="message/index"><i class="icon_orgzmain"></i><span>我的消息</span></a></li>
		</shiro:hasPermission>		 --%>

		<shiro:hasPermission name="customer_list">
			<li class="center"><a href="javascript:;" data-id="customer"
				data-link="customer/index.html"><i class="icon_num"></i><span>客户管理</span></a></li>
		</shiro:hasPermission>
		
			<shiro:hasPermission name="contact_list">
			<li class="center"><a href="javascript:;" data-id="contact"
				data-link="contact/index"><i class="icon_multisite"></i><span>联系人管理</span></a></li>
		</shiro:hasPermission>
		
		 <shiro:hasPermission name="highseas_list">
			<li class="center"><a href="javascript:;" data-id="highSeas"
				data-link="highseas/index.html"><i class="icon_livemanage"></i><span>公海管理</span></a></li>
		</shiro:hasPermission> 
		
		<shiro:hasPermission name="cusRecord_index">
		<li class="center"><a href="javascript:;" data-id="setting"
				data-link="cusRecord/index"><i class="icon_location_gps"></i><span>处理记录查询</span></a></li>
		</shiro:hasPermission>
		
		 <!-- <li class="center"><a href="javascript:;" data-id="customerImport"
				data-link="customerImport/upload_excel"><i class="icon_multisite"></i><span>客户数据导入</span></a></li>
		 -->
		
		<shiro:hasPermission name="analysis_index">
		<li class="center"><a href="javascript:;" data-id="setting"
				data-link="analysis/index"><i class="icon_membercost"></i><span>智能分析</span></a></li>
		
		</shiro:hasPermission>
		
		<li class="center"><a href="javascript:;" data-id="setting"
				data-link="setting/plat_rule"><i class="icon_device"></i><span>平台规则</span></a></li>
		
		<c:if test="${accountInfo.id<0}">
		<%-- <shiro:hasPermission name="dept_mamage"> --%>
			<li class="center"><a href="javascript:;" data-id="department"
				data-link="department/index.html"><i class="icon_site"></i><span>部门管理</span></a></li>
		<%-- </shiro:hasPermission> --%>

		<%-- <shiro:hasPermission name="account_mamage"> --%>
			<li class="center"><a href="javascript:;" data-id="account"
				data-link="account/index.html"><i class="icon_mem"></i><span>账号管理</span></a></li>
		<%-- </shiro:hasPermission> --%>
		
		<%-- <shiro:hasPermission name="group_index"> --%>
			<li class="center"><a href="javascript:;" data-id="group"
				data-link="group/index.html"><i class="icon_elec_fence"></i><span>群组管理</span></a></li>
		<%-- </shiro:hasPermission> --%>				
		<%-- <shiro:hasPermission name="system_mamage"> --%>
			<li class="center"><a href="javascript:;" data-id="setting"
				data-link="setting/index"><i class="icon_center"></i><span>平台设置</span></a></li>
		<%-- </shiro:hasPermission> --%>
		</c:if>
	</ul>
	<div class="left_slide" onclick="SlideLeft(this)" >
	</div>
</div>
