<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>版本维护</title> 
		<t:base type="jquery,easyui,tools"></t:base>
	</head>		 
	<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true" action="systemController.do?saveVersion" >
	   <input type="hidden" id="btn_sub" class="btn_sub"/>	  			 
	  	<fieldset class="step">
	  	<legend>版本添加</legend>
	  	 <div class="form">
						<label class="form">
							版本名称:
						</label>
						<input name="versionName" id="versionName" class="easyui-validatebox" value="">
					</div>		 				  					
					  <div class="form">
						<label class="form">
							版本代码:
						</label>
						<input name="versionCode" id="versionCode" class="easyui-validatebox" value="">
					</div>					 	
          </fieldset>
		</t:formvalid>			 			  
	</body>
</html>

