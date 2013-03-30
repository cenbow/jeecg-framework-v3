<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:dategrid name="userList" title="用户管理" actionUrl="userController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="用户名" sortable="false" field="userName" width="20" query="true"></t:dgCol>
   <t:dgCol title="部门" field="TSDepart_departname"></t:dgCol>
   <t:dgCol title="真实姓名" field="realName" query="true"></t:dgCol>
   <t:dgCol title="状态" sortable="true" field="status" replace="正常_1,禁用_0,超级管理员_-1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="szqm(id)" title="设置签名" />
   <t:dgDelOpt title="删除" url="userController.do?del&id={id}&userName={userName}" />
   <t:dgToolBar title="用户录入" icon="icon-add" url="userController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="用户编辑" icon="icon-edit" onclick="update('用户编辑','userController.do?addorupdate')"></t:dgToolBar>
  </t:dategrid>
  </div>
  </div>
 <script type="text/javascript">
	function szqm(id) {
		createwindow('设置签名', 'userController.do?addsign&id=' + id);
	}
</script>
