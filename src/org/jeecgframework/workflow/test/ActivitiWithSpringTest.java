/*     */ package org.jeecgframework.workflow.test;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.activiti.engine.FormService;
/*     */ import org.activiti.engine.IdentityService;
/*     */ import org.activiti.engine.ManagementService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.runtime.Execution;
/*     */ import org.activiti.engine.runtime.ExecutionQuery;
/*     */ import org.activiti.engine.runtime.ProcessInstance;
/*     */ import org.junit.Test;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.test.context.ContextConfiguration;
/*     */ import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
/*     */ 
/*     */ @ContextConfiguration({"classpath:application-context.xml"})
/*     */ public class ActivitiWithSpringTest extends AbstractTransactionalJUnit4SpringContextTests
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private RuntimeService runtimeService;
/*     */ 
/*     */   @Autowired
/*     */   private TaskService taskService;
/*     */ 
/*     */   @Autowired
/*     */   private ManagementService managerService;
/*     */ 
/*     */   @Autowired
/*     */   private IdentityService identityService;
/*     */ 
/*     */   @Autowired
/*     */   private FormService formService;
/*     */ 
/*     */   @Test
/*     */   public void triggerMyProcess()
/*     */   {
/*  78 */     Map variables = new HashMap();
/*  79 */     variables.put("姓名", "程序员");
/*  80 */     variables.put("职务", "高级软件工程师");
/*  81 */     variables.put("语言", "Java/C#");
/*  82 */     variables.put("操作系统", "Window,Linux，unix，Aix");
/*  83 */     variables.put("工作地点", "苏州高新技术软件园");
/*     */ 
/*  87 */     ProcessInstance pi = this.runtimeService.startProcessInstanceByKey("DeveloperWorkExam", variables);
/*  88 */     assert (pi != null);
/*     */ 
/*  90 */     List executions = this.runtimeService.createExecutionQuery().list();
/*  91 */     assert (executions.size() == 1);
/*     */ 
/*  93 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().singleResult();
/*  94 */     this.runtimeService.setVariable(execution.getId(), "type", "receiveTask");
/*  95 */     this.runtimeService.signal(execution.getId());
/*     */ 
/*  97 */     executions = this.runtimeService.createExecutionQuery().list();
/*  98 */     assert (executions.size() == 1);
/*     */ 
/* 100 */     execution = (Execution)executions.get(0);
/* 101 */     this.runtimeService.setVariable(execution.getId(), "oper", "录用此人....");
/* 102 */     this.runtimeService.signal(execution.getId());
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.test.ActivitiWithSpringTest
 * JD-Core Version:    0.6.0
 */