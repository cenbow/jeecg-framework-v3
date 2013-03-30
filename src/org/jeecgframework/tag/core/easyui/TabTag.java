/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class TabTag extends TagSupport
/*    */ {
/*    */   private String href;
/*    */   private String iframe;
/*    */   private String id;
/*    */   private String title;
/* 20 */   private String icon = "icon-default";
/*    */   private String width;
/*    */   private String heigth;
/*    */   private boolean cache;
/*    */   private String content;
/* 25 */   private boolean closable = false;
/*    */ 
/*    */   public int doStartTag() throws JspTagException {
/* 28 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 31 */     Tag t = findAncestorWithClass(this, TabsTag.class);
/* 32 */     TabsTag parent = (TabsTag)t;
/* 33 */     parent.setTab(this.id, this.title, this.iframe, this.href, this.icon, this.cache, this.content, this.width, this.heigth, this.closable);
/* 34 */     return 6;
/*    */   }
/*    */   public void setHref(String href) {
/* 37 */     this.href = href;
/*    */   }
/*    */   public void setId(String id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   public void setTitle(String title) {
/* 43 */     this.title = title;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 46 */     this.icon = icon;
/*    */   }
/*    */   public void setWidth(String width) {
/* 49 */     this.width = width;
/*    */   }
/*    */   public void setHeigth(String heigth) {
/* 52 */     this.heigth = heigth;
/*    */   }
/*    */   public void setCache(boolean cache) {
/* 55 */     this.cache = cache;
/*    */   }
/*    */   public void setContent(String content) {
/* 58 */     this.content = content;
/*    */   }
/*    */   public void setClosable(boolean closable) {
/* 61 */     this.closable = closable;
/*    */   }
/*    */   public void setIframe(String iframe) {
/* 64 */     this.iframe = iframe;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.TabTag
 * JD-Core Version:    0.6.0
 */