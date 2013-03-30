/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.tag.vo.easyui.Tab;
/*     */ 
/*     */ public class TabsTag extends TagSupport
/*     */ {
/*     */   private String id;
/*     */   private String width;
/*     */   private String heigth;
/*     */   private boolean plain;
/*     */   private boolean fit;
/*     */   private boolean border;
/*     */   private String scrollIncrement;
/*     */   private String scrollDuration;
/*     */   private boolean tools;
/*  32 */   private boolean tabs = true;
/*  33 */   private boolean iframe = true;
/*  34 */   private String tabPosition = "top";
/*     */ 
/*  88 */   private List<Tab> tabList = new ArrayList();
/*     */ 
/*     */   public void setIframe(boolean iframe)
/*     */   {
/*  37 */     this.iframe = iframe;
/*     */   }
/*     */ 
/*     */   public void setTabs(boolean tabs) {
/*  41 */     this.tabs = tabs;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  45 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/*  49 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setHeigth(String heigth) {
/*  53 */     this.heigth = heigth;
/*     */   }
/*     */ 
/*     */   public void setPlain(boolean plain) {
/*  57 */     this.plain = plain;
/*     */   }
/*     */ 
/*     */   public void setFit(boolean fit) {
/*  61 */     this.fit = fit;
/*     */   }
/*     */ 
/*     */   public void setBorder(boolean border) {
/*  65 */     this.border = border;
/*     */   }
/*     */ 
/*     */   public void setScrollIncrement(String scrollIncrement) {
/*  69 */     this.scrollIncrement = scrollIncrement;
/*     */   }
/*     */ 
/*     */   public void setScrollDuration(String scrollDuration) {
/*  73 */     this.scrollDuration = scrollDuration;
/*     */   }
/*     */ 
/*     */   public void setTools(boolean tools) {
/*  77 */     this.tools = tools;
/*     */   }
/*     */ 
/*     */   public void setTabPosition(String tabPosition) {
/*  81 */     this.tabPosition = tabPosition;
/*     */   }
/*     */ 
/*     */   public void setTabList(List<Tab> tabList) {
/*  85 */     this.tabList = tabList;
/*     */   }
/*     */ 
/*     */   public int doStartTag()
/*     */     throws JspTagException
/*     */   {
/*  91 */     this.tabList.clear();
/*  92 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException {
/*     */     try {
/*  97 */       JspWriter out = this.pageContext.getOut();
/*  98 */       out.print(end().toString());
/*  99 */       out.flush();
/*     */     } catch (IOException e) {
/* 101 */       e.printStackTrace();
/*     */     }
/* 103 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/* 107 */     StringBuffer sb = new StringBuffer();
/* 108 */     if (this.iframe) {
/* 109 */       sb.append("<script type=\"text/javascript\">");
/* 110 */       sb.append("$(function(){");
/* 111 */       sb.append("$(document).ready(function() {");
/* 112 */       if (this.tabList.size() > 0) {
/* 113 */         for (Tab tab : this.tabList) {
/* 114 */           sb.append("add" + this.id + "('" + tab.getTitle() + "','" + tab.getHref() + "','" + tab.getId() + "','" + tab.getIcon() + "','" + tab.isClosable() + "');");
/*     */         }
/*     */       }
/* 117 */       sb.append("function add" + this.id + "(title,url,id,icon,closable) {");
/* 118 */       sb.append("$('#" + this.id + "').tabs('add',{");
/* 119 */       sb.append("id:id,");
/* 120 */       sb.append("title:title,");
/* 121 */       if (this.iframe)
/* 122 */         sb.append("content:createFrame" + this.id + "(id),");
/*     */       else {
/* 124 */         sb.append("href:url,");
/*     */       }
/* 126 */       sb.append("closable:closable=(closable =='false')?false : true,");
/* 127 */       sb.append("icon:icon");
/* 128 */       sb.append("});");
/* 129 */       sb.append("}");
/* 130 */       sb.append("$('#" + this.id + "').tabs(");
/* 131 */       sb.append("{");
/* 132 */       sb.append("onSelect : function(title) {");
/* 133 */       sb.append("var p = $(this).tabs('getTab', title);");
/* 134 */       if (this.tabList.size() > 0) {
/* 135 */         for (Tab tab : this.tabList) {
/* 136 */           sb.append("if (title == '" + tab.getTitle() + "') {");
/* 137 */           sb.append("p.find('iframe').attr('src',");
/* 138 */           sb.append("'" + tab.getHref() + "');}");
/*     */         }
/*     */       }
/* 141 */       sb.append("}");
/* 142 */       sb.append("});");
/*     */ 
/* 144 */       sb.append("function createFrame" + this.id + "(id)");
/* 145 */       sb.append("{");
/* 146 */       sb.append("var s = '<iframe id=\"'+id+'\" scrolling=\"no\" frameborder=\"0\"  src=\"about:jeecg\" width=\"100%\" height=\"99.5%\"></iframe>';");
/* 147 */       sb.append("return s;");
/* 148 */       sb.append("}");
/* 149 */       sb.append("});");
/* 150 */       sb.append("});");
/* 151 */       sb.append("</script>");
/*     */     }
/* 153 */     if (this.tabs) {
/* 154 */       sb.append("<div id=\"" + this.id + "\" tabPosition=\"" + this.tabPosition + "\" border=flase style=\"margin:0px;padding:0px;overflow:hidden;\" class=\"easyui-tabs\" fit=\"true\">");
/* 155 */       if (!this.iframe) {
/* 156 */         for (Tab tab : this.tabList) {
/* 157 */           if (tab.getHref() != null) {
/* 158 */             sb.append("<div title=\"" + tab.getTitle() + "\" href=\"" + tab.getHref() + "\" style=\"margin:0px;padding:0px;overflow:hidden;\"></div>");
/*     */           } else {
/* 160 */             sb.append("<div title=\"" + tab.getTitle() + "\"  style=\"margin:0px;padding:0px;overflow:hidden;\">");
/* 161 */             sb.append("<iframe id=\"'" + tab.getId() + "'\" scrolling=\"no\" frameborder=\"0\"  src=\"" + tab.getIframe() + "\" width=\"100%\" height=\"99.5%\"></iframe>';");
/* 162 */             sb.append("</div>");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 167 */       sb.append("</div>");
/*     */     }
/*     */ 
/* 170 */     return sb;
/*     */   }
/*     */ 
/*     */   public void setTab(String id, String title, String iframe, String href, String iconCls, boolean cache, String content, String width, String heigth, boolean closable) {
/* 174 */     Tab tab = new Tab();
/* 175 */     tab.setId(id);
/* 176 */     tab.setTitle(title);
/* 177 */     tab.setHref(href);
/* 178 */     tab.setCache(cache);
/* 179 */     tab.setIframe(iframe);
/* 180 */     tab.setContent(content);
/* 181 */     tab.setHeigth(heigth);
/* 182 */     tab.setIcon(iconCls);
/* 183 */     tab.setWidth(width);
/* 184 */     tab.setClosable(closable);
/* 185 */     this.tabList.add(tab);
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.TabsTag
 * JD-Core Version:    0.6.0
 */