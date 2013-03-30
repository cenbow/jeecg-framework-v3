/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class DropDownMenuTag extends TagSupport
/*    */ {
/*    */   protected String name;
/*    */   protected String classLi;
/* 22 */   protected String href = "#";
/*    */   protected String icon;
/*    */   protected String color;
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 26 */     this.name = name;
/*    */   }
/*    */   public void setClassLi(String classLi) {
/* 29 */     this.classLi = classLi;
/*    */   }
/*    */   public void setHref(String href) {
/* 32 */     this.href = href;
/*    */   }
/*    */   public void setIcon(String icon) {
/* 35 */     this.icon = icon;
/*    */   }
/*    */   public void setColor(String color) {
/* 38 */     this.color = color;
/*    */   }
/*    */ 
/*    */   public int doStartTag() throws JspTagException {
/* 42 */     return 6;
/*    */   }
/*    */ 
/*    */   public int doEndTag() throws JspTagException {
/* 46 */     Tag t = findAncestorWithClass(this, DropDownTag.class);
/* 47 */     DropDownTag parent = (DropDownTag)t;
/* 48 */     parent.setColumn(this.name, this.classLi, this.href, this.icon, this.color);
/* 49 */     return 6;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.DropDownMenuTag
 * JD-Core Version:    0.6.0
 */