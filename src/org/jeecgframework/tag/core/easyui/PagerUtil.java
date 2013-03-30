/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class PagerUtil
/*     */ {
/*   6 */   private int curPageNO = 1;
/*     */   private int rowsCount;
/*     */   private int pageCount;
/*     */   private String actionUrl;
/*     */   private Map<String, Object> map;
/*     */ 
/*     */   public PagerUtil(int curPageNo, int allCount, int pageSize, Map<String, Object> map, String actionUrl)
/*     */   {
/*  12 */     this.curPageNO = curPageNo;
/*  13 */     this.rowsCount = allCount;
/*  14 */     this.map = map;
/*  15 */     this.actionUrl = actionUrl;
/*  16 */     this.pageCount = (int)Math.ceil(allCount / pageSize);
/*     */   }
/*     */ 
/*     */   public int first() {
/*  20 */     return 1;
/*     */   }
/*     */ 
/*     */   public int last()
/*     */   {
/*  25 */     return this.pageCount;
/*     */   }
/*     */ 
/*     */   public int previous()
/*     */   {
/*  30 */     return this.curPageNO - 1 < 1 ? 1 : this.curPageNO - 1;
/*     */   }
/*     */ 
/*     */   public int next()
/*     */   {
/*  35 */     return this.curPageNO + 1 > this.pageCount ? this.pageCount : this.curPageNO + 1;
/*     */   }
/*     */ 
/*     */   public boolean isFirst()
/*     */   {
/*  40 */     return this.curPageNO == 1;
/*     */   }
/*     */ 
/*     */   public boolean isLast()
/*     */   {
/*  45 */     return this.curPageNO == this.pageCount;
/*     */   }
/*     */   protected StringBuffer getStrByImage(StringBuffer sb) {
/*  48 */     String join = getJoin();
/*  49 */     String conditions = getConditions();
/*  50 */     sb.append("<script language='javascript'>\n");
/*  51 */     sb.append("function commonSubmit(val){\n");
/*     */ 
/*  53 */     sb.append("var patrn=/^[0-9]{1,20}$/;\n");
/*  54 */     sb.append("if (!patrn.exec(val)){\n");
/*  55 */     sb.append(" alert(\"请输入有效页号！\");\n");
/*  56 */     sb.append(" return false ;\n");
/*  57 */     sb.append(" }else{\n");
/*  58 */     sb.append("    document.getElementById('pageGoto').href='" + this.actionUrl + join + "curPageNO='+val+'" + conditions + "';" + "\n");
/*  59 */     sb.append("    return true ;\n");
/*  60 */     sb.append("} \n");
/*  61 */     sb.append(" }\n");
/*  62 */     sb.append("</script>\n");
/*  63 */     sb.append("&nbsp;<span class=pageArea id=pageArea>共<b>" + this.rowsCount + "</b>条&nbsp;当前第" + this.curPageNO + "/" + this.pageCount + "页&nbsp;&nbsp;&nbsp;");
/*  64 */     if (isFirst()) {
/*  65 */       sb.append("<a class=\"pageFirstDisable\"  title=\"首页\" onMouseMove=\"style.cursor='hand'\">&nbsp;</a><a class=\"pagePreviousDisable\" title=\"上一页\"  onMouseMove=\"style.cursor='hand'\">&nbsp;</a>");
/*     */     } else {
/*  67 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=1" + conditions + "' class=pageFirst title=首页 onMouseMove=\"style.cursor='hand'\"></a>");
/*  68 */       sb.append("<a class=\"pagePrevious\" href='" + this.actionUrl + join + "curPageNO=" + previous() + conditions + "' title=\"上一页\"  onMouseMove=\"style.cursor='hand'\")\">&nbsp;</a>");
/*     */     }
/*  70 */     if ((this.curPageNO - this.pageCount == 0) || (this.pageCount == 0) || (this.pageCount == 1)) {
/*  71 */       sb.append("<a class=pageNextDisable  title=下一页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pageLastDisable title=尾页 onMouseMove=\"style.cursor='hand'\">&nbsp;</a>&nbsp;");
/*     */     } else {
/*  73 */       sb.append("<a class=pageNext href='" + this.actionUrl + join + "curPageNO=" + next() + conditions + "' title=下一页 onMouseMove=\"style.cursor='hand'\")\">&nbsp;</a>");
/*  74 */       sb.append("<a class=pageLast href='" + this.actionUrl + join + "curPageNO=" + this.pageCount + conditions + "' title=尾页 onMouseMove=\"style.cursor='hand'\" )\">&nbsp;</a>");
/*     */     }
/*     */ 
/*  77 */     if ((this.pageCount == 1) || (this.pageCount == 0)) {
/*  78 */       sb.append(" &nbsp;转到:<input class=\"SmallInput\" type=text style=TEXT-ALIGN: center maxLength=4 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;");
/*  79 */       sb.append("<A class=pageGoto id=pageGoto title=转到 onclick='return commonSubmit()'>&nbsp;</A>&nbsp;&nbsp;</span>");
/*     */     } else {
/*  81 */       sb.append(" &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=4 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;");
/*  82 */       sb.append("<A  class=pageGoto id=pageGoto title=转到 onclick='commonSubmit(document.all.pageroffsetll.value)'>&nbsp;</A>&nbsp;</span");
/*     */     }
/*  84 */     return sb;
/*     */   }
/*     */ 
/*     */   protected StringBuffer getStr(StringBuffer sb) {
/*  88 */     String join = getJoin();
/*  89 */     String conditions = getConditions();
/*     */ 
/*  91 */     String str = "";
/*  92 */     str = str;
/*  93 */     if (isFirst()) {
/*  94 */       sb.append("第" + this.curPageNO + "页&nbsp;共" + this.pageCount + "页&nbsp;首页 ");
/*     */     } else {
/*  96 */       sb.append("第" + this.curPageNO + "页&nbsp;共" + this.pageCount + "页&nbsp;<a href='" + this.actionUrl + join + "curPageNO=1" + conditions + "'>首页</a>&nbsp;");
/*  97 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=" + previous() + conditions + "' onMouseMove=\"style.cursor='hand'\" alt=\"上一页\">上一页</a>&nbsp;");
/*     */     }
/*  99 */     if ((isLast()) || (this.rowsCount == 0)) {
/* 100 */       sb.append("尾页&nbsp;");
/*     */     } else {
/* 102 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=" + next() + conditions + "' onMouseMove=\"style.cursor='hand'\" >下一页</a>&nbsp;");
/* 103 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=" + this.pageCount + conditions + "'>尾页</a>&nbsp;");
/*     */     }
/* 105 */     sb.append("&nbsp;共" + this.rowsCount + "条记录&nbsp;");
/*     */ 
/* 107 */     str = str + "&nbsp;转到<select name='page' onChange=\"location='" + this.actionUrl + join + "curPageNO='+this.options[this.selectedIndex].value\">";
/* 108 */     int begin = this.curPageNO > 10 ? this.curPageNO - 10 : 1;
/* 109 */     int end = this.pageCount - this.curPageNO > 10 ? this.curPageNO + 10 : this.pageCount;
/* 110 */     for (int i = begin; i <= end; i++) {
/* 111 */       if (i == this.curPageNO)
/* 112 */         str = str + "<option value='" + i + "' selected>第" + i + "页</option>";
/*     */       else
/* 114 */         str = str + "<option value='" + i + "'>第" + i + "页</option>";
/*     */     }
/* 116 */     str = str + "</select>";
/* 117 */     sb.append(str);
/* 118 */     return sb;
/*     */   }
/*     */ 
/*     */   protected String getConditions()
/*     */   {
/* 126 */     String conditions = "";
/* 127 */     if (this.map != null)
/*     */     {
/* 129 */       for (Map.Entry entry : this.map.entrySet()) {
/* 130 */         conditions = conditions + "&" + (String)entry.getKey() + "=" + entry.getValue();
/*     */       }
/*     */     }
/* 133 */     return conditions;
/*     */   }
/*     */ 
/*     */   protected String getJoin()
/*     */   {
/* 143 */     String join = "";
/* 144 */     if (this.actionUrl.indexOf("?") == -1)
/* 145 */       join = "?";
/*     */     else {
/* 147 */       join = "&";
/*     */     }
/* 149 */     return join;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.PagerUtil
 * JD-Core Version:    0.6.0
 */