/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class AutocompleteTag extends TagSupport
/*     */ {
/*     */   private String name;
/*     */   private String dataSource;
/*  23 */   private Integer minLength = Integer.valueOf(2);
/*     */   private String labelField;
/*     */   private String searchField;
/*     */   private String valueField;
/*     */   private String entityName;
/*     */   private String selectfun;
/*     */   private String label;
/*     */   private String value;
/*     */   private String datatype;
/*     */   private String nullmsg;
/*     */   private String errormsg;
/*     */   private String closefun;
/*     */ 
/*     */   public void setClosefun(String closefun)
/*     */   {
/*  36 */     this.closefun = closefun;
/*     */   }
/*     */   public void setDatatype(String datatype) {
/*  39 */     this.datatype = datatype;
/*     */   }
/*     */   public void setNullmsg(String nullmsg) {
/*  42 */     this.nullmsg = nullmsg;
/*     */   }
/*     */   public void setErrormsg(String errormsg) {
/*  45 */     this.errormsg = errormsg;
/*     */   }
/*     */   public void setLabel(String label) {
/*  48 */     this.label = label;
/*     */   }
/*     */   public void setValue(String value) {
/*  51 */     this.value = value;
/*     */   }
/*     */   public int doStartTag() throws JspTagException {
/*  54 */     return 6;
/*     */   }
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  58 */       JspWriter out = this.pageContext.getOut();
/*  59 */       out.print(end().toString());
/*     */     } catch (IOException e) {
/*  61 */       e.printStackTrace();
/*     */     }
/*  63 */     return 6;
/*     */   }
/*     */   public StringBuffer end() {
/*  66 */     StringBuffer sb = new StringBuffer();
/*  67 */     sb.append("<script type=\"text/javascript\">");
/*  68 */     sb.append("$(function() {var tag=false;$(\"#" + 
/*  69 */       this.name + "\" ).autocomplete({" + 
/*  70 */       "source: function(request,response) {" + 
/*  71 */       "$.ajax({" + 
/*  72 */       "url: \"commonController.do?getAutoList\"," + 
/*  73 */       "jsonp:\"jsonpcallback\"," + 
/*  74 */       "dataType: \"jsonp\"," + 
/*  75 */       "data: {" + 
/*  76 */       "featureClass: \"P\"," + 
/*  77 */       "style: \"full\"," + 
/*  78 */       "maxRows: 10," + 
/*  79 */       "labelField: \"" + this.labelField + "\"," + 
/*  80 */       "valueField: \"" + this.valueField + "\"," + 
/*  81 */       "searchField: \"" + this.searchField + "\"," + 
/*  82 */       "entityName: \"" + this.entityName + "\"," + 
/*  83 */       "trem: encodeURIComponent(request.term)" + 
/*  84 */       "}," + 
/*  85 */       "success: function(data) {" + 
/*  86 */       "response($.map(data.geonames,function(item) {");
/*  87 */     sb.append("var labels=\"\";");
/*  88 */     String[] labels = this.labelField.split(",");
/*  89 */     sb.append("if(item.nodate=='yes'){");
/*  90 */     for (String string : labels) {
/*  91 */       sb.append("labels+=item." + string + "+\",\";");
/*     */     }
/*  93 */     sb.append("}");
/*  94 */     sb.append("else{labels=item.nodate;}");
/*  95 */     sb.append("return {label: labels,value: item." + 
/*  97 */       this.searchField + "," + 
/*  98 */       "val: item." + this.valueField + "," + 
/*  99 */       "obj: item" + 
/* 100 */       "}" + 
/* 101 */       "}));" + 
/* 102 */       "}" + 
/* 103 */       "});" + 
/* 104 */       "}," + 
/* 105 */       "minLength: " + this.minLength + "," + 
/* 106 */       "select: function( event, ui ) {" + 
/* 107 */       "$('#" + this.valueField + "').val(ui.item.val);");
/* 108 */     if (this.selectfun != null)
/*     */     {
/* 110 */       sb.append(this.selectfun + "(ui.item.obj);");
/*     */     }
/* 112 */     sb.append("tag=true;if(ui.item.obj.nodate!='yes'){tag=false}");
/* 113 */     sb.append("},open: function() {$( this ).removeClass( \"ui-corner-all\" ).addClass( \"ui-corner-top\" );tag=false;},close: function() {$( this ).removeClass( \"ui-corner-top\" ).addClass( \"ui-corner-all\" );if(tag==false){$('#" + 
/* 120 */       this.valueField + "').val('');$('#" + this.name + "').val('');");
/* 121 */     if (this.closefun != null)
/*     */     {
/* 123 */       sb.append(this.closefun + "(tag)");
/*     */     }
/* 125 */     sb.append("}}});});");
/*     */ 
/* 129 */     sb.append("</script>");
/* 130 */     sb.append("<input type=\"text\" id=\"" + this.name + "\" ");
/* 131 */     if (StringUtil.isNotEmpty(this.label))
/*     */     {
/* 133 */       sb.append(" value=" + this.label + " readonly=true");
/*     */     }
/* 135 */     sb.append(">");
/* 136 */     sb.append("<input type=\"hidden\" id=\"" + this.valueField + "\" name=\"" + this.valueField + "\"");
/* 137 */     if (StringUtil.isNotEmpty(this.value))
/*     */     {
/* 139 */       sb.append(" value=" + this.value);
/*     */     }
/* 141 */     if (StringUtil.isNotEmpty(this.datatype))
/*     */     {
/* 143 */       sb.append(" datatype=" + this.datatype);
/*     */     }
/* 145 */     if (StringUtil.isNotEmpty(this.nullmsg))
/*     */     {
/* 147 */       sb.append(" nullmsg=" + this.nullmsg);
/*     */     }
/* 149 */     if (StringUtil.isNotEmpty(this.errormsg))
/*     */     {
/* 151 */       sb.append(" errormsg=" + this.errormsg);
/*     */     }
/* 153 */     sb.append(">");
/* 154 */     return sb;
/*     */   }
/*     */   public void setName(String name) {
/* 157 */     this.name = name;
/*     */   }
/*     */   public void setDataSource(String dataSource) {
/* 160 */     this.dataSource = dataSource;
/*     */   }
/*     */   public void setMinLength(Integer minLength) {
/* 163 */     this.minLength = minLength;
/*     */   }
/*     */   public void setLabelField(String labelField) {
/* 166 */     this.labelField = labelField;
/*     */   }
/*     */   public void setValueField(String valueField) {
/* 169 */     this.valueField = valueField;
/*     */   }
/*     */   public void setEntityName(String entityName) {
/* 172 */     this.entityName = entityName;
/*     */   }
/*     */   public void setSearchField(String searchField) {
/* 175 */     this.searchField = searchField;
/*     */   }
/*     */   public void setSelectfun(String selectfun) {
/* 178 */     this.selectfun = selectfun;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.AutocompleteTag
 * JD-Core Version:    0.6.0
 */