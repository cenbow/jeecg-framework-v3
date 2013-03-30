<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统配置信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="configController.do?save">
   <input name="id" type="hidden" " value="${config.id }">
   <fieldset class="step">
    <div class="form">
     <label class="Validform_label" >
      编码:
     </label>
     <input name="code" class="inputxt" <c:if test="${config.code!=null&&config.code!='' }">readonly</c:if>  value="${config.code }" datatype="*1-20">
     <span class="Validform_checktip">编码范围在1~20位字符</span>
    </div>
    <div class="form">
    <label class="Validform_label" >
      名称:
     </label>
     <input name="name" class="inputxt"  value="${config.name }" datatype="*1-20" >
     <span class="Validform_checktip">用户名范围在1~20位字符</span>
    </div>
    <div class="form">
    <label class="Validform_label" >
      值:
     </label>
     <input name="contents" class="inputxt" id="contents" value="${config.contents }" datatype="*1-20">
     <span class="Validform_checktip">值范围在1~20位字符</span>
    </div>
    <div class="form">
     <label class="Validform_label" >
      备注:
     </label>
     <textarea rows="3"  name="note" id="note">${config.note}</textarea>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
