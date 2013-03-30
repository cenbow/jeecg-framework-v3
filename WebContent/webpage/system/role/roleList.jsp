<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true" >
 <div region="center" style="padding: 1px;">
  <t:dategrid name="roleList" title="角色列表" actionUrl="roleController.do?roleGrid" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="角色编码" field="rolecode"></t:dgCol>
   <t:dgCol title="角色名称" field="roleName" width="50" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="30"></t:dgCol>
   <t:dgDelOpt url="roleController.do?delRole&id={id}" title="删除"></t:dgDelOpt>
   <t:dgFunOpt funname="setfunbyrole(id,roleName)" title="权限设置"></t:dgFunOpt>
   <t:dgToolBar title="角色录入" icon="icon-add" url="roleController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="角色编辑" icon="icon-edit" url="roleController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:dategrid>
 </div>
 <div region="east" style="width: 200px;" split="true">
  <div tools="#tt" class="easyui-panel" title="权限设置" style="padding: 10px;" fit="true" border="false" id="function-panel">
  </div>
 </div>
 <div id="tt">
  <a href="#" class="icon-save" onclick="mysubmit();"></a>
 </div>
</div>
<<script type="text/javascript">
function setfunbyrole(id,roleName) {
	$("#function-panel").panel({title :roleName+":当前权限"});
	$('#function-panel').panel("refresh", "roleController.do?fun&roleId=" + id);
}
</script>
