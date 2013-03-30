/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DateGridToolBarTag extends TagSupport
/*    */ {
/*    */   protected String url;
/*    */   protected String title;
/*    */   private String exp;
/*    */   private String funname;
/*    */   private String icon;
/*    */   private String onclick;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 19 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 22 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 23 */     DateGridTag parent = (DateGridTag)t;
/* 24 */     parent.setToolbar(this.url, this.title, this.icon, this.exp, this.onclick, this.funname);
/* 25 */     return 6;
/*    */   }
/*    */   public void setFunname(String funname) {
/* 28 */     this.funname = funname;
/*    */   }
/*    */   public void setExp(String exp) {
/* 31 */     this.exp = exp;
/*    */   }
/*    */   public void setUrl(String url) {
/* 34 */     this.url = url;
/*    */   }
/*    */   public void setTitle(String title) {
/* 37 */     this.title = title;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 40 */     this.icon = icon;
/*    */   }
/*    */   public void setOnclick(String onclick) {
/* 43 */     this.onclick = onclick;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridToolBarTag
 * JD-Core Version:    0.6.0
 */