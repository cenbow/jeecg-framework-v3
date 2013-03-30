<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
<t:dategrid name="templateList" title="模版列表" actionUrl="templateController.do?datagrid" idField="id">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="模版名称" field="templatename" ></t:dgCol>
 <t:dgCol title="模版路径" field="templatepath" width="50"></t:dgCol>
 <t:dgCol title="模版编码" field="templatecode"></t:dgCol>
 <t:dgCol title="操作" field="opt"></t:dgCol>
 <t:dgFunOpt funname="teplateparam(id)" title="模板参数"></t:dgFunOpt>
 <t:dgDelOpt url="attachmentController.do?delTemplate&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="上传模板" icon="icon-add" url="attachmentController.do?template" funname="openuploadwin"></t:dgToolBar>
 <t:dgToolBar title="模版编辑" icon="icon-edit" url="templateController.do?addorupdate" funname="editfs"></t:dgToolBar>
</t:dategrid>
</div>
</div>
<script type="text/javascript">
 function teplateparam(id) {
  addOneTab('模板参数', 'templateController.do?paramList&templateid='+id);
 }
</script>
