<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:dategrid name="parameterList" title="参数列表" actionUrl="templateController.do?datagridParam&templateid=${templateid}" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="参数名称" field="parametername"></t:dgCol>
   <t:dgCol title="参数表达式" field="parametercount" width="100"></t:dgCol>
   <t:dgCol title="参数标签" field="parametertag"></t:dgCol>
   <t:dgCol title="描述" field="parameterremark" width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>
   <t:dgDelOpt url="templateController.do?delParam&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="templateController.do?addorupdateParam&templateid=${templateid}" funname="add"></t:dgToolBar>
   <t:dgToolBar title="参数编辑" icon="icon-edit" url="templateController.do?addorupdateParam&templateid=${templateid}" funname="update"></t:dgToolBar>
  </t:dategrid>
   </div>
  </div>
