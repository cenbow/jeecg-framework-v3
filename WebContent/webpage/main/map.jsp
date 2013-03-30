<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/context/mytags.jsp"%>
<html>
	<head>
		<title>地图</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<script src="plug-in/OpenLayers-2.11/lib/OpenLayers.js"></script>
		<script src="plug-in/OpenLayers-2.11/myOpenlayers.js"></script>
		<link rel="stylesheet" type="text/css"
			href="plug-in/OpenLayers-2.11/theme/map.css">
		<script src="plug-in/iColorPicker/js/iColorPicker.js"></script>
	</head>


	<body class="easyui-layout" onload="init();" style="visibility: visible;">
		 <div id="mainPanles" region="center" style="overflow-y:hidden">
			<div id="panel" class="customEditingToolbar"></div>
			<div id="map">
			</div>
		</div>
		<div region="east"  title="综合查询" split="true" style="width: 290px;">

			<div id="tabs" class="easyui-tabs" border="false"  fit="true">

				<div title="结果" iconCls="icon-search" id="result"
					style="padding: 1px;">

					<div class="easyui-panel" title="操作信息"
						style="height: 20px; width: 280px; padding: 10px;">

						<div id="message" style="color: #0099FF;">

						</div>
					</div>


					<div iconCls="icon-search" style="padding: 1px;">
						<div id=mapQueryListtb>
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-search" onclick="dingwei();">定位</a>
						</div>

						<t:dategrid width="280" fit="false" showPageList="false" showRefresh="false" showText="false" checkbox="true" name="mapQueryList"
							title="查询结果" actionUrl="mapController.do?querymap" idField="gid">
							<t:dgCol title="gid" field="gid" hidden="false"></t:dgCol>
							<t:dgCol title="项目" field="name"></t:dgCol>
						</t:dategrid>
					</div>
				</div>


				<div title="查询" iconCls="icon-search" id="search"
					style="padding: 2px; overflow:hidden;">
					<div class="easyui-panel" title="基本选择"
						style="height: 10px; padding: 10px; width: 280px;">
						<tr align="left">
							<td style="width: 100px">
								<h10 style="color: #0099FF;">
								图层:
								</h10>
								<select id="tcxz" name="tcxz" onchange="selectTuCeng(this.value)">
									<option value="t_projectdetial">
										地下室
									</option>
									<option value="m_building">
										房屋
									</option>
								</select>
							</td>
						</tr>
					</div>
					<div id="aa" title="分类查询" class="easyui-accordion" 
					style="width: 280px; height:25px" fit="false">
						<div iconCls="icon-search" title="关键字查询"
							style="overflow:hidden; padding: 5px;">
							<div id="query1">
								<table class="table-edit" width="100%">
									<form id="ff" method="post" novalidate>
										<tr align="left">
											<td style="width: 50px">
												<h4 style="color: #0099FF;">
													关键字:
												</h4>
											</td>
											<td>
												<input class="easyui-validatebox" style="width: 120px"
													id="param" name="param" textField="text">
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="datagrid-toolbar">
													<a href="#" class="easyui-linkbutton" plain="true"
														iconCls="icon-search" onclick="queryTuCeng($('#tcxz').val());">查询</a>
												</div>
											</td>
										</tr>
									</form>
								</table>
							</div>
						</div>

						<div iconCls="icon-search" title="任意点周边查询" style="padding: 0px;">
							<div id="query1">

								<div class="datagrid-toolbar">
								<input type="hidden" id="biaodiangeo" name="biaodiangeo"
										value="">
									<a href="#" class="easyui-linkbutton" plain="true"
										iconCls="icon-add" onclick="bufferActive('dian');">标点</a>
								</div>
								<table class="table-edit" width="100%">

									<tr>
										<td>
											<h4 style="color: #0099FF;">
												状态：
											</h4>
										</td>
										<td>
											<div style="color: #0099FF;" id="biaodian">
												未标点
											</div>
										</td>
									</tr>
									<tr align="left">
										<td style="width: 50px">

											<h4 style="color: #0099FF;">
												范围：
											</h4>
										</td>
										<td>
											<input type="text" name="fanwei" min="0" precision="2"
												style="width: 120px" required="true" id="fanwei" value="100">

										</td>
									</tr>

								</table>
								<div class="datagrid-toolbar">
									<a href="#" class="easyui-linkbutton" plain="true"
										iconCls="icon-search" onclick="querenBuffer('dian');">查询</a>
									<a href="#" class="easyui-linkbutton" plain="true"
										iconCls="icon-cancel" onclick="ClearBufferLayer();">清除</a>
								</div>
							</div>
						</div>

						<div iconCls="icon-search" title="任意线周边查询" style="padding: 0px;">
							<div id="query2" value="">
								<div class="datagrid-toolbar">
								<input type="hidden" id="huaxiangeo" name="huaxiangeo" value="">
									<a href="#" class="easyui-linkbutton" plain="true"
										iconCls="icon-add" onclick="bufferActive('xian');">画线</a>
								</div>
								<table class="table-edit" width="100%">

									<tr>
										<td>
											<h4 style="color: #0099FF;">
												状态：
											</h4>
										</td>
										<td>
											<div style="color: #0099FF;" id="huaxian">
												未画线
											</div>
										</td>
									</tr>
									<tr align="left">
										<td style="width: 50px">

											<h4 style="color: #0099FF;">
												范围：
											</h4>
										</td>
										<td>
											<input type="text" name="fanweiLine" min="0" precision="2"
												style="width: 120px" required="true" id="fanweiLine"
												value="100">

										</td>
									</tr>

								</table>
								<div class="datagrid-toolbar">
									<a href="#" class="easyui-linkbutton" plain="true"
										iconCls="icon-search" onclick="querenBuffer('xian');">查询</a>
									<a href="#" class="easyui-linkbutton" plain="true"
										iconCls="icon-cancel" onclick="ClearBufferLayer();">清除</a>
								</div>

							</div>
						</div>
						<div iconCls="icon-search" title="区域查询" style="padding: 1px;">
							<div iconCls="icon-search">
								<t:dategrid width="280" fit="false" showPageList="false" showRefresh="false" showText="false"  name="regionList" title="区域查询"
									actionUrl="mapController.do?queryRegion" idField="gid">
									<t:dgCol title="gid" field="gid" hidden="false"></t:dgCol>
									<t:dgCol title="区域" field="name"></t:dgCol>
								</t:dategrid>
							</div>
						</div>
					</div>
				</div>

				<div title="标注" id="biaozhu" iconCls="icon-add"
					style="padding: 0px; overflow: hidden;">
					<div class="easyui-panel" title="标注设置"
						style="height:25px; width: 280px; padding: 10px;">
						<div>
							<img src="images/renfang/map/text.png" class="nodeStyle" 
								onclick="xuanzetubiao('wb','');" />
							<img src="images/renfang/map/flag.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/home.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/bomb.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/world.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							</div>
							<div>
							<img src="images/renfang/map/tux.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/user.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/television.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/telephone.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/printer.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
						</div>
						<div>
							<img src="images/renfang/map/monitor.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
							<img src="images/renfang/map/camera.png" class="nodeStyle"
								onclick="xuanzetubiao('tp',this.src);" />
						    <img src="images/renfang/map/star.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','star');" />
							<img src="images/renfang/map/cross.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','cross');" />
							<img src="images/renfang/map/square.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','square');" />
						</div>
						<div>	
							<img src="images/renfang/map/lightning.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','lightning');" />
							<img src="images/renfang/map/x.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','x');" />
							<img src="images/renfang/map/rectangle.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','rectangle');" />
							<img src="images/renfang/map/triangle.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','triangle');" />
							<img src="images/renfang/map/circle.png" class="nodeStyle"
								onclick="xuanzetubiao('fh','circle');" />
						</div>
					<table class="table-edit"  width="100%">
					  <tr align="left">
							<td style="width: 70px">
							颜色：
							</td>
							<td >
							<input type="text" name="fhys" class="iColorPicker"
								id="fhys" value="#f00">
							</td>
					 </tr>
						<tr>
							<td>
							大小：
							</td>
							<td >
							<input type="text" name="fhdz" id="fhdz" class="easyui-numberbox" min="0" value="6" precision="0">
							</td>
						</tr>
						<tr >
							<td>
							描述：
							</td>
							<td >
							<input type="text" name="markerTitle" id="markerTitle">
							</td>
						</tr>
						<tr align="left">
							<td >
							描述色：
							</td>
							<td >
							<input type="text" name="markeryanse" class="iColorPicker"
								id="markeryanse" value="#f00">
							</td>
						</tr>
						<tr align="left">
							<td >
							字体大小：
							</td>
							<td >
							<input type="text" name="markerztsize" class="easyui-numberbox" min="0" value="24" precision="0"
								id="markerztsize" >
							</td>
						</tr>
						<tr align="left">
							<td >
							偏移位置：
							</td>
							<td >
							x
							<input type="text" name="markerweizhix" class="easyui-numberbox" min="0" value="0" precision="0"
								id="markerweizhix" style="width: 30px">
							y
							<input type="text" name="markerweizhiy" class="easyui-numberbox" min="0" value="0" precision="0"
								id="markerweizhiy" style="width: 30px">
							</td>
						</tr>
						</table>
						<div class="datagrid-toolbar">
							<input type="hidden" id="imgpath" value="">
							<input type="hidden" id="imgType" value="">
							<a href="#" class="easyui-linkbutton" plain="true"
								onclick="addBiaoZhu();">标注</a>
							<a href="#" class="easyui-linkbutton" plain="true"
								onclick="ClearBiaoZhu();">清除标注</a>
						</div>
						<div>
							编辑类型
							<select onchange="editDesign(this.value)">
								<option value="drawLayer">
									规划设计
								</option>
								<option value="lableLayer">
									标注
								</option>
							</select>
						</div>
					</div>
				</div>
				
				<div title="其它" id="qita" iconCls="icon-add"
					style="width: 280px; padding: 1px; overflow: hidden;">
					<div>
						编辑类型
						<select onchange="editDesign(this.value)">
							<option value="drawLayer">
								规划设计
							</option>
							<option value="lableLayer">
								标注
							</option>
						</select>
					</div>
					<div class="datagrid-toolbar">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-add" onclick="baocunpingmu();">保存</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-edit" onclick="bianjipingmu();">编辑</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-cancel" onclick="shanchupingmu();">删除</a>
					</div>
					<div class="fitem">
						<form id="daw" method="post" novalidate>
							<label>
								规划名称：
							</label>
							<input type="hidden" name="draws" id="draws" value="">
							<input type="hidden" name="drawjsonstr" id="drawjsonstr">
							<input type="hidden" name="lablejsonstr" id="lablejsonstr">
							<input type="text" name="drawName" id="drawName"
								style="width: 150px">
					</div>
					<div class="fitem">
						<label>
							备注：
						</label>
						<input type="text" name="note" id="note" style="width: 150px">
					</div>
					</form>
					<div iconCls="icon-search" style="width: 100%">
						<t:dategrid width="280"  fit="false" showPageList="false" showRefresh="false" showText="false" checkbox="true" name="drawList"
							title="规划查询" actionUrl="mapController.do?queryDrawHistory"
							idField="drawid">
							<t:dgCol title="序号" field="drawid" hidden="false"></t:dgCol>
							<t:dgCol title="规划名称" field="drawname"></t:dgCol>
							<t:dgCol title="规划时间" field="drawDate"></t:dgCol>
						</t:dategrid>
					</div>
				</div>

			</div>
		</div>
	</body>
</html>