<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
<t:dategrid name="newsList" title="新闻管理" actionUrl="systemController.do?documentList&typecode=news" idField="id" fit="true">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="标题" field="documentTitle" width="20" query="true"></t:dgCol>
 <t:dgCol title="创建时间" field="createdate"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <!--<t:dgFunOpt funname="edit(id)" title="修改"></t:dgFunOpt>-->
 <t:dgDelOpt url="systemController.do?delDocument&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="新闻录入" icon="icon-add" onclick="add('文件录入','systemController.do?addNews')"></t:dgToolBar>
 <t:dgToolBar title="新闻编辑" icon="icon-edit" onclick="add('新闻编辑','systemController.do?addNews','id')"></t:dgToolBar>
</t:dategrid>
</div>
</div>
<script type="text/javascript">
	function edit(id) {
		createwindow('修改', 'systemController.do?addNews&id=' + id, 1);
	}
</script>
