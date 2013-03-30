/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ 
/*     */ public class UploadTag extends TagSupport
/*     */ {
/*     */   protected String id;
/*     */   protected String uploader;
/*     */   protected String name;
/*     */   protected String formData;
/*  25 */   protected String extend = "";
/*  26 */   protected String buttonText = "浏览";
/*  27 */   protected boolean multi = true;
/*  28 */   protected String queueID = "filediv";
/*  29 */   protected boolean dialog = true;
/*     */   protected String callback;
/*  31 */   protected boolean auto = false;
/*     */   protected String onUploadSuccess;
/*  33 */   protected boolean view = false;
/*     */ 
/*  35 */   public void setView(boolean view) { this.view = view; }
/*     */ 
/*     */   public void setOnUploadSuccess(String onUploadSuccess) {
/*  38 */     this.onUploadSuccess = onUploadSuccess;
/*     */   }
/*     */   public void setAuto(boolean auto) {
/*  41 */     this.auto = auto;
/*     */   }
/*     */   public void setCallback(String callback) {
/*  44 */     this.callback = callback;
/*     */   }
/*     */   public void setDialog(boolean dialog) {
/*  47 */     this.dialog = dialog;
/*     */   }
/*     */   public void setQueueID(String queueID) {
/*  50 */     this.queueID = queueID;
/*     */   }
/*     */   public void setButtonText(String buttonText) {
/*  53 */     this.buttonText = buttonText;
/*     */   }
/*     */   public void setMulti(boolean multi) {
/*  56 */     this.multi = multi;
/*     */   }
/*     */   public void setUploader(String uploader) {
/*  59 */     this.uploader = uploader;
/*     */   }
/*     */   public void setName(String name) {
/*  62 */     this.name = name;
/*     */   }
/*     */   public int doStartTag() throws JspTagException {
/*  65 */     return 6;
/*     */   }
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  69 */       JspWriter out = this.pageContext.getOut();
/*  70 */       out.print(end().toString());
/*  71 */       out.flush();
/*     */     } catch (IOException e) {
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     return 6;
/*     */   }
/*     */   public StringBuffer end() {
/*  78 */     StringBuffer sb = new StringBuffer();
/*  79 */     if ("pic".equals(this.extend))
/*     */     {
/*  81 */       this.extend = "*.jpg;*,jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
/*     */     }
/*  83 */     if (this.extend.equals("office"))
/*     */     {
/*  85 */       this.extend = "*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm";
/*     */     }
/*  87 */     sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
/*  88 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
/*  89 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>");
/*  90 */     sb.append("<script type=\"text/javascript\">var flag = false;var fileitem=\"\";var fileKey=\"\";var m = new Map();$(function(){$('#" + 
/*  96 */       this.id + "').uploadify({" + 
/*  97 */       "buttonText:'" + this.buttonText + "'," + 
/*  98 */       "auto:" + this.auto + "," + 
/*  99 */       "progressData:'speed'," + 
/* 100 */       "multi:" + this.multi + "," + 
/* 101 */       "height:25," + 
/* 102 */       "overrideEvents:['onDialogClose']," + 
/* 103 */       "fileTypeDesc:'文件格式:'," + 
/* 104 */       "queueID:'" + this.queueID + "'," + 
/* 105 */       "fileTypeExts:'" + this.extend + "'," + 
/* 106 */       "fileSizeLimit:'3MB'," + 
/* 107 */       "swf:'plug-in/uploadify/uploadify.swf',\t" + 
/* 108 */       "uploader:'" + this.uploader + "&jsessionid='+$(\"#sessionUID\").val()+''," + 
/* 109 */       "onUploadStart : function(file) { ");
/* 110 */     if (this.formData != null) {
/* 111 */       String[] paramnames = this.formData.split(",");
/* 112 */       for (int i = 0; i < paramnames.length; i++) {
/* 113 */         String paramname = paramnames[i];
/* 114 */         sb.append("var " + paramname + "=$('#" + paramname + "').val();");
/*     */       }
/* 116 */       sb.append("$('#" + this.id + "').uploadify(\"settings\", \"formData\", {");
/* 117 */       for (int i = 0; i < paramnames.length; i++) {
/* 118 */         String paramname = paramnames[i];
/* 119 */         if (i == paramnames.length - 1)
/* 120 */           sb.append("'" + paramname + "':" + paramname);
/*     */         else {
/* 122 */           sb.append("'" + paramname + "':" + paramname + ",");
/*     */         }
/*     */       }
/* 125 */       sb.append("});");
/*     */     }
/* 127 */     sb.append("} ,onQueueComplete : function(queueData) { ");
/*     */ 
/* 129 */     if (this.dialog)
/*     */     {
/* 131 */       sb.append("var win = frameElement.api.opener;win.reloadTable(); frameElement.api.close();");
/*     */     }
/* 137 */     else if (this.callback != null) {
/* 138 */       sb.append(this.callback + "();");
/*     */     }
/* 140 */     if (this.view)
/*     */     {
/* 142 */       sb.append("$(\"#viewmsg\").html(m.toString());");
/* 143 */       sb.append("$(\"#fileKey\").val(fileKey);");
/*     */     }
/* 145 */     sb.append("},");
/*     */ 
/* 147 */     sb.append("onUploadSuccess : function(file, data, response) {");
/* 148 */     sb.append("var d=$.parseJSON(data);");
/* 149 */     if (this.view)
/*     */     {
/* 151 */       sb.append("var fileitem=\"<span id='\"+d.attributes.id+\"'><a href='#' onclick=openwindow('文件查看','\"+d.attributes.viewhref+\"','70%','80%') title='查看'>\"+d.attributes.name+\"</a><img border='0' onclick=confuploadify('\"+d.attributes.delurl+\"','\"+d.attributes.id+\"') title='删除' src='plug-in/uploadify/img/uploadify-cancel.png' widht='15' height='15'>&nbsp;&nbsp;</span>\";");
/* 152 */       sb.append("m.put(d.attributes.id,fileitem);");
/* 153 */       sb.append("fileKey=d.attributes.fileKey;");
/*     */     }
/* 155 */     if (this.onUploadSuccess != null)
/*     */     {
/* 157 */       sb.append(this.onUploadSuccess + "(d,file,response);");
/*     */     }
/* 159 */     sb.append("if(d.success){");
/* 160 */     sb.append("var win = frameElement.api.opener");
/* 161 */     sb.append("win.tip(d.msg);");
/* 162 */     sb.append("}");
/* 163 */     sb.append("},");
/* 164 */     sb.append("onFallback : function(){tip(\"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试\")},");
/* 165 */     sb.append("onSelectError : function(file, errorCode, errorMsg){");
/* 166 */     sb.append("switch(errorCode) {");
/* 167 */     sb.append("case -100:");
/* 168 */     sb.append("tip(\"上传的文件数量已经超出系统限制的\"+$('#" + this.id + "').uploadify('settings','queueSizeLimit')+\"个文件！\");");
/* 169 */     sb.append("break;");
/* 170 */     sb.append("case -110:tip(\"文件 [\"+file.name+\"] 大小超出系统限制的\"+$('#" + 
/* 171 */       this.id + "').uploadify('settings','fileSizeLimit')+\"大小！\");" + 
/* 172 */       "break;" + 
/* 173 */       "case -120:" + 
/* 174 */       "tip(\"文件 [\"+file.name+\"] 大小异常！\");" + 
/* 175 */       "break;" + 
/* 176 */       "case -130:" + 
/* 177 */       "tip(\"文件 [\"+file.name+\"] 类型不正确！\");" + 
/* 178 */       "break;" + 
/* 179 */       "}");
/* 180 */     sb.append("},onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});function upload() {\t$('#" + 
/* 187 */       this.id + "').uploadify('upload', '*');" + 
/* 188 */       "\t\treturn flag;" + 
/* 189 */       "}" + 
/* 190 */       "function cancel() {" + 
/* 191 */       "$('#" + this.id + "').uploadify('cancel', '*');" + 
/* 192 */       "}" + 
/* 193 */       "</script>");
/* 194 */     sb.append("<span id=\"" + this.id + "span\"><input type=\"file\" name=\"" + this.name + "\" id=\"" + this.id + "\" /></span>");
/* 195 */     if (this.view)
/*     */     {
/* 197 */       sb.append("<span id=\"viewmsg\"></span>");
/* 198 */       sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
/*     */     }
/*     */ 
/* 201 */     return sb;
/*     */   }
/*     */   public void setId(String id) {
/* 204 */     this.id = id;
/*     */   }
/*     */   public void setFormData(String formData) {
/* 207 */     this.formData = formData;
/*     */   }
/*     */   public void setExtend(String extend) {
/* 210 */     this.extend = extend;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.UploadTag
 * JD-Core Version:    0.6.0
 */