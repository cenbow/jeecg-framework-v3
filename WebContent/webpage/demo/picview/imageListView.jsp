<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <script type="text/javascript">
	function loadimg(data) {
		showcontent(data.rows[0].id,data.rows[0].subclassname);
	}
	function showcontent(v, scn) {
		$('#pic').attr("src", "commonController.do?openViewFile&contentfield=pictureIndex&subclassname="+scn+"&fileid=" + v);
	}
	function selectrow(rowIndex, rowData) {
		showcontent(rowData.id,rowData.subclassname);
	}
</script>

  <div class="easyui-layout" fit="true">
   <div region="east" title="图片预览" style="padding: 3px; width: 550px" split="true">
   <t:dategrid onLoadSuccess="loadimg" onClick="selectrow"  name="documentList"  actionUrl="systemController.do?documentList&typecode=news" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="类名" field="subclassname" hidden="false"></t:dgCol>
   <t:dgCol title="图片名称" field="documentTitle" width="20"></t:dgCol>
  </t:dategrid>
   </div>
   <div region="center" style="width: 550px;">
    <div class="easyui-panel" title="预览区域" style="padding: 10px;" fit="true" border="false" id="function-panel">
     <iframe name="pic" id="pic" scrolling="no" frameborder="0" style="width: 100%; height: 99%;"></iframe>
    </div>
   </div>
  </div>

