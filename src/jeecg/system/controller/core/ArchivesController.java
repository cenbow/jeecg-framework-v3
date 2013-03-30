package jeecg.system.controller.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSArchivesBox;
import jeecg.system.pojo.base.TSArchivesCabinet;
import jeecg.system.pojo.base.TSArchivesFiles;
import jeecg.system.pojo.base.TSAttachment;
import jeecg.system.pojo.base.TSDepart;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 档案管理信息处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/archivesController")
public class ArchivesController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ArchivesController.class);
	private UserService userService;
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

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 档案柜列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "archives")
	public ModelAndView archives() {
		return new ModelAndView("archives/archivesList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSArchivesCabinet.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);;;
	}

	/**
	 * 删除档案柜
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSArchivesCabinet archivesCabinet, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		archivesCabinet = systemService.getEntity(TSArchivesCabinet.class, archivesCabinet.getId());
		message = "档案柜: " + archivesCabinet.getCabinetName() + "被删除 成功";
		systemService.delete(archivesCabinet);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		
		return j;
	}
     public  void upEntity(TSDepart depart){
	   List<TSUser> users=systemService.findByProperty(TSUser.class,"TSDepart.id",depart.getId());
      if (users.size()>0) {
		for (TSUser tsUser : users) {
			tsUser.setTSDepart(null);
			systemService.saveOrUpdate(tsUser);
		}
	}
     }
	/**
	 * 添加档案柜
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSArchivesCabinet archivesCabinet, HttpServletRequest request) {	
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(archivesCabinet.getId())) {
			message = "档案柜: " + archivesCabinet.getCabinetName() + "被更新成功";			
			systemService.saveOrUpdate(archivesCabinet);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "档案柜: " + archivesCabinet.getCabinetName() + "被添加成功";
			archivesCabinet.setTSUser(ResourceUtil.getSessionUserName());
			archivesCabinet.setCreateDate(new Date());
			systemService.save(archivesCabinet);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 档案柜列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSArchivesCabinet archivesCabinet, HttpServletRequest req) {		
		if (StringUtil.isNotEmpty(archivesCabinet.getId())) {
			archivesCabinet = systemService.getEntity(TSArchivesCabinet.class,
					archivesCabinet.getId());
			req.setAttribute("archivesCabinet",archivesCabinet);
		}
		return new ModelAndView("archives/archiveCabinet");
	}

	

	/**
	 * 档案盒列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "archivesBox")
	public ModelAndView archivesBox(HttpServletRequest request) {
		return new ModelAndView("archives/archivesBoxList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridBoxh")
	public void datagridBoxh(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String archiveStatus=request.getParameter("archiveStatus");
		CriteriaQuery cq = new CriteriaQuery(TSArchivesBox.class, dataGrid);
		cq.eq("archiveState",Short.parseShort("1"));
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridBox")
	public void datagridBox(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String archiveNum=oConvertUtils.getString(request.getParameter("archiveNum"));

		CriteriaQuery cq = new CriteriaQuery(TSArchivesBox.class, dataGrid);
		cq.eq("archiveNum",archiveNum);
		cq.eq("archiveState",Short.parseShort("0"));
		
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 入柜列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "boxCabinet")
	public ModelAndView boxCabinet() {		
		/*TSArchivesBox box=new TSArchivesBox();
		box.setArchiveCreateData(new java.util.Date() );
		box.setArchiveCustodyTime("jjjj");
		box.setArchiveNum("22");
		box.setArchiveSecurity(Short.valueOf( "22"));
		box.setArchiveTitle("title");
		box.setArchiveUtil("util");
		TSArchivesCabinet cabinet=new TSArchivesCabinet();
		cabinet.setId("402881763a540d40013a5411e12b0006");
		box.setTSArchivesCabinet(cabinet);
		systemService.save(box);*/
		return new ModelAndView("archives/boxesList");
	}
	/**
	 * 入柜列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cabinet")
	public ModelAndView cabinet() {		
		return new ModelAndView("archives/cabineList");
	}
	/**
	 * 更新档案盒
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveBox")
	@ResponseBody
	public AjaxJson saveBox(TSArchivesBox archivesBox, HttpServletRequest request) {	
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(archivesBox.getId())) {
			message = "档案盒: " + archivesBox.getArchiveTitle() + "被更新成功";			
			systemService.saveOrUpdate(archivesBox);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		
		return j;
	}
	/**
	 * 档案盒列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "updateBox")
	public ModelAndView updateBox( HttpServletRequest request) {		
		String boxId=request.getParameter("boxId");
		TSArchivesBox archivesBox = systemService.getEntity(TSArchivesBox.class,boxId);
		request.setAttribute("archivesBox",archivesBox);
		if (archivesBox.getTSArchivesCabinet()!=null) {
			TSArchivesCabinet archivesCabinet=systemService.getEntity(TSArchivesCabinet.class,archivesBox.getTSArchivesCabinet().getId());
		    request.setAttribute("archivesCabinet",archivesCabinet);
		}
		return new ModelAndView("archives/archivesBox");
		
	}
	/**
	 * 查看档案盒文件列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "archivesFile")
	public ModelAndView archivesFile(HttpServletRequest request) {	
		String boxId=request.getParameter("id");
		request.setAttribute("boxId",boxId);
		return new ModelAndView("archives/archivesFileList");
	}	

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridFile")
	public void datagridFile(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq =new CriteriaQuery(TSAttachment.class,dataGrid);
		String boxId=request.getParameter("boxId");
		List<TSAttachment> attachments=new ArrayList<TSAttachment>();
		List<TSArchivesFiles> archivesFiles=systemService.findByProperty(TSArchivesFiles.class,"TSArchivesBox.id",boxId);
		for (TSArchivesFiles tsArchivesFiles : archivesFiles) {
			TSAttachment attachment=systemService.getEntity(TSAttachment.class,tsArchivesFiles.getFileKey());	
			attachments.add(attachment);
		}
		dataGrid.setReaults(attachments);
		dataGrid.setTotal(attachments.size());
		TagUtil.datagrid(response, dataGrid);
	}
	
	
}
