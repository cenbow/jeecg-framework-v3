/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DateGridConfOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String message;
/*    */   private String exp;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 21 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 24 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 25 */     DateGridTag parent = (DateGridTag)t;
/* 26 */     parent.setConfUrl(this.url, this.title, this.message, this.exp);
/* 27 */     return 6;
/*    */   }
/*    */   public void setExp(String exp) {
/* 30 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 33 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 36 */     this.title = title;
/*    */   }
/*    */   public void setMessage(String message) {
/* 39 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridConfOptTag
 * JD-Core Version:    0.6.0
 */