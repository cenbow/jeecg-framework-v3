/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class ColorChangeTag extends TagSupport
/*    */ {
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 19 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 23 */       JspWriter out = this.pageContext.getOut();
/* 24 */       out.print(end().toString());
/*    */     } catch (IOException e) {
/* 26 */       e.printStackTrace();
/*    */     }
/* 28 */     return 6;
/*    */   }
/*    */   public StringBuffer end() {
/* 31 */     StringBuffer sb = new StringBuffer();
/* 32 */     return sb;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.ColorChangeTag
 * JD-Core Version:    0.6.0
 */