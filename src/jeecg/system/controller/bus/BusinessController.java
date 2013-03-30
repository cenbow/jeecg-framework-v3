package jeecg.system.controller.bus;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSBusConfig;
import jeecg.system.pojo.base.TSPrjstatus;
import jeecg.system.pojo.base.TSType;
import jeecg.system.pojo.base.TSTypegroup;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.activiti.engine.history.HistoricProcessInstance;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.workflow.model.activiti.ActivitiCom;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.model.activiti.Variable;
import org.jeecgframework.workflow.pojo.activiti.ActRuTask;
import org.jeecgframework.workflow.pojo.bus.TBBormoney;
import org.jeecgframework.workflow.pojo.bus.TBBusinesstrip;
import org.jeecgframework.workflow.pojo.bus.TBLeave;
import org.jeecgframework.workflow.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @ClassName: busController
 * @Description: TODO(演示业务处理类)
 * @author accpman
 */
@Controller
@RequestMapping("/busController")
public class BusinessController extends BaseController {
	private static final Logger logger = Logger.getLogger(BusinessController.class);
	private SystemService systemService;
	private ActivitiService activitiService;
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

	@Autowired
	public void setActivitiService(ActivitiService activitiService) {
		this.activitiService = activitiService;
	}

	/**
	 * 请假申请页面跳转
	 */
	@RequestMapping(params = "aoruleave")
	public ModelAndView aoruleave(TBLeave leave, HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		if (leave.getId() != null) {
			leave = systemService.getEntity(TBLeave.class, leave.getId());
		}
		if (StringUtil.isNotEmpty(taskId)) {
			String businessKey = activitiService.getBusinessKeyByTask(taskId);
			leave = systemService.getEntity(TBLeave.class, businessKey);
			
		}
		TSTypegroup typegroup = systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", "leave");
		List<TSType> typeList = typegroup.getTSTypes();
		request.setAttribute("typeList", typeList);
		request.setAttribute("taskId", taskId);
		request.setAttribute("leave", leave);
		return new ModelAndView("business/demobus/leave");

	}

	/**
	 * 请假列表页面跳转
	 */
	@RequestMapping(params = "leaveList")
	public ModelAndView leaveList(HttpServletRequest request) {
		return new ModelAndView("business/demobus/leaveList");
	}

	/**
	 * 请假列表数据
	 */

