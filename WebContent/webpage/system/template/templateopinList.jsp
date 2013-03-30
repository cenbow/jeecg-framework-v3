<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>模版集合</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body>
  <t:dategrid name="templateopinList" title="模版列表" actionUrl="templateController.do?templateopinList" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="描述" field="descript" width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt url="templateController.do?deltemplateopin&id={id}" title="删除"></t:dgDelOpt>
  </t:dategrid>
  <div id="templateopinListtb" style="padding: 5px; height: 30px">
   <div style="float: left;">
    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('用户录入','templateController.do?addorupdateopin','templateController.do?saveopin')">意见录入</a>
    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('用户编辑','templateController.do?addorupdateopin','id')">编辑</a>
   </div>
  </div>
 </body>
</html>
