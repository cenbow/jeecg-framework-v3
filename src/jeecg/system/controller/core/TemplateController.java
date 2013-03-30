package jeecg.system.controller.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSOpinTemplate;
import jeecg.system.pojo.base.TSParameter;
import jeecg.system.pojo.base.TSTemplate;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StreamUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 模版信息处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/templateController")
public class TemplateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(TemplateController.class);
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
	 * 模版列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "template")
	public ModelAndView template() {
		return new ModelAndView("system/template/templateList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSTemplate.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除模版
	 * 
	 * @param template
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSTemplate template, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		template = systemService.getEntity(TSTemplate.class, template.getId());
		message = "模版: " + template.getTemplatename() + "被删除 成功";
		systemService.delete(template);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		
		return j;
	}

	/**
	 * 保存模版
	 * 
	 * @param template
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSTemplate template, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String content = request.getParameter("templatecontent");
		//String content = request.getParameter("aa");
		if (StringUtil.isNotEmpty(template.getId())) {
			template = systemService.getEntity(TSTemplate.class,
					template.getId());
			writeFile(request.getSession().getServletContext()
					.getRealPath("/")+template.getTemplatepath(), content);
			template.setTemplatecontent(StreamUtils.StringTObyte(content));
			systemService.saveOrUpdate(template);
			//录入模板中的参数
			//editorParameter(content,template);
			systemService.addLog("模版：" + template.getTemplatename() + "更新成功",
					Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}
	/**
	 * 文件中写入内容
	 * @param path 文件路径
	 * @param content 写入内容
	 */
	public void writeFile(String path, String content) {
        File writefile;
        try {           
            writefile = new File(path);
            // 如果文本文件不存在则创建它
            if (writefile.exists() == false) {
                writefile.createNewFile();
                writefile = new File(path); // 重新实例化
            }
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(writefile),"UTF-8");
            output.write(content);
            output.flush();
            output.close();
          
        } catch (Exception e) {
             e.printStackTrace();
        }
	}
	 /**
	  * 编辑模版参数且将模板中参数录入数据库中
	  * @param content
	  */
	public void editorParameter(String content,TSTemplate template) {		
	    String[] dtp=content.split("<dtp>");
	    for (int i =1; i < dtp.length; i++) {
			String bb=dtp[i];
			if (bb.contains("</dtp>")) {
				String cc=bb.substring(0,bb.indexOf("</dtp>"));
				//List<TParameter> parameters=systemService.findByProperty(TParameter.class,"parametername",cc);	 
				String hql="from TParameter p where p.parametername='"+cc+"' and p.TTemplate.teid="+template.getId()+"";
				List<TSParameter> parameters=systemService.findByQueryString(hql);
				if (parameters.size()<1) {
					TSParameter parameter=new TSParameter();
					parameter.setParametercount("#"+cc+"#");
					parameter.setParametername(cc);
					parameter.setParametertag("dtp");
					parameter.setTSTemplate(template);
					systemService.saveOrUpdate(parameter);
				}				
			}
			
		}
	     //编辑each标签
	    String[] each=content.split("<var>");
	    for (int i =1; i < each.length; i++) {
			String bb=each[i];
			if (bb.contains("</var>")) {
			String cc=bb.substring(0,bb.indexOf("</var>"));
			//List<TParameter> parameters=systemService.findByProperty(TParameter.class,"parametername",cc);	 
			String hql="from TParameter p where p.parametername='"+cc+"' and p.TTemplate.teid="+template.getId()+"";
			List<TSParameter> parameters=systemService.findByQueryString(hql);
			if (parameters.size()<1) {
				TSParameter parameter=new TSParameter();
				parameter.setParametercount("#"+cc+"#");
				parameter.setParametername(cc);
				parameter.setParametertag("each");
				parameter.setTSTemplate(template);
				systemService.saveOrUpdate(parameter);
			}
			}
		}
	}
	/**
	 * 编辑模版页面
	 * 
	 * @param template
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSTemplate template, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(template.getId())) {
			template = systemService.getEntity(TSTemplate.class,
					template.getId());
			byte[] tt = template.getTemplatecontent();
			String attachmentcontent = StreamUtils.byteTOString(tt);
			req.setAttribute("attachmentcontent", attachmentcontent);
			req.setAttribute("templateid", template.getId());
		}
		return new ModelAndView("system/template/editTemplate");
	}

	/**
	 * 参数列表页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "paramList")
	public ModelAndView paramList(HttpServletRequest request) {
		String templateid =request.getParameter("templateid");
		request.setAttribute("templateid", templateid);
		return new ModelAndView("system/template/parameterList");
	}

	/**
	 * easyuiAJAX请求参数列表数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridParam")
	public void datagridParam(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String templateid =request.getParameter("templateid");
		CriteriaQuery cq = new CriteriaQuery(TSParameter.class, dataGrid);
		cq.eq("TSTemplate.id",templateid);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除参数
	 * 
	 * @param parameter
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "delParam")
	@ResponseBody
	public AjaxJson delParam(TSParameter parameter, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		parameter = systemService.getEntity(TSParameter.class,
				parameter.getId());
		message = "参数: " + parameter.getParametername() + "被删除 成功";
		systemService.delete(parameter);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		
		return j;
	}

	/**
	 * 添加和更新参数
	 * 
	 * @param parameter
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveParam")
	@ResponseBody
	public AjaxJson saveParam(TSParameter parameter, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String templateid= request.getParameter("templateid");
		TSTemplate template =systemService.get(TSTemplate.class,templateid);		
		parameter.setTSTemplate(template);		
		if (StringUtil.isNotEmpty(parameter.getId())) {
			message = "模版: " + parameter.getParametername() + "被更新成功";
			systemService.saveOrUpdate(parameter);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "模版: " + parameter.getParametername() + "被添加成功";
			systemService.save(parameter);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 打开参数 信息页面
	 * 
	 * @param parameter
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addorupdateParam")
	public ModelAndView addorupdateParam(TSParameter parameter,
			HttpServletRequest request) {
		String templateid =request.getParameter("templateid");
		request.setAttribute("templateid", templateid);
		if (parameter.getId() != null) {
			parameter = systemService.getEntity(TSParameter.class,
					parameter.getId());
			request.setAttribute("parameter", parameter);
		}
		return new ModelAndView("system/template/parameter");
	}

/**
 * 模版参数列表跳转页面
 * 
 * @return
 */
