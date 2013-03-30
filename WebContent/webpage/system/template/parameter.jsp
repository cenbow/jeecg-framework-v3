<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>参数信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="templateController.do?saveParam">
   <input name="id" type="hidden" " value="${parameter.id }">
   <input name="templateid" type="hidden" " value="${templateid}">
   <fieldset class="step">
    <div class="form">
     <label class="Validform_label">
      参数名称:
     </label>
     <input name="parametername" class="inputxt" value="${parameter.parametername}" datatype="*3-50">
     <span class="Validform_checktip">参数名称在3~50位字符</span>
    </div>
    <div class="form">
     <label class="Validform_label">
      表达式:
     </label>
     <input name="parametercount" class="inputxt" value="${parameter.parametercount}" datatype="*3-50">
     <span class="Validform_checktip">表达式在3~50位字符</span>
    </div>
    <div class="form">
     <label class="Validform_label">
      参数类型:
     </label>
     <SELECT name="parametertag">
      <OPTION value="dtp" <c:if test="${parameter.parametertag eq 'dtp'}">selected</c:if>>
       普通
      </OPTION>
      <OPTION value="each" <c:if test="${parameter.parametertag eq 'each'}">selected</c:if>>
       循环
      </OPTION>
     </SELECT>
    </div>
    <div class="form">
     <label class="Validform_label">
      备注:
     </label>
     <input name="parameterremark" class="inputxt" value="${parameter.parameterremark}">
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
