<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>新闻法规</title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript" charset="UTF-8" src="plug-in/kindeditor/kindeditor.js"></script>
  <script type="text/javascript" src="plug-in/kindeditor/lang/zh_CN.js"></script>
  <script type="text/javascript">     
     var editor;
   KindEditor.ready(function(K) {
    editor=K.create('textarea[name="attachmentcontent"]',{
     filterMode:false,
     items:['source', 'undo', 'redo', '|', 'print', 'code', 'cut','copy' ,'paste','|', 'justifyleft', 'justifycenter', 'justifyright',
       'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
       'superscript', 'clearhtml', 'quickformat', 'selectall', '|',
       'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
       'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat']
    });
   });
   function test(curform)
   {
   	editor.sync();
    upload();
    return false;
   }
 
     </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="test">
   <input id="id" name="id" type="hidden" value="${news.id }">
   <fieldset class="step">
    <legend>
     新闻法规
    </legend>
    <div class="form">
     <label class="form">
      标题名称:
     </label>
     <input name="documentTitle" id="documentTitle" value="${news.documentTitle }" datatype="s3-50" errormsg="范围在3~50位字符!">
     <span class="Validform_checktip">标题名称在3~50位字符,且不为空</span>
    </div>
    <div class="form">
     <label class="form">
      内容:
     </label>
     <textarea id="attachmentcontent" name="attachmentcontent" style="width: 100%; height: 250px;">
	    ${attachmentcontent}
	 </textarea>
     <br>
    </div>
    <div class="form" id="test">
    </div>
    <div class="form" id="test">
     <t:upload name="picindex" buttonText="上传焦点图" uploader="systemController.do?saveNews" extend="pic" id="file_upload" formData="id,documentTitle,attachmentcontent"></t:upload>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
