/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.tag.vo.easyui.Carousel;
/*    */ 
/*    */ public class CarouselsTag extends TagSupport
/*    */ {
/*    */   private String id;
/*    */   private String leftIcon;
/*    */   private String rightIcon;
/* 26 */   private List<Carousel> carList = new ArrayList();
/*    */ 
/*    */   public void setLeftIcon(String leftIcon)
/*    */   {
/* 30 */     this.leftIcon = leftIcon;
/*    */   }
/*    */ 
/*    */   public void setRightIcon(String rightIcon) {
/* 34 */     this.rightIcon = rightIcon;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 38 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public void setCarList(List<Carousel> carList) {
/* 42 */     this.carList = carList;
/*    */   }
/*    */   public void setTab(String path, String message, boolean active) {
/* 45 */     Carousel c = new Carousel();
/* 46 */     c.setActive(active);
/* 47 */     c.setMessage(message);
/* 48 */     c.setPath(path);
/* 49 */     this.carList.add(c);
/*    */   }
/*    */   public int doStartTag() throws JspTagException {
/* 52 */     this.carList.clear();
/* 53 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 57 */       JspWriter out = this.pageContext.getOut();
/* 58 */       out.print(end().toString());
/*    */     } catch (IOException e) {
/* 60 */       e.printStackTrace();
/*    */     }
/* 62 */     return 6;
/*    */   }
/*    */   public StringBuffer end() {
/* 65 */     StringBuffer sb = new StringBuffer();
/* 66 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/bootstrap/js/bootstrap-carousel.js\"></script>");
/* 67 */     sb.append("<div class=\"span9 columns\"><div id=\"" + this.id + "\" class=\"carousel slide\">" + 
/* 68 */       "<div class=\"carousel-inner\">");
/* 69 */     if (this.carList.size() > 0) {
/* 70 */       for (Carousel c : this.carList) {
/* 71 */         sb.append(" <div class=\"item");
/* 72 */         if (c.isActive()) {
/* 73 */           sb.append(" active");
/*    */         }
/* 75 */         sb.append("\">");
/* 76 */         sb.append(" <img src=\"" + c.getPath() + "\" alt=\"\">");
/* 77 */         sb.append("<div class=\"carousel-caption\">" + c.getMessage() + "</div></div>");
/*    */       }
/*    */     }
/*    */ 
/* 81 */     sb.append("</div><a class=\"left carousel-control\" href=\"#" + this.id + "\" data-slide=\"prev\">" + this.leftIcon + "</a>");
/* 82 */     sb.append("<a class=\"right carousel-control\" href=\"#" + this.id + "\" data-slide=\"next\">" + this.rightIcon + "</a>");
/* 83 */     sb.append("</div></div>");
/* 84 */     return sb;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.CarouselsTag
 * JD-Core Version:    0.6.0
 */