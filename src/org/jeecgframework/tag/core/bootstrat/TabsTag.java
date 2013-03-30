/*    */ package org.jeecgframework.tag.core.bootstrat;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.tag.vo.easyui.TabMenu;
/*    */ 
/*    */ public class TabsTag extends TagSupport
/*    */ {
/*    */   private String classUl;
/* 23 */   private List<TabMenu> tabList = new ArrayList();
/*    */ 
/*    */   public void setClassUl(String classUl) {
/* 26 */     this.classUl = classUl;
/*    */   }
/*    */   public void setTabList(List<TabMenu> tabList) {
/* 29 */     this.tabList = tabList;
/*    */   }
/*    */   public void setTab(String classLi, String url, String title, String icon, String iconColor, boolean tab) {
/* 32 */     TabMenu tabMenu = new TabMenu();
/* 33 */     tabMenu.setClassLi(classLi);
/* 34 */     tabMenu.setIcon(icon);
/* 35 */     tabMenu.setIconColor(iconColor);
/* 36 */     tabMenu.setTitle(title);
/* 37 */     tabMenu.setUrl(url);
/* 38 */     tabMenu.setIconColor(iconColor);
/* 39 */     tabMenu.setTab(tab);
/* 40 */     this.tabList.add(tabMenu);
/*    */   }
/*    */   public int doStartTag() throws JspTagException {
/* 43 */     this.tabList.clear();
/* 44 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 48 */       JspWriter out = this.pageContext.getOut();
/* 49 */       out.print(end().toString());
/*    */     } catch (IOException e) {
/* 51 */       e.printStackTrace();
/*    */     }
/* 53 */     return 6;
/*    */   }
/*    */   public StringBuffer end() {
/* 56 */     StringBuffer sb = new StringBuffer();
/* 57 */     sb.append("<script type=\"text/javascript\" src=\"plug-in/bootstrap/js/bootstrap-tab.js\"></script>");
/* 58 */     sb.append("<ul class=\"nav " + this.classUl + "\">");
/* 59 */     if (this.tabList.size() > 0) {
/* 60 */       for (TabMenu menu : this.tabList) {
/* 61 */         if (menu.getClassLi() != null)
/* 62 */           sb.append("<li class=\"" + menu.getClassLi() + "\">");
/*    */         else {
/* 64 */           sb.append("<li>");
/*    */         }
/* 66 */         if (menu.isTab())
/* 67 */           sb.append("<a href=\"" + menu.getUrl() + "\" data-toggle=\"tab\">");
/*    */         else {
/* 69 */           sb.append("<a href=\"" + menu.getUrl() + "\">");
/*    */         }
/* 71 */         if (menu.getIcon() != null) {
/* 72 */           sb.append("<i class=\"" + menu.getIcon() + " " + menu.getIconColor() + "\"></i>");
/*    */         }
/* 74 */         sb.append(menu.getTitle() + "</a></li>");
/*    */       }
/*    */     }
/* 77 */     sb.append("</ul> ");
/* 78 */     return sb;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.bootstrat.TabsTag
 * JD-Core Version:    0.6.0
 */