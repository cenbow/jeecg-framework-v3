<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
	<head>
		<title>测试页面</title>
		<t:base type="jquery,jqueryui"></t:base>
	</head>
	<body style="overflow-y: hidden" scroll="no">		
		<t:autocomplete searchField="userName" valueField="id" labelField="userName,password" name="ttt" entityName="TSUser"></t:autocomplete>			
	</body>
</html>
