<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:dategrid name="versionList" title="版本管理" actionUrl="systemController.do?versionList" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="版本名称" field="versionName" width="100"></t:dgCol>
   <t:dgCol title="版本编码" field="versionCode"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt url="systemController.do?delVersion&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="添加版本" icon="icon-add" url="systemController.do?addversion" funname="add"></t:dgToolBar>
  </t:dategrid>
</div>
</div>
  