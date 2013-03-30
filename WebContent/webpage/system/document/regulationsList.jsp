<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:dategrid name="regulationsList" title="技术文档" actionUrl="systemController.do?documentList&typecode=regulations" idField="id" fit="true">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="标题" field="documentTitle" width="20" query="true"></t:dgCol>
 <t:dgCol title="创建时间" field="createdate"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgFunOpt funname="edit(id)" title="修改"></t:dgFunOpt>
 <t:dgDelOpt url="systemController.do?delDocument&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="法规录入" icon="icon-add" onclick="add('文档录入','systemController.do?addRegulations')"></t:dgToolBar>
</t:dategrid>

<script type="text/javascript">
	function edit(id) {
		createwindow('修改', 'systemController.do?addRegulations&id=' + id, 1);
	}
</script>
