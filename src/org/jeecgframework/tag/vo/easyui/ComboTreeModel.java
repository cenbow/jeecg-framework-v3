/*    */ package org.jeecgframework.tag.vo.easyui;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ComboTreeModel
/*    */   implements Serializable
/*    */ {
/*    */   private String idField;
/*    */   private String textField;
/*    */   private String iconCls;
/*    */   private String childField;
/*    */   private String srcField;
/*    */ 
/*    */   public ComboTreeModel(String idField, String textField, String childField)
/*    */   {
/* 20 */     this.idField = idField;
/* 21 */     this.textField = textField;
/* 22 */     this.childField = childField;
/*    */   }
/*    */   public ComboTreeModel(String idField, String textField, String childField, String srcField) {
/* 25 */     this.idField = idField;
/* 26 */     this.textField = textField;
/* 27 */     this.childField = childField;
/* 28 */     this.srcField = srcField;
/*    */   }
/*    */   public String getIconCls() {
/* 31 */     return this.iconCls;
/*    */   }
/*    */   public void setIconCls(String iconCls) {
/* 34 */     this.iconCls = iconCls;
/*    */   }
/*    */   public String getChildField() {
/* 37 */     return this.childField;
/*    */   }
/*    */   public void setChildField(String childField) {
/* 40 */     this.childField = childField;
/*    */   }
/*    */   public String getIdField() {
/* 43 */     return this.idField;
/*    */   }
/*    */   public void setIdField(String idField) {
/* 46 */     this.idField = idField;
/*    */   }
/*    */   public String getTextField() {
/* 49 */     return this.textField;
/*    */   }
/*    */   public void setTextField(String textField) {
/* 52 */     this.textField = textField;
/*    */   }
/*    */   public String getSrcField() {
/* 55 */     return this.srcField;
/*    */   }
/*    */   public void setSrcField(String srcField) {
/* 58 */     this.srcField = srcField;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.vo.easyui.ComboTreeModel
 * JD-Core Version:    0.6.0
 */