	@RequestMapping(params = "leaveGrid")
	public void leaveGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBLeave.class, dataGrid);
		String begintime=oConvertUtils.getString(request.getParameter("begintime"));
		String endtime=oConvertUtils.getString(request.getParameter("endtime"));
		if (!begintime.equals("") || !endtime.equals("")) {
			cq.ge("begintime", DataUtils.str2Date(begintime, DataUtils.date_sdf));
			cq.le("endtime", DataUtils.str2Date(endtime, DataUtils.date_sdf));
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 保存请假申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveLeave")
	@ResponseBody
	public AjaxJson saveLeave(TBLeave leave, HttpServletRequest request, Variable var) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String code = oConvertUtils.getString(request.getParameter("code"), "new");
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		TSBusConfig busConfig = systemService.getTSBusConfig(TBLeave.class, "leave");
		TSPrjstatus prjstatus = systemService.findUniqueByProperty(TSPrjstatus.class, "code", code);
		leave.setTSBusConfig(busConfig);
		leave.setTSPrjstatus(prjstatus);
		leave.setTSUser(user);
		if (StringUtil.isNotEmpty(leave.getId())) {
			systemService.updateEntitie(leave);
			message = "更新成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			systemService.save(leave);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		if (StringUtil.isNotEmpty(taskId)) {
			ProcessHandle processHandle = activitiService.getProcessHandle(taskId);
			Map<String, Object> map = var.getVariableMap(processHandle.getTpProcesspros());
			ActivitiCom activitiCom = activitiService.complete(taskId, map);
			if (activitiCom.getComplete()) {
				message = activitiCom.getMsg();
			} else {
				message = activitiCom.getMsg();
			}
		}
		j.setMsg(message);

		return j;
	}

	/**
	 * 删除请假
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delLeave")
	@ResponseBody
	public AjaxJson delLeave(TBLeave leave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		leave = systemService.getEntity(TBLeave.class, leave.getId());
		message = "删除成功";
		systemService.delete(leave);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 请假待办任务页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "leaveTaskList")
	public ModelAndView leaveTaskList() {
		return new ModelAndView("business/demobus/leavetaskList");
	}

	/**
	 * 出差列表页面跳转
	 */
	@RequestMapping(params = "bustripList")
	public ModelAndView bustripList(HttpServletRequest request) {
		return new ModelAndView("business/demobus/bustripList");
	}

	/**
	 * 出差列表数据
	 */

	@RequestMapping(params = "bustripGrid")
	public void bustripGrid(HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBBusinesstrip.class, dataGrid);
		String begintime=oConvertUtils.getString(request.getParameter("begintime"));
		String endtime=oConvertUtils.getString(request.getParameter("endtime"));
		if (!begintime.equals("") || !endtime.equals("")) {
			cq.ge("begintime", DataUtils.str2Date(begintime, DataUtils.date_sdf));
			cq.le("endtime", DataUtils.str2Date(endtime, DataUtils.date_sdf));
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 请假申请页面跳转
	 */
	@RequestMapping(params = "aorubustrip")
	public ModelAndView aorubustrip(TBBusinesstrip bustrip, HttpServletRequest request) {
		if (bustrip.getId() != null) {
			bustrip = systemService.getEntity(TBBusinesstrip.class, bustrip.getId());
			request.setAttribute("bustrip", bustrip);
		}
		return new ModelAndView("business/demobus/bustrip");
	}

	/**
	 * 保存出差申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveBustrip")
	@ResponseBody
	public AjaxJson saveBustrip(TBBusinesstrip bustrip, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String code = oConvertUtils.getString(request.getParameter("code"), "new");
		TSBusConfig busConfig = systemService.getTSBusConfig(TBBusinesstrip.class, "bustrip");
		TSPrjstatus prjstatus = systemService.findUniqueByProperty(TSPrjstatus.class, "code", code);
		bustrip.setTSBusConfig(busConfig);
		bustrip.setTSPrjstatus(prjstatus);
		bustrip.setTSUser(user);
		if (StringUtil.isNotEmpty(bustrip.getId())) {
			systemService.updateEntitie(bustrip);
			message = "更新成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			systemService.save(bustrip);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		j.setMsg(message);

		return j;
	}

	/**
	 * 删除出差
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delBustrip")
	@ResponseBody
	public AjaxJson delBustrip(TBBusinesstrip bustrip, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bustrip = systemService.getEntity(TBBusinesstrip.class, bustrip.getId());
		message = "删除成功";
		systemService.delete(bustrip);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 出差待办任务页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "bustripTaskList")
	public ModelAndView bustripTaskList() {
		return new ModelAndView("business/demobus/bustriptaskList");
	}

	/**
	 * 总经理审批页面跳转
	 */
	@RequestMapping(params = "managerApp")
	public ModelAndView managerApp(HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		String businessKey = activitiService.getBusinessKeyByTask(taskId);
		TBBusinesstrip tbBusinesstrip = systemService.getEntity(TBBusinesstrip.class, businessKey);
		Double bormoney = tbBusinesstrip.getBustripmoney();
		if (bormoney > 0) {
			request.setAttribute("bormoney", "true");
		} else {
			request.setAttribute("bormoney", "false");
		}
		request.setAttribute("tbBusinesstrip", tbBusinesstrip);
		request.setAttribute("taskId", taskId);
		return new ModelAndView("business/demobus/managerApp");
	}

	/**
	 * 部门领导审批页面跳转
	 */
	@RequestMapping(params = "deptLeaderApp")
	public ModelAndView deptLeaderApp(HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		String businessKey = activitiService.getBusinessKeyByTask(taskId);
		TBBusinesstrip tbBusinesstrip = systemService.getEntity(TBBusinesstrip.class, businessKey);
		request.setAttribute("tbBusinesstrip", tbBusinesstrip);
		request.setAttribute("taskId", taskId);
		return new ModelAndView("business/demobus/deptLeaderApp");
	}

	/**
	 * 调整申请跳转
	 */
	@RequestMapping(params = "modifyApply")
	public ModelAndView modifyApply(HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		String businessKey = activitiService.getBusinessKeyByTask(taskId);
		TBBusinesstrip tbBusinesstrip = systemService.getEntity(TBBusinesstrip.class, businessKey);
		request.setAttribute("tbBusinesstrip", tbBusinesstrip);
		request.setAttribute("taskId", taskId);
		return new ModelAndView("business/demobus/modifyApply");
	}

	/**
	 * 调整申请保存
	 */
	@RequestMapping(params = "modifyApplySave")
	@ResponseBody
	public AjaxJson modifyApplySave(HttpServletRequest request, Variable var, TBBusinesstrip tbBusinesstrip) {
		AjaxJson j = new AjaxJson();
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		saveBustrip(tbBusinesstrip, request);
		ProcessHandle processHandle = activitiService.getProcessHandle(taskId);
		Map<String, Object> map = var.getVariableMap(processHandle.getTpProcesspros());
		ActivitiCom activitiCom = activitiService.complete(taskId, map);
		if (activitiCom.getComplete()) {
			j.setMsg(activitiCom.getMsg());
		} else {
			j.setMsg(activitiCom.getMsg());
		}
		return j;
	}

	/**
	 * 借款申请页面跳转
	 */
	@RequestMapping(params = "aorubormoney")
	public ModelAndView aorubormoney(TBBormoney tbBormoney, HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		if (tbBormoney.getId() != null) {
			tbBormoney = systemService.getEntity(TBBusinesstrip.class, tbBormoney.getId());
			request.setAttribute("tbBormoney", tbBormoney);
		}
		request.setAttribute("taskId", taskId);
		return new ModelAndView("business/demobus/bormoney");
	}

	/**
	 * 保存借款申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveBormoney")
	@ResponseBody
	public AjaxJson saveBormoney(TBBormoney tbBormoney, HttpServletRequest request, Variable var) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		TSBusConfig busConfig = systemService.getTSBusConfig(TBBormoney.class, "bormoney");
		TSPrjstatus prjstatus = systemService.findUniqueByProperty(TSPrjstatus.class, "code", "new");
		tbBormoney.setTSBusConfig(busConfig);
		tbBormoney.setTSPrjstatus(prjstatus);
		tbBormoney.setTSUser(user);
		tbBormoney.setCreatetime(DataUtils.gettimestamp());
		if (StringUtil.isNotEmpty(tbBormoney.getId())) {
			systemService.updateEntitie(tbBormoney);
			message = "更新成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			systemService.save(tbBormoney);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		if (StringUtil.isNotEmpty(taskId)) {
			ProcessHandle processHandle = activitiService.getProcessHandle(taskId);
			Map<String, Object> map = var.getVariableMap(processHandle.getTpProcesspros());
			ActivitiCom activitiCom = activitiService.complete(taskId, map);
			activitiService.updateHiProcInstBusKey(processHandle.getBusinessKey(), tbBormoney.getId());
			TSPrjstatus prjstatu = systemService.findUniqueByProperty(TSPrjstatus.class, "code", "doing");
			tbBormoney.setTSPrjstatus(prjstatu);
			systemService.updateEntitie(tbBormoney);
			if (activitiCom.getComplete()) {
				message = activitiCom.getMsg();
			} else {
				message = activitiCom.getMsg();
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 借款申请页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "bormoneyList")
	public ModelAndView bormoneyList() {
		return new ModelAndView("business/demobus/bormoneyList");
	}

	/**
	 * 借款申请列表数据
	 */

	@RequestMapping(params = "bormoneyGrid")
	public void bormoneyGrid(HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBBormoney.class, dataGrid);
		String createtime=oConvertUtils.getString(request.getParameter("createtime"));
		if (!createtime.equals("") ) {
			cq.ge("createtime", DataUtils.str2Date(createtime, DataUtils.date_sdf));
			
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delBormoney")
	@ResponseBody
	public AjaxJson delBormoney(TBBormoney tbBormoney, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbBormoney = systemService.getEntity(TBBormoney.class, tbBormoney.getId());
		message = "删除成功";
		systemService.delete(tbBormoney);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 借款待办任务页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "bormoneyTaskList")
	public ModelAndView bormoneyTaskList() {
		return new ModelAndView("business/demobus/bormoneytaskList");
	}
}
