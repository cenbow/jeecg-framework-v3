/*      */ package org.jeecgframework.workflow.controller.process;
/*      */ 
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import jeecg.system.pojo.base.TSBusConfig;
/*      */ import jeecg.system.pojo.base.TSTable;
/*      */ import jeecg.system.pojo.base.TSType;
/*      */ import jeecg.system.pojo.base.TSTypegroup;
/*      */ import jeecg.system.service.SystemService;
/*      */ import jeecg.system.service.UserService;
/*      */ import org.activiti.engine.RepositoryService;
/*      */ import org.activiti.engine.repository.DeploymentBuilder;
/*      */ import org.apache.log4j.Logger;
/*      */ import org.jeecgframework.core.common.controller.BaseController;
/*      */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*      */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*      */ import org.jeecgframework.core.common.model.json.ComboBox;
/*      */ import org.jeecgframework.core.common.model.json.ComboTree;
/*      */ import org.jeecgframework.core.common.model.json.DataGrid;
/*      */ import org.jeecgframework.core.constant.Globals;
/*      */ import org.jeecgframework.core.util.ResourceUtil;
/*      */ import org.jeecgframework.core.util.StreamUtils;
/*      */ import org.jeecgframework.core.util.StringUtil;
/*      */ import org.jeecgframework.core.util.oConvertUtils;
/*      */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*      */ import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
/*      */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*      */ import org.jeecgframework.workflow.pojo.base.TPFormpro;
/*      */ import org.jeecgframework.workflow.pojo.base.TPListerer;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcessListener;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcesspro;
/*      */ import org.jeecgframework.workflow.service.ActivitiService;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Controller;
/*      */ import org.springframework.web.bind.annotation.RequestMapping;
/*      */ import org.springframework.web.bind.annotation.ResponseBody;
/*      */ import org.springframework.web.servlet.ModelAndView;
/*      */ 
/*      */ @Controller
/*      */ @RequestMapping({"/processController"})
/*      */ public class ProcessController extends BaseController
/*      */ {
/*   60 */   private static final Logger logger = Logger.getLogger(ProcessController.class);
/*      */   private UserService userService;
/*      */   private SystemService systemService;
/*      */   private String message;
/*      */   private ActivitiService activitiService;
/*      */   protected RepositoryService repositoryService;
/*      */ 
/*      */   public String getMessage()
/*      */   {
/*   68 */     return this.message;
/*      */   }
/*      */ 
/*      */   public void setMessage(String message) {
/*   72 */     this.message = message;
/*      */   }
/*      */   @Autowired
/*      */   public void setActivitiService(ActivitiService activitiService) {
/*   77 */     this.activitiService = activitiService;
/*      */   }
/*      */   @Autowired
/*      */   public void setSystemService(SystemService systemService) {
/*   82 */     this.systemService = systemService;
/*      */   }
/*      */ 
/*      */   public UserService getUserService() {
/*   86 */     return this.userService;
/*      */   }
/*      */   @Autowired
/*      */   public void setUserService(UserService userService) {
/*   91 */     this.userService = userService;
/*      */   }
/*      */   @Autowired
/*      */   public void setRepositoryService(RepositoryService repositoryService) {
/*   96 */     this.repositoryService = repositoryService;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processDesigner"})
/*      */   public ModelAndView processDesigner(HttpServletRequest request)
/*      */   {
/*  106 */     String processid = oConvertUtils.getString(request.getParameter("id"), "0");
/*  107 */     request.setAttribute("processid", processid);
/*  108 */     return new ModelAndView("designer/index");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processProperties"})
/*      */   public ModelAndView processProperties(HttpServletRequest request)
/*      */   {
/*  118 */     String turn = oConvertUtils.getString(request.getParameter("turn"));
/*  119 */     String id = oConvertUtils.getString(request.getParameter("id"));
/*  120 */     String checkbox = oConvertUtils.getString(request.getParameter("checkbox"));
/*  121 */     String processId = oConvertUtils.getString(request.getParameter("processId"));
/*  122 */     TPProcess tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processId);
/*  123 */     if (tProcess != null) {
/*  124 */       request.setAttribute("processDefinitionId", tProcess.getId());
/*      */     }
/*  126 */     TSTypegroup typegroup = (TSTypegroup)this.systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", "process");
/*  127 */     List proTypeList = this.systemService.findByProperty(TSType.class, "TSTypegroup.id", typegroup.getId());
/*  128 */     request.setAttribute("checkbox", checkbox);
/*  129 */     request.setAttribute("id", id);
/*  130 */     request.setAttribute("proTypeList", proTypeList);
/*  131 */     request.setAttribute("processId", processId);
/*  132 */     return new ModelAndView("designer/" + turn);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processIframe"})
/*      */   public ModelAndView processIframe(HttpServletRequest request)
/*      */   {
/*  142 */     String typeid = request.getParameter("typeid");
/*  143 */     request.setAttribute("typeid", typeid);
/*  144 */     List typegroupList = this.systemService.findByProperty(TSTypegroup.class, "typegroupcode", "process");
/*  145 */     request.setAttribute("typegroupList", typegroupList);
/*  146 */     return new ModelAndView("workflow/process/processIframe");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processComboBox"})
/*      */   @ResponseBody
/*      */   public List<ComboBox> processComboBox(HttpServletResponse response, HttpServletRequest request)
/*      */   {
/*  157 */     ComboBox comboBox = new ComboBox();
/*  158 */     comboBox.setId("typecode");
/*  159 */     comboBox.setText("typename");
/*  160 */     List comboBoxs = new ArrayList();
/*  161 */     TSTypegroup typegroup = (TSTypegroup)this.systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", "process");
/*  162 */     List proTypeList = this.systemService.findByProperty(TSType.class, "TSTypegroup.id", typegroup.getId());
/*  163 */     comboBoxs = TagUtil.getComboBox(proTypeList, null, comboBox);
/*  164 */     return comboBoxs;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processTypeTree"})
/*      */   @ResponseBody
/*      */   public List<ComboTree> processTypeTree(HttpServletRequest request, ComboTree comboTree)
/*      */   {
/*  173 */     CriteriaQuery cq = new CriteriaQuery(TSType.class);
/*  174 */     if (comboTree.getId() != null) {
/*  175 */       cq.eq("TSType.id", comboTree.getId());
/*      */     }
/*  177 */     if (comboTree.getId() == null) {
/*  178 */       cq.isNull("TSType");
/*      */     }
/*      */ 
/*  181 */     cq.add();
/*  182 */     List typeList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
/*  183 */     List comboTrees = new ArrayList();
/*  184 */     ComboTreeModel comboTreeModel = new ComboTreeModel("id", "typename", "TSTypes", "typecode");
/*  185 */     comboTrees = this.systemService.ComboTree(typeList, comboTreeModel, null);
/*  186 */     return comboTrees;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processList"})
/*      */   public ModelAndView processList(HttpServletRequest request)
/*      */   {
/*  196 */     String typeid = request.getParameter("typeid");
/*  197 */     request.setAttribute("typeid", typeid);
/*  198 */     return new ModelAndView("workflow/process/processList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getProcessXml"})
/*      */   @ResponseBody
/*      */   public void getProcessXml(HttpServletRequest request, HttpServletResponse response)
/*      */   {
/*  210 */     response.setContentType("text/xml;charset=UTF-8");
/*  211 */     String processId = oConvertUtils.getString(request.getParameter("processId"));
/*  212 */     TPProcess tProcess = (TPProcess)this.systemService.getEntity(TPProcess.class, processId);
/*      */     try
/*      */     {
/*  215 */       String retstr = StreamUtils.InputStreamTOString(StreamUtils.byteTOInputStream(tProcess.getProcessxml()));
/*  216 */       response.getWriter().write(retstr);
/*      */     } catch (Exception e1) {
/*  218 */       e1.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveProcess"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveProcess(HttpServletRequest request)
/*      */   {
/*  228 */     AjaxJson j = new AjaxJson();
/*  229 */     String processDefinitionId = oConvertUtils.getString(request.getParameter("processDefinitionId"));
/*  230 */     String processDescriptor = oConvertUtils.getString(request.getParameter("processDescriptor"));
/*  231 */     String processName = oConvertUtils.getString(request.getParameter("processName"));
/*  232 */     String processkey = oConvertUtils.getString(request.getParameter("processkey"));
/*  233 */     String params = oConvertUtils.getString(request.getParameter("params"));
/*  234 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/*  235 */     String nodes = oConvertUtils.getString(request.getParameter("nodes"));
/*  236 */     TSType tsType = (TSType)this.systemService.getEntity(TSType.class, typeid);
/*  237 */     TPProcess tProcess = (TPProcess)this.systemService.getEntity(TPProcess.class, processDefinitionId);
/*  238 */     if (tProcess != null) {
/*  239 */       tProcess.setProcessname(processName);
/*  240 */       tProcess.setTSType(tsType);
/*  241 */       tProcess.setProcesskey(processkey);
/*  242 */       if (!processDescriptor.equals("")) {
/*  243 */         tProcess.setProcessxml(StreamUtils.StringTObyte(processDescriptor));
/*      */       }
/*  245 */       this.systemService.updateEntitie(tProcess);
/*  246 */       j.setMsg("流程修改成功");
/*      */     } else {
/*  248 */       List processes = this.systemService.findByProperty(TPProcess.class, "processkey", processkey);
/*  249 */       if (processes.size() == 0) {
/*  250 */         tProcess = new TPProcess();
/*  251 */         tProcess.setProcessname(processName);
/*  252 */         tProcess.setProcessstate(Globals.Process_Deploy_NO);
/*  253 */         tProcess.setTSType(tsType);
/*  254 */         tProcess.setProcesskey(processkey);
/*  255 */         if (!processDescriptor.equals("")) {
/*  256 */           tProcess.setProcessxml(StreamUtils.StringTObyte(processDescriptor));
/*      */         }
/*  258 */         this.systemService.save(tProcess);
/*  259 */         j.setMsg("流程创建成功");
/*      */       } else {
/*  261 */         j.setMsg("流程ID已存在");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  304 */     if ((nodes != null) && (nodes.length() > 3)) {
/*  305 */       String[] temp = nodes.split("@@@");
/*  306 */       for (int i = 0; i < temp.length; i++) {
/*  307 */         TPProcessnode tProcessnode = null;
/*  308 */         String[] fileds = temp[i].split("###");
/*  309 */         String tid = fileds[0].substring(3);
/*  310 */         String name = fileds[1].substring(9);
/*  311 */         tProcessnode = this.activitiService.getTPProcessnode(tid, processkey);
/*  312 */         if (tProcessnode == null) {
/*  313 */           tProcessnode = new TPProcessnode();
/*  314 */           tProcessnode.setProcessnodecode(tid);
/*  315 */           tProcessnode.setProcessnodename(name);
/*  316 */           tProcessnode.setTPProcess(tProcess);
/*  317 */           tProcessnode.setTPForm(null);
/*  318 */           this.systemService.save(tProcessnode);
/*      */         } else {
/*  320 */           tProcessnode.setProcessnodecode(tid);
/*  321 */           tProcessnode.setProcessnodename(name);
/*  322 */           tProcessnode.setTPProcess(tProcess);
/*  323 */           tProcessnode.setTPForm(null);
/*  324 */           this.systemService.updateEntitie(tProcessnode);
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  330 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addOrupdateVariable"})
/*      */   public ModelAndView addOrupdateVariable(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  342 */     TPProcessnode processnode = null;
/*  343 */     String processproid = ResourceUtil.getParameter("processproid");
/*  344 */     String processId = ResourceUtil.getParameter("processId");
/*  345 */     String processNode = request.getParameter("processNode");
/*  346 */     String processDefinitionId = request.getParameter("processDefinitionId");
/*  347 */     request.setAttribute("processid", processId);
/*  348 */     if (processpro.getId() != null) {
/*  349 */       processpro = (TPProcesspro)this.systemService.getEntity(TPProcesspro.class, processpro.getId());
/*  350 */       processnode = processpro.getTPProcessnode();
/*  351 */       request.setAttribute("processpro", processpro);
/*  352 */       request.setAttribute("processnode", processnode);
/*      */     }
/*  354 */     request.setAttribute("processId", processId);
/*  355 */     request.setAttribute("processNode", processNode);
/*  356 */     request.setAttribute("processDefinitionId", processDefinitionId);
/*  357 */     return new ModelAndView("designer/processpro");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveVariable"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveVariable(TPProcesspro tProcesspro, HttpServletRequest request)
/*      */   {
/*  367 */     AjaxJson j = new AjaxJson();
/*  368 */     String processproId = ResourceUtil.getParameter("processproid");
/*  369 */     String processId = ResourceUtil.getParameter("processId");
/*  370 */     String processNode = ResourceUtil.getParameter("procesnode");
/*  371 */     String processDefinitionId = ResourceUtil.getParameter("processDefinitionId");
/*  372 */     TPProcess tProcess = null;
/*  373 */     TPProcessnode tProcessnode = null;
/*  374 */     if (StringUtil.isNotEmpty(processDefinitionId)) {
/*  375 */       tProcess = (TPProcess)this.systemService.getEntity(TPProcess.class, oConvertUtils.getString(processDefinitionId));
/*      */     }
/*  377 */     else if (StringUtil.isNotEmpty(processId)) {
/*  378 */       tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processId);
/*  379 */       if (tProcess == null) {
/*  380 */         tProcess = new TPProcess();
/*  381 */         tProcess.setProcesskey(processId);
/*  382 */         this.systemService.save(tProcess);
/*      */       } else {
/*  384 */         this.systemService.updateEntitie(tProcess);
/*      */       }
/*      */     }
/*      */ 
/*  388 */     if (StringUtil.isNotEmpty(processNode)) {
/*  389 */       tProcessnode = (TPProcessnode)this.systemService.findUniqueByProperty(TPProcessnode.class, "processnodecode", processNode);
/*  390 */       if (tProcessnode == null) {
/*  391 */         tProcessnode = new TPProcessnode();
/*  392 */         tProcessnode.setTPProcess(tProcess);
/*  393 */         tProcessnode.setTPForm(null);
/*  394 */         tProcessnode.setProcessnodecode(processNode);
/*  395 */         this.systemService.save(tProcessnode);
/*      */       } else {
/*  397 */         tProcessnode.setTPProcess(tProcess);
/*  398 */         tProcessnode.setTPForm(null);
/*  399 */         tProcessnode.setProcessnodecode(processNode);
/*  400 */         this.systemService.updateEntitie(tProcessnode);
/*      */       }
/*      */     }
/*      */ 
/*  404 */     if (StringUtil.isNotEmpty(processproId)) {
/*  405 */       tProcesspro.setTPProcess(tProcess);
/*  406 */       this.systemService.updateEntitie(tProcesspro);
/*      */     } else {
/*  408 */       tProcesspro.setTPProcess(tProcess);
/*  409 */       tProcesspro.setTPProcessnode(tProcessnode);
/*  410 */       this.systemService.save(tProcesspro);
/*      */     }
/*  412 */     j.setMsg("变量保存成功!");
/*      */ 
/*  414 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"deleteVariable"})
/*      */   @ResponseBody
/*      */   public AjaxJson deleteVariable(HttpServletRequest request)
/*      */   {
/*  426 */     AjaxJson j = new AjaxJson();
/*  427 */     String variableId = oConvertUtils.getString(request.getParameter("variableId"));
/*  428 */     this.systemService.deleteEntityById(TPProcesspro.class, variableId);
/*  429 */     j.setMsg("变量删除成功!");
/*      */ 
/*  431 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getVariables"})
/*      */   @ResponseBody
/*      */   public void getVariables(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  443 */     String processNode = oConvertUtils.getString(request.getParameter("processNode"));
/*  444 */     String processId = oConvertUtils.getString(request.getParameter("processId"));
/*  445 */     TPProcess tProcess = null;
/*  446 */     if (StringUtil.isNotEmpty(processId)) {
/*  447 */       tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processId);
/*      */     }
/*  449 */     if (tProcess != null) {
/*  450 */       CriteriaQuery cq = new CriteriaQuery(TPProcesspro.class, dataGrid);
/*  451 */       cq.createAlias("TPProcessnode", "TPProcessnode");
/*  452 */       cq.eq("TPProcessnode.processnodecode", processNode);
/*  453 */       cq.eq("TPProcess.id", tProcess.getId());
/*  454 */       cq.add();
/*  455 */       this.systemService.getDataGridReturn(cq, true);
/*  456 */       TagUtil.datagrid(response, dataGrid);
/*      */     }
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getVariable"})
/*      */   @ResponseBody
/*      */   public void getVariable(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  469 */     String variableId = oConvertUtils.getString(request.getParameter("variableId"));
/*  470 */     CriteriaQuery cq = new CriteriaQuery(TPProcesspro.class, dataGrid);
/*  471 */     cq.eq("processproid", variableId);
/*  472 */     cq.add();
/*  473 */     this.systemService.getDataGridReturn(cq, true);
/*  474 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processTabs"})
/*      */   public ModelAndView processTabs(HttpServletRequest request)
/*      */   {
/*  484 */     String processid = request.getParameter("processid");
/*  485 */     request.setAttribute("processid", processid);
/*  486 */     return new ModelAndView("workflow/process/processTabs");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processpro"})
/*      */   public ModelAndView processpro(HttpServletRequest request)
/*      */   {
/*  496 */     String processid = request.getParameter("processid");
/*  497 */     request.setAttribute("processid", processid);
/*  498 */     return new ModelAndView("workflow/process/processproList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"busbase"})
/*      */   public ModelAndView busbase(HttpServletRequest request)
/*      */   {
/*  508 */     String processid = request.getParameter("processid");
/*  509 */     request.setAttribute("processid", processid);
/*  510 */     return new ModelAndView("workflow/process/busbaseList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processtype"})
/*      */   public ModelAndView processtype()
/*      */   {
/*  520 */     return new ModelAndView("workflow/process/processtypeList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processnode"})
/*      */   public ModelAndView processnode(HttpServletRequest request)
/*      */   {
/*  530 */     String processid = request.getParameter("processid");
/*  531 */     request.setAttribute("processid", processid);
/*  532 */     return new ModelAndView("workflow/processnode/processnodeList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processGrid"})
/*      */   public void processGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  541 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/*  542 */     CriteriaQuery cq = new CriteriaQuery(TPProcess.class, dataGrid);
/*  543 */     if (StringUtil.isNotEmpty(typeid)) {
/*  544 */       cq.eq("TSType.id", typeid);
/*  545 */       cq.add();
/*      */     }
/*  547 */     this.systemService.getDataGridReturn(cq, true);
/*  548 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processproList"})
/*      */   public void processproList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  557 */     String processid = request.getParameter("processid");
/*  558 */     CriteriaQuery cq = new CriteriaQuery(TPProcesspro.class, dataGrid);
/*  559 */     if (StringUtil.isNotEmpty(processid)) {
/*  560 */       cq.eq("TPProcess.id", processid);
/*  561 */       cq.add();
/*      */     }
/*  563 */     this.systemService.getDataGridReturn(cq, true);
/*  564 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridBus"})
/*      */   public void datagridBus(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  578 */     String processid = request.getParameter("processid");
/*  579 */     CriteriaQuery cq = new CriteriaQuery(TSBusConfig.class, dataGrid);
/*  580 */     if (StringUtil.isNotEmpty(processid)) {
/*  581 */       cq.eq("TPProcess.id", processid);
/*  582 */       cq.add();
/*      */     }
/*  584 */     this.systemService.getDataGridReturn(cq, true);
/*  585 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridtype"})
/*      */   public void datagridtype(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  599 */     CriteriaQuery cq = new CriteriaQuery(TSType.class, dataGrid);
/*  600 */     this.systemService.getDataGridReturn(cq, true);
/*  601 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridNode"})
/*      */   public void datagridNode(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  616 */     String processid = request.getParameter("processid");
/*  617 */     CriteriaQuery cq = new CriteriaQuery(TPProcessnode.class, dataGrid);
/*  618 */     if (StringUtil.isNotEmpty(processid)) {
/*  619 */       cq.eq("TPProcess.id", processid);
/*  620 */       cq.add();
/*      */     }
/*  622 */     this.systemService.getDataGridReturn(cq, true);
/*  623 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delType"})
/*      */   @ResponseBody
/*      */   public AjaxJson delType(TSType processtype, HttpServletRequest request)
/*      */   {
/*  634 */     AjaxJson j = new AjaxJson();
/*  635 */     processtype = (TSType)this.systemService.getEntity(TSType.class, processtype.getId());
/*  636 */     this.message = ("流程类别: " + processtype.getTypename() + "被删除 成功");
/*  637 */     this.systemService.delete(processtype);
/*  638 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/*  640 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delBus"})
/*      */   @ResponseBody
/*      */   public AjaxJson delBus(TSBusConfig busbase, HttpServletRequest request)
/*      */   {
/*  651 */     AjaxJson j = new AjaxJson();
/*  652 */     busbase = (TSBusConfig)this.systemService.getEntity(TSBusConfig.class, busbase.getId());
/*  653 */     this.message = ("流程类别: " + busbase.getBusname() + "被删除 成功");
/*  654 */     this.systemService.delete(busbase);
/*  655 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/*  657 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delprocess"})
/*      */   @ResponseBody
/*      */   public AjaxJson delprocess(TPProcess process, HttpServletRequest request)
/*      */   {
/*  668 */     AjaxJson j = new AjaxJson();
/*  669 */     process = (TPProcess)this.systemService.getEntity(TPProcess.class, process.getId());
/*  670 */     this.message = ("流程: " + process.getProcessname() + "被删除 成功");
/*  671 */     this.systemService.delete(process);
/*  672 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/*  674 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delPro"})
/*      */   @ResponseBody
/*      */   public AjaxJson delPro(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  685 */     AjaxJson j = new AjaxJson();
/*  686 */     processpro = (TPProcesspro)this.systemService.getEntity(TPProcesspro.class, processpro.getId());
/*  687 */     this.message = ("流程类别: " + processpro.getProcessproname() + "被删除 成功");
/*  688 */     this.systemService.delete(processpro);
/*  689 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/*  691 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delNode"})
/*      */   @ResponseBody
/*      */   public AjaxJson delNode(TPProcessnode processnode, HttpServletRequest request)
/*      */   {
/*  702 */     AjaxJson j = new AjaxJson();
/*  703 */     processnode = (TPProcessnode)this.systemService.getEntity(TPProcessnode.class, processnode.getId());
/*  704 */     this.message = ("流程节点: " + processnode.getProcessnodename() + "被删除 成功");
/*  705 */     this.systemService.delete(processnode);
/*  706 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/*  708 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveType"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveType(TSType processtype, HttpServletRequest request)
/*      */   {
/*  720 */     AjaxJson j = new AjaxJson();
/*  721 */     if (processtype.getId() != null) {
/*  722 */       this.message = ("流程类型: " + processtype.getTypename() + "被更新成功");
/*  723 */       this.userService.saveOrUpdate(processtype);
/*  724 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/*  726 */       this.message = ("流程类型: " + processtype.getTypename() + "被添加成功");
/*  727 */       this.userService.saveOrUpdate(processtype);
/*  728 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  731 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveBus"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveBus(TSBusConfig busbase, HttpServletRequest request)
/*      */   {
/*  743 */     AjaxJson j = new AjaxJson();
/*  744 */     if (StringUtil.isNotEmpty(busbase.getId())) {
/*  745 */       this.message = ("业务参数: " + busbase.getBusname() + "被更新成功");
/*  746 */       this.userService.saveOrUpdate(busbase);
/*  747 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/*  749 */       this.message = ("业务参数: " + busbase.getBusname() + "被添加成功");
/*  750 */       this.userService.save(busbase);
/*  751 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  754 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"savePro"})
/*      */   @ResponseBody
/*      */   public AjaxJson savePro(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  766 */     AjaxJson j = new AjaxJson();
/*  767 */     if (StringUtil.isNotEmpty(processpro.getId())) {
/*  768 */       this.message = ("流程参数: " + processpro.getProcessproname() + "被更新成功");
/*  769 */       this.userService.saveOrUpdate(processpro);
/*  770 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/*  772 */       this.message = ("流程参数: " + processpro.getProcessproname() + "被添加成功");
/*  773 */       this.userService.save(processpro);
/*  774 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  777 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveNode"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveNode(TPProcessnode processnode, HttpServletRequest request)
/*      */   {
/*  789 */     AjaxJson j = new AjaxJson();
/*  790 */     String formid = oConvertUtils.getString(request.getAttribute("fromid"));
/*  791 */     TPForm form = (TPForm)this.systemService.getEntity(TPForm.class, formid);
/*  792 */     processnode.setTPForm(form);
/*  793 */     if (StringUtil.isNotEmpty(processnode.getId())) {
/*  794 */       this.message = ("流程节点: " + processnode.getProcessnodename() + "被更新成功");
/*  795 */       this.userService.saveOrUpdate(processnode);
/*  796 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/*  798 */       this.message = ("流程节点: " + processnode.getProcessnodename() + "被添加成功");
/*  799 */       this.userService.save(processnode);
/*  800 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  803 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateType"})
/*      */   public ModelAndView addorupdateType(TSType processtype, HttpServletRequest req)
/*      */   {
/*  813 */     if (StringUtil.isNotEmpty(processtype.getId())) {
/*  814 */       processtype = (TSType)this.systemService.getEntity(TSType.class, processtype.getId());
/*  815 */       req.setAttribute("processtype", processtype);
/*      */     }
/*  817 */     return new ModelAndView("workflow/process/processtype");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateBus"})
/*      */   public ModelAndView addorupdateBus(TSBusConfig busbase, HttpServletRequest req)
/*      */   {
/*  827 */     String processid = req.getParameter("processid");
/*  828 */     TSType type = (TSType)this.systemService.findUniqueByProperty(TSType.class, "typecode", "t_b");
/*  829 */     if (type != null) {
/*  830 */       List tableList = this.systemService.findByProperty(TSTable.class, "TSType.id", type.getId());
/*  831 */       req.setAttribute("tableList", tableList);
/*      */     }
/*  833 */     if (StringUtil.isNotEmpty(busbase.getId())) {
/*  834 */       busbase = (TSBusConfig)this.systemService.getEntity(TSBusConfig.class, busbase.getId());
/*  835 */       req.setAttribute("busbase", busbase);
/*      */     }
/*  837 */     req.setAttribute("processid", processid);
/*  838 */     return new ModelAndView("workflow/process/busbase");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdatePro"})
/*      */   public ModelAndView addorupdatePro(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  848 */     String processid = request.getParameter("processid");
/*  849 */     request.setAttribute("processid", processid);
/*  850 */     List nodeList = this.systemService.findByProperty(TPProcessnode.class, "TPProcess.id", processid);
/*  851 */     request.setAttribute("nodeList", nodeList);
/*  852 */     List typeList = this.systemService.loadAll(TSType.class);
/*  853 */     request.setAttribute("typeList", typeList);
/*  854 */     List forms = this.systemService.loadAll(TPForm.class);
/*  855 */     request.setAttribute("forms", forms);
/*  856 */     if (StringUtil.isNotEmpty(processpro.getId())) {
/*  857 */       processpro = (TPProcesspro)this.systemService.getEntity(TPProcesspro.class, processpro.getId());
/*  858 */       request.setAttribute("processpro", processpro);
/*      */     }
/*  860 */     return new ModelAndView("workflow/process/processpro");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateNode"})
/*      */   public ModelAndView addorupdateNode(TPProcessnode processnode, HttpServletRequest request)
/*      */   {
/*  870 */     String processid = request.getParameter("processid");
/*  871 */     request.setAttribute("processid", processid);
/*  872 */     List processList = this.systemService.loadAll(TPProcess.class);
/*  873 */     request.setAttribute("processList", processList);
/*  874 */     List formList = this.systemService.loadAll(TPForm.class);
/*  875 */     request.setAttribute("formList", formList);
/*  876 */     if (processnode.getId() != null) {
/*  877 */       processnode = (TPProcessnode)this.systemService.getEntity(TPProcessnode.class, processnode.getId());
/*  878 */       request.setAttribute("processnode", processnode);
/*      */     }
/*  880 */     return new ModelAndView("workflow/processnode/processnode");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"deployProcess"})
/*      */   @ResponseBody
/*      */   public AjaxJson deployProcess(HttpServletRequest request)
/*      */   {
/*  892 */     AjaxJson j = new AjaxJson();
/*  893 */     String processid = request.getParameter("processid");
/*  894 */     TPProcess process = (TPProcess)this.systemService.getEntity(TPProcess.class, processid);
/*  895 */     if (process != null) {
/*      */       try {
/*  897 */         this.repositoryService.createDeployment().addInputStream(process.getProcesskey() + ".bpmn20.xml", StreamUtils.byteTOInputStream(process.getProcessxml())).deploy();
/*  898 */         process.setProcessstate(Globals.Process_Deploy_YES);
/*  899 */         this.systemService.updateEntitie(process);
/*  900 */         this.message = "发布成功";
/*      */       } catch (Exception e) {
/*  902 */         this.message = "发布失败";
/*      */       }
/*      */     }
/*  905 */     j.setMsg(this.message);
/*  906 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"form"})
/*      */   public ModelAndView form(HttpServletRequest request)
/*      */   {
/*  921 */     return new ModelAndView("workflow/form/formsList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"formpro"})
/*      */   public ModelAndView formpro(HttpServletRequest request)
/*      */   {
/*  931 */     String formid = request.getParameter("formid");
/*  932 */     TPForm form = (TPForm)this.systemService.get(TPForm.class, formid);
/*  933 */     request.setAttribute("form", form);
/*  934 */     return new ModelAndView("workflow/form/formproList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridForm"})
/*      */   public void datagridForm(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  951 */     CriteriaQuery cq = new CriteriaQuery(TPForm.class, dataGrid);
/*      */ 
/*  955 */     this.systemService.getDataGridReturn(cq, true);
/*  956 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridFPro"})
/*      */   public void datagridFPro(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  970 */     String formid = request.getParameter("formid");
/*  971 */     CriteriaQuery cq = new CriteriaQuery(TPFormpro.class, dataGrid);
/*  972 */     if (StringUtil.isNotEmpty(formid)) {
/*  973 */       cq.eq("TPForm.id", formid);
/*  974 */       cq.add();
/*      */     }
/*  976 */     this.systemService.getDataGridReturn(cq, true);
/*  977 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateForm"})
/*      */   public ModelAndView addorupdateForm(TPForm form, HttpServletRequest req)
/*      */   {
/*  987 */     List processList = this.systemService.loadAll(TPProcess.class);
/*  988 */     req.setAttribute("processList", processList);
/*  989 */     List typeList = this.systemService.loadAll(TSType.class);
/*  990 */     req.setAttribute("typeList", typeList);
/*  991 */     if (form.getId() != null) {
/*  992 */       form = (TPForm)this.systemService.getEntity(TPForm.class, form.getId());
/*  993 */       req.setAttribute("form", form);
/*      */     }
/*  995 */     return new ModelAndView("workflow/form/form");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateFPro"})
/*      */   public ModelAndView addorupdateFPro(TPFormpro formpro, HttpServletRequest req)
/*      */   {
/* 1005 */     String formid = req.getParameter("formid");
/* 1006 */     req.setAttribute("formid", formid);
/* 1007 */     String processid = req.getParameter("processid");
/* 1008 */     req.setAttribute("processid", processid);
/* 1009 */     List typeList = this.systemService.loadAll(TSType.class);
/* 1010 */     req.setAttribute("typeList", typeList);
/* 1011 */     if (formpro.getId() != null) {
/* 1012 */       formpro = (TPFormpro)this.systemService.getEntity(TPFormpro.class, formpro.getId());
/* 1013 */       req.setAttribute("formpro", formpro);
/*      */     }
/* 1015 */     return new ModelAndView("workflow/form/formpro");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveForm"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveForm(TPForm form, HttpServletRequest request)
/*      */   {
/* 1027 */     AjaxJson j = new AjaxJson();
/* 1028 */     if (StringUtil.isNotEmpty(form.getId())) {
/* 1029 */       this.message = ("表单: " + form.getFormname() + "被更新成功");
/* 1030 */       this.systemService.saveOrUpdate(form);
/* 1031 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/* 1033 */       this.message = ("表单: " + form.getFormname() + "被添加成功");
/* 1034 */       this.userService.save(form);
/* 1035 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/* 1038 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveFPro"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveFPro(TPFormpro formpro, HttpServletRequest request)
/*      */   {
/* 1050 */     AjaxJson j = new AjaxJson();
/* 1051 */     if (StringUtil.isNotEmpty(formpro.getId())) {
/* 1052 */       this.message = ("表单参数: " + formpro.getFormproname() + "被更新成功");
/* 1053 */       this.systemService.saveOrUpdate(formpro);
/* 1054 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/* 1056 */       this.message = ("表单参数: " + formpro.getFormproname() + "被添加成功");
/* 1057 */       this.userService.save(formpro);
/* 1058 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/* 1061 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delForm"})
/*      */   @ResponseBody
/*      */   public AjaxJson delForm(TPForm form, HttpServletRequest request)
/*      */   {
/* 1072 */     AjaxJson j = new AjaxJson();
/* 1073 */     form = (TPForm)this.systemService.getEntity(TPForm.class, form.getId());
/* 1074 */     this.message = ("表单: " + form.getFormname() + "被删除 成功");
/* 1075 */     this.systemService.delete(form);
/* 1076 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/* 1078 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delFPro"})
/*      */   @ResponseBody
/*      */   public AjaxJson delFPro(TPFormpro formpro, HttpServletRequest request)
/*      */   {
/* 1089 */     AjaxJson j = new AjaxJson();
/* 1090 */     formpro = (TPFormpro)this.systemService.getEntity(TPFormpro.class, formpro.getId());
/* 1091 */     this.message = ("表单参数: " + formpro.getFormproname() + "被删除 成功");
/* 1092 */     this.systemService.delete(formpro);
/* 1093 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */ 
/* 1095 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addpro"})
/*      */   public ModelAndView addpro(HttpServletRequest request)
/*      */   {
/* 1139 */     String typeid = request.getParameter("id");
/* 1140 */     request.setAttribute("typeid", typeid);
/* 1141 */     return new ModelAndView("workflow/process/process");
/*      */   }
/*      */   @RequestMapping(params={"choosePro"})
/*      */   public ModelAndView choosePro(HttpServletRequest request) {
/* 1146 */     List formList = this.systemService.loadAll(TPForm.class);
/* 1147 */     request.setAttribute("formList", formList);
/* 1148 */     return new ModelAndView("workflow/process/process");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"chooseListener"})
/*      */   public ModelAndView chooseListener(HttpServletRequest request)
/*      */   {
/* 1159 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/* 1160 */     return new ModelAndView("designer/listenerList", "typeid", typeid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"listenerGrid"})
/*      */   @ResponseBody
/*      */   public void listenerGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/* 1172 */     Short listenerstate = oConvertUtils.getShort(request.getParameter("status"));
/* 1173 */     Short typeid = oConvertUtils.getShort(request.getParameter("typeid"));
/* 1174 */     CriteriaQuery cq = new CriteriaQuery(TPListerer.class, dataGrid);
/* 1175 */     if (StringUtil.isNotEmpty(listenerstate)) {
/* 1176 */       cq.eq("listenerstate", listenerstate);
/*      */     }
/* 1178 */     if (StringUtil.isNotEmpty(typeid)) {
/* 1179 */       cq.eq("typeid", typeid);
/*      */     }
/* 1181 */     cq.add();
/* 1182 */     this.systemService.getDataGridReturn(cq, true);
/* 1183 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveListener"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveListener(TPListerer tpListerer, HttpServletRequest request)
/*      */   {
/* 1195 */     AjaxJson j = new AjaxJson();
/* 1196 */     String event = "";
/* 1197 */     Short typeid = oConvertUtils.getShort(request.getParameter("typeid"));
/* 1198 */     if (typeid.equals(Globals.Listener_Type_Excution))
/* 1199 */       event = oConvertUtils.getString(request.getParameter("executioneven"));
/*      */     else {
/* 1201 */       event = oConvertUtils.getString(request.getParameter("taskeven"));
/*      */     }
/* 1203 */     tpListerer.setListenereven(event);
/* 1204 */     if (StringUtil.isNotEmpty(tpListerer.getId())) {
/* 1205 */       this.message = ("监听: " + tpListerer.getListenername() + "更新成功");
/* 1206 */       this.userService.saveOrUpdate(tpListerer);
/* 1207 */       this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*      */     } else {
/* 1209 */       this.message = ("监听: " + tpListerer.getListenername() + "添加成功");
/* 1210 */       tpListerer.setListenerstate(Globals.Listener_No);
/* 1211 */       this.userService.save(tpListerer);
/* 1212 */       this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
/*      */     }
/* 1214 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delListeren"})
/*      */   @ResponseBody
/*      */   public AjaxJson delListeren(TPListerer tpListerer, HttpServletRequest request)
/*      */   {
/* 1225 */     AjaxJson j = new AjaxJson();
/* 1226 */     tpListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, tpListerer.getId());
/* 1227 */     if (tpListerer.getTProcessListeners().size() == 0) {
/* 1228 */       this.message = ("监听: " + tpListerer.getListenername() + " 删除成功");
/* 1229 */       this.systemService.delete(tpListerer);
/* 1230 */       this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/*      */     }
/*      */     else {
/* 1233 */       this.message = ("监听: " + tpListerer.getListenername() + "已经在运行中无法删除");
/*      */     }
/*      */ 
/* 1236 */     j.setMsg(this.message);
/* 1237 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"setListeren"})
/*      */   @ResponseBody
/*      */   public AjaxJson setListeren(HttpServletRequest request)
/*      */   {
/* 1249 */     AjaxJson j = new AjaxJson();
/* 1250 */     String id = request.getParameter("id");
/* 1251 */     Short status = oConvertUtils.getShort(request.getParameter("status"));
/* 1252 */     TPListerer tpListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, id);
/* 1253 */     if (tpListerer != null) {
/* 1254 */       tpListerer.setListenerstate(status);
/* 1255 */       this.systemService.updateEntitie(tpListerer);
/* 1256 */       if (status.equals(Globals.Listener_No))
/* 1257 */         j.setMsg("监听已禁用");
/*      */       else {
/* 1259 */         j.setMsg("监听已启用");
/*      */       }
/*      */     }
/* 1262 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"setProcessListener"})
/*      */   @ResponseBody
/*      */   public AjaxJson setProcessListener(HttpServletRequest request)
/*      */   {
/* 1274 */     AjaxJson j = new AjaxJson();
/* 1275 */     String id = request.getParameter("id");
/* 1276 */     TPProcessListener tpProcessListener = (TPProcessListener)this.systemService.getEntity(TPProcessListener.class, id);
/* 1277 */     if (tpProcessListener != null) {
/* 1278 */       Short status = Globals.Process_Listener_NO.equals(tpProcessListener.getStatus()) ? Globals.Process_Listener_YES : Globals.Process_Listener_NO;
/* 1279 */       tpProcessListener.setStatus(status);
/* 1280 */       this.systemService.updateEntitie(tpProcessListener);
/* 1281 */       if (status.equals(Globals.Process_Listener_NO)) {
/* 1282 */         j.setSuccess(false);
/* 1283 */         j.setMsg("监听已禁用");
/*      */       } else {
/* 1285 */         j.setMsg("监听已启用");
/*      */       }
/*      */     }
/* 1288 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delProcesListeren"})
/*      */   @ResponseBody
/*      */   public AjaxJson delProcesListeren(TPProcessListener tpProcessListener, HttpServletRequest request)
/*      */   {
/* 1299 */     AjaxJson j = new AjaxJson();
/* 1300 */     tpProcessListener = (TPProcessListener)this.systemService.getEntity(TPProcessListener.class, tpProcessListener.getId());
/* 1301 */     this.message = ("监听: " + tpProcessListener.getTPListerer().getListenername() + " 删除成功");
/* 1302 */     this.systemService.delete(tpProcessListener);
/* 1303 */     this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
/* 1304 */     j.setMsg(this.message);
/* 1305 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getNodelisteners"})
/*      */   @ResponseBody
/*      */   public void getNodelisteners(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/* 1317 */     String type = oConvertUtils.getString(request.getParameter("type"));
/* 1318 */     String processNode = oConvertUtils.getString(request.getParameter("processNode"));
/* 1319 */     String processkey = oConvertUtils.getString(request.getParameter("processId"));
/* 1320 */     CriteriaQuery cq = new CriteriaQuery(TPProcessListener.class, dataGrid);
/* 1321 */     if (type.equals(Globals.Listener_Type_Task)) {
/* 1322 */       TPProcessnode tProcessnode = this.activitiService.getTPProcessnode(processNode, processkey);
/* 1323 */       if (tProcessnode != null)
/* 1324 */         cq.eq("TPProcessnode.id", tProcessnode.getId());
/*      */     }
/*      */     else {
/* 1327 */       cq.eq("nodename", processNode);
/*      */     }
/* 1329 */     cq.add();
/* 1330 */     this.systemService.getDataGridReturn(cq, false);
/* 1331 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveProcessListener"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveProcessListener(HttpServletRequest request)
/*      */   {
/* 1341 */     AjaxJson j = new AjaxJson();
/* 1342 */     Short type = oConvertUtils.getShort(request.getParameter("type"));
/* 1343 */     String listenerid = oConvertUtils.getString(request.getParameter("listenerid"));
/* 1344 */     String processkey = oConvertUtils.getString(request.getParameter("processkey"));
/* 1345 */     String taskDefinitionKey = ResourceUtil.getParameter("processNode");
/* 1346 */     TPProcess tProcess = null;
/* 1347 */     TPProcessnode tProcessnode = null;
/* 1348 */     if (StringUtil.isNotEmpty(processkey)) {
/* 1349 */       tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processkey);
/* 1350 */       if (tProcess == null) {
/* 1351 */         tProcess = new TPProcess();
/* 1352 */         tProcess.setProcesskey(processkey);
/* 1353 */         this.systemService.save(tProcess);
/*      */       }
/*      */     }
/* 1356 */     if ((type.equals(Globals.Listener_Type_Task)) && 
/* 1357 */       (StringUtil.isNotEmpty(taskDefinitionKey))) {
/* 1358 */       tProcessnode = this.activitiService.getTPProcessnode(taskDefinitionKey, processkey);
/* 1359 */       if (tProcessnode == null) {
/* 1360 */         tProcessnode = new TPProcessnode();
/* 1361 */         tProcessnode.setTPProcess(tProcess);
/* 1362 */         tProcessnode.setTPForm(null);
/* 1363 */         tProcessnode.setProcessnodecode(taskDefinitionKey);
/* 1364 */         this.systemService.save(tProcessnode);
/*      */       } else {
/* 1366 */         tProcessnode.setTPProcess(tProcess);
/* 1367 */         tProcessnode.setTPForm(null);
/* 1368 */         tProcessnode.setProcessnodecode(taskDefinitionKey);
/* 1369 */         this.systemService.updateEntitie(tProcessnode);
/*      */       }
/*      */     }
/*      */ 
/* 1373 */     if (StringUtil.isNotEmpty(listenerid)) {
/* 1374 */       String[] listens = listenerid.split(",");
/* 1375 */       int len = listens.length;
/* 1376 */       for (int i = 0; i < len; i++) {
/* 1377 */         TPProcessListener tPProcessListener = new TPProcessListener();
/* 1378 */         TPListerer tPListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, listens[i]);
/* 1379 */         tPProcessListener.setTPListerer(tPListerer);
/* 1380 */         if (type.equals(Globals.Listener_Type_Task)) {
/* 1381 */           tPProcessListener.setTPProcessnode(tProcessnode);
/*      */         }
/* 1383 */         if (type.equals(Globals.Listener_Type_Excution)) {
/* 1384 */           tPProcessListener.setTPProcess(tProcess);
/* 1385 */           tPProcessListener.setNodename(taskDefinitionKey);
/*      */         }
/* 1387 */         tPProcessListener.setStatus(Globals.Process_Deploy_NO);
/* 1388 */         this.systemService.save(tPProcessListener);
/*      */       }
/*      */     }
/*      */ 
/* 1392 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"busConfigGrid"})
/*      */   public void busConfigGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/* 1401 */     CriteriaQuery cq = new CriteriaQuery(TSBusConfig.class, dataGrid);
/* 1402 */     this.systemService.getDataGridReturn(cq, true);
/* 1403 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"listenerList"})
/*      */   public ModelAndView listenerList(HttpServletRequest request)
/*      */   {
/* 1414 */     return new ModelAndView("workflow/listener/listenerList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"aouListener"})
/*      */   public ModelAndView aouListener(TPListerer tpListerer, HttpServletRequest request)
/*      */   {
/* 1425 */     if (tpListerer.getId() != null) {
/* 1426 */       tpListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, tpListerer.getId());
/* 1427 */       request.setAttribute("tpListerer", tpListerer);
/*      */     }
/* 1429 */     return new ModelAndView("workflow/listener/listener");
/*      */   }
/*      */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.ProcessController
 * JD-Core Version:    0.6.0
 */