
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>${sessionScope.organization.organizaName }</title>
  <base>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript">
	jq(function() {
		jq('#departid').tree({
			url : 'departController.do?setPFunction',
			onClick : function(node) {
				jq('#function-panel').panel("refresh", "userController.do?chooseUser&departid=" + node.id);
			}
		});
		jq('#tt').tree({
			animate : true,
			onClick : function(node) {
				jq('#function-panel').panel("refresh", "userController.do?chooseUser&roleid=" + node.id);
			}
		});
	});
</script>
 </head>
 <body class="easyui-layout">
  <!-- 左侧-->
  <div region="west" split="true" title="导航菜单" style="width: 150px; padding: 1px;">
   <div id="nav" class="easyui-accordion" fit="true" border="false">
    <div title="按部门选择" icon="icon-save" style="overflow: auto; padding: 10px;" selected="true">
     <ul id="departid"></ul>
    </div>
    <div title="按角色选择" icon="icon-reload" style="padding: 10px;">
     <ul id="tt" class="easyui-tree">
      <c:forEach items="${roleList}" var="role">
       <li id="${role.id}">
        <span>${role.rolename }</span>
       </li>
      </c:forEach>
     </ul>
    </div>
   </div>
  </div>
  <!-- 中间-->
  <div id="maintt" region="center" style="overflow: hidden; width: 130px">
   <div class="easyui-panel" style="padding: 10px;" fit="true" border="false" id="function-panel">
    <t:dategrid name="userList" title="用户管理" actionUrl="userController.do?datagridUser&roleid=${roleid }&departid=${departid}" idField="userid">
     <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
     <t:dgCol title="用户名" field="username" width="100"></t:dgCol>
    </t:dategrid>
   </div>
  </div>
 </body>
</html>