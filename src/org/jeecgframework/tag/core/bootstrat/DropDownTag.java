/*     */ package org.jeecgframework.tag.core.bootstrat;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.jeecgframework.tag.vo.easyui.DropDownMenu;
/*     */ 
/*     */ public class DropDownTag extends TagSupport
/*     */ {
/*  31 */   protected boolean btn = false;
/*  32 */   protected boolean dropup = false;
/*     */   protected String butClass;
/*     */   protected String butColor;
/*     */   protected String butIcon;
/*     */   protected String name;
/*  37 */   protected String url = "#";
/*     */   protected String aIcon;
/*  39 */   protected List<DropDownMenu> menuList = new ArrayList();
/*     */ 
/*  41 */   public void setUrl(String url) { this.url = url; }
/*     */ 
/*     */   public void setBtn(boolean btn) {
/*  44 */     this.btn = btn;
/*     */   }
/*     */ 
/*     */   public void setDropup(boolean dropup) {
/*  48 */     this.dropup = dropup;
/*     */   }
/*     */   public void setButClass(String butClass) {
/*  51 */     this.butClass = butClass;
/*     */   }
/*     */ 
/*     */   public void setButColor(String butColor) {
/*  55 */     this.butColor = butColor;
/*     */   }
/*     */ 
/*     */   public void setButIcon(String butIcon) {
/*  59 */     this.butIcon = butIcon;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  63 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setaIcon(String aIcon) {
/*  67 */     this.aIcon = aIcon;
/*     */   }
/*     */ 
/*     */   public void setMenuList(List<DropDownMenu> menuList) {
/*  71 */     this.menuList = menuList;
/*     */   }
/*     */ 
/*     */   public void setColumn(String name, String classLi, String href, String icon, String color)
/*     */   {
/*  83 */     DropDownMenu dropDownMenu = new DropDownMenu();
/*  84 */     dropDownMenu.setClassLi(classLi);
/*  85 */     dropDownMenu.setColor(color);
/*  86 */     dropDownMenu.setHref(href);
/*  87 */     dropDownMenu.setIcon(icon);
/*  88 */     dropDownMenu.setName(name);
/*  89 */     this.menuList.add(dropDownMenu);
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException
/*     */   {
/*  94 */     this.menuList.clear();
/*  95 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspException
/*     */   {
/*     */     try {
/* 101 */       JspWriter out = this.pageContext.getOut();
/* 102 */       out.print(end().toString());
/*     */     } catch (IOException e) {
/* 104 */       e.printStackTrace();
/*     */     }
/* 106 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer end() {
/* 110 */     StringBuffer sb = new StringBuffer();
/* 111 */     if (this.btn) {
/* 112 */       if (this.dropup)
/* 113 */         sb.append("<div class=\"btn-group dropup\">");
/*     */       else {
/* 115 */         sb.append("<div class=\"btn-group\">");
/*     */       }
/* 117 */       sb.append("<a class=\"btn");
/* 118 */       if (this.butClass != null) {
/* 119 */         sb.append(" btn-" + this.butClass);
/*     */       }
/* 121 */       sb.append(" dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\" >");
/*     */     } else {
/* 123 */       sb.append("<ul class=\"nav\">");
/* 124 */       sb.append("<li class=\"dropdown\">");
/* 125 */       sb.append("<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">");
/*     */     }
/* 127 */     if (this.butIcon != null) {
/* 128 */       sb.append("<i class=\"icon-" + this.butIcon);
/* 129 */       if (this.butColor != null) {
/* 130 */         sb.append(" icon-" + this.butColor);
/*     */       }
/* 132 */       sb.append("\"></i>");
/*     */     }
/* 134 */     sb.append(this.name);
/* 135 */     if (this.aIcon != null) {
/* 136 */       sb.append("<span class=\"" + this.aIcon + "\"></span>");
/*     */     }
/* 138 */     sb.append("</a>");
/* 139 */     if (this.menuList.size() > 0) {
/* 140 */       sb.append(" <ul class=\"dropdown-menu\">");
/* 141 */       for (DropDownMenu menu : this.menuList) {
/* 142 */         sb.append("<li");
/* 143 */         if (menu.getClassLi() != null) {
/* 144 */           sb.append(" class=\"" + menu.getClassLi() + "\"");
/*     */         }
/* 146 */         sb.append("><a href=\"" + menu.getHref() + "\">");
/* 147 */         if (menu.getIcon() != null) {
/* 148 */           sb.append("<i class=\"icon-" + menu.getIcon());
/* 149 */           if (menu.getColor() != null) {
/* 150 */             sb.append(" icon-" + menu.getColor());
/*     */           }
/* 152 */           sb.append("\"></i>");
/*     */         }
/* 154 */         sb.append(menu.getName() + "</a></li>");
/*     */       }
/* 156 */       sb.append("</ul>");
/*     */     }
/* 158 */     if (this.btn)
/* 159 */       sb.append("</div>");
/*     */     else {
/* 161 */       sb.append("</li></ul>");
/*     */     }
/* 163 */     return sb;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.DropDownTag
 * JD-Core Version:    0.6.0
 */