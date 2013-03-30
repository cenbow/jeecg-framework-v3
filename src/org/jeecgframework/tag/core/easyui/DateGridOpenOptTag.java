/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DateGridOpenOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/* 17 */   protected String width = "100%";
/* 18 */   protected String height = "100%";
/*    */   protected String title;
/*    */   private String exp;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 22 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 25 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 26 */     DateGridTag parent = (DateGridTag)t;
/* 27 */     parent.setOpenUrl(this.url, this.title, this.width, this.height, this.exp);
/* 28 */     return 6;
/*    */   }
/*    */   public void setWidth(String width) {
/* 31 */     this.width = width;
/*    */   }
/*    */   public void setHeight(String height) {
/* 34 */     this.height = height;
/*    */   }
/*    */   public void setExp(String exp) {
/* 37 */     this.exp = exp;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 41 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 44 */     this.title = title;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridOpenOptTag
 * JD-Core Version:    0.6.0
 */