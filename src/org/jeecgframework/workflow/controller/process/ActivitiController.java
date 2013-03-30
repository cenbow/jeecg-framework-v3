/*     */ package org.jeecgframework.workflow.controller.process;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import jeecg.system.pojo.base.TSBusConfig;
/*     */ import jeecg.system.pojo.base.TSPrjstatus;
/*     */ import jeecg.system.pojo.base.TSTable;
/*     */ import jeecg.system.pojo.base.TSUser;
/*     */ import jeecg.system.service.SystemService;
/*     */ import org.activiti.engine.ActivitiException;
/*     */ import org.activiti.engine.IdentityService;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.history.HistoricProcessInstance;
/*     */ import org.activiti.engine.identity.GroupQuery;
/*     */ import org.activiti.engine.identity.User;
/*     */ import org.activiti.engine.identity.UserQuery;
/*     */ import org.activiti.engine.impl.RepositoryServiceImpl;
/*     */ import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.engine.repository.Deployment;
/*     */ import org.activiti.engine.repository.DeploymentBuilder;
/*     */ import org.activiti.engine.repository.DeploymentQuery;
/*     */ import org.activiti.engine.repository.ProcessDefinition;
/*     */ import org.activiti.engine.repository.ProcessDefinitionQuery;
/*     */ import org.activiti.engine.runtime.ProcessInstance;
/*     */ import org.activiti.engine.runtime.ProcessInstanceQuery;
/*     */ import org.activiti.engine.task.Task;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.common.UploadFile;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.MyClassLoader;
/*     */ import org.jeecgframework.core.util.ReflectHelper;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.workflow.model.activiti.ActivitiCom;
/*     */ import org.jeecgframework.workflow.model.activiti.ProcessHandle;
/*     */ import org.jeecgframework.workflow.model.activiti.Variable;
/*     */ import org.jeecgframework.workflow.pojo.activiti.ActHiProcinst;
/*     */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*     */ import org.jeecgframework.workflow.service.ActivitiService;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import org.springframework.web.servlet.view.RedirectView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/activitiController"})
/*     */ public class ActivitiController
/*     */ {
/*  77 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */   protected RepositoryService repositoryService;
/*     */   protected RuntimeService runtimeService;
/*     */   private ActivitiService activitiService;
/*     */   protected TaskService taskService;
/*     */   protected IdentityService identityService;
/*     */   private SystemService systemService;
/*     */   private String message;
/*  85 */   private ModelAndView modelAndView = null;
/*     */ 
/*  89 */   @Autowired
/*     */   public void setIdentityService(IdentityService identityService) { this.identityService = identityService; }
/*     */ 
/*     */   @Autowired
/*     */   public void setTaskService(TaskService taskService) {
/*  94 */     this.taskService = taskService;
/*     */   }
/*     */   @Autowired
/*     */   public void setRepositoryService(RepositoryService repositoryService) {
/*  99 */     this.repositoryService = repositoryService;
/*     */   }
/*     */   @Autowired
/*     */   public void setRuntimeService(RuntimeService runtimeService) {
/* 104 */     this.runtimeService = runtimeService;
/*     */   }
/*     */   @Autowired
/*     */   public void setActivitiService(ActivitiService activitiService) {
/* 109 */     this.activitiService = activitiService;
/*     */   }
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService) {
/* 114 */     this.systemService = systemService;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"deploymentList"})
/*     */   public ModelAndView deploymentList()
/*     */   {
/* 124 */     return new ModelAndView("workflow/deployment/deploymentList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processDeploymentGrid"})
/*     */   public void processDeploymentGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 132 */     List objects = new ArrayList();
/* 133 */     List<ProcessDefinition> processDefinitionList = this.activitiService.processDefinitionList();
/* 134 */     for (ProcessDefinition processDefinition : processDefinitionList) {
/* 135 */       String deploymentId = processDefinition.getDeploymentId();
/* 136 */       Deployment deployment = (Deployment)this.repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
/* 137 */       objects.add(new Object[] { processDefinition, deployment });
/*     */     }
/* 139 */     dataGrid.setTotal(processDefinitionList.size());
/* 140 */     dataGrid.setReaults(processDefinitionList);
/* 141 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"openDeployProcess"})
/*     */   public ModelAndView openDeployProcess()
/*     */   {
/* 153 */     return new ModelAndView("workflow/deployment/deploypro");
/*     */   }
/* 159 */   @RequestMapping(params={"deployProcess"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public AjaxJson deployProcess(HttpServletRequest request) { UploadFile uploadFile = new UploadFile(request);
/* 160 */     AjaxJson j = new AjaxJson();
/* 161 */     Map<String, MultipartFile> fileMap = uploadFile.getMultipartRequest().getFileMap();
/* 162 */     for (Map.Entry entity : fileMap.entrySet()) {
/* 163 */       MultipartFile file = (MultipartFile)entity.getValue();
/* 164 */       String fileName = file.getOriginalFilename();
/*     */       try {
/* 166 */         InputStream fileInputStream = file.getInputStream();
/* 167 */         String extension = FilenameUtils.getExtension(fileName);
/* 168 */         if ((extension.equals("zip")) || (extension.equals("bar"))) {
/* 169 */           ZipInputStream zip = new ZipInputStream(fileInputStream);
/* 170 */           this.repositoryService.createDeployment().addZipInputStream(zip).deploy();
/* 171 */         } else if (extension.equals("png")) {
/* 172 */           this.repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
/* 173 */         } else if (extension.indexOf("bpmn20.xml") != -1) {
/* 174 */           this.repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
/* 175 */         } else if (extension.equals("bpmn"))
/*     */         {
/* 179 */           String baseName = FilenameUtils.getBaseName(fileName);
/* 180 */           this.repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
/*     */         } else {
/* 182 */           throw new ActivitiException("错误信息:不支改文件类型" + extension);
/*     */         }
/*     */       } catch (Exception e) {
/* 185 */         this.logger.error("错误信息:在部署过程中,文件输入流异常" + e.toString());
/*     */       }
/*     */     }
/*     */ 
/* 189 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"startBusProcess"})
/*     */   @ResponseBody
/*     */   public AjaxJson startBusProcess(HttpServletRequest request)
/*     */   {
/* 201 */     AjaxJson j = new AjaxJson();
/*     */     try {
/* 203 */       String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
/* 204 */       String busconfigId = oConvertUtils.getString(request.getParameter("busconfigKey"));
/* 205 */       TSBusConfig tsBusbase = (TSBusConfig)this.systemService.getEntity(TSBusConfig.class, busconfigId);
/* 206 */       if (tsBusbase != null) {
/* 207 */         Class entityClass = MyClassLoader.getClassByScn(tsBusbase.getTSTable().getEntityName());
/* 208 */         Object objbus = this.systemService.getEntity(entityClass, businessKey);
/* 209 */         TSPrjstatus prjstatus = (TSPrjstatus)this.systemService.findUniqueByProperty(TSPrjstatus.class, "code", "doing");
/* 210 */         ReflectHelper reflectHelper = new ReflectHelper(objbus);
/* 211 */         TSPrjstatus busPrjstatus = (TSPrjstatus)reflectHelper.getMethodValue("TSPrjstatus");
/* 212 */         String objbusstate = busPrjstatus.getCode();
/* 213 */         if (!prjstatus.getCode().equals(objbusstate)) {
/* 214 */           Map variables = new HashMap();
/* 215 */           this.activitiService.startWorkflow(objbus, businessKey, variables, tsBusbase);
/* 216 */           reflectHelper.setMethodValue("TSPrjstatus", prjstatus);
/* 217 */           this.systemService.saveOrUpdate(objbus);
/* 218 */           this.message = "提交成功,已进入办理流程";
/*     */         } else {
/* 220 */           this.message = "已提交,正在办理中";
/*     */         }
/*     */       }
/* 223 */       else if (busconfigId.equals("undefined")) {
/* 224 */         this.message = "busconfigKey参数未设置,请在业务列表设置参数";
/*     */       } else {
/* 226 */         this.message = "流程未关联,请在流程配置中配置业务";
/*     */       }
/*     */     }
/*     */     catch (ActivitiException e)
/*     */     {
/* 231 */       if (e.getMessage().indexOf("no processes deployed with key") != -1) {
/* 232 */         this.message = "没有部署流程!,请在流程配置中部署流程";
/*     */       }
/*     */       else
/* 235 */         this.message = "启动流程失败!,内部错误";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 239 */       this.message = "启动流程失败!,内部错误";
/*     */     }
/* 241 */     j.setMsg(this.message);
/* 242 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"claim"})
/*     */   @ResponseBody
/*     */   public AjaxJson claim(HttpServletRequest request)
/*     */   {
/* 251 */     AjaxJson j = new AjaxJson();
/* 252 */     String userId = ResourceUtil.getSessionUserName().getUserName().toString();
/* 253 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 254 */     this.taskService.claim(taskId, userId);
/* 255 */     j.setMsg("签收完成");
/* 256 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"openProcessPic"})
/*     */   public ModelAndView openProcessPic(HttpServletRequest request)
/*     */   {
/* 264 */     String tag = oConvertUtils.getString(request.getParameter("tag"));
/* 265 */     if (tag.equals("task")) {
/* 266 */       String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 267 */       Task task = this.activitiService.getTask(taskId);
/* 268 */       String processInstanceId = task.getProcessInstanceId();
/* 269 */       ActivityImpl activityImpl = this.activitiService.getProcessMap(processInstanceId);
/* 270 */       request.setAttribute("activityImpl", activityImpl);
/* 271 */       request.setAttribute("processInstanceId", processInstanceId);
/*     */     }
/* 273 */     else if (tag.equals("deployment")) {
/* 274 */       request.setAttribute("resourceName", oConvertUtils.getString(request.getParameter("resourceName")));
/* 275 */       request.setAttribute("deploymentId", oConvertUtils.getString(request.getParameter("deploymentId")));
/* 276 */     } else if (tag.equals("project")) {
/* 277 */       String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
/* 278 */       HistoricProcessInstance historicProcessInstance = this.activitiService.getHiProcInstByBusKey(businessKey);
/* 279 */       String processInstanceId = historicProcessInstance.getId();
/* 280 */       ActivityImpl activityImpl = this.activitiService.getProcessMap(processInstanceId);
/* 281 */       request.setAttribute("activityImpl", activityImpl);
/* 282 */       request.setAttribute("processInstanceId", processInstanceId);
/*     */     }
/* 284 */     request.setAttribute("tag", tag);
/* 285 */     return new ModelAndView("workflow/process/processPic");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processPic"})
/*     */   public void processPic(HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 300 */     response.setContentType("UTF-8");
/* 301 */     response.setCharacterEncoding("UTF-8");
/* 302 */     String resourceType = oConvertUtils.getString(request.getParameter("resourceType"));
/* 303 */     String processInstanceId = oConvertUtils.getString(request.getParameter("processInstanceId"));
/* 304 */     InputStream resourceAsStream = null;
/* 305 */     ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/* 306 */     ProcessDefinition singleResult = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
/* 307 */     String resourceName = "";
/* 308 */     if (resourceType.equals("image"))
/* 309 */       resourceName = singleResult.getDiagramResourceName();
/* 310 */     else if (resourceType.equals("xml")) {
/* 311 */       resourceName = singleResult.getResourceName();
/*     */     }
/* 313 */     resourceAsStream = this.repositoryService.getResourceAsStream(singleResult.getDeploymentId(), resourceName);
/* 314 */     ProcessDefinitionEntity def = (ProcessDefinitionEntity)((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
/* 315 */     resourceAsStream = ProcessDiagramGenerator.generateDiagram(def, "png", this.activitiService.highLight(processInstanceId));
/* 316 */     byte[] b = new byte[1024];
/* 317 */     int len = -1;
/* 318 */     while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
/* 319 */       response.getOutputStream().write(b, 0, len);
/*     */     }
/* 321 */     response.getOutputStream().flush();
/* 322 */     response.getOutputStream().close();
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processPicByDeploy"})
/*     */   public void processPicByDeploy(HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 337 */     String deploymentId = oConvertUtils.getString(request.getParameter("deploymentId"));
/* 338 */     String resourceName = oConvertUtils.getString(request.getParameter("resourceName"));
/* 339 */     InputStream resourceAsStream = this.repositoryService.getResourceAsStream(deploymentId, resourceName);
/* 340 */     byte[] b = new byte[1024];
/* 341 */     int len = -1;
/* 342 */     while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
/* 343 */       response.getOutputStream().write(b, 0, len);
/*     */     }
/* 345 */     response.getOutputStream().flush();
/* 346 */     response.getOutputStream().close();
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskList"})
/*     */   public void taskList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 355 */     TSUser user = ResourceUtil.getSessionUserName();
/* 356 */     String buscode = oConvertUtils.getString(request.getParameter("busCode"));
/* 357 */     TSTable table = (TSTable)this.systemService.findUniqueByProperty(TSTable.class, "entityKey", buscode);
/* 358 */     if (table != null) {
/* 359 */       List tsBusConfigs = this.systemService.findByProperty(TSBusConfig.class, "TSTable.id", table.getId());
/* 360 */       List taskList = this.activitiService.findTodoTasks(user.getUserName(), tsBusConfigs);
/* 361 */       dataGrid.setTotal(taskList.size());
/* 362 */       dataGrid.setReaults(taskList);
/* 363 */       TagUtil.datagrid(response, dataGrid);
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"openProcessHandle"})
/*     */   public ModelAndView openProcessHandle(HttpServletRequest request)
/*     */   {
/* 373 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 374 */     ProcessHandle processHandle = this.activitiService.getProcessHandle(taskId);
/* 375 */     if (processHandle.getTpForm() != null) {
/* 376 */       TPForm tForm = processHandle.getTpForm();
/*     */ 
/* 378 */       List formpros = tForm.getTPFormpros();
/* 379 */       request.setAttribute("formpros", formpros);
/* 380 */       request.setAttribute("action", tForm.getFormaction());
/* 381 */       this.modelAndView = new ModelAndView("workflow/processHandle/dynamicHandle");
/*     */     } else {
/* 383 */       String modelandview = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandview(), "activitiController.do?processHandle");
/* 384 */       this.modelAndView = new ModelAndView(new RedirectView(modelandview), "taskId", taskId);
/*     */     }
/* 386 */     return this.modelAndView;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processHandle"})
/*     */   public ModelAndView processHandle(HttpServletRequest request)
/*     */   {
/* 394 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 395 */     request.setAttribute("taskId", taskId);
/* 396 */     return new ModelAndView("workflow/processhandle/processHandle");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processComplete"})
/*     */   @ResponseBody
/*     */   public AjaxJson processComplete(HttpServletRequest request, Variable var)
/*     */   {
/* 410 */     AjaxJson j = new AjaxJson();
/* 411 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 412 */     ProcessHandle processHandle = this.activitiService.getProcessHandle(taskId);
/* 413 */     Map map = var.getVariableMap(processHandle.getTpProcesspros());
/* 414 */     ActivitiCom complete = this.activitiService.complete(taskId, map);
/* 415 */     if (complete.getComplete().booleanValue())
/* 416 */       j.setMsg(complete.getMsg());
/*     */     else {
/* 418 */       j.setMsg(complete.getMsg());
/*     */     }
/* 420 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"deleteProcess"})
/*     */   @ResponseBody
/*     */   public AjaxJson deleteProcess(HttpServletRequest request)
/*     */   {
/* 431 */     AjaxJson j = new AjaxJson();
/* 432 */     String deploymentId = oConvertUtils.getString(request.getParameter("deploymentId"));
/* 433 */     String key = oConvertUtils.getString(request.getParameter("key"));
/* 434 */     TPProcess process = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", key);
/* 435 */     ProcessDefinition processDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).processDefinitionKey(key).singleResult();
/* 436 */     List actHiProcinsts = this.systemService.findByProperty(ActHiProcinst.class, "procDefId", processDefinition.getId());
/* 437 */     if (actHiProcinsts.size() <= 0) {
/* 438 */       this.repositoryService.deleteDeployment(deploymentId, true);
/* 439 */       process.setProcessstate(Globals.Process_Deploy_NO);
/* 440 */       this.systemService.updateEntitie(process);
/* 441 */       this.message = "流程删除成功";
/*     */     } else {
/* 443 */       this.message = "流程跟业务已关联无法删除";
/*     */     }
/* 445 */     j.setMsg(this.message);
/* 446 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"setProcessState"})
/*     */   @ResponseBody
/*     */   public AjaxJson setProcessState(HttpServletRequest request)
/*     */   {
/* 455 */     AjaxJson j = new AjaxJson();
/* 456 */     String state = oConvertUtils.getString(request.getParameter("state"));
/* 457 */     String processDefinitionId = oConvertUtils.getString(request.getParameter("processDefinitionId"));
/* 458 */     if (state.equals("active")) {
/* 459 */       this.repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
/* 460 */       this.message = "流程已激活";
/*     */     } else {
/* 462 */       this.repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
/* 463 */       this.message = "流程已挂起";
/*     */     }
/* 465 */     j.setMsg(this.message);
/* 466 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getUsers"})
/*     */   @ResponseBody
/*     */   public void getUsers(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 478 */     CriteriaQuery cq = new CriteriaQuery(User.class, dataGrid);
/* 479 */     List userList = this.identityService.createUserQuery().list();
/* 480 */     dataGrid.setTotal(userList.size());
/* 481 */     dataGrid.setReaults(userList);
/* 482 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getGroups"})
/*     */   @ResponseBody
/*     */   public void getGroups(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 494 */     List groupList = this.identityService.createGroupQuery().list();
/* 495 */     dataGrid.setTotal(groupList.size());
/* 496 */     dataGrid.setReaults(groupList);
/* 497 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.ActivitiController
 * JD-Core Version:    0.6.0
 */