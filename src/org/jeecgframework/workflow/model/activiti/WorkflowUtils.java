/*    */ package org.jeecgframework.workflow.model.activiti;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class WorkflowUtils
/*    */ {
/*    */   public static String parseToZhType(String type)
/*    */   {
/* 19 */     Map types = new HashMap();
/* 20 */     types.put("userTask", "用户任务");
/* 21 */     types.put("serviceTask", "系统任务");
/* 22 */     types.put("startEvent", "开始节点");
/* 23 */     types.put("endEvent", "结束节点");
/* 24 */     types.put("exclusiveGateway", "条件判断节点(系统自动根据条件处理)");
/* 25 */     types.put("inclusiveGateway", "并行处理任务");
/* 26 */     types.put("callActivity", "子流程");
/* 27 */     return types.get(type) == null ? type : (String)types.get(type);
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.activiti.WorkflowUtils
 * JD-Core Version:    0.6.0
 */