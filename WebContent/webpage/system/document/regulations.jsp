<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>技术文档</title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript" charset="UTF-8" src="plug-in/kindeditor/kindeditor.js"></script>
  <script type="text/javascript" src="plug-in/kindeditor/lang/zh_CN.js"></script>
  <script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="attachmentcontent"]', {
			filterMode : false,
			items : [ 'undo', 'redo', '|', 'print', 'code', 'cut', 'copy', 'paste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat' ]
		});
	});
	function sync(curform) {
		editor.sync();
		return false;
	}
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="sync" action="systemController.do?saveRegulations">
    <input name="id" name="id" type="hidden" value="${regulations.id }">
    <fieldset class="step">
     <legend>
      技术文档
     </legend>
     <div class="form">
      <label class="form">
       标题名称:
      </label>
      <input name="documentTitle" id="documentTitle" value="${regulations.documentTitle }" datatype="s3-50" errormsg="范围在3~50位字符!">
      <span class="Validform_checktip">标题名称在3~50位字符,且不为空</span>
     </div>
     <div class="form">
      <label class="form">
       内容:
      </label>
      <textarea id="attachmentcontent" name="attachmentcontent" style="width: 100%;">
	   ${attachmentcontent}
	 </textarea>
      <br>
     </div>
    </fieldset>
  </t:formvalid>
 </body>
</html>
