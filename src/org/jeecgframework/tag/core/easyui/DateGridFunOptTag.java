/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DateGridFunOptTag extends TagSupport
/*    */ {
/*    */   protected String title;
/*    */   private String exp;
/*    */   private String funname;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 20 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 23 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 24 */     DateGridTag parent = (DateGridTag)t;
/* 25 */     parent.setFunUrl(this.title, this.exp, this.funname);
/* 26 */     return 6;
/*    */   }
/*    */   public void setFunname(String funname) {
/* 29 */     this.funname = funname;
/*    */   }
/*    */   public void setExp(String exp) {
/* 32 */     this.exp = exp;
/*    */   }
/*    */   public void setTitle(String title) {
/* 35 */     this.title = title;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridFunOptTag
 * JD-Core Version:    0.6.0
 */