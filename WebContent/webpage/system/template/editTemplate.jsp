<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>模版信息</title> 		
		<t:base type="jquery,easyui,tools"></t:base>
     <script type="text/javascript"  charset="UTF-8" src="plug-in/kindeditor/kindeditor.js"></script>
     <script type="text/javascript" src="plug-in/kindeditor/lang/zh_CN.js"></script>  
     <style type="text/css">
     .ke-icon-newPage{
        background-image: url(images/accordion/search.gif);
        background-position: 0px -1200px;
        width: 16px;
        height: 16px;
}   
  .ke-icon-save{
        background-image: url(images/accordion/no.png);
        background-position: 0px -1136px;
        width: 16px;
        height: 16px;
}   
.ke-icon-example3{
        background-image: url(images/accordion/no.png);
        background-position: 0px -1120px;
        width: 16px;
        height: 16px;
}   
     </style>
     <script type="text/javascript">     
     var editor;
			KindEditor.ready(function(K) {
				editor=K.create('textarea[name="templatecontent"]',{
					filterMode:false,
					items:['newPage','save','source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut','copy' ,'paste','|', 'justifyleft', 'justifycenter', 'justifyright',
							'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
							'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', 
							'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
							'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
							'table', 'hr', 'emoticons', 'pagebreak','clearhtml','example3']
							
				});

			});
			KindEditor.lang({
                 newPage : '新建'
               });
			KindEditor.plugin('newPage', function(K) {
		        var self = this, name = 'newPage';
		        // 点击图标时执行
		        self.clickToolbar(name, function() {			        	
		        	window.location.reload();
		        	//清空编辑其中内容	
		         //self.html("");		               
		        });
		   });
			KindEditor.lang({
                save : '保存'
              });
			KindEditor.plugin('save', function(K) {
		        var editor = this, name = 'save';
		        // 点击图标时执行
		        editor.clickToolbar(name, function() {	
		        	ajaxdoSub('templateController.do?save','myform');
		        	});
		        });
			// 自定义插件 #2
			KindEditor.lang({
				example3 : '模版参数列表'
			});
			KindEditor.plugin('example3', function(K) {				
				var self = this, name = 'example3';				
				self.clickToolbar(name, function(){	
					openList('模版参数列表','templateController.do?list&templateid=${templateid}',600,300,'parametername',self);
				});
			});	
			function openList(title, url,width, height,id,self) {
				$.dialog.open(url, {
					title : title,
					lock : true,
					height : height,
					width : width,
					opacity : 0.4,
					button : [ {
						name : '确认',
						callback : function() {	
						iframe = this.iframe.contentWindow;
						var value=iframe.getSelected(id);
						self.insertHtml('<dtp>'+value+'</dtp>');	
						},
						focus : true
					}, {
						name : '取消',
						callback : function() {

						}
					} ]
				});
			}
     </script>
	</head>		
	<body style="overflow-y: hidden" scroll="no">
	<div id="content">
	<div id="wrapper">
	 <div id="steps">
	 <form name="myform" id="myform" action="templateController.do?save"  method="post" >
	   <input type="hidden" id="btn_sub" class="btn_sub"/>
	  <input type="hidden" name="id" value="${templateid}">
	  <textarea id="myself" name="templatecontent" style="width:100%;height:650px;">
	    ${attachmentcontent}
	 </textarea>	
	 </form>
	 </div>
	 </div>
	 </div>	
	</body>
</html>




