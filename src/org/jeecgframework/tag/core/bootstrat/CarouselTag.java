/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.tagext.Tag;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ 
/*    */ public class CarouselTag extends TagSupport
/*    */ {
/*    */   private String path;
/*    */   private String message;
/* 18 */   private boolean active = false;
/*    */ 
/*    */   public void setPath(String path) {
/* 21 */     this.path = path;
/*    */   }
/*    */   public void setMessage(String message) {
/* 24 */     this.message = message;
/*    */   }
/*    */   public void setActive(boolean active) {
/* 27 */     this.active = active;
/*    */   }
/*    */   public int doStartTag() throws JspTagException {
/* 30 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/* 33 */     Tag t = findAncestorWithClass(this, CarouselsTag.class);
/* 34 */     CarouselsTag parent = (CarouselsTag)t;
/* 35 */     parent.setTab(this.path, this.message, this.active);
/* 36 */     return 6;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.CarouselTag
 * JD-Core Version:    0.6.0
 */