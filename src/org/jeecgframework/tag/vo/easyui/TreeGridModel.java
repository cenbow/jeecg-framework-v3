/*    */ package org.jeecgframework.tag.vo.easyui;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TreeGridModel
/*    */   implements Serializable
/*    */ {
/*    */   private String idField;
/*    */   private String textField;
/*    */   private String childList;
/*    */   private String parentId;
/*    */   private String parentText;
/*    */   private String code;
/*    */   private String src;
/*    */   private String roleid;
/*    */   private String icon;
/*    */ 
/*    */   public String getIcon()
/*    */   {
/* 22 */     return this.icon;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 25 */     this.icon = icon;
/*    */   }
/*    */   public String getRoleid() {
/* 28 */     return this.roleid;
/*    */   }
/*    */   public void setRoleid(String roleid) {
/* 31 */     this.roleid = roleid;
/*    */   }
/*    */   public String getParentText() {
/* 34 */     return this.parentText;
/*    */   }
/*    */   public void setParentText(String parentText) {
/* 37 */     this.parentText = parentText;
/*    */   }
/*    */   public String getCode() {
/* 40 */     return this.code;
/*    */   }
/*    */   public void setCode(String code) {
/* 43 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getSrc() {
/* 47 */     return this.src;
/*    */   }
/*    */   public void setSrc(String src) {
/* 50 */     this.src = src;
/*    */   }
/*    */   public String getParentId() {
/* 53 */     return this.parentId;
/*    */   }
/*    */   public void setParentId(String parentId) {
/* 56 */     this.parentId = parentId;
/*    */   }
/*    */   public String getIdField() {
/* 59 */     return this.idField;
/*    */   }
/*    */   public void setIdField(String idField) {
/* 62 */     this.idField = idField;
/*    */   }
/*    */   public String getTextField() {
/* 65 */     return this.textField;
/*    */   }
/*    */   public void setTextField(String textField) {
/* 68 */     this.textField = textField;
/*    */   }
/*    */   public String getChildList() {
/* 71 */     return this.childList;
/*    */   }
/*    */   public void setChildList(String childList) {
/* 74 */     this.childList = childList;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.vo.easyui.TreeGridModel
 * JD-Core Version:    0.6.0
 */