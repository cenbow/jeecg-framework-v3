<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.0.min.js"></script>
<LINK rel="stylesheet" href="plug-in/smallslider/css/lab.css" type="text/css"></LINK>
<LINK rel="stylesheet" href="plug-in/smallslider/css/smallslider.css" type="text/css"></LINK>
<SCRIPT type="text/javascript" src="plug-in/smallslider/js/jquery.smallslider.js"></SCRIPT>
<script type="text/javascript">
	//焦点图
	$(document).ready(function() {
		$('#flashbox').smallslider({
			onImageStop : false,
			switchEffect : 'ease',
			switchEase : 'easeOutBounce',
			switchPath : 'up',
			switchMode : 'hover',
			textSwitch : 2,
			textPosition : 'top',
			textAlign : 'center'
		});
	});
</script>
<div id="flashbox" class="smallslider">
 <ul>
  <c:forEach items="${picList}" var="pic" varStatus="stauts">
   <li>
    <a href="#" onclick=""><img width="320" height="200" src="commonController.do?viewFile&subclassname=${pic.subclassname}&contentfield=pictureIndex&fileid=${pic.id}" title="" alt="${pic.documentTitle}" /> </a>
   </li>
  </c:forEach>
 </ul>
</div>
