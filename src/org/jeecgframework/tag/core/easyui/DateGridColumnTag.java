/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.tagext.Tag;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ 
/*     */ public class DateGridColumnTag extends TagSupport
/*     */ {
/*     */   protected String title;
/*     */   protected String field;
/*     */   protected Integer width;
/*     */   protected String rowspan;
/*     */   protected String colspan;
/*     */   protected String align;
/*  22 */   protected boolean sortable = true;
/*     */   protected boolean checkbox;
/*     */   protected String formatter;
/*  25 */   protected boolean hidden = true;
/*     */   protected String replace;
/*     */   protected String treefield;
/*     */   protected boolean image;
/*  29 */   protected boolean query = false;
/*  30 */   protected boolean bSearchable = true;
/*     */   protected String url;
/*  32 */   protected String funname = "openwindow";
/*     */   protected String arg;
/*     */ 
/*     */   public void setArg(String arg)
/*     */   {
/*  35 */     this.arg = arg;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  39 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public void setFunname(String funname) {
/*  43 */     this.funname = funname;
/*     */   }
/*     */ 
/*     */   public void setbSearchable(boolean bSearchable) {
/*  47 */     this.bSearchable = bSearchable;
/*     */   }
/*     */ 
/*     */   public void setQuery(boolean query) {
/*  51 */     this.query = query;
/*     */   }
/*     */ 
/*     */   public void setImage(boolean image) {
/*  55 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public void setTreefield(String treefield) {
/*  59 */     this.treefield = treefield;
/*     */   }
/*     */ 
/*     */   public void setReplace(String replace) {
/*  63 */     this.replace = replace;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  67 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/*  71 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public void setWidth(Integer width) {
/*  75 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setRowspan(String rowspan) {
/*  79 */     this.rowspan = rowspan;
/*     */   }
/*     */ 
/*     */   public void setColspan(String colspan) {
/*  83 */     this.colspan = colspan;
/*     */   }
/*     */ 
/*     */   public void setAlign(String align) {
/*  87 */     this.align = align;
/*     */   }
/*     */ 
/*     */   public void setSortable(boolean sortable) {
/*  91 */     this.sortable = sortable;
/*     */   }
/*     */ 
/*     */   public void setCheckbox(boolean checkbox) {
/*  95 */     this.checkbox = checkbox;
/*     */   }
/*     */ 
/*     */   public void setFormatter(String formatter) {
/*  99 */     this.formatter = formatter;
/*     */   }
/*     */ 
/*     */   public void setHidden(boolean hidden) {
/* 103 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException {
/* 107 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/* 111 */     Tag t = findAncestorWithClass(this, DateGridTag.class);
/* 112 */     DateGridTag parent = (DateGridTag)t;
/* 113 */     parent.setColumn(this.title, this.field, this.width, this.rowspan, this.colspan, this.align, this.sortable, this.checkbox, this.formatter, this.hidden, this.replace, this.treefield, this.image, this.query, this.url, this.funname, this.arg);
/* 114 */     return 6;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridColumnTag
 * JD-Core Version:    0.6.0
 */