/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class ComboTreeTag extends TagSupport
/*    */ {
/*    */   protected String id;
/*    */   protected String url;
/*    */   protected String name;
/*    */   protected String width;
/*    */   protected String value;
/* 23 */   private boolean multiple = false;
/*    */ 
/* 25 */   public int doStartTag() throws JspTagException { return 6; }
/*    */ 
/*    */   public int doEndTag() throws JspTagException
/*    */   {
/*    */     try {
/* 30 */       JspWriter out = this.pageContext.getOut();
/* 31 */       out.print(end().toString());
/* 32 */       out.flush();
/*    */     } catch (IOException e) {
/* 34 */       e.printStackTrace();
/*    */     }
/* 36 */     return 6;
/*    */   }
/*    */ 
/*    */   public StringBuffer end() {
/* 40 */     StringBuffer sb = new StringBuffer();
/* 41 */     this.width = (this.width == null ? "140" : this.width);
/* 42 */     sb.append("<script type=\"text/javascript\">$(function() { $('#" + 
/* 43 */       this.id + "').combotree({\t\t " + 
/* 44 */       "url :'" + this.url + "'," + 
/* 45 */       "width :'" + this.width + "'," + 
/* 46 */       "multiple:" + this.multiple + 
/* 47 */       "});\t\t" + 
/* 48 */       "});\t" + 
/* 49 */       "</script>");
/* 50 */     sb.append("<input  name=\"" + this.name + "\" id=\"" + this.id + "\" ");
/* 51 */     if (this.value != null)
/*    */     {
/* 53 */       sb.append("value=" + this.value);
/*    */     }
/* 55 */     sb.append(">");
/* 56 */     return sb;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 60 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 64 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 68 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public void setWidth(String width) {
/* 72 */     this.width = width;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 76 */     this.value = value;
/*    */   }
/*    */   public void setMultiple(boolean multiple) {
/* 79 */     this.multiple = multiple;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.ComboTreeTag
 * JD-Core Version:    0.6.0
 */