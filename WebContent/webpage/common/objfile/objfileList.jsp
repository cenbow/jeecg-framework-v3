<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
	function load(data) {
		showcontent(data.rows[0].id, data.rows[0].subclassname);
	}
	function showcontent(v, scn) {
		$('#pic').attr(
				"src",
				"commonController.do?openViewFile&subclassname=" + scn
						+ "&fileid=" + v);
	}
	function selectrow(rowIndex, rowData) {
		showcontent(rowData.id, rowData.subclassname);
	}
</script>
<body style="overflow-y: hidden" scroll="no">
	<div class="easyui-layout" fit="true">
		<div region="east" style="padding: 3px;" split="true">
			<t:dategrid name="archivedocList" onLoadSuccess="load"
				onClick="selectrow" title="附件列表"
				actionUrl="commonController.do?objfileGrid&businessKey=${businessKey}&subclassname=${subclassname}&typename=${typename}&typecode=${typecode }&filekey=${filekey }"
				idField="id">
				<t:dgCol title="编号" hidden="false" field="id"></t:dgCol>
				<t:dgCol title="类名" field="subclassname" hidden="false"></t:dgCol>
				<t:dgCol title="资料名称" field="attachmenttitle"></t:dgCol>
			</t:dategrid>
		</div>
		<div region="center" style="width: 650px;">
			<div class="easyui-panel" title="预览区域" style="padding: 10px;"
				fit="true" border="false" id="function-panel">
				<iframe name="pic" id="pic" scrolling="no" frameborder="0"
					style="width: 100%; height: 99%;"></iframe>
			</div>
		</div>
	</div>