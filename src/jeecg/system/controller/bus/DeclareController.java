package jeecg.system.controller.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSAttachment;
import jeecg.system.pojo.base.TSBusConfig;
import jeecg.system.pojo.base.TSPrjstatus;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.DeclareService;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.Highchart;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.template.Template;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StreamUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.workflow.pojo.bus.TBApproval;
import org.jeecgframework.workflow.pojo.bus.TBDeclaretable;
import org.jeecgframework.workflow.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 项目申报处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/declareController")
public class DeclareController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DeclareController.class);
	private DeclareService declareService;
	private SystemService systemService;
	private ActivitiService activitiService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setDeclareService(DeclareService declareService) {
		this.declareService = declareService;
	}

	@Autowired
	public void setActivitiService(ActivitiService activitiService) {
		this.activitiService = activitiService;
	}

	/**
	 * 项目申报列表页面跳转checkdeclare
	 * 
	 * @return
	 */
	@RequestMapping(params = "declaretableList")
	public ModelAndView declaretableList() {
		return new ModelAndView("business/declare/declaretableList");
	}

	/**
	 * 项目查询列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "checkdeclare")
	public ModelAndView checkdeclare() {
		return new ModelAndView("business/declare/checkdeclare");
	}

	/**
	 * easyuiAJAX请求项目申报列表数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "declareGrid")
	public void declareGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String starttime = oConvertUtils.getString(request.getParameter("starttime"));
		String endtime = oConvertUtils.getString(request.getParameter("endtime"));
		String pname = oConvertUtils.getString(request.getParameter("pname"));
		CriteriaQuery cq = new CriteriaQuery(TBDeclaretable.class, dataGrid);
		if (!starttime.equals("") && !endtime.equals("")) {
			cq.ge("declaredate", DataUtils.str2Date(starttime, DataUtils.date_sdf));
			cq.le("declaredate", DataUtils.str2Date(endtime, DataUtils.date_sdf));
		}
		if (!pname.equals("")) {
			cq.like("projectname", pname);
		}
		cq.addOrder("id", SortDirection.desc);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyuiAJAX请求项目申报列表数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid1")
	public void datagrid1(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBDeclaretable.class, dataGrid);
		String constructionunit = oConvertUtils.getString(request.getParameter("constructionunit"));
		String projectlocation = oConvertUtils.getString(request.getParameter("projectlocation"));
		String projectname = oConvertUtils.getString(request.getParameter("projectname"));
		String administrativeregion = oConvertUtils.getString(request.getParameter("administrativeregion"));
		String declaretype = oConvertUtils.getString(request.getParameter("declaretype"));
		if (!constructionunit.equals("")) {
			// cq.eq("constructionunit", constructionunit);
			cq.like("constructionunit", constructionunit);
		}
		if (!projectlocation.equals("")) {
			// cq.eq("projectlocation", projectlocation);
			cq.like("projectlocation", projectlocation);
		}
		if (!projectname.equals("")) {
			// cq.eq("projectname", project);
			cq.like("projectname", projectname);
		}
		if (!administrativeregion.equals("")) {
			// cq.eq("administrativeregion", administrativeregion);
			cq.like("administrativeregion", administrativeregion);
		}
		if (!declaretype.equals("")) {
			cq.eq("declaretype", oConvertUtils.getShort(declaretype));
			// cq.like("declaretype", oConvertUtils.getShort(declaretype));
		}
		if (!declaretype.equals("")) {
			if (!projectname.equals("")) {
				cq.eq("declaretype", oConvertUtils.getShort(declaretype));
				cq.like("projectname", projectname);
				cq.add(cq.and(cq, 0, 1));
			} else {
				cq.eq("declaretype", oConvertUtils.getShort(declaretype));
				cq.add();
			}
		}
		cq.addOrder("id", SortDirection.asc);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TBDeclaretable declaretable, String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		declaretable = systemService.getEntity(TBDeclaretable.class, declaretable.getId());
		declareService.delete(declaretable);
		systemService.addLog("项目申报信息删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		return j;
	}

	/**
	 * 项目申报录入
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TBDeclaretable declaretable, HttpServletRequest request) {
		String declareableId = declaretable.getId();// 业务ID
		TSUser user = ResourceUtil.getSessionUserName();
		AjaxJson j = new AjaxJson();
		TSBusConfig busConfig = systemService.getTSBusConfig(TBDeclaretable.class, "rfmainprocess");
		TSPrjstatus prjstatus = systemService.findUniqueByProperty(TSPrjstatus.class, "code", "new");
		String msg = "";// 提示信息
		if (StringUtil.isNotEmpty(declareableId)) {
			declaretable.setTSBusConfig(busConfig);
			declaretable.setTSPrjstatus(prjstatus);
			declaretable.setTSUser(user);
			declareService.updateEntitie(declaretable);
			msg = "项目申报信息更新成功";
			systemService.addLog(msg, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			declaretable.setTSBusConfig(busConfig);
			declaretable.setTSPrjstatus(prjstatus);
			declaretable.setTSUser(user);
			declareService.save(declaretable);
			msg = "项目申报信息录入成功";
			systemService.addLog(msg, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(msg);

		return j;
	}

	/**
	 * 项目申报列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TBDeclaretable declaretable, HttpServletRequest req) {
		if (declaretable.getId() != null) {
			declaretable = systemService.getEntity(TBDeclaretable.class, declaretable.getId());
			req.setAttribute("declaretable", declaretable);
		}

		return new ModelAndView("business/declare/declaretable");
	}

	/**
	 * 模板查看页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "editTemplate")
	public ModelAndView editTemplate(HttpServletRequest request) {
		String declareid = request.getParameter("declareid");
		request.setAttribute("declareid", declareid);
		String code = request.getParameter("code");
		String attachmenttitle = request.getParameter("attachmenttitle");
		request.setAttribute("code", code);
		request.setAttribute("attachmenttitle", attachmenttitle);
		TBDeclaretable declaretable = systemService.get(TBDeclaretable.class, declareid);
		List<TSAttachment> attachmentList = declareService.getAttachmentsByCode(declaretable.getId(), code);
		String attachmentcontent = null;
		if (attachmentList.size() > 0) {
			TSAttachment attachment = attachmentList.get(0);
			byte[] attachmentcontentbyte = attachment.getAttachmentcontent();
			attachmentcontent = StreamUtils.byteTOString(attachmentcontentbyte);
			request.setAttribute("attachment", attachment);// 保存模板

		} else {
			Map<String, String> map = null;
			Template template = new Template();
			template.setDataSourceMap(map);
			template.setTemplatecCode(code);
			if (code.equals("jzmxb") || code.equals("ydmxb")) {
				List projectdetials = null;
				template.setDataSet(projectdetials);
			}
			attachmentcontent = systemService.getTempleContent(template);
		}
		request.setAttribute("declareable", declaretable);
		request.setAttribute("attachmentcontent", attachmentcontent);// 保存模板内容
		return new ModelAndView("business/declare/editTemplate");
	}

	/**
	 * 审核表文件审批
	 * 
	 * @return
	 */
	@RequestMapping(params = "appAttachment")
	@ResponseBody
	public AjaxJson appAttachment(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));// taskID
		String businessKey = activitiService.getBusinessKeyByTask(taskId);// 业务Id
		TBDeclaretable declaretable = declareService.getEntity(TBDeclaretable.class, businessKey);
		String declaretype = declaretable.getDeclaretype().toString(); // 申报项目类型：1：建筑项目 2：异地建设
		String approvalopinion = oConvertUtils.getString(request.getParameter("backReason"));
		String code = oConvertUtils.getString(request.getParameter("code"));// 审批标示，同意或退回
		Integer approvalOperation = (code.equals("exit")) ? 0 : 1;

		TSUser user = ResourceUtil.getSessionUserName();
		List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String stateCode = roleUsers.get(0).getTSRole().getRoleCode() + code;
		// 获取审批状态
		TSPrjstatus prjstatus = declareService.findUniqueByProperty(TSPrjstatus.class, "code", stateCode);
		TBApproval approval = new TBApproval();
		approval.setApprovaldate(DataUtils.gettimestamp());
		approval.setTSUser(user);
		approval.setApprovalrole(roleUsers.get(0).getTSRole().getRoleName());
		approval.setApprovalopinion(approvalopinion);
		approval.setApprovaloperation(approvalOperation);
		declareService.saveOrUpdate(approval);
		systemService.addLog("审批信息添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		// 更新项目状态
		if (prjstatus != null) {
			declaretable.setTSPrjstatus(prjstatus);
			declareService.saveOrUpdate(declaretable);
			systemService.addLog("项目状态更新成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		activitiService.complete(taskId, null);// 完成流程

		return j;
	}

	/**
	 * 审批页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "app")
	public ModelAndView app(HttpServletRequest request) {
		String declareid = request.getParameter("declareid");
		request.setAttribute("declareid", declareid);
		return new ModelAndView("business/declare/app");
	}

	/**
	 * 项目统计
	 * 
	 * @return
	 */
	@RequestMapping(params = "reportList")
	public ModelAndView reportList(HttpServletRequest request) {
		return new ModelAndView("business/report/reportList");
	}

	/**
	 * 项目统计饼图
	 * 
	 * @return
	 */
	@RequestMapping(params = "report")
	public ModelAndView report(HttpServletRequest request) {
		return new ModelAndView("business/report/report");
	}

	/**
	 * 项目统计线性图
	 * 
	 * @return
	 */
	@RequestMapping(params = "reportLine")
	public ModelAndView reportLine(HttpServletRequest request) {
		return new ModelAndView("business/report/reportLine");
	}

	/**
	 * 项目统计竖形图
	 * 
	 * @return
	 */
	@RequestMapping(params = "reportBar")
	public ModelAndView reportBar(HttpServletRequest request) {
		return new ModelAndView("business/report/reportBar");
	}

	/**
	 * 报表数据生成
	 * 
	 * @return
	 */
	@RequestMapping(params = "getReportData")
	@ResponseBody
	public List<Highchart> getReportData(HttpServletRequest request, HttpServletResponse response) {
		String a = request.getParameter("a");
		List<Highchart> list = new ArrayList<Highchart>();
		Highchart hc = new Highchart();
		StringBuffer sb = new StringBuffer();
		if (a.equals("0")) {
			sb.append("SELECT administrativeregion ,count(administrativeregion) FROM TBDeclaretable group by administrativeregion");
		} else {
			sb.append("SELECT administrativeregion ,count(administrativeregion) FROM TBDeclaretable WHERE declaretype=" + a + " group by administrativeregion");
		}
		List declareList = declareService.findByQueryString(sb.toString());
		List lt = new ArrayList();
		hc = new Highchart();
		hc.setName("各区统计表");
		hc.setType("pie");
		Map<String, Object> map;
		if (declareList.size() > 0) {
			for (Object object : declareList) {
				map = new HashMap<String, Object>();
				Object[] obj = (Object[]) object;
				map.put("name", obj[0]);
				map.put("y", obj[1]);
				lt.add(map);
			}
		}
		hc.setData(lt);
		list.add(hc);
		return list;
	}

	/**
	 * 线型报表生成
	 * 
	 * @return
	 */
	@RequestMapping(params = "getReportLine")
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getReportLine(HttpServletRequest request, HttpServletResponse response) {

		String[] region = { "金水区", "惠济区", "二七区", "中原区" };
		String a = request.getParameter("a");

		StringBuffer sb = new StringBuffer();

		if (a.equals("0")) {

			sb.append("SELECT administrativeregion ,count(id), to_char(declaredate,'MM') FROM TBDeclaretable  group by to_char(declaredate,'MM'),administrativeregion");
		} else {
			sb.append("SELECT administrativeregion ,count(id), to_char(declaredate,'MM') FROM TBDeclaretable where  declaretype=" + a + " group by to_char(declaredate,'MM'),administrativeregion");
		}
		List declareList = declareService.findByQueryString(sb.toString());
		List<Map> lt = new ArrayList<Map>();
		Map map;
		for (int i = 0; i < region.length; i++) {
			map = new HashMap();
			map.put("name", region[i]);
			Integer[] mon = new Integer[12];
			if (declareList.size() > 0) {
				for (Object object : declareList) {
					Object[] obj = (Object[]) object;
					if (region[i].equals(obj[0])) {
						map.put("data", getMonth(oConvertUtils.getInt(obj[1], 0), obj[2].toString(), mon));
					}
				}
			}
			lt.add(map);
		}

		return lt;
	}

	/**
	 * 月份遍历和赋值
	 * 
	 * @param count
	 *            区域每月的项目数
	 * @param m
	 *            月份
	 * @param mon
	 *            区域的12的项目数数组集
	 * @return
	 */
	public Integer[] getMonth(int count, String m, Integer[] mon) {
		String[] month = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		for (int i = 0; i < month.length; i++) {
			if (month[i].equals(m)) {
				mon[i] = count;
			} else {
				mon[i] = 0;
			}
		}
		return mon;
	}

	/**
	 * 柱形报表数据生成
	 * 
	 * @return
	 */
	@RequestMapping(params = "getReportBar")
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getReportBar(HttpServletRequest request, HttpServletResponse response) {
		String a = request.getParameter("a");
		String[] region = { "经开区", "金水区", "中原区", "二七区", "惠济区" };
		Highchart hc = new Highchart();
		StringBuffer sb = new StringBuffer();
		if (a.equals("0")) {
			sb.append("SELECT administrativeregion ,count(administrativeregion) FROM TBDeclaretable group by administrativeregion");
		} else {
			sb.append("SELECT administrativeregion ,count(administrativeregion) FROM TBDeclaretable WHERE declaretype=" + a + " group by administrativeregion");
		}
		List declareList = declareService.findByQueryString(sb.toString());
		List<Map> lt = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("name", "各区比例");
		Integer[] count = new Integer[region.length];
		for (int i = 0; i < region.length; i++) {
			if (declareList.size() > 0) {
				for (Object object : declareList) {
					Object[] obj = (Object[]) object;
					if (region[i].equals(obj[0])) {
						count[i] = oConvertUtils.getInt(obj[1], 0);
					}
				}
			}
		}
		map.put("data", count);
		lt.add(map);
		return lt;
	}

	/**
	 * 高级查询
	 * 
	 * @return
	 */
	@RequestMapping(params = "adSearch")
	public ModelAndView adSearch(HttpServletRequest request) {
		return new ModelAndView("business/declare/search");
	}

}
