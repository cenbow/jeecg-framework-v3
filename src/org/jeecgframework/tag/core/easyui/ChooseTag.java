/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class ChooseTag extends TagSupport
/*     */ {
/*     */   protected String hiddenName;
/*     */   protected String textname;
/*     */   protected String icon;
/*     */   protected String title;
/*     */   protected String url;
/*     */   protected String top;
/*     */   protected String left;
/*     */   protected String width;
/*     */   protected String height;
/*     */   protected String name;
/*     */   protected String hiddenid;
/*  31 */   protected Boolean isclear = Boolean.valueOf(false);
/*     */   protected String fun;
/*     */ 
/*     */   public int doStartTag()
/*     */     throws JspTagException
/*     */   {
/*  35 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  40 */       JspWriter out = this.pageContext.getOut();
/*  41 */       out.print(end().toString());
/*  42 */       out.flush();
/*     */     } catch (IOException e) {
/*  44 */       e.printStackTrace();
/*     */     }
/*  46 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  50 */     StringBuffer sb = new StringBuffer();
/*  51 */     sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + this.icon + "\" onClick=\"choose()\">选择</a>");
/*  52 */     if ((this.isclear.booleanValue()) && (StringUtil.isNotEmpty(this.textname))) {
/*  53 */       sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll();\">清空</a>");
/*     */     }
/*  55 */     sb.append("<script type=\"text/javascript\">");
/*  56 */     sb.append("function choose(){");
/*  57 */     sb.append("$.dialog.open('" + this.url + "', {");
/*  58 */     if (this.title != null) {
/*  59 */       sb.append("title: '" + this.title + "',");
/*     */     }
/*     */ 
/*  62 */     sb.append("lock : true,");
/*  63 */     if (this.width != null)
/*  64 */       sb.append("width :'" + this.width + "',");
/*     */     else {
/*  66 */       sb.append("width :400,");
/*     */     }
/*  68 */     if (this.height != null)
/*  69 */       sb.append("height :'" + this.height + "',");
/*     */     else {
/*  71 */       sb.append("height :350,");
/*     */     }
/*  73 */     if (this.left != null)
/*  74 */       sb.append("left :'" + this.left + "',");
/*     */     else {
/*  76 */       sb.append("left :'85%',");
/*     */     }
/*  78 */     if (this.top != null)
/*  79 */       sb.append("top :'" + this.top + "',");
/*     */     else {
/*  81 */       sb.append("top :'65%',");
/*     */     }
/*  83 */     sb.append("opacity : 0.4,");
/*  84 */     sb.append("button : [ {");
/*  85 */     sb.append("name : '确认',");
/*  86 */     sb.append("callback : function() {");
/*  87 */     sb.append("iframe = this.iframe.contentWindow;");
/*  88 */     String[] textnames = (String[])null;
/*  89 */     if (StringUtil.isNotEmpty(this.textname))
/*     */     {
/*  91 */       textnames = this.textname.split(",");
/*  92 */       for (int i = 0; i < textnames.length; i++) {
/*  93 */         sb.append("var " + textnames[i] + "=iframe.get" + this.name + "Selections('" + textnames[i] + "');\t");
/*  94 */         sb.append("$('#" + textnames[i] + "').val(" + textnames[i] + ");");
/*     */       }
/*     */     }
/*  97 */     sb.append("var id =iframe.get" + this.name + "Selections('" + this.hiddenid + "');");
/*  98 */     sb.append("if (id!== undefined &&id!=\"\"){");
/*  99 */     sb.append("$('#" + this.hiddenName + "').val(id);");
/* 100 */     sb.append("}");
/* 101 */     if (StringUtil.isNotEmpty(this.fun))
/*     */     {
/* 103 */       sb.append(this.fun + "();");
/*     */     }
/* 105 */     sb.append("},");
/* 106 */     sb.append("focus : true");
/* 107 */     sb.append("}, {");
/* 108 */     sb.append("name : '取消',");
/* 109 */     sb.append("callback : function() {");
/* 110 */     sb.append("}");
/* 111 */     sb.append("} ]");
/* 112 */     sb.append("});");
/* 113 */     sb.append("}");
/* 114 */     if ((this.isclear.booleanValue()) && (StringUtil.isNotEmpty(this.textname))) {
/* 115 */       sb.append("function clearAll(){");
/* 116 */       for (int i = 0; i < textnames.length; i++) {
/* 117 */         sb.append("$('#" + textnames[i] + "').val(\"\");");
/*     */       }
/* 119 */       sb.append("$('#" + this.hiddenName + "').val(\"\");");
/* 120 */       sb.append("}");
/*     */     }
/* 122 */     sb.append("</script>");
/* 123 */     return sb;
/*     */   }
/*     */ 
/*     */   public void setHiddenName(String hiddenName) {
/* 127 */     this.hiddenName = hiddenName;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 131 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon) {
/* 135 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   public void setTextname(String textname) {
/* 139 */     this.textname = textname;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 143 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 147 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public void setTop(String top) {
/* 151 */     this.top = top;
/*     */   }
/*     */ 
/*     */   public void setLeft(String left) {
/* 155 */     this.left = left;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/* 159 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/* 163 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public void setIsclear(Boolean isclear) {
/* 167 */     this.isclear = isclear;
/*     */   }
/*     */ 
/*     */   public void setHiddenid(String hiddenid) {
/* 171 */     this.hiddenid = hiddenid;
/*     */   }
/*     */   public void setFun(String fun) {
/* 174 */     this.fun = fun;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.ChooseTag
 * JD-Core Version:    0.6.0
 */