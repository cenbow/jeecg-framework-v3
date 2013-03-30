/*     */ package org.jeecgframework.workflow.service.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import jeecg.system.pojo.base.TSBusConfig;
/*     */ import jeecg.system.pojo.base.TSRole;
/*     */ import jeecg.system.pojo.base.TSRoleUser;
/*     */ import jeecg.system.pojo.base.TSTable;
/*     */ import jeecg.system.pojo.base.TSUser;
/*     */ import org.activiti.engine.ActivitiException;
/*     */ import org.activiti.engine.HistoryService;
/*     */ import org.activiti.engine.IdentityService;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.delegate.Expression;
/*     */ import org.activiti.engine.history.HistoricProcessInstance;
/*     */ import org.activiti.engine.history.HistoricProcessInstanceQuery;
/*     */ import org.activiti.engine.identity.Group;
/*     */ import org.activiti.engine.identity.GroupQuery;
/*     */ import org.activiti.engine.identity.User;
/*     */ import org.activiti.engine.identity.UserQuery;
/*     */ import org.activiti.engine.impl.ProcessEngineImpl;
/*     */ import org.activiti.engine.impl.RepositoryServiceImpl;
/*     */ import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
/*     */ import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
/*     */ import org.activiti.engine.impl.db.DbSqlSession;
/*     */ import org.activiti.engine.impl.db.DbSqlSessionFactory;
/*     */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*     */ import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
/*     */ import org.activiti.engine.impl.task.TaskDefinition;
/*     */ import org.activiti.engine.repository.ProcessDefinition;
/*     */ import org.activiti.engine.repository.ProcessDefinitionQuery;
/*     */ import org.activiti.engine.runtime.Execution;
/*     */ import org.activiti.engine.runtime.ExecutionQuery;
/*     */ import org.activiti.engine.runtime.ProcessInstance;
/*     */ import org.activiti.engine.runtime.ProcessInstanceQuery;
/*     */ import org.activiti.engine.task.Task;
/*     */ import org.activiti.engine.task.TaskQuery;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ import org.apache.ibatis.session.SqlSession;
/*     */ import org.apache.ibatis.session.SqlSessionFactory;
/*     */ import org.jeecgframework.core.common.dao.ICommonDao;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.MyClassLoader;
/*     */ import org.jeecgframework.core.util.ReflectHelper;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.workflow.model.activiti.ActivitiCom;
/*     */ import org.jeecgframework.workflow.model.activiti.Process;
/*     */ import org.jeecgframework.workflow.model.activiti.ProcessHandle;
/*     */ import org.jeecgframework.workflow.model.activiti.WorkflowUtils;
/*     */ import org.jeecgframework.workflow.pojo.activiti.ActHiProcinst;
/*     */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*     */ import org.jeecgframework.workflow.service.ActivitiService;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("activitiService")
/*     */ @Transactional
/*     */ public class ActivitiServiceImpl extends CommonServiceImpl
/*     */   implements ActivitiService
/*     */ {
/*  77 */   private static Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);
/*     */   private IdentityService identityService;
/*     */   private RuntimeService runtimeService;
/*     */   protected TaskService taskService;
/*     */   protected HistoryService historyService;
/*     */   protected RepositoryService repositoryService;
/*     */   protected String hql;
/*     */ 
/*     */   @Autowired
/*     */   public void setIdentityService(IdentityService identityService)
/*     */   {
/*  88 */     this.identityService = identityService;
/*     */   }
/*     */   @Autowired
/*     */   public void setRuntimeService(RuntimeService runtimeService) {
/*  93 */     this.runtimeService = runtimeService;
/*     */   }
/*     */   @Autowired
/*     */   public void setTaskService(TaskService taskService) {
/*  98 */     this.taskService = taskService;
/*     */   }
/*     */   @Autowired
/*     */   public void setHistoryService(HistoryService historyService) {
/* 103 */     this.historyService = historyService;
/*     */   }
/*     */   @Autowired
/*     */   public void setRepositoryService(RepositoryService repositoryService) {
/* 108 */     this.repositoryService = repositoryService;
/*     */   }
/*     */ 
/*     */   public void save(TSUser user, String roleidstr, Short synToActiviti)
/*     */   {
/* 115 */     if (Globals.Activiti_Deploy_YES.equals(synToActiviti)) {
/* 116 */       String userId = user.getUserName();
/* 117 */       UserQuery userQuery = this.identityService.createUserQuery();
/* 118 */       List activitiUsers = userQuery.userId(userId).list();
/* 119 */       if (activitiUsers.size() == 1) {
/* 120 */         updateActivitiData(user, roleidstr, (User)activitiUsers.get(0)); } else {
/* 121 */         if (activitiUsers.size() > 1) {
/* 122 */           String errorMsg = "发现重复用户：id=" + userId;
/* 123 */           logger.error(errorMsg);
/* 124 */           throw new RuntimeException(errorMsg);
/*     */         }
/* 126 */         newActivitiUser(user, roleidstr);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void newActivitiUser(TSUser user, String roleidstr)
/*     */   {
/* 133 */     String userId = user.getUserName();
/* 134 */     addActivitiGroup(roleidstr);
/*     */ 
/* 136 */     saveActivitiUser(user);
/*     */ 
/* 138 */     addMembershipToIdentify(roleidstr, userId);
/*     */   }
/*     */ 
/*     */   private void addActivitiGroup(String roleidstr)
/*     */   {
/* 143 */     GroupQuery groupQuery = this.identityService.createGroupQuery();
/* 144 */     String[] roleIds = roleidstr.split(",");
/* 145 */     for (String string : roleIds) {
/* 146 */       TSRole role = (TSRole)this.commonDao.getEntity(TSRole.class, string);
/* 147 */       if (role != null) {
/* 148 */         List activitiGroups = groupQuery.groupId(role.getRoleCode()).list();
/* 149 */         if (activitiGroups.size() <= 0)
/* 150 */           saveActivitiGroup(role);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void saveActivitiGroup(TSRole role)
/*     */   {
/* 163 */     Group activitiGroup = this.identityService.newGroup(role.getRoleCode());
/* 164 */     activitiGroup.setId(role.getRoleCode());
/* 165 */     activitiGroup.setName(role.getRoleName());
/* 166 */     this.identityService.saveGroup(activitiGroup);
/*     */   }
/*     */ 
/*     */   private void saveActivitiUser(TSUser user) {
/* 170 */     String userId = oConvertUtils.getString(user.getUserName());
/* 171 */     User activitiUser = this.identityService.newUser(userId);
/* 172 */     cloneAndSaveActivitiUser(user, activitiUser);
/*     */   }
/*     */ 
/*     */   private void cloneAndSaveActivitiUser(TSUser user, User activitiUser)
/*     */   {
/* 177 */     activitiUser.setFirstName(user.getUserName());
/* 178 */     activitiUser.setLastName(user.getUserName());
/* 179 */     activitiUser.setPassword(user.getPassword());
/* 180 */     activitiUser.setEmail(user.getEmail());
/* 181 */     this.identityService.saveUser(activitiUser);
/*     */   }
/*     */ 
/*     */   private void addMembershipToIdentify(String roleidstr, String userId)
/*     */   {
/* 186 */     String[] roleIds = roleidstr.split(",");
/* 187 */     for (String string : roleIds) {
/* 188 */       TSRole role = (TSRole)this.commonDao.getEntity(TSRole.class, string);
/* 189 */       logger.debug("add role to activit: {}", role);
/* 190 */       if (role != null)
/* 191 */         this.identityService.createMembership(userId, role.getRoleCode());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void updateActivitiData(TSUser user, String roleidstr, User activitiUser)
/*     */   {
/* 197 */     String[] roleIds = roleidstr.split(",");
/* 198 */     String userId = user.getUserName();
/* 199 */     if (roleIds.length > 0) {
/* 200 */       addActivitiGroup(roleidstr);
/*     */     }
/*     */ 
/* 203 */     cloneAndSaveActivitiUser(user, activitiUser);
/*     */ 
/* 205 */     List<Group> activitiGroups = this.identityService.createGroupQuery().groupMember(userId).list();
/* 206 */     for (Group group : activitiGroups) {
/* 207 */       logger.debug("delete group from activit: {}", group);
/* 208 */       this.identityService.deleteMembership(userId, group.getId());
/*     */     }
/*     */ 
/* 211 */     addMembershipToIdentify(roleidstr, userId);
/*     */   }
/*     */ 
/*     */   public void delete(TSUser user)
/*     */   {
/* 222 */     if (user == null) {
/* 223 */       logger.debug("删除用户时，找不到ID为" + user.getUserName() + "的用户");
/*     */     }
/*     */ 
/* 226 */     List<TSRoleUser> roleUserList = findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
/* 227 */     if (roleUserList.size() >= 1) {
/* 228 */       for (TSRoleUser tRoleUser : roleUserList) {
/* 229 */         TSRole role = tRoleUser.getTSRole();
/* 230 */         if (role != null) {
/* 231 */           this.identityService.deleteMembership(user.getUserName(), role.getRoleCode());
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 237 */     this.identityService.deleteUser(user.getUserName());
/*     */   }
/*     */ 
/*     */   public ProcessInstance startWorkflow(Object entity, String businessKey, Map<String, Object> variables, TSBusConfig tsBusbase)
/*     */   {
/* 244 */     ReflectHelper reflectHelper = new ReflectHelper(entity);
/* 245 */     TSUser tsUser = (TSUser)reflectHelper.getMethodValue("TSUser");
/* 246 */     this.identityService.setAuthenticatedUserId(tsUser.getUserName());
/* 247 */     ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(tsBusbase.getTPProcess().getProcesskey(), businessKey, variables);
/* 248 */     return processInstance;
/*     */   }
/*     */ 
/*     */   public List findTodoTasks(String userId, List<TSBusConfig> tsBusConfigs)
/*     */   {
/* 259 */     List results = new ArrayList();
/* 260 */     List<Task> tasks = new ArrayList();
/* 261 */     String busentity = "";
/*     */ 
/* 264 */     if (tsBusConfigs.size() > 0) {
/* 265 */       for (TSBusConfig busConfig : tsBusConfigs) {
/* 266 */         String processKey = busConfig.getTPProcess().getProcesskey();
/* 267 */         busentity = busConfig.getTSTable().getEntityName();
/*     */ 
/* 269 */         List todoList = ((TaskQuery)((TaskQuery)this.taskService.createTaskQuery().processDefinitionKey(processKey).taskAssignee(userId).orderByTaskPriority().desc()).orderByTaskCreateTime().desc()).list();
/*     */ 
/* 271 */         List unsignedTasks = ((TaskQuery)((TaskQuery)this.taskService.createTaskQuery().processDefinitionKey(processKey).taskCandidateUser(userId).orderByTaskPriority().desc()).orderByTaskCreateTime().desc()).list();
/*     */ 
/* 273 */         tasks.addAll(todoList);
/* 274 */         tasks.addAll(unsignedTasks);
/*     */       }
/*     */ 
/* 277 */       for (Task task : tasks) {
/* 278 */         String processInstanceId = task.getProcessInstanceId();
/* 279 */         ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/*     */ 
/* 281 */         String businessKey = getBusinessKeyByTask(task);
/* 282 */         Class entityClass = MyClassLoader.getClassByScn(busentity);
/* 283 */         Object entityObj = getEntity(entityClass, businessKey);
/* 284 */         if (entityObj != null) {
/* 285 */           ReflectHelper reflectHelper = new ReflectHelper(entityObj);
/* 286 */           Process process = (Process)reflectHelper.getMethodValue("Process");
/* 287 */           process.setTask(task);
/* 288 */           process.setProcessInstance(processInstance);
/* 289 */           process.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
/*     */ 
/* 291 */           results.add(entityObj);
/*     */         } else {
/* 293 */           return tasks;
/*     */         }
/*     */       }
/*     */     }
/* 297 */     return results;
/*     */   }
/*     */ 
/*     */   public ProcessDefinition getProcessDefinition(String processDefinitionId)
/*     */   {
/* 308 */     return (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> traceProcess(String processInstanceId)
/*     */     throws Exception
/*     */   {
/* 318 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
/*     */ 
/* 320 */     Object property = PropertyUtils.getProperty(execution, "activityId");
/* 321 */     String activityId = "";
/* 322 */     if (property != null) {
/* 323 */       activityId = property.toString();
/*     */     }
/* 325 */     ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/* 326 */     ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
/* 327 */     List<ActivityImpl> activitiList = processDefinition.getActivities();
/*     */ 
/* 329 */     List activityInfos = new ArrayList();
/* 330 */     for (ActivityImpl activity : activitiList)
/*     */     {
/* 332 */       boolean currentActiviti = false;
/* 333 */       String id = activity.getId();
/*     */ 
/* 336 */       if (id.equals(activityId)) {
/* 337 */         currentActiviti = true;
/*     */       }
/*     */ 
/* 340 */       Map activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);
/*     */ 
/* 342 */       activityInfos.add(activityImageInfo);
/*     */     }
/*     */ 
/* 345 */     return activityInfos;
/*     */   }
/*     */ 
/*     */   private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance, boolean currentActiviti)
/*     */     throws Exception
/*     */   {
/* 358 */     Map vars = new HashMap();
/* 359 */     Map activityInfo = new HashMap();
/* 360 */     activityInfo.put("currentActiviti", Boolean.valueOf(currentActiviti));
/* 361 */     setPosition(activity, activityInfo);
/* 362 */     setWidthAndHeight(activity, activityInfo);
/*     */ 
/* 364 */     Map properties = activity.getProperties();
/* 365 */     vars.put("任务类型", WorkflowUtils.parseToZhType(properties.get("type").toString()));
/*     */ 
/* 367 */     ActivityBehavior activityBehavior = activity.getActivityBehavior();
/* 368 */     logger.debug("activityBehavior={}", activityBehavior);
/* 369 */     if ((activityBehavior instanceof UserTaskActivityBehavior))
/*     */     {
/* 371 */       Task currentTask = null;
/*     */ 
/* 376 */       if (currentActiviti) {
/* 377 */         currentTask = getCurrentTaskInfo(processInstance);
/*     */       }
/*     */ 
/* 383 */       UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior)activityBehavior;
/* 384 */       TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
/* 385 */       Set candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
/* 386 */       if (!candidateGroupIdExpressions.isEmpty())
/*     */       {
/* 389 */         setTaskGroup(vars, candidateGroupIdExpressions);
/*     */ 
/* 392 */         if (currentTask != null) {
/* 393 */           setCurrentTaskAssignee(vars, currentTask);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 398 */     vars.put("节点说明", properties.get("documentation"));
/*     */ 
/* 400 */     String description = activity.getProcessDefinition().getDescription();
/* 401 */     vars.put("描述", description);
/*     */ 
/* 403 */     logger.debug("trace variables: {}", vars);
/* 404 */     activityInfo.put("vars", vars);
/* 405 */     return activityInfo;
/*     */   }
/*     */ 
/*     */   private void setTaskGroup(Map<String, Object> vars, Set<Expression> candidateGroupIdExpressions) {
/* 409 */     String roles = "";
/* 410 */     for (Expression expression : candidateGroupIdExpressions) {
/* 411 */       String expressionText = expression.getExpressionText();
/* 412 */       if (expressionText.startsWith("$")) {
/* 413 */         expressionText = expressionText.replace("${insuranceType}", "life");
/*     */       }
/* 415 */       String roleName = ((Group)this.identityService.createGroupQuery().groupId(expressionText).singleResult()).getName();
/* 416 */       roles = roles + roleName;
/*     */     }
/* 418 */     vars.put("任务所属角色", roles);
/*     */   }
/*     */ 
/*     */   private void setCurrentTaskAssignee(Map<String, Object> vars, Task currentTask)
/*     */   {
/* 428 */     String assignee = currentTask.getAssignee();
/* 429 */     if (assignee != null) {
/* 430 */       User assigneeUser = (User)this.identityService.createUserQuery().userId(assignee).singleResult();
/* 431 */       String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
/* 432 */       vars.put("当前处理人", userInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Task getCurrentTaskInfo(ProcessInstance processInstance)
/*     */   {
/* 443 */     Task currentTask = null;
/*     */     try {
/* 445 */       String activitiId = (String)PropertyUtils.getProperty(processInstance, "activityId");
/* 446 */       logger.debug("current activity id: {}", activitiId);
/*     */ 
/* 448 */       currentTask = (Task)this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId).singleResult();
/* 449 */       logger.debug("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));
/*     */     }
/*     */     catch (Exception e) {
/* 452 */       logger.error("can not get property activityId from processInstance: {}", processInstance);
/*     */     }
/* 454 */     return currentTask;
/*     */   }
/*     */ 
/*     */   private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo)
/*     */   {
/* 464 */     activityInfo.put("width", Integer.valueOf(activity.getWidth()));
/* 465 */     activityInfo.put("height", Integer.valueOf(activity.getHeight()));
/*     */   }
/*     */ 
/*     */   private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo)
/*     */   {
/* 475 */     activityInfo.put("x", Integer.valueOf(activity.getX()));
/* 476 */     activityInfo.put("y", Integer.valueOf(activity.getY()));
/*     */   }
/*     */ 
/*     */   public ActivityImpl getProcessMap(String processInstanceId)
/*     */   {
/* 486 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
/* 487 */     Object property = null;
/*     */     try {
/* 489 */       property = PropertyUtils.getProperty(execution, "activityId");
/*     */     } catch (IllegalAccessException e) {
/* 491 */       logger.error("反射异常", e);
/*     */     } catch (InvocationTargetException e) {
/* 493 */       e.printStackTrace();
/*     */     } catch (NoSuchMethodException e) {
/* 495 */       e.printStackTrace();
/*     */     }
/* 497 */     String activityId = "";
/* 498 */     if (property != null) {
/* 499 */       activityId = property.toString();
/*     */     }
/* 501 */     ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/* 502 */     ProcessDefinition processDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
/* 503 */     ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl)processDefinition;
/* 504 */     String processDefinitionId = pdImpl.getId();
/* 505 */     ProcessDefinitionEntity def = (ProcessDefinitionEntity)((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
/* 506 */     ActivityImpl actImpl = null;
/* 507 */     List activitiIds = this.runtimeService.getActiveActivityIds(execution.getId());
/* 508 */     List a = highLight(processInstanceId);
/* 509 */     List<ActivityImpl> activitiList = def.getActivities();
/*     */ 
/* 511 */     for (ActivityImpl activityImpl : activitiList) {
/* 512 */       String id = activityImpl.getId();
/* 513 */       if (id.equals(activityId)) {
/* 514 */         actImpl = activityImpl;
/* 515 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 519 */     return actImpl;
/*     */   }
/*     */ 
/*     */   public List<String> highLight(String processInstanceId)
/*     */   {
/* 526 */     List highLihth = new ArrayList();
/* 527 */     List<Execution> executions = this.runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
/* 528 */     for (Execution execution : executions) {
/* 529 */       ExecutionEntity entity = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(execution.getId()).singleResult();
/* 530 */       highLihth.add(entity.getActivityId());
/*     */     }
/* 532 */     return highLihth;
/*     */   }
/*     */ 
/*     */   public String oldgetBusinessKeyByTask(Task task)
/*     */   {
/* 543 */     String businessKey = "";
/* 544 */     TaskEntity taskEntity = (TaskEntity)this.taskService.createTaskQuery().taskId(task.getId()).singleResult();
/* 545 */     ExecutionEntity executionEntity = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(taskEntity.getExecutionId()).singleResult();
/* 546 */     if (executionEntity.getSuperExecutionId() == null) {
/* 547 */       businessKey = executionEntity.getBusinessKey();
/*     */     } else {
/* 549 */       ExecutionEntity executionEntity2 = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(executionEntity.getSuperExecutionId()).singleResult();
/* 550 */       ExecutionEntity executionEntity3 = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(executionEntity2.getParentId()).singleResult();
/* 551 */       businessKey = executionEntity3.getBusinessKey();
/*     */     }
/* 553 */     return businessKey;
/*     */   }
/*     */ 
/*     */   public String getBusinessKeyByTask(Task task)
/*     */   {
/* 563 */     String businessKey = "";
/* 564 */     TaskEntity taskEntity = (TaskEntity)this.taskService.createTaskQuery().taskId(task.getId()).singleResult();
/* 565 */     HistoricProcessInstance hiproins = (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().processInstanceId(taskEntity.getProcessInstanceId()).singleResult();
/* 566 */     if (hiproins != null) {
/* 567 */       if ((hiproins.getSuperProcessInstanceId() != null) && (hiproins.getBusinessKey() == null)) {
/* 568 */         hiproins = (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().processInstanceId(hiproins.getSuperProcessInstanceId()).singleResult();
/* 569 */         businessKey = hiproins.getBusinessKey();
/*     */       } else {
/* 571 */         businessKey = hiproins.getBusinessKey();
/*     */       }
/*     */     }
/* 574 */     return businessKey;
/*     */   }
/*     */ 
/*     */   public HistoricProcessInstance getHiProcInstByBusKey(String businessKey)
/*     */   {
/* 584 */     HistoricProcessInstance hiproins = null;
/* 585 */     hiproins = (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
/* 586 */     return hiproins;
/*     */   }
/*     */ 
/*     */   public HistoricProcessInstance getHiProcInstByParprocInstId(String parprocInstId)
/*     */   {
/* 596 */     return (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().superProcessInstanceId(parprocInstId).singleResult();
/*     */   }
/*     */ 
/*     */   public void updateHiProcInstBusKey(String parBusKey, String subBusKey)
/*     */   {
/* 609 */     HistoricProcessInstance parhiproins = getHiProcInstByBusKey(parBusKey);
/* 610 */     HistoricProcessInstance subhiproins = getHiProcInstByParprocInstId(parhiproins.getId());
/* 611 */     if (subhiproins != null) {
/* 612 */       HistoricProcessInstanceEntity historicProcessInstanceEntity = (HistoricProcessInstanceEntity)subhiproins;
/* 613 */       ActHiProcinst actHiProcinst = (ActHiProcinst)getEntity(ActHiProcinst.class, historicProcessInstanceEntity.getId());
/* 614 */       actHiProcinst.setBusinessKey(subBusKey);
/* 615 */       updateEntitie(actHiProcinst);
/*     */     }
/*     */   }
/*     */ 
/*     */   public ActivitiCom complete(String taskId, Map<String, Object> map)
/*     */   {
/* 624 */     ActivitiCom activitiCom = new ActivitiCom();
/* 625 */     String msg = "";
/* 626 */     Boolean complete = Boolean.valueOf(false);
/*     */     try {
/* 628 */       this.taskService.complete(taskId, map);
/* 629 */       complete = Boolean.valueOf(true);
/* 630 */       msg = "办理成功";
/*     */     } catch (ActivitiException e) {
/* 632 */       if (e.getMessage().indexOf("no processes deployed with key") != -1) {
/* 633 */         msg = "没有部署子流程!,请在流程配置中部署流程";
/* 634 */         complete = Boolean.valueOf(false);
/*     */       } else {
/* 636 */         msg = "启动流程失败!,内部错误";
/* 637 */         complete = Boolean.valueOf(false);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 641 */       msg = "内部错误";
/* 642 */       complete = Boolean.valueOf(false);
/*     */     }
/* 644 */     activitiCom.setComplete(complete);
/* 645 */     activitiCom.setMsg(msg);
/* 646 */     return activitiCom;
/*     */   }
/*     */ 
/*     */   public String getBusinessKeyByTask(String taskId)
/*     */   {
/* 653 */     Task task = (Task)this.taskService.createTaskQuery().taskId(taskId).singleResult();
/* 654 */     return getBusinessKeyByTask(task);
/*     */   }
/*     */ 
/*     */   public TPProcessnode getTPProcessnode(String taskDefinitionKey, String processkey)
/*     */   {
/* 661 */     TPProcessnode processnode = null;
/* 662 */     this.hql = ("from TPProcessnode t where t.TPProcess.processkey='" + processkey + "' and t.processnodecode='" + taskDefinitionKey + "'");
/* 663 */     List processnodeList = this.commonDao.findByQueryString(this.hql);
/* 664 */     if (processnodeList.size() > 0) {
/* 665 */       processnode = (TPProcessnode)processnodeList.get(0);
/*     */     }
/* 667 */     return processnode;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List processDefinitionList()
/*     */   {
/* 675 */     return this.repositoryService.createProcessDefinitionQuery().list();
/*     */   }
/*     */ 
/*     */   public Task getTask(String taskId)
/*     */   {
/* 685 */     return (Task)this.taskService.createTaskQuery().taskId(taskId).singleResult();
/*     */   }
/*     */ 
/*     */   public ProcessHandle getProcessHandle(String taskId)
/*     */   {
/* 695 */     ProcessHandle processHandle = new ProcessHandle();
/* 696 */     Task task = getTask(taskId);
/* 697 */     String businessKey = getBusinessKeyByTask(taskId);
/* 698 */     String processDefinitionKey = getProcessDefinition(task.getProcessDefinitionId()).getKey();
/* 699 */     String taskDefinitionKey = task.getTaskDefinitionKey();
/*     */ 
/* 701 */     TPProcess tpProcess = (TPProcess)findUniqueByProperty(TPProcess.class, "processkey", processDefinitionKey);
/* 702 */     TPProcessnode tpProcessnode = getTPProcessnode(taskDefinitionKey, processDefinitionKey);
/* 703 */     TPForm tpForm = tpProcessnode.getTPForm();
/* 704 */     List tpProcesspros = tpProcessnode.getTPProcesspros();
/* 705 */     processHandle.setProcessDefinitionKey(processDefinitionKey);
/* 706 */     processHandle.setTaskDefinitionKey(taskDefinitionKey);
/* 707 */     processHandle.setBusinessKey(businessKey);
/* 708 */     processHandle.setTaskId(taskId);
/* 709 */     processHandle.setTpForm(tpForm);
/* 710 */     processHandle.setTpProcess(tpProcess);
/* 711 */     processHandle.setTpProcessnode(tpProcessnode);
/* 712 */     processHandle.setTpProcesspros(tpProcesspros);
/* 713 */     return processHandle;
/*     */   }
/*     */ 
/*     */   private static SqlSession getSqlSession() {
/* 717 */     ProcessEngineImpl processEngine = null;
/* 718 */     DbSqlSessionFactory dbSqlSessionFactory = (DbSqlSessionFactory)processEngine.getProcessEngineConfiguration().getSessionFactories().get(DbSqlSession.class);
/* 719 */     SqlSessionFactory sqlSessionFactory = dbSqlSessionFactory.getSqlSessionFactory();
/* 720 */     return sqlSessionFactory.openSession();
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.service.impl.ActivitiServiceImpl
 * JD-Core Version:    0.6.0
 */