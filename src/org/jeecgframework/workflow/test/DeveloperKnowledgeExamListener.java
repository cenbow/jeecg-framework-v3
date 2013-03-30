/*    */ package org.jeecgframework.workflow.test;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import java.util.logging.Logger;
/*    */ import org.activiti.engine.delegate.DelegateExecution;
/*    */ import org.activiti.engine.delegate.JavaDelegate;
/*    */ 
/*    */ public class DeveloperKnowledgeExamListener
/*    */   implements JavaDelegate
/*    */ {
/* 28 */   private Logger logger = Logger.getLogger(DeveloperKnowledgeExamListener.class.getName());
/*    */ 
/*    */   public void execute(DelegateExecution execute)
/*    */     throws Exception
/*    */   {
/* 35 */     this.logger.info("开始开发知识面试了....");
/* 36 */     Map<String, Object> variables = execute.getVariables();
/* 37 */     Set<Entry<String, Object>> infos = variables.entrySet();
/* 38 */     for (Map.Entry entry : infos) {
/* 39 */       this.logger.info((String)entry.getKey() + " " + entry.getValue());
/*    */     }
/* 41 */     this.logger.info("开始开发知识面试了....");
/* 42 */     execute.setVariable("result", "该考生开发知识面试通过了....");
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.test.DeveloperKnowledgeExamListener
 * JD-Core Version:    0.6.0
 */