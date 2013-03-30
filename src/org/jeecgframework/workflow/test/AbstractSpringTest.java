/*    */ package org.jeecgframework.workflow.test;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import org.activiti.engine.HistoryService;
/*    */ import org.activiti.engine.ManagementService;
/*    */ import org.activiti.engine.ProcessEngine;
/*    */ import org.activiti.engine.RepositoryService;
/*    */ import org.activiti.engine.RuntimeService;
/*    */ import org.activiti.engine.TaskService;
/*    */ import org.junit.After;
/*    */ import org.junit.Before;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.test.context.ContextConfiguration;
/*    */ import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
/*    */ 
/*    */ @ContextConfiguration({"classpath:application-context.xml"})
/*    */ public abstract class AbstractSpringTest extends AbstractTransactionalJUnit4SpringContextTests
/*    */ {
/* 39 */   private final Logger log = Logger.getLogger(AbstractSpringTest.class.getName());
/*    */ 
/*    */   @Autowired
/*    */   private ProcessEngine processEngine;
/*    */ 
/*    */   @Autowired
/*    */   protected RepositoryService repositoryService;
/*    */ 
/*    */   @Autowired
/*    */   protected RuntimeService runtimeService;
/*    */ 
/*    */   @Autowired
/*    */   protected TaskService taskService;
/*    */ 
/*    */   @Autowired
/*    */   protected HistoryService historyService;
/*    */ 
/*    */   @Autowired
/*    */   protected ManagementService managementService;
/*    */   protected String deploymentId;
/*    */ 
/* 63 */   @Before
/*    */   public void initialize() throws Exception { beforeTest(); }
/*    */ 
/*    */   @After
/*    */   public void clean() throws Exception {
/* 68 */     afterTest();
/*    */   }
/*    */ 
/*    */   protected abstract void beforeTest()
/*    */     throws Exception;
/*    */ 
/*    */   protected abstract void afterTest()
/*    */     throws Exception;
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.test.AbstractSpringTest
 * JD-Core Version:    0.6.0
 */