package jeecg.system.controller.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSBaseBus;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSTable;
import jeecg.system.pojo.base.TSType;
import jeecg.system.pojo.base.TSTypegroup;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.Tstamp;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 数据库操作处理类
 * 
 */
@Controller
@RequestMapping("/dbController")
public class DataBaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DataBaseController.class);
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 数据表分组页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tableTypeTabs")
	public ModelAndView tableTypeTabs(HttpServletRequest request) {
		Integer tableSize = systemService.getAllDbTableSize();
		List<TSTable> tsTableList = systemService.loadAll(TSTable.class);
		if (tableSize > tsTableList.size()) {
			TSTypegroup tsTypegroup = systemService.getTypeGroup(Globals.TypeGroup_Database,"数据表");
			TSType actType=systemService.getType(Globals.DataBase_Activiti, "工作流引擎表", tsTypegroup);
			TSType baseType=systemService.getType(Globals.DataBase_Base, "系统基础表", tsTypegroup);
			TSType busType=systemService.getType(Globals.DataBase_Bus, "业务表", tsTypegroup);
			TSType proType=systemService.getType(Globals.DataBase_Process, "自定义引擎表", tsTypegroup);
			List<DBTable> dbTables = systemService.getAllDbTableName();
			for (DBTable dbTable : dbTables) {
				TSTable tsTable = new TSTable();
				tsTable.setEntityName(dbTable.getEntityName());
				tsTable.setTableName(dbTable.getTableName());
				tsTable.setEntityKey(dbTable.getTableTitle().substring(dbTable.getTableTitle().lastIndexOf("_")+1));
				if (dbTable.getTableName().indexOf(Globals.DataBase_Activiti) >= 0) {
					tsTable.setTSType(actType);
				}
				if (dbTable.getTableName().indexOf(Globals.DataBase_Base) >= 0) {
					tsTable.setTSType(baseType);
				}
				if (dbTable.getTableName().indexOf(Globals.DataBase_Bus) >= 0) {
					tsTable.setTSType(busType);
				}
				if (dbTable.getTableName().indexOf(Globals.DataBase_Process) >= 0) {
					tsTable.setTSType(proType);
				}
				TSTable oldTable = systemService.findUniqueByProperty(TSTable.class, "tableName", dbTable.getTableName());
				if (oldTable == null) {
					systemService.save(tsTable);
				}
			}
		}
		TSTypegroup tsTypeList=systemService.findUniqueByProperty(TSTypegroup.class,"typegroupcode",Globals.TypeGroup_Database);
		request.setAttribute("tsTypeList", tsTypeList.getTSTypes());
		return new ModelAndView("database/tableTypeTabs");
	}

	/**
	 * 数据表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tableList")
	public ModelAndView tableList(HttpServletRequest request) {
		String typeid=oConvertUtils.getString(request.getParameter("typeid"));
		TSType type=systemService.getEntity(TSType.class,typeid);
		request.setAttribute("type",type);
		return new ModelAndView("database/tableList");
	}
	/**
	 * 数据表编辑跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "aouTable")
	public ModelAndView aouTable(TSTable table,HttpServletRequest req) {
		table=systemService.getEntity(TSTable.class,table.getId());
		req.setAttribute("table",table);
		return new ModelAndView("database/datatable");
	}
	/**
	 * 数据表编辑
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveTable")
	@ResponseBody
	public AjaxJson saveTable(TSTable table, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(table.getId())) {
			message = "数据表: " + table.getTableName() + "更新成功";
			systemService.saveOrUpdate(table);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "数据表: " + table.getTableName() + "添加成功";
			systemService.save(table);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		return j;
	}

	/**
	 * 数据表请求数据
	 */
	@RequestMapping(params = "tableGrid")
	public void tableGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String typeid=oConvertUtils.getString(request.getParameter("typeid"));
		CriteriaQuery cq = new CriteriaQuery(TSTable.class, dataGrid);
		cq.eq("TSType.id",typeid);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
}
