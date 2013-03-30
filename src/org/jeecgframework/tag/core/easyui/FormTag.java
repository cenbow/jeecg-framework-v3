/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.core.util.ContextHolderUtils;
/*     */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*     */ import org.jeecgframework.workflow.pojo.base.TPFormpro;
/*     */ 
/*     */ public class FormTag extends TagSupport
/*     */ {
/*     */   protected String action;
/*     */   protected List<TPFormpro> items;
/*     */ 
/*     */   public int doStartTag()
/*     */     throws JspTagException
/*     */   {
/*  28 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  33 */       JspWriter out = this.pageContext.getOut();
/*  34 */       out.print(end().toString());
/*  35 */       out.flush();
/*     */     } catch (IOException e) {
/*  37 */       e.printStackTrace();
/*     */     }
/*  39 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/*  43 */     StringBuffer sb = new StringBuffer();
/*  44 */     StringBuffer jssb = new StringBuffer();
/*  45 */     StringBuffer radio = new StringBuffer("1");
/*  46 */     StringBuffer checkbox = new StringBuffer("1");
/*  47 */     List<TPFormpro> formpros = this.items;
/*  48 */     TPForm form = null;
/*  49 */     if ((formpros != null) && (formpros.size() > 0)) {
/*  50 */       form = ((TPFormpro)formpros.get(0)).getTPForm();
/*  51 */       if (this.action == null) {
/*  52 */         this.action = form.getFormaction();
/*     */       }
/*  54 */       sb.append("<fieldset class=\"step\">");
/*  55 */       sb.append("<legend>" + form.getFormnote() + "</legend>");
/*  56 */       for (TPFormpro tFormpro : formpros)
/*     */       {
/*  58 */         Valid(tFormpro, jssb);
/*     */ 
/*  60 */         form(tFormpro, sb, radio, checkbox);
/*     */       }
/*  62 */       sb.append("<script type=\"text/javascript\">");
/*  63 */       sb.append(jssb.toString());
/*  64 */       sb.append("</script>");
/*     */     }
/*  66 */     return sb;
/*     */   }
/*     */ 
/*     */   public StringBuffer Valid(TPFormpro tFormpro, StringBuffer jssb) {
/*  70 */     if (tFormpro.getFormprofun().length() > 0) {
/*  71 */       jssb.append("function " + tFormpro.getFormprofun() + "(){");
/*  72 */       jssb.append(tFormpro.getFormproscript() + "};");
/*     */     }
/*  74 */     return jssb;
/*     */   }
/*     */ 
/*     */   public StringBuffer form(TPFormpro tFormpro, StringBuffer sb, StringBuffer b, StringBuffer c)
/*     */   {
/*  80 */     if (tFormpro.getFormprotype().equals("hidden")) {
/*  81 */       sb.append("<input type=\"hidden\" name=\"" + 
/*  82 */         tFormpro.getFormprokey() + "\" id=\"" + 
/*  83 */         tFormpro.getFormprokey() + "\"");
/*  84 */       if (tFormpro.getFormproval().startsWith("$")) {
/*  85 */         String name = tFormpro.getFormproval().substring(
/*  86 */           tFormpro.getFormproval().indexOf("$") + 2, 
/*  87 */           tFormpro.getFormproval().length() - 1);
/*  88 */         String val = ContextHolderUtils.getRequest().getAttribute(name)
/*  89 */           .toString();
/*  90 */         sb.append(" value=\"" + val + "\">");
/*     */       } else {
/*  92 */         sb.append(" value=\"" + tFormpro.getFormproval() + "\">");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  97 */     if (tFormpro.getFormprotype().equals("radio")) {
/*  98 */       if (b.toString().equals("1")) {
/*  99 */         sb.append("<div class=\"form\">");
/* 100 */         sb.append("<label class=\"form\">操作：</label>");
/*     */       }
/* 102 */       sb.append("<input type=\"radio\" name=\"opt\"");
/* 103 */       if (tFormpro.getFormprofun() != null) {
/* 104 */         sb.append("onClick=" + tFormpro.getFormprofun() + "();");
/*     */       }
/* 106 */       sb.append("id=\"" + tFormpro.getFormprokey() + "\"  value=\"" + 
/* 107 */         tFormpro.getId() + "\">" + 
/* 108 */         tFormpro.getFormproname() + "&nbsp;&nbsp;");
/* 109 */       if (!b.toString().equals("1")) {
/* 110 */         sb.append("</div>");
/*     */       }
/* 112 */       b.append("2");
/*     */     }
/*     */ 
/* 115 */     if (tFormpro.getFormprotype().equals("text")) {
/* 116 */       sb.append("<div class=\"form\">");
/* 117 */       sb.append("<label class=\"form\">" + tFormpro.getFormproname() + 
/* 118 */         ":</label>");
/* 119 */       sb.append("<input type=\"text\" name=\"" + tFormpro.getFormprokey() + 
/* 120 */         "\" id=\"" + tFormpro.getFormprokey() + "\" value=\"" + 
/* 121 */         tFormpro.getFormproval() + "\">");
/* 122 */       sb.append("</div>");
/*     */     }
/*     */ 
/* 125 */     if (tFormpro.getFormprotype().equals("checkbox")) {
/* 126 */       if (c.toString().equals("1")) {
/* 127 */         sb.append("<div class=\"form\">");
/* 128 */         sb.append("<label class=\"form\">多选操作：</label>");
/*     */       }
/* 130 */       sb.append("<input type=\"checkbox\" name=\"" + 
/* 131 */         tFormpro.getFormprokey() + "\" id=\"" + 
/* 132 */         tFormpro.getFormprokey() + "\" value=\"" + 
/* 133 */         tFormpro.getFormproval() + "\">" + 
/* 134 */         tFormpro.getFormproname() + "&nbsp;&nbsp;");
/* 135 */       if (!c.toString().equals("1")) {
/* 136 */         sb.append("</div>");
/*     */       }
/* 138 */       c.append("2");
/*     */     }
/*     */ 
/* 141 */     if (tFormpro.getFormprotype().equals("textarea")) {
/* 142 */       sb.append("<div class=\"form\"");
/* 143 */       if (tFormpro.getFormprocss().length() > 0) {
/* 144 */         sb.append(tFormpro.getFormprocss());
/*     */       }
/* 146 */       sb.append(">");
/* 147 */       sb.append("<label class=\"form\">" + tFormpro.getFormproname() + 
/* 148 */         ":</label>");
/* 149 */       sb.append("<textarea name=\"" + tFormpro.getFormprokey() + 
/* 150 */         "\"  rows=\"3\" cols=\"30\" id=\"" + 
/* 151 */         tFormpro.getFormprokey() + "\" rows=\"\">");
/* 152 */       sb.append("</textarea>");
/* 153 */       sb.append("</div>");
/*     */     }
/*     */ 
/* 156 */     if (tFormpro.getFormprotype().equals("select")) {
/* 157 */       String val = tFormpro.getFormproval();
/* 158 */       String[] vals = val.split(",");
/* 159 */       sb.append("<div class=\"form\">");
/* 160 */       sb.append("<label class=\"form\">" + tFormpro.getFormproname() + 
/* 161 */         ":</label>");
/* 162 */       sb.append("<select name=\"" + tFormpro.getFormprokey() + "\" id=\"" + 
/* 163 */         tFormpro.getFormprokey() + "\">");
/* 164 */       if (vals.length > 0) {
/* 165 */         for (int i = 0; i < vals.length; i++) {
/* 166 */           sb.append("<option value=\"" + vals[i] + "\">" + vals[i] + 
/* 167 */             "</option>");
/*     */         }
/*     */       }
/* 170 */       sb.append("</select></div>");
/*     */     }
/* 172 */     return sb;
/*     */   }
/*     */ 
/*     */   public void setAction(String action) {
/* 176 */     this.action = action;
/*     */   }
/*     */ 
/*     */   public void setItems(List<TPFormpro> items) {
/* 180 */     this.items = items;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.FormTag
 * JD-Core Version:    0.6.0
 */