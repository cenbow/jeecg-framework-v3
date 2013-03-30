/*    */ package org.jeecgframework.workflow.listener.activiti;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.activiti.engine.delegate.DelegateTask;
/*    */ import org.activiti.engine.delegate.TaskListener;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Component
/*    */ @Transactional
/*    */ public class AfterModifyApplyContentProcessor
/*    */   implements TaskListener
/*    */ {
/*    */   public void notify(DelegateTask delegateTask)
/*    */   {
/* 25 */     System.out.print("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" + delegateTask.getEventName());
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.listener.activiti.AfterModifyApplyContentProcessor
 * JD-Core Version:    0.6.0
 */