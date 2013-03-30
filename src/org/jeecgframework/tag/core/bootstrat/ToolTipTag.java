/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class ToolTipTag extends TagSupport
/*    */ {
/* 17 */   protected String href = "#";
/*    */   protected String title;
/*    */   protected String name;
/*    */   protected String beforeName;
/*    */   protected String aftername;
/*    */ 
/*    */   public void setBeforeName(String beforeName)
/*    */   {
/* 24 */     this.beforeName = beforeName;
/*    */   }
/*    */ 
/*    */   public void setAftername(String aftername) {
/* 28 */     this.aftername = aftername;
/*    */   }
/*    */ 
/*    */   public void setHref(String href) {
/* 32 */     this.href = href;
/*    */   }
/*    */ 
/*    */   public void setTitle(String title) {
/* 36 */     this.title = title;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 40 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 45 */       JspWriter out = this.pageContext.getOut();
/* 46 */       out.print(end().toString());
/*    */     } catch (IOException e) {
/* 48 */       e.printStackTrace();
/*    */     }
/* 50 */     return 6;
/*    */   }
/*    */ 
/*    */   public StringBuffer end() {
/* 54 */     StringBuffer sb = new StringBuffer();
/* 55 */     sb.append("<div class=\"tooltip-demo well\">");
/* 56 */     sb.append(this.beforeName);
/* 57 */     sb.append("<a href=\"" + this.href + "\" rel=\"tooltip\" title=\"" + this.title + "\">" + this.name + "</a>" + this.aftername + "</div>");
/* 58 */     return sb;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.ToolTipTag
 * JD-Core Version:    0.6.0
 */