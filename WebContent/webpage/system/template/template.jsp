<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <t:base type="jquery,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <t:formvalid formid="formobj" layout="div" dialog="true">
      <fieldset class="step">
       <div class="form">
        <label class="Validform_label">
         模版名称:
        </label>
        <input  class="inputxt"  id="templatename" value="${template.templatename }" datatype="s3-20">
        <span class="Validform_checktip">模版名称在3~20位字符</span>
       </div>
       <div class="form">
       <label class="Validform_label">
         模版编码:
        </label>
        <input class="inputxt" id="templatecode" value="${template.templatecode}" datatype="s3-20">
        <span class="Validform_checktip">模版编码在3~20位字符</span>
       </div>
        <div class="form" id="test">
       </div>
       <div class="form">
        <t:upload name="模版上传" uploader="attachmentController.do?uploadTemplate" extend="office" id="file_upload" formData="templatename,templatecode"></t:upload>
       </div>
      </fieldset>
 </t:formvalid>
 </body>
</html>

