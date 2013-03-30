/*    */ package org.jeecgframework.workflow.test;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.logging.Logger;
/*    */ import org.activiti.engine.delegate.DelegateExecution;
/*    */ import org.activiti.engine.delegate.JavaDelegate;
/*    */ 
/*    */ public class HumanResourceExamListener
/*    */   implements JavaDelegate
/*    */ {
/* 28 */   private Logger logger = Logger.getLogger(HumanResourceExamListener.class.getName());
/*    */ 
/*    */   public void execute(DelegateExecution execute)
/*    */     throws Exception
/*    */   {
/* 37 */     this.logger.info("检查该考试是否通过开发知识考试....");
/* 38 */     Map variables = execute.getVariables();
/* 39 */     String reuslt = variables.get("result").toString();
/* 40 */     this.logger.info("开发知识面试结果" + reuslt);
/* 41 */     this.logger.info("开始人事面试了....");
/* 42 */     execute.setVariable("result", "该考生开发知识面试通过了....");
/* 43 */     this.logger.info("人事面试完毕....等候通知....");
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.test.HumanResourceExamListener
 * JD-Core Version:    0.6.0
 */