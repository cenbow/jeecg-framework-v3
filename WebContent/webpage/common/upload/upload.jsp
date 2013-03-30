<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>文件上传</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true">
   <input name="roleid" type="hidden" value="${role.id}">
   <fieldset class="step">
    <legend>
     上传信息
    </legend>
    <div class="form">
     <label class="form">
      文件类型:
     </label>
     <select name="typeid" id="typeid" style="width: 190px">
      <c:forEach items="${infotype }" var="type">
       <option value="${type.id}">
        ${type.typename}
       </option>
      </c:forEach>
     </select>
    </div>
    <div class="form">
     <label class="form">
      备注:
     </label>
     <input name="note" id="note" type="text" value="${attachment.note}" />
    </div>
    <div class="form">
     <t:upload name="模版上传" uploader="attachmentController.do?attach&businessKey=${businessKey}" extend="office" id="file_upload" formData="typeid,note"></t:upload>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
