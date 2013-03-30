/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ 
/*     */ public class FormValidationTag extends TagSupport
/*     */ {
/*  13 */   protected String formid = "formobj";
/*  14 */   protected Boolean refresh = Boolean.valueOf(true);
/*     */   protected String callback;
/*     */   protected String beforeSubmit;
/*  17 */   protected String btnsub = "btn_sub";
/*  18 */   protected String btnreset = "btn_reset";
/*  19 */   protected String layout = "div";
/*     */   protected String usePlugin;
/*  21 */   protected boolean dialog = true;
/*     */   protected String action;
/*     */   protected String tabtitle;
/*     */ 
/*     */   public void setTabtitle(String tabtitle)
/*     */   {
/*  26 */     this.tabtitle = tabtitle;
/*     */   }
/*     */ 
/*     */   public void setDialog(boolean dialog) {
/*  30 */     this.dialog = dialog;
/*     */   }
/*     */ 
/*     */   public void setBtnsub(String btnsub) {
/*  34 */     this.btnsub = btnsub;
/*     */   }
/*     */ 
/*     */   public void setRefresh(Boolean refresh) {
/*  38 */     this.refresh = refresh;
/*     */   }
/*     */ 
/*     */   public void setBtnreset(String btnreset) {
/*  42 */     this.btnreset = btnreset;
/*     */   }
/*     */ 
/*     */   public void setFormid(String formid) {
/*  46 */     this.formid = formid;
/*     */   }
/*     */ 
/*     */   public void setAction(String action) {
/*  50 */     this.action = action;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspException
/*     */   {
/*     */     try {
/*  56 */       JspWriter out = this.pageContext.getOut();
/*  57 */       StringBuffer sb = new StringBuffer();
/*  58 */       if ("div".equals(this.layout)) {
/*  59 */         sb.append("<div id=\"content\">");
/*  60 */         sb.append("<div id=\"wrapper\">");
/*  61 */         sb.append("<div id=\"steps\">");
/*     */       }
/*  63 */       sb.append("<form id=\"" + this.formid + "\" action=\"" + this.action + "\" name=\"" + this.formid + "\" method=\"post\">");
/*  64 */       if (("btn_sub".equals(this.btnsub)) && (this.dialog))
/*  65 */         sb.append("<input type=\"hidden\" id=\"" + this.btnsub + "\" class=\"" + this.btnsub + "\"/>");
/*  66 */       out.print(sb.toString());
/*  67 */       out.flush();
/*     */     } catch (IOException e) {
/*  69 */       e.printStackTrace();
/*     */     }
/*  71 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspException
/*     */   {
/*     */     try {
/*  77 */       JspWriter out = this.pageContext.getOut();
/*  78 */       StringBuffer sb = new StringBuffer();
/*  79 */       if (this.layout.equals("div")) {
/*  80 */         sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/divfrom.css\" type=\"text/css\"/>");
/*  81 */         if (this.tabtitle != null)
/*  82 */           sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/form.js\"></script>");
/*     */       }
/*  84 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\"/>");
/*  85 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
/*  86 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3_min.js\"></script>");
/*  87 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype.js\"></script>");
/*  88 */       if (this.usePlugin != null) {
/*  89 */         if (this.usePlugin.indexOf("jqtransform") >= 0) {
/*  90 */           sb.append("<SCRIPT type=\"text/javascript\" src=\"plug-in/Validform/plugin/jqtransform/jquery.jqtransform.js\"></SCRIPT>");
/*  91 */           sb.append("<LINK rel=\"stylesheet\" href=\"plug-in/Validform/plugin/jqtransform/jqtransform.css\" type=\"text/css\"></LINK>");
/*     */         }
/*  93 */         if (this.usePlugin.indexOf("password") >= 0) {
/*  94 */           sb.append("<SCRIPT type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></SCRIPT>");
/*     */         }
/*     */       }
/*  97 */       sb.append("<script type=\"text/javascript\">");
/*  98 */       sb.append("$(function(){");
/*  99 */       sb.append("$(\"#" + this.formid + "\").Validform({" + "tiptype:function(msg,o,cssctl){");
/* 100 */       sb.append("if(!o.obj.is(\"form\")){");
/* 101 */       if (this.layout.equals("div"))
/* 102 */         sb.append("var objtip=o.obj.siblings(\".Validform_checktip\");");
/*     */       else {
/* 104 */         sb.append("var objtip = o.obj.parents(\"td\").find(\".Validform_checktip\");");
/*     */       }
/* 106 */       sb.append("cssctl(objtip,o.type);objtip.text(msg);}");
/* 107 */       sb.append("},");
/* 108 */       sb.append("btnSubmit:\"#" + this.btnsub + "\",");
/* 109 */       sb.append("btnReset:\"#" + this.btnreset + "\",");
/* 110 */       sb.append("ajaxPost:true,");
/* 111 */       if (this.beforeSubmit != null) {
/* 112 */         sb.append("beforeSubmit:function(curform){var tag=false;");
/* 113 */         sb.append("return " + this.beforeSubmit + "(curform);");
/*     */ 
/* 115 */         sb.append("},");
/*     */       }
/* 117 */       if (this.usePlugin != null) {
/* 118 */         StringBuffer passsb = new StringBuffer();
/* 119 */         if (this.usePlugin.indexOf("password") >= 0) {
/* 120 */           passsb.append("passwordstrength:{");
/* 121 */           passsb.append("minLen:6,");
/* 122 */           passsb.append("maxLen:18,");
/* 123 */           passsb.append("trigger:function(obj,error)");
/* 124 */           passsb.append("{");
/* 125 */           passsb.append("if(error)");
/* 126 */           passsb.append("{");
/* 127 */           passsb.append("obj.parent().next().find(\".Validform_checktip\").show();");
/* 128 */           passsb.append("obj.find(\".passwordStrength\").hide();");
/* 129 */           passsb.append("}");
/* 130 */           passsb.append("else");
/* 131 */           passsb.append("{");
/* 132 */           passsb.append("$(\".passwordStrength\").show();");
/* 133 */           passsb.append("obj.parent().next().find(\".Validform_checktip\").hide();");
/* 134 */           passsb.append("}");
/* 135 */           passsb.append("}");
/* 136 */           passsb.append("}");
/*     */         }
/* 138 */         StringBuffer jqsb = new StringBuffer();
/* 139 */         if (this.usePlugin.indexOf("jqtransform") >= 0) {
/* 140 */           if (this.usePlugin.indexOf("password") >= 0) {
/* 141 */             sb.append(",");
/*     */           }
/* 143 */           jqsb.append("jqtransform :{selector:\"select\"}");
/*     */         }
/* 145 */         sb.append("usePlugin:{");
/* 146 */         if (this.usePlugin.indexOf("password") >= 0) {
/* 147 */           sb.append(passsb);
/*     */         }
/* 149 */         if (this.usePlugin.indexOf("jqtransform") >= 0) {
/* 150 */           sb.append(jqsb);
/*     */         }
/* 152 */         sb.append("},");
/*     */       }
/* 154 */       sb.append("callback:function(data){");
/* 155 */       if (this.dialog) {
/* 156 */         sb.append("var win = frameElement.api.opener;");
/* 157 */         if (this.refresh.booleanValue()) {
/* 158 */           sb.append("win.reloadTable();");
/*     */         }
/* 160 */         if (StringUtil.isNotEmpty(this.callback)) {
/* 161 */           sb.append("win." + this.callback + "(data);");
/*     */         }
/* 163 */         sb.append("if(data.success){frameElement.api.close();}");
/* 164 */         sb.append("win.tip(data.msg);");
/*     */       } else {
/* 166 */         sb.append(this.callback + "(data);");
/*     */       }
/* 168 */       sb.append("}});});</script>");
/* 169 */       sb.append("</form>");
/* 170 */       if ("div".equals(this.layout)) {
/* 171 */         sb.append("</div>");
/* 172 */         if (this.tabtitle != null) {
/* 173 */           String[] tabtitles = this.tabtitle.split(",");
/* 174 */           sb.append("<div id=\"navigation\" style=\"display: none;\">");
/* 175 */           sb.append("<ul>");
/* 176 */           for (String string : tabtitles) {
/* 177 */             sb.append("<li>");
/* 178 */             sb.append("<a href=\"#\">" + string + "</a>");
/* 179 */             sb.append("</li>");
/*     */           }
/* 181 */           sb.append("</ul>");
/* 182 */           sb.append("</div>");
/*     */         }
/* 184 */         sb.append("</div></div>");
/*     */       }
/* 186 */       out.print(sb.toString());
/* 187 */       out.flush();
/*     */     } catch (IOException e) {
/* 189 */       e.printStackTrace();
/*     */     }
/* 191 */     return 6;
/*     */   }
/*     */ 
/*     */   public void setUsePlugin(String usePlugin) {
/* 195 */     this.usePlugin = usePlugin;
/*     */   }
/*     */ 
/*     */   public void setLayout(String layout) {
/* 199 */     this.layout = layout;
/*     */   }
/*     */ 
/*     */   public void setBeforeSubmit(String beforeSubmit) {
/* 203 */     this.beforeSubmit = beforeSubmit;
/*     */   }
/*     */ 
/*     */   public void setCallback(String callback) {
/* 207 */     this.callback = callback;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.FormValidationTag
 * JD-Core Version:    0.6.0
 */