/*    */ package org.jeecgframework.workflow.test;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.RepositoryService;
/*    */ import org.activiti.engine.RuntimeService;
/*    */ import org.activiti.engine.repository.Deployment;
/*    */ import org.activiti.engine.repository.DeploymentBuilder;
/*    */ import org.activiti.engine.runtime.Execution;
/*    */ import org.activiti.engine.runtime.ExecutionQuery;
/*    */ import org.activiti.engine.runtime.ProcessInstance;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.context.ContextConfiguration;
/*    */ 
/*    */ @ContextConfiguration({"classpath:application-context.xml"})
/*    */ public class ActivitiWithSpringStandaloneTest extends AbstractSpringTest
/*    */ {
/*    */   protected void beforeTest()
/*    */     throws Exception
/*    */   {
/* 31 */     Deployment deployment = this.repositoryService
/* 32 */       .createDeployment()
/* 33 */       .addClasspathResource(
/* 34 */       "com/renfang/test/SprintActiviti56.bpmn20.xml")
/* 35 */       .deploy();
/* 36 */     this.deploymentId = deployment.getId();
/*    */   }
/*    */ 
/*    */   protected void afterTest() throws Exception
/*    */   {
/* 41 */     this.repositoryService.deleteDeployment(this.deploymentId, true);
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void triggerMyProcess() {
/* 47 */     Map variables = new HashMap();
/* 48 */     variables.put("姓名", "程序员");
/* 49 */     variables.put("职务", "高级软件工程师");
/* 50 */     variables.put("语言", "Java/C#");
/* 51 */     variables.put("操作系统", "Window,Linux，unix，Aix");
/* 52 */     variables.put("工作地点", "苏州高新技术软件园");
/*    */ 
/* 55 */     ProcessInstance pi = this.runtimeService.startProcessInstanceByKey("DeveloperWorkExam", variables);
/* 56 */     assert (pi != null);
/*    */ 
/* 58 */     List executions = this.runtimeService.createExecutionQuery().list();
/* 59 */     assert (executions.size() == 1);
/*    */ 
/* 61 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().singleResult();
/* 62 */     this.runtimeService.setVariable(execution.getId(), "type", "receiveTask");
/* 63 */     this.runtimeService.signal(execution.getId());
/*    */ 
/* 65 */     executions = this.runtimeService.createExecutionQuery().list();
/* 66 */     assert (executions.size() == 1);
/*    */ 
/* 68 */     execution = (Execution)executions.get(0);
/* 69 */     this.runtimeService.setVariable(execution.getId(), "oper", "录用此人....");
/* 70 */     this.runtimeService.signal(execution.getId());
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.test.ActivitiWithSpringStandaloneTest
 * JD-Core Version:    0.6.0
 */