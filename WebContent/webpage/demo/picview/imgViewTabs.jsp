<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:tabs id="tt" iframe="false" tabPosition="bottom">
 <t:tab iframe="demoController.do?smallslider&turn=picview/smallslider" icon="icon-search" title="焦点图" id="auto"></t:tab>
 <t:tab href="demoController.do?autoupload&turn=picview/imageListView" icon="icon-search" title="多图预览" id="default"></t:tab>
</t:tabs>

