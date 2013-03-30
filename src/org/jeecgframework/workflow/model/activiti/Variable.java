/*    */ package org.jeecgframework.workflow.model.activiti;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import jodd.util.StringUtil;
/*    */ import org.apache.commons.beanutils.ConvertUtils;
/*    */ import org.jeecgframework.workflow.pojo.base.TPProcesspro;
/*    */ 
/*    */ public class Variable
/*    */ {
/*    */   private String keys;
/*    */   private String values;
/*    */   private String types;
/*    */   List<TPProcesspro> myvarialbe;
/*    */ 
/*    */   public String getKeys()
/*    */   {
/* 19 */     return this.keys;
/*    */   }
/*    */ 
/*    */   public void setKeys(String keys) {
/* 23 */     this.keys = keys;
/*    */   }
/*    */ 
/*    */   public String getValues() {
/* 27 */     return this.values;
/*    */   }
/*    */ 
/*    */   public void setValues(String values) {
/* 31 */     this.values = values;
/*    */   }
/*    */ 
/*    */   public String getTypes() {
/* 35 */     return this.types;
/*    */   }
/*    */ 
/*    */   public void setTypes(String types) {
/* 39 */     this.types = types;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getVariableMap(List<TPProcesspro> myvarialbe) {
/* 43 */     this.myvarialbe = myvarialbe;
/* 44 */     Map vars = new HashMap();
/* 45 */     ConvertUtils.register(new DateConverter(), Date.class);
/*    */ 
/* 47 */     if (StringUtil.isBlank(this.keys)) {
/* 48 */       return vars;
/*    */     }
/*    */ 
/* 51 */     String[] arrayKey = this.keys.split(",");
/* 52 */     String[] arrayValue = this.values.split(",");
/* 53 */     String[] arrayType = this.types.split(",");
/* 54 */     for (int i = 0; i < arrayKey.length; i++) {
/* 55 */       String key = getKey(arrayKey[i]);
/* 56 */       String value = arrayValue[i];
/* 57 */       String type = arrayType[i];
/*    */ 
/* 59 */       Class targetType = ((PropertyType)Enum.valueOf(PropertyType.class, type)).getValue();
/* 60 */       Object objectValue = ConvertUtils.convert(value, targetType);
/* 61 */       vars.put(key, objectValue);
/*    */     }
/* 63 */     return vars;
/*    */   }
/*    */ 
/*    */   public String getKey(String key)
/*    */   {
/* 71 */     if (this.myvarialbe.size() > 0) {
/* 72 */       for (TPProcesspro processpro : this.myvarialbe) {
/* 73 */         if (key.equals(processpro.getProcessprotype())) {
/* 74 */           key = processpro.getProcessprokey();
/*    */         }
/*    */       }
/*    */     }
/* 78 */     return key;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.activiti.Variable
 * JD-Core Version:    0.6.0
 */