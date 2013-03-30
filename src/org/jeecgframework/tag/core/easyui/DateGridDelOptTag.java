/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DateGridDelOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String message;
/*    */   private String exp;
/*    */   private String funname;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 22 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 25 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 26 */     DateGridTag parent = (DateGridTag)t;
/* 27 */     parent.setDelUrl(this.url, this.title, this.message, this.exp, this.funname);
/* 28 */     return 6;
/*    */   }
/*    */   public void setFunname(String funname) {
/* 31 */     this.funname = funname;
/*    */   }
/*    */   public void setExp(String exp) {
/* 34 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 37 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 40 */     this.title = title;
/*    */   }
/*    */   public void setMessage(String message) {
/* 43 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridDelOptTag
 * JD-Core Version:    0.6.0
 */