<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>菜单信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript">
	$(function() {
		$('#cc').combotree({
			url : 'functionController.do?setPFunction'
		});
	});
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="functionController.do?saveFunction">
  <input type="hidden" name="functionOrder" value="${function.functionOrder}">
   <input name="id" type="hidden" value="${function.id}">
   <fieldset class="step">
    <div class="form">
     <label class="Validform_label">
      菜单名称:
     </label>
     <input name="functionName" class="inputxt" value="${function.functionName}" datatype="s4-10">
     <span class="Validform_checktip">菜单名称范围4~10位字符,且不为空</span>
    </div>
     <div class="form">
    <label class="Validform_label">
      菜单等级:
     </label>
     <select name="functionLevel"  onchange="showfunction(this)"  datatype="*">
      <option value="0" <c:if test="${function.functionLevel eq 0}">selected="selected"</c:if>>
       一级菜单
      </option>
      <option value="1" <c:if test="${function.functionLevel eq 1}">selected="selected"</c:if>>
       二级菜单
      </option>
     </select>
     <span class="Validform_checktip"></span>
    </div>
     <div class="form" id="pfun">
     <label class="Validform_label">
      父菜单:
     </label>
     <input id="cc" name="TSFunction.id" value="${function.TSFunction.id}">
    </div>
    <div class="form" id="funurl">
     <label class="Validform_label">
      菜单地址:
     </label>
     <input name="functionUrl" class="inputxt" value="${function.functionUrl}">
    </div>
      <div class="form">
     <label class="Validform_label">
      图标名称:
     </label>
     <select name="TSIcon.id">
      <c:forEach items="${iconlist}" var="icon">
       <option value="${icon.id}" <c:if test="${icon.id==function.TSIcon.id }">selected="selected"</c:if>>
        ${icon.iconName}
       </option>
      </c:forEach>
     </select>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
