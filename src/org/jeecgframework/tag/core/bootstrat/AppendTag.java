/*     */ package org.jeecgframework.tag.core.bootstrat;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ 
/*     */ public class AppendTag extends TagSupport
/*     */ {
/*     */   protected boolean prependClass;
/*     */   protected boolean appendClass;
/*     */   protected String append;
/*     */   protected String prepend;
/*     */   protected String textClass;
/*     */   protected String id;
/*     */   protected String size;
/*     */   protected String value;
/*     */   protected String name;
/*     */   protected String placeholder;
/*  29 */   protected boolean disabled = false;
/*     */   protected String icon;
/*     */ 
/*     */   public void setPlaceholder(String placeholder)
/*     */   {
/*  32 */     this.placeholder = placeholder;
/*     */   }
/*     */   public void setDisabled(boolean disabled) {
/*  35 */     this.disabled = disabled;
/*     */   }
/*     */   public void setName(String name) {
/*  38 */     this.name = name;
/*     */   }
/*     */   public void setTextClass(String textClass) {
/*  41 */     this.textClass = textClass;
/*     */   }
/*     */   public void setId(String id) {
/*  44 */     this.id = id;
/*     */   }
/*     */   public void setSize(String size) {
/*  47 */     this.size = size;
/*     */   }
/*     */   public void setValue(String value) {
/*  50 */     this.value = value;
/*     */   }
/*     */   public void setPrepend(String prepend) {
/*  53 */     this.prepend = prepend;
/*     */   }
/*     */   public void setPrependClass(boolean prependClass) {
/*  56 */     this.prependClass = prependClass;
/*     */   }
/*     */ 
/*     */   public void setAppendClass(boolean appendClass) {
/*  60 */     this.appendClass = appendClass;
/*     */   }
/*     */ 
/*     */   public void setAppend(String append) {
/*  64 */     this.append = append;
/*     */   }
/*     */   public int doStartTag() throws JspTagException {
/*  67 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  72 */       JspWriter out = this.pageContext.getOut();
/*  73 */       out.print(end().toString());
/*     */     } catch (IOException e) {
/*  75 */       e.printStackTrace();
/*     */     }
/*  77 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  81 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  83 */     if ((this.prependClass) || (this.appendClass)) {
/*  84 */       sb.append("<div class=\"");
/*  85 */       if (this.prependClass) {
/*  86 */         sb.append("input-prepend ");
/*     */       }
/*  88 */       if (this.appendClass) {
/*  89 */         sb.append("input-append ");
/*     */       }
/*  91 */       sb.append("\">");
/*  92 */       if (this.icon != null) {
/*  93 */         sb.append("<span class=\"add-on\"><i class=\"icon-" + this.icon + "\"></i></span>");
/*     */       }
/*  95 */       if ((this.prependClass) && (this.prepend != null)) {
/*  96 */         sb.append("<span class=\"add-on\">" + this.prepend + "</span>");
/*     */       }
/*     */     }
/*  99 */     sb.append("<input class=\"" + this.textClass + "\" id=\"" + this.id + "\" size=\"" + this.size + "\"" + 
/* 100 */       " type=\"text\" name=\"" + this.name + "\" placeholder=\"" + this.placeholder + "\"");
/* 101 */     if (this.disabled)
/* 102 */       sb.append(" disabled>");
/*     */     else {
/* 104 */       sb.append(">");
/*     */     }
/* 106 */     if ((this.appendClass) && (this.prepend != null)) {
/* 107 */       sb.append("<span class=\"add-on\">" + this.append + "</span>");
/*     */     }
/* 109 */     if ((this.prependClass) || (this.appendClass)) {
/* 110 */       sb.append("</div>");
/*     */     }
/* 112 */     return sb;
/*     */   }
/*     */   public void setIcon(String icon) {
/* 115 */     this.icon = icon;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.AppendTag
 * JD-Core Version:    0.6.0
 */