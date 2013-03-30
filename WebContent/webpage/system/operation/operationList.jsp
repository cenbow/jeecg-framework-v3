<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:dategrid name="operationList" title="操作管理" actionUrl="functionController.do?opdategrid" idField="id">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="操作名称" field="operationname" width="100"></t:dgCol>
 <t:dgCol title="代码" field="operationcode"></t:dgCol>
 <t:dgCol title="权限名称" field="TSFunction_functionName"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgDelOpt url="functionController.do?delop&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="操作录入" icon="icon-add" url="functionController.do?addorupdateop" funname="add"></t:dgToolBar>
 <t:dgToolBar title="操作编辑" icon="icon-edit" url="functionController.do?addorupdateop" funname="update"></t:dgToolBar>
</t:dategrid>
