/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class AlertTag extends TagSupport
/*    */ {
/*    */   protected String className;
/*    */   protected String text;
/*    */ 
/*    */   public void setClassName(String className)
/*    */   {
/* 24 */     this.className = className;
/*    */   }
/*    */ 
/*    */   public void setText(String text) {
/* 28 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public int doStartTag() throws JspTagException {
/* 32 */     return 6;
/*    */   }
/*    */ 
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 37 */       JspWriter out = this.pageContext.getOut();
/* 38 */       out.print(end().toString());
/*    */     } catch (IOException e) {
/* 40 */       e.printStackTrace();
/*    */     }
/* 42 */     return 6;
/*    */   }
/*    */ 
/*    */   public StringBuffer end() {
/* 46 */     StringBuffer sb = new StringBuffer();
/* 47 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/bootstrap/js/bootstrap-alert.js\"></script>");
/* 48 */     if (this.className != null)
/* 49 */       sb.append("<div class=\"alert alert-" + this.className + "\">");
/*    */     else {
/* 51 */       sb.append("<div class=\"alert\">");
/*    */     }
/* 53 */     sb.append(" <a class=\"close\" data-dismiss=\"alert\">×</a>");
/* 54 */     sb.append(this.text + "</div");
/* 55 */     return sb;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.AlertTag
 * JD-Core Version:    0.6.0
 */