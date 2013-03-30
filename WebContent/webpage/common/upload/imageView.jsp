<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>图片类型附件查看</title>
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.0.min.js"></script>
  <link rel="stylesheet" href="plug-in/iviewer/jquery.iviewer.css" />
  <script type="text/javascript" src="plug-in/iviewer/jqueryui.js"></script>
  <script type="text/javascript" src="plug-in/iviewer/jquery.mousewheel.min.js"></script>
  <script type="text/javascript" src="plug-in/iviewer/jquery.iviewer.js"></script>
  <script type="text/javascript">
	$(document).ready(function() {
		var iv1 = $("#viewer").iviewer({
			src : "commonController.do?viewFile&subclassname=${subclassname}&contentfield=${contentfield}&fileid=${fileid}",
			update_on_resize : true,
			zoom_animation : true,
			mousewheel : true,
			onMouseMove : function(ev, coords) {
				autoHeight();
			},
			onStartDrag : function(ev, coords) {
				
			},
			onDrag : function(ev, coords) {
				
			}
		});
	});
	//调整DIV高度
	function autoHeight() {
		var h = $(window).height();
		var h_old = 300;
		if (h > h_old) {
			$(".viewer").css('height', h-20);
		} else {
			return false;
		}
	}
</script>
  <style>
.viewer {
	width: 99%;
	height: 500px;
	border: 0px solid black;
	position: relative;
	border: 1px solid #ffffff;
    cursor:hand;
}


</style>
 </head>
 <body >
 
   <div id="viewer" class="viewer"></div>
 
 </body>
</html>
