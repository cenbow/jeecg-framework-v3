<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
<t:dategrid name="departList" title="部门列表" actionUrl="departController.do?datagrid" idField="departid">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="部门名称" field="departname"></t:dgCol>
 <t:dgCol title="职能描述" field="description"></t:dgCol>
 <t:dgCol title="操作" field="opt"></t:dgCol>
 <t:dgDelOpt url="departController.do?del&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="部门录入" icon="icon-add" url="departController.do?addorupdate" funname="add"></t:dgToolBar>
 <t:dgToolBar title="部门编辑" icon="icon-edit" url="departController.do?addorupdate" funname="update"></t:dgToolBar>
</t:dategrid>
</div>
</div>