@RequestMapping(params = "list")
public ModelAndView list(HttpServletRequest request) {
	String templateid = request.getParameter("templateid");
	request.setAttribute("templateid", templateid);
	return new ModelAndView("system/template/parameterLists");
}

/**
 * 意见模版页面跳转
 * 
 * @return
 */
@RequestMapping(params = "templateopin")
public ModelAndView templateopin() {
	return new ModelAndView("system/template/templateopinList");
}
/**
 * easyuiAJAX请求数据
 * 
 * @param request
 * @param response
 * @param dataGrid
 */
@RequestMapping(params = "templateopinList")
public void templateopinList(HttpServletRequest request,
		HttpServletResponse response, DataGrid dataGrid) {
	CriteriaQuery cq = new CriteriaQuery(TSOpinTemplate.class, dataGrid);
	this.systemService.getDataGridReturn(cq, true);
	TagUtil.datagrid(response, dataGrid);
}

/**
 * 删除意见模版
 * 
 * @param template
 * @param request
 * @return
 */
@RequestMapping(params = "deltemplateopin")
@ResponseBody
public AjaxJson deltemplateopin( HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	String id=request.getParameter("id");
	TSOpinTemplate tsOpinTemplate=systemService.getEntity(TSOpinTemplate.class, id);
    if(tsOpinTemplate!=null)
    {
    	systemService.delete(tsOpinTemplate);		
    }
	message="模版："+ tsOpinTemplate.getDescript()+"删除成功";
	systemService.addLog(message, Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);	
	
	return j;
}

/**
 * easyuiAJAX请求数据： 用户选择角色列表
 * 
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params = "addorupdateopin")
public ModelAndView addorupdateopin(TSOpinTemplate tsOpinTemplate, HttpServletRequest req) {
	if (StringUtil.isNotEmpty(tsOpinTemplate.getId())) {
		tsOpinTemplate = systemService.getEntity(TSOpinTemplate.class, tsOpinTemplate.getId());		
		req.setAttribute("tsOpinTemplate", tsOpinTemplate);
	}
	return new ModelAndView("system/template/opininfo");

}

/**
 * 用户录入
 * @param user
 * @param req
 * @return
 */

	@RequestMapping(params = "saveopin")
	@ResponseBody

	public AjaxJson saveopin(TSOpinTemplate tsOpinTemplate,HttpServletRequest req) {	
		AjaxJson j = new AjaxJson();	
		if (StringUtil.isNotEmpty(tsOpinTemplate.getId())) {
			message = "模版: " + tsOpinTemplate.getDescript() + "被更新成功";			 
			systemService.updateEntitie(tsOpinTemplate);			
		}else{
			systemService.save(tsOpinTemplate);	
		}
		
		return j;
	}
}