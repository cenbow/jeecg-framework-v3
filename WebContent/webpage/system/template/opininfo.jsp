<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>意见模版信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="templateController.do?saveopin">
   <input name="id" type="hidden" value="${tsOpinTemplate.id }">
   <fieldset class="step">
    <legend>
     模版信息
    </legend>
    <div class="form">
     <label class="form">
      描述:
     </label>
     <input name="descript" value="${tsOpinTemplate.descript }" datatype="*1-500" errormsg="用户名范围在2~10位字符之间,且不为空!">
     <span class="Validform_checktip">描述范围1~500位字符之间,且不为空</span>
    </div>
   </fieldset>
   <div id="navigation" style="display: none;">
   </div>
  </t:formvalid>
 </body>