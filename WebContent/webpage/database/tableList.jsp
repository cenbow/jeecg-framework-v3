<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:dategrid name="${type.typecode}List" title="数据表列表" actionUrl="dbController.do?tableGrid&typeid=${type.id }" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="表名称" field="tableName"></t:dgCol>
   <t:dgCol title="实体名称" field="entityName" width="50"></t:dgCol>
    <t:dgCol title="描述" field="tableTitle"></t:dgCol>
  </t:dategrid>
 <div id="${type.typecode}Listtb" style="padding: 3px; height: 25px">
  <div style="float: left;">
   <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','dbController.do?aouTable','id')">编辑</a>
  </div>
 </div>

