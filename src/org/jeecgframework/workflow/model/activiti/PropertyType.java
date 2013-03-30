/*    */ package org.jeecgframework.workflow.model.activiti;
/*    */ 
/*    */ public enum PropertyType
/*    */ {
/* 11 */   S(String.class), I(Integer.class), L(Long.class), F(Float.class), N(Double.class), D(java.util.Date.class), SD(java.sql.Date.class), 
/* 12 */   B(Boolean.class);
/*    */ 
/*    */   private Class<?> clazz;
/*    */ 
/* 17 */   private PropertyType(Class<?> clazz) { this.clazz = clazz; }
/*    */ 
/*    */   public Class<?> getValue()
/*    */   {
/* 21 */     return this.clazz;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.activiti.PropertyType
 * JD-Core Version:    0.6.0
 */