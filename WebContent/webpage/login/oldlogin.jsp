<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui"></t:base>
<title>系统登录</title>
<style type="text/css">
.header {
	background: url('plug-in/login/images/${version.versionCode}login.png')
		no-repeat;
	background1: #A1C4E4;
	position: relative;
	height: 60px;
	width: 100%;
	padding-left1: 5px;
}

.toptitle {
	color: #dc44dd;
	font-size: 15px;
	font-weight: bold;
}
</style>
<link rel="shortcut icon" href="images/favicon.ico">
</head>
<body style="visibility: visible">
	<div class="easyui-dialog"
		style="width: 500px; height: 300px; background: #fafafa; overflow: hidden"
		title="${organization.organizaName}" closable="false" border="false">
		<div class="header" style="height: 60px;"></div>
		<div class="bg" style="padding: 20px 0;">
			<form name="login" action="userController.do?login" method="post">
				<div style="padding-left: 50px">
					<table cellpadding="0" cellspacing="3">
						<tr>
							<td><img src="images/renfang/231.png" width="40" height="40">
							</td>
							<td class="toptitle">帐号：</td>
							<td><input style="width: 180px" name="userName"
								value="admin"></input></td>
						</tr>
						<tr>
							<td><img src="images/renfang/232.png" width="40" height="40">
							</td>
							<td class="toptitle">密码：</td>
							<td><input style="width: 180px" type="password"
								name="password" value="123"></input></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td><a href="#" class="easyui-linkbutton" iconCls="icon-ok"
								onclick="javascript:document.login.submit();">登录</a> 
								<a href="#"
								class="easyui-linkbutton" iconCls="icon-cancel"
								onclick="javascript:document.forms['login'].reset();">重置</a></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>