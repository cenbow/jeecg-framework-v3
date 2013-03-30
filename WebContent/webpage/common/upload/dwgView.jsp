<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html >
<HTML>
<HEAD>
<base href="<%=basePath%>">
<title>dwg文件预览</title>
</HEAD>
<BODY >
<table border="0" width="100%">
  <tr>
    <td width="100%">            


<object classid="clsid:6EEC44E0-338B-408A-983E-B43E6F22B929" id="MxDrawXCtrl1"  width=100% height="600" align="left"> 
  <param name="_Version" value="65536">
  <param name="_ExtentX" value="24262">
  <param name="_ExtentY" value="16219">
  <param name="_StockProps" value="0">
  <param name="DwgFilePath" value="<%=basePath %>${realpath}">
  <param name="IsRuningAtIE" value="1">
  <param name="EnablePrintCmd" value="0">
  <param name="ShowCommandWindow" value="1">
  <param name="ShowToolBars" value="1">
  <param name="ShowModelBar" value="0">
  <param name="IniFilePath" value="ResPath=Compare,LoadMrx=Compare.mrx,EnableSingleSelection=Y">
  <param name="ToolBarFiles" value="Compare-ToolBar.mxt">
</object>
  </td>
  </tr>
</table>

</BODY>
</HTML>



















