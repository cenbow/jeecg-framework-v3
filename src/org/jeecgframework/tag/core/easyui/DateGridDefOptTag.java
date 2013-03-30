/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DateGridDefOptTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String exp;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 20 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 23 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 24 */     DateGridTag parent = (DateGridTag)t;
/* 25 */     parent.setDefUrl(this.url, this.title, this.exp);
/* 26 */     return 6;
/*    */   }
/*    */ 
/*    */   public void setExp(String exp) {
/* 30 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 33 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 36 */     this.title = title;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridDefOptTag
 * JD-Core Version:    0.6.0
 */