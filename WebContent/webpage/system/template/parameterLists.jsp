<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>参数集合</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:dategrid name="parameterList" title="参数列表" actionUrl="templateController.do?datagridParam&templateid=${templateid}" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="参数名称" field="parametername" width="100"></t:dgCol>
   <t:dgCol title="参数表达式" field="parametercount" width="100"></t:dgCol>
   <t:dgCol title="注释" field="parameterremark" width="100"></t:dgCol>
   <t:dgCol title="参数标签" field="parametertag" width="100"></t:dgCol>
  </t:dategrid>
 </body>
</html>
