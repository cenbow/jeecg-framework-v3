/*     */ package org.jeecgframework.tag.vo.easyui;
/*     */ 
/*     */ public class DateGridColumn
/*     */ {
/*     */   protected String title;
/*     */   protected String field;
/*     */   protected Integer width;
/*     */   protected String rowspan;
/*     */   protected String colspan;
/*     */   protected String align;
/*     */   protected boolean sortable;
/*     */   protected boolean checkbox;
/*     */   protected String formatter;
/*     */   protected boolean hidden;
/*     */   protected String treefield;
/*     */   protected boolean image;
/*     */   protected boolean query;
/*     */   protected String url;
/*  25 */   protected String funname = "openwindow";
/*     */   protected String arg;
/*     */ 
/*     */   public boolean isQuery()
/*     */   {
/*  28 */     return this.query;
/*     */   }
/*     */ 
/*     */   public String getArg() {
/*  32 */     return this.arg;
/*     */   }
/*     */ 
/*     */   public void setArg(String arg) {
/*  36 */     this.arg = arg;
/*     */   }
/*     */ 
/*     */   public void setQuery(boolean query) {
/*  40 */     this.query = query;
/*     */   }
/*     */ 
/*     */   public boolean isImage() {
/*  44 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(boolean image) {
/*  48 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public String getTreefield() {
/*  52 */     return this.treefield;
/*     */   }
/*     */ 
/*     */   public void setTreefield(String treefield) {
/*  56 */     this.treefield = treefield;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  60 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/*  64 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public void setWidth(Integer width) {
/*  68 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setRowspan(String rowspan) {
/*  72 */     this.rowspan = rowspan;
/*     */   }
/*     */ 
/*     */   public void setColspan(String colspan) {
/*  76 */     this.colspan = colspan;
/*     */   }
/*     */ 
/*     */   public void setAlign(String align) {
/*  80 */     this.align = align;
/*     */   }
/*     */ 
/*     */   public void setSortable(boolean sortable) {
/*  84 */     this.sortable = sortable;
/*     */   }
/*     */ 
/*     */   public void setCheckbox(boolean checkbox) {
/*  88 */     this.checkbox = checkbox;
/*     */   }
/*     */ 
/*     */   public void setFormatter(String formatter) {
/*  92 */     this.formatter = formatter;
/*     */   }
/*     */   public boolean isHidden() {
/*  95 */     return this.hidden;
/*     */   }
/*     */ 
/*     */   public void setHidden(boolean hidden) {
/*  99 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 103 */     return this.title;
/*     */   }
/*     */ 
/*     */   public String getField() {
/* 107 */     return this.field;
/*     */   }
/*     */ 
/*     */   public Integer getWidth() {
/* 111 */     return this.width;
/*     */   }
/*     */ 
/*     */   public String getRowspan() {
/* 115 */     return this.rowspan;
/*     */   }
/*     */ 
/*     */   public String getColspan() {
/* 119 */     return this.colspan;
/*     */   }
/*     */ 
/*     */   public String getAlign() {
/* 123 */     return this.align;
/*     */   }
/*     */ 
/*     */   public boolean isSortable() {
/* 127 */     return this.sortable;
/*     */   }
/*     */ 
/*     */   public boolean isCheckbox() {
/* 131 */     return this.checkbox;
/*     */   }
/*     */ 
/*     */   public String getFormatter() {
/* 135 */     return this.formatter;
/*     */   }
/*     */   public String getUrl() {
/* 138 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 142 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getFunname() {
/* 146 */     return this.funname;
/*     */   }
/*     */ 
/*     */   public void setFunname(String funname) {
/* 150 */     this.funname = funname;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.vo.easyui.DateGridColumn
 * JD-Core Version:    0.6.0
 */