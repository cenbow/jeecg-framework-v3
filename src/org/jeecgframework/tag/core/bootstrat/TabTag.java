/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class TabTag extends TagSupport
/*    */ {
/*    */   private String classLi;
/* 17 */   private String url = "#";
/*    */   private String title;
/*    */   private String icon;
/*    */   private String iconColor;
/* 21 */   private boolean tab = true;
/*    */ 
/* 23 */   public void setTab(boolean tab) { this.tab = tab; }
/*    */ 
/*    */   public int doStartTag() throws JspTagException {
/* 26 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 29 */     Tag t = findAncestorWithClass(this, TabsTag.class);
/* 30 */     TabsTag parent = (TabsTag)t;
/* 31 */     parent.setTab(this.classLi, this.url, this.title, this.icon, this.iconColor, this.tab);
/* 32 */     return 6;
/*    */   }
/*    */   public void setClassLi(String classLi) {
/* 35 */     this.classLi = classLi;
/*    */   }
/*    */   public void setUrl(String url) {
/* 38 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 41 */     this.title = title;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 44 */     this.icon = icon;
/*    */   }
/*    */   public void setIconColor(String iconColor) {
/* 47 */     this.iconColor = iconColor;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.TabTag
 * JD-Core Version:    0.6.0
 */