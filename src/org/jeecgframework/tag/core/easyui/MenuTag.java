/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import jeecg.system.pojo.base.TSFunction;
/*    */ import org.jeecgframework.core.util.ListtoMenu;
/*    */ 
/*    */ public class MenuTag extends TagSupport
/*    */ {
/* 24 */   protected String style = "easyui";
/*    */   protected List<TSFunction> parentFun;
/*    */   protected List<TSFunction> childFun;
/*    */ 
/*    */   public void setParentFun(List<TSFunction> parentFun)
/*    */   {
/* 28 */     this.parentFun = parentFun;
/*    */   }
/*    */ 
/*    */   public void setChildFun(List<TSFunction> childFun) {
/* 32 */     this.childFun = childFun;
/*    */   }
/*    */ 
/*    */   public int doStartTag() throws JspTagException {
/* 36 */     return 6;
/*    */   }
/*    */ 
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 41 */       JspWriter out = this.pageContext.getOut();
/* 42 */       out.print(end().toString());
/*    */     }
/*    */     catch (IOException e) {
/* 45 */       e.printStackTrace();
/*    */     }
/* 47 */     return 6;
/*    */   }
/*    */ 
/*    */   public StringBuffer end() {
/* 51 */     StringBuffer sb = new StringBuffer();
/* 52 */     if (this.style.equals("easyui")) {
/* 53 */       sb.append("<div id=\"nav\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");
/* 54 */       sb.append(ListtoMenu.getEasyuiMenu(this.parentFun, this.childFun));
/* 55 */       sb.append("</div>");
/*    */     }
/* 57 */     if (this.style.equals("bootstrap"))
/*    */     {
/* 59 */       sb.append(ListtoMenu.getBootMenu(this.parentFun, this.childFun));
/*    */     }
/* 61 */     if (this.style.equals("json"))
/*    */     {
/* 63 */       sb.append("<script type=\"text/javascript\">");
/* 64 */       sb.append("var _menus=" + ListtoMenu.getMenu(this.parentFun, this.childFun));
/* 65 */       sb.append("</script>");
/*    */     }
/*    */ 
/* 68 */     return sb;
/*    */   }
/*    */   public void setStyle(String style) {
/* 71 */     this.style = style;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.MenuTag
 * JD-Core Version:    0.6.0
 */