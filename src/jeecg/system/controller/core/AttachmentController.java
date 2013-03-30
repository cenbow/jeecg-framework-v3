package jeecg.system.controller.core;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSAttachment;
import jeecg.system.pojo.base.TSTemplate;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StreamUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.workflow.pojo.bus.TBDeclaretable;
import org.jeecgframework.workflow.pojo.bus.TBInfotype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 项目申报处理类
 * 
 * @author Administrator
 * @param <BLOB>
 * @param <SerializableBlob>
 * 
 */
@Controller
@RequestMapping("/attachmentController")
public class AttachmentController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AttachmentController.class);
	private UserService userService;
	private SystemService systemService;

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
	 * 上传页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upLeg")
	public ModelAndView upLeg(HttpServletRequest request) {
		String businessKey = request.getParameter("businessKey");
		request.setAttribute("businessKey", businessKey);
		return new ModelAndView("business/declare/uploadleg");
	}

	/**
	 * 上传页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "uploadAttachment")
	public ModelAndView uploadAttachment(HttpServletRequest req) {
		String businessKey = req.getParameter("businessKey");
		req.setAttribute("businessKey", businessKey);
		List<TBInfotype> infotype = systemService.getList(TBInfotype.class);
		req.setAttribute("infotype", infotype);
		return new ModelAndView("common/upload/upload");
	}

	
	/**
	 * 上传文件申报项目信息文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "attach", method = RequestMethod.POST)
	public ModelAndView attach(HttpServletRequest request) throws Exception {
		// 设置用户
		TSUser user = ResourceUtil.getSessionUserName();
		UploadFile uploadFile = new UploadFile(request);
		request.setCharacterEncoding("UTF-8");
		String typeid = request.getParameter("typeid");
		String note = request.getParameter("note");
		TSAttachment attach = new TSAttachment();
		attach.setNote(note);
		// 设置上传时间
		attach.setCreatedate(DataUtils.gettimestamp());
		// 设置文件类型
		if (typeid != null) {
			TBInfotype infotype = systemService.get(TBInfotype.class, typeid);
			attach.setTBInfotype(infotype);
		}
		String businessKey = request.getParameter("businessKey");
		if (user != null) {
			attach.setTSUser(user);
		}
		attach.setBusinessKey(businessKey);
		uploadFile.setCusPath(businessKey);
		uploadFile.setObject(attach);
		uploadFile.setRealPath("realpath");
		uploadFile.setExtend("extend");
		uploadFile.setTitleField("attachmenttitle");
		uploadFile.setSwfpath("swfpath");
		uploadFile.setByteField("attachmentcontent");
		systemService.uploadFile(uploadFile);
		return new ModelAndView("business/declare/attachmentList");
	}

	public List<String> loadFiles(HttpServletRequest request, TBDeclaretable declaretable) {
		List<String> files = new ArrayList<String>();
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "upload" + "/" + declaretable.getId() + "/";
		File file = new File(ctxPath);
		if (file.exists()) {
			File[] fs = file.listFiles();
			String fname = null;
			for (File f : fs) {
				fname = f.getName();
				if (f.isFile()) {
					files.add(fname);
				}
			}
		}
		return files;
	}

	@RequestMapping(params = "downloads")
	public void downloads(TSAttachment attachment, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadFile uploadFile = new UploadFile(request, response);
		if (attachment.getId() != null) {
			attachment = systemService.get(TSAttachment.class, attachment.getId());
		}
		String downLoadPath = attachment.getRealpath();
		// uploadFile.setRealPath(downLoadPath);
		uploadFile.setContent(attachment.getAttachmentcontent());
		uploadFile.setTitleField(attachment.getAttachmenttitle());
		uploadFile.setExtend(attachment.getExtend());
		uploadFile.setObject(attachment);
		systemService.viewOrDownloadFile(uploadFile);
	}
	/**
	 * 删除上传的文件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSAttachment attachment, HttpServletRequest request, String ids) {
		AjaxJson j = new AjaxJson();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String message = null;
		String filename;
		String name = null;
		if (StringUtil.isNotEmpty(attachment.getId())) {
			attachment = systemService.get(TSAttachment.class, attachment.getId());
			message = "上传文件: " + attachment.getAttachmenttitle() + "被删除成功";
			name = attachment.getRealpath();
			userService.delete(attachment);
		}
		try {
			filename = java.net.URLDecoder.decode(name, "UTF-8");
			File file = new File(ctxPath + filename);
			if (file.exists()) {
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		return j;
	}
	/**
	 * 删除上传的模版文件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delTemplate")
	@ResponseBody
	public AjaxJson delTemplate(TSTemplate template, HttpServletRequest request, String ids) {
		AjaxJson j = new AjaxJson();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String message = null;
		String filename;
		String name = null;
		if (StringUtil.isNotEmpty(template.getId())) {
			template = systemService.get(TSTemplate.class, template.getId());
			message = "模版文件: " + template.getTemplatename() + "被删除成功";
			name = template.getTemplatepath();
			userService.delete(template);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		try {
			filename = java.net.URLDecoder.decode(name, "UTF-8");
			File file = new File(ctxPath + filename);
			if (file.exists()) {
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		
		return j;
	}

	/**
	 * 上传页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "template")
	public ModelAndView template(HttpServletRequest request) {
		return new ModelAndView("system/template/template");
	}

	/**
	 * 上传文件申报项目信息文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "uploadTemplate", method = RequestMethod.POST)
	public ModelAndView uploadTemplate(HttpServletRequest request) throws Exception {
		UploadFile uploadFile = new UploadFile(request);
		request.setCharacterEncoding("UTF-8");
		String templatename = request.getParameter("templatename");
		String templatecode = request.getParameter("templatecode");
		String templateorder = request.getParameter("templateorder");
		String note = request.getParameter("note");
		TSTemplate template = new TSTemplate();
		template.setTemplatecode(templatecode);
		template.setTemplatename(templatename);
		uploadFile.setObject(template);
		uploadFile.setRealPath("templatepath");
		uploadFile.setByteField("templatecontent");
		systemService.uploadFile(uploadFile);
		return new ModelAndView("business/declare/attachmentList");
	}

	//
	/**
	 * 预览系统文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "tepView")
	public ModelAndView tepView(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		TSAttachment attachment = systemService.getEntity(TSAttachment.class, id);
		byte[] attachmentcontentbyte = attachment.getAttachmentcontent();
		String attachmentcontent = StreamUtils.byteTOString(attachmentcontentbyte);
		request.setAttribute("attachmentcontent", attachmentcontent);// 保存模板
		return new ModelAndView("business/projectfiling/templateView");
	}

	

	

	
	

	
}
