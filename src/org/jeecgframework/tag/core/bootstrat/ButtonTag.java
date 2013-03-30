/*     */ package org.jeecgframework.tag.core.bootstrat;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ 
/*     */ public class ButtonTag extends TagSupport
/*     */ {
/*     */   protected String name;
/*     */   protected String className;
/*     */   protected String size;
/*  22 */   protected Boolean disable = Boolean.valueOf(false);
/*  23 */   protected String href = "#";
/*     */   protected String type;
/*     */   protected String icon;
/*     */   protected String color;
/*  27 */   protected boolean rel = false;
/*     */   protected String title;
/*     */   protected String dataContent;
/*     */ 
/*     */   public void setRel(boolean rel)
/*     */   {
/*  32 */     this.rel = rel;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  36 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setDataContent(String dataContent) {
/*  40 */     this.dataContent = dataContent;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon) {
/*  44 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   public void setColor(String color) {
/*  48 */     this.color = color;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  52 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setClassName(String className) {
/*  56 */     this.className = className;
/*     */   }
/*     */ 
/*     */   public void setSize(String size) {
/*  60 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public void setDisable(Boolean disable) {
/*  64 */     this.disable = disable;
/*     */   }
/*     */ 
/*     */   public void setHref(String href) {
/*  68 */     this.href = href;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  72 */     this.type = type;
/*     */   }
/*     */   public int doStartTag() throws JspTagException {
/*  75 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  80 */       JspWriter out = this.pageContext.getOut();
/*  81 */       out.print(end().toString());
/*     */     } catch (IOException e) {
/*  83 */       e.printStackTrace();
/*     */     }
/*  85 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  89 */     StringBuffer sb = new StringBuffer();
/*  90 */     sb.append("<a class=\"btn");
/*  91 */     if (this.className != null) {
/*  92 */       sb.append(" btn-" + this.className);
/*     */     }
/*  94 */     if (this.size != null) {
/*  95 */       sb.append(" btn-" + this.size);
/*     */     }
/*  97 */     sb.append("\" href=\"" + this.href + "\"");
/*  98 */     if (this.rel) {
/*  99 */       sb.append(" rel=\"popover\" title=\"" + this.title + "\"  data-content=\"" + this.dataContent + "\"");
/*     */     }
/* 101 */     if (this.disable.booleanValue()) {
/* 102 */       sb.append(" disabled");
/*     */     }
/* 104 */     sb.append(">");
/* 105 */     if (this.icon != null) {
/* 106 */       sb.append("<i class=\"icon-" + this.icon);
/* 107 */       if (this.color != null) {
/* 108 */         sb.append(" icon-" + this.color);
/*     */       }
/* 110 */       sb.append("\"></i>");
/*     */     }
/* 112 */     sb.append(this.name + "</a>");
/* 113 */     return sb;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.ButtonTag
 * JD-Core Version:    0.6.0
 */