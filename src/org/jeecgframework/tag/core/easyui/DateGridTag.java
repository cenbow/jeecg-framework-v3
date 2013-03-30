/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.vo.easyui.ColumnValue;
/*     */ import org.jeecgframework.tag.vo.easyui.DateGridColumn;
/*     */ import org.jeecgframework.tag.vo.easyui.DateGridUrl;
/*     */ import org.jeecgframework.tag.vo.easyui.OptTypeDirection;
/*     */ 
/*     */ public class DateGridTag extends TagSupport
/*     */ {
/*  31 */   protected String fields = "";
/*     */   protected String name;
/*     */   protected String title;
/*  34 */   protected String idField = "id";
/*  35 */   protected boolean treegrid = false;
/*  36 */   protected List<DateGridUrl> urlList = new ArrayList();
/*  37 */   protected List<DateGridUrl> toolBarList = new ArrayList();
/*  38 */   protected List<DateGridColumn> columnList = new ArrayList();
/*  39 */   protected List<ColumnValue> columnValueList = new ArrayList();
/*     */   public Map<String, Object> map;
/*     */   private String actionUrl;
/*     */   public int allCount;
/*     */   public int curPageNo;
/*  44 */   public int pageSize = 10;
/*  45 */   public boolean pagination = true;
/*     */   private String width;
/*     */   private String height;
/*  48 */   private boolean checkbox = false;
/*  49 */   private boolean showPageList = true;
/*  50 */   private boolean fit = true;
/*  51 */   private boolean fitColumns = true;
/*     */   private String sortName;
/*  53 */   private String sortOrder = "asc";
/*  54 */   private boolean showRefresh = true;
/*  55 */   private boolean showText = true;
/*  56 */   private String style = "easyui";
/*     */   private String onLoadSuccess;
/*     */   private String onClick;
/*     */   private String onDblClick;
/*     */ 
/*     */   public void setOnLoadSuccess(String onLoadSuccess)
/*     */   {
/*  62 */     this.onLoadSuccess = onLoadSuccess;
/*     */   }
/*     */ 
/*     */   public void setOnClick(String onClick) {
/*  66 */     this.onClick = onClick;
/*     */   }
/*     */ 
/*     */   public void setOnDblClick(String onDblClick) {
/*  70 */     this.onDblClick = onDblClick;
/*     */   }
/*     */ 
/*     */   public void setShowText(boolean showText) {
/*  74 */     this.showText = showText;
/*     */   }
/*     */ 
/*     */   public void setPagination(boolean pagination) {
/*  78 */     this.pagination = pagination;
/*     */   }
/*     */ 
/*     */   public void setCheckbox(boolean checkbox) {
/*  82 */     this.checkbox = checkbox;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/*  86 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public void setTreegrid(boolean treegrid) {
/*  90 */     this.treegrid = treegrid;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/*  94 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/*  98 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public void setIdField(String idField) {
/* 102 */     this.idField = idField;
/*     */   }
/*     */ 
/*     */   public void setActionUrl(String actionUrl) {
/* 106 */     this.actionUrl = actionUrl;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 110 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 114 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setFit(boolean fit) {
/* 118 */     this.fit = fit;
/*     */   }
/*     */ 
/*     */   public void setShowPageList(boolean showPageList) {
/* 122 */     this.showPageList = showPageList;
/*     */   }
/*     */ 
/*     */   public void setShowRefresh(boolean showRefresh) {
/* 126 */     this.showRefresh = showRefresh;
/*     */   }
/*     */ 
/*     */   public void setConfUrl(String url, String title, String message, String exp)
/*     */   {
/* 132 */     DateGridUrl dateGridUrl = new DateGridUrl();
/* 133 */     dateGridUrl.setTitle(title);
/* 134 */     dateGridUrl.setUrl(url);
/* 135 */     dateGridUrl.setType(OptTypeDirection.Confirm);
/* 136 */     dateGridUrl.setMessage(message);
/* 137 */     dateGridUrl.setExp(exp);
/* 138 */     this.urlList.add(dateGridUrl);
/*     */   }
/*     */ 
/*     */   public void setDelUrl(String url, String title, String message, String exp, String funname)
/*     */   {
/* 146 */     DateGridUrl dateGridUrl = new DateGridUrl();
/* 147 */     dateGridUrl.setTitle(title);
/* 148 */     dateGridUrl.setUrl(url);
/* 149 */     dateGridUrl.setType(OptTypeDirection.Del);
/* 150 */     dateGridUrl.setMessage(message);
/* 151 */     dateGridUrl.setExp(exp);
/* 152 */     dateGridUrl.setFunname(funname);
/* 153 */     this.urlList.add(dateGridUrl);
/*     */   }
/*     */ 
/*     */   public void setDefUrl(String url, String title, String exp)
/*     */   {
/* 159 */     DateGridUrl dateGridUrl = new DateGridUrl();
/* 160 */     dateGridUrl.setTitle(title);
/* 161 */     dateGridUrl.setUrl(url);
/* 162 */     dateGridUrl.setType(OptTypeDirection.Deff);
/* 163 */     dateGridUrl.setExp(exp);
/* 164 */     this.urlList.add(dateGridUrl);
/*     */   }
/*     */ 
/*     */   public void setToolbar(String url, String title, String icon, String exp, String onclick, String funname)
/*     */   {
/* 170 */     DateGridUrl dateGridUrl = new DateGridUrl();
/* 171 */     dateGridUrl.setTitle(title);
/* 172 */     dateGridUrl.setUrl(url);
/* 173 */     dateGridUrl.setType(OptTypeDirection.ToolBar);
/* 174 */     dateGridUrl.setIcon(icon);
/* 175 */     dateGridUrl.setOnclick(onclick);
/* 176 */     dateGridUrl.setExp(exp);
/* 177 */     dateGridUrl.setFunname(funname);
/* 178 */     this.toolBarList.add(dateGridUrl);
/*     */   }
/*     */ 
/*     */   public void setFunUrl(String title, String exp, String funname)
/*     */   {
/* 185 */     DateGridUrl dateGridUrl = new DateGridUrl();
/* 186 */     dateGridUrl.setTitle(title);
/* 187 */     dateGridUrl.setType(OptTypeDirection.Fun);
/* 188 */     dateGridUrl.setExp(exp);
/* 189 */     dateGridUrl.setFunname(funname);
/* 190 */     this.urlList.add(dateGridUrl);
/*     */   }
/*     */ 
/*     */   public void setOpenUrl(String url, String title, String width, String height, String exp)
/*     */   {
/* 197 */     DateGridUrl dateGridUrl = new DateGridUrl();
/* 198 */     dateGridUrl.setTitle(title);
/* 199 */     dateGridUrl.setUrl(url);
/* 200 */     dateGridUrl.setWidth(width);
/* 201 */     dateGridUrl.setHeight(height);
/* 202 */     dateGridUrl.setType(OptTypeDirection.OpenWin);
/* 203 */     dateGridUrl.setExp(exp);
/* 204 */     this.urlList.add(dateGridUrl);
/*     */   }
/*     */ 
/*     */   public void setColumn(String title, String field, Integer width, String rowspan, String colspan, String align, boolean sortable, boolean checkbox, String formatter, boolean hidden, String replace, String treefield, boolean image, boolean query, String url, String funname, String arg)
/*     */   {
/* 216 */     DateGridColumn dateGridColumn = new DateGridColumn();
/* 217 */     dateGridColumn.setAlign(align);
/* 218 */     dateGridColumn.setCheckbox(checkbox);
/* 219 */     dateGridColumn.setColspan(colspan);
/* 220 */     dateGridColumn.setField(field);
/* 221 */     dateGridColumn.setFormatter(formatter);
/* 222 */     dateGridColumn.setHidden(hidden);
/* 223 */     dateGridColumn.setRowspan(rowspan);
/* 224 */     dateGridColumn.setSortable(sortable);
/* 225 */     dateGridColumn.setTitle(title);
/* 226 */     dateGridColumn.setWidth(width);
/* 227 */     dateGridColumn.setTreefield(treefield);
/* 228 */     dateGridColumn.setImage(image);
/* 229 */     dateGridColumn.setQuery(query);
/* 230 */     dateGridColumn.setUrl(url);
/* 231 */     dateGridColumn.setFunname(funname);
/* 232 */     dateGridColumn.setArg(arg);
/* 233 */     this.columnList.add(dateGridColumn);
/* 234 */     if (field != "opt") {
/* 235 */       this.fields = (this.fields + field + ",");
/*     */     }
/* 237 */     if (replace != null) {
/* 238 */       String[] test = replace.split(",");
/* 239 */       String text = "";
/* 240 */       String value = "";
/* 241 */       for (String string : test) {
/* 242 */         text = text + string.substring(0, string.indexOf("_")) + ",";
/* 243 */         value = value + string.substring(string.indexOf("_") + 1) + ",";
/*     */       }
/* 245 */       setColumn(field, text, value);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setColumn(String name, String text, String value)
/*     */   {
/* 259 */     ColumnValue columnValue = new ColumnValue();
/* 260 */     columnValue.setName(name);
/* 261 */     columnValue.setText(text);
/* 262 */     columnValue.setValue(value);
/* 263 */     this.columnValueList.add(columnValue);
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspTagException
/*     */   {
/* 268 */     this.urlList.clear();
/* 269 */     this.toolBarList.clear();
/* 270 */     this.columnValueList.clear();
/* 271 */     this.columnList.clear();
/* 272 */     this.fields = "";
/* 273 */     return 6;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspException
/*     */   {
/*     */     try {
/* 279 */       JspWriter out = this.pageContext.getOut();
/* 280 */       if (this.style.equals("easyui")) {
/* 281 */         out.print(end().toString());
/*     */       } else {
/* 283 */         out.print(datatables().toString());
/* 284 */         out.flush();
/*     */       }
/*     */     } catch (IOException e) {
/* 287 */       e.printStackTrace();
/*     */     }
/* 289 */     return 6;
/*     */   }
/*     */ 
/*     */   public StringBuffer datatables()
/*     */   {
/* 298 */     StringBuffer sb = new StringBuffer();
/* 299 */     sb.append("<script type=\"text/javascript\">");
/* 300 */     sb.append("$(document).ready(function() {");
/* 301 */     sb.append("var oTable = $('#userList').dataTable({");
/*     */ 
/* 304 */     sb.append("\"bProcessing\" : true,");
/* 305 */     sb.append("\"bPaginate\" : true,");
/* 306 */     sb.append("\"sPaginationType\" : \"full_numbers\",");
/* 307 */     sb.append("\"bFilter\" : true,");
/* 308 */     sb.append("\"bSort\" : true, ");
/* 309 */     sb.append("\"bAutoWidth\" : true,");
/* 310 */     sb.append("\"bLengthChange\" : true,");
/* 311 */     sb.append("\"bInfo\" : true,");
/* 312 */     sb.append("\"sAjaxSource\" : \"userController.do?test\",");
/* 313 */     sb.append("\"bServerSide\" : true,");
/* 314 */     sb.append("\"oLanguage\" : {\"sLengthMenu\" : \" _MENU_ 条记录\",\"sZeroRecords\" : \"没有检索到数据\",\"sInfo\" : \"第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条\",\"sInfoEmtpy\" : \"没有数据\",\"sProcessing\" : \"正在加载数据...\",\"sSearch\" : \"搜索\",\"oPaginate\" : {\"sFirst\" : \"首页\",\"sPrevious\" : \"前页\", \"sNext\" : \"后页\",\"sLast\" : \"尾页\"}},");
/*     */ 
/* 316 */     sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
/*     */ 
/* 318 */     sb.append("oSettings.jqXHR = $.ajax({\"dataType\" : 'json',\"type\" : \"POST\",\"url\" : sSource,\"data\" : aoData,\"success\" : fnCallback});},");
/*     */ 
/* 325 */     sb.append("\"aoColumns\" : [ ");
/* 326 */     int i = 0;
/* 327 */     for (DateGridColumn column : this.columnList) {
/* 328 */       i++;
/* 329 */       sb.append("{");
/* 330 */       sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
/* 331 */       if (column.getField().equals("opt")) {
/* 332 */         sb.append(",\"mData\":\"" + this.idField + "\"");
/* 333 */         sb.append(",\"sWidth\":\"20%\"");
/* 334 */         sb.append(",\"bSortable\":false");
/* 335 */         sb.append(",\"bSearchable\":false");
/* 336 */         sb.append(",\"mRender\" : function(data, type, rec) {");
/* 337 */         getOptUrl(sb);
/* 338 */         sb.append("}");
/*     */       } else {
/* 340 */         int colwidth = column.getWidth() == null ? column.getTitle().length() * 15 : column.getWidth().intValue();
/* 341 */         sb.append(",\"sName\":\"" + column.getField() + "\"");
/* 342 */         sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
/* 343 */         sb.append(",\"mData\":\"" + column.getField() + "\"");
/* 344 */         sb.append(",\"sWidth\":\"" + colwidth + "\"");
/* 345 */         sb.append(",\"bSortable\":" + column.isSortable());
/* 346 */         sb.append(",\"bVisible\":" + column.isHidden());
/* 347 */         sb.append(",\"bSearchable\":" + column.isQuery());
/*     */       }
/* 349 */       sb.append("}");
/* 350 */       if (i < this.columnList.size()) {
/* 351 */         sb.append(",");
/*     */       }
/*     */     }
/* 354 */     sb.append("]});});</script>");
/* 355 */     sb.append("<table width=\"100%\"  class=\"" + this.style + "\" id=\"" + this.name + "\" toolbar=\"#" + this.name + "tb\"></table>");
/* 356 */     return sb;
/*     */   }
/*     */ 
/*     */   public void setStyle(String style)
/*     */   {
/* 361 */     this.style = style;
/*     */   }
/*     */ 
/*     */   public StringBuffer end()
/*     */   {
/* 370 */     String grid = "";
/* 371 */     StringBuffer sb = new StringBuffer();
/* 372 */     this.width = (this.width == null ? "auto" : this.width);
/* 373 */     this.height = (this.height == null ? "auto" : this.height);
/* 374 */     sb.append("<script type=\"text/javascript\">");
/* 375 */     sb.append("$(function(){");
/* 376 */     if (this.treegrid) {
/* 377 */       grid = "treegrid";
/* 378 */       sb.append("$('#" + this.name + "').treegrid({");
/* 379 */       sb.append("idField:'id',");
/* 380 */       sb.append("treeField:'text',");
/*     */     } else {
/* 382 */       grid = "datagrid";
/* 383 */       sb.append("$('#" + this.name + "').datagrid({");
/* 384 */       sb.append("idField: '" + this.idField + "',");
/*     */     }
/* 386 */     if (this.title != null) {
/* 387 */       sb.append("title: '" + this.title + "',");
/*     */     }
/* 389 */     sb.append("url:'" + this.actionUrl + "&field=" + this.fields + "',");
/* 390 */     if (this.fit)
/* 391 */       sb.append("fit:true,");
/*     */     else {
/* 393 */       sb.append("fit:false,");
/*     */     }
/* 395 */     sb.append("loadMsg: '数据加载中...',");
/* 396 */     sb.append("pageSize: " + this.pageSize + ",");
/* 397 */     sb.append("pagination:" + this.pagination + ",");
/*     */ 
/* 399 */     if (StringUtils.isNotBlank(this.sortName)) {
/* 400 */       sb.append("sortName:'" + this.sortName + "',");
/*     */     }
/*     */ 
/* 403 */     sb.append("sortOrder:'" + this.sortOrder + "',");
/* 404 */     sb.append("rownumbers:true,");
/* 405 */     sb.append("singleSelect:" + (!this.checkbox) + ",");
/* 406 */     if (this.fitColumns)
/* 407 */       sb.append("fitColumns:true,");
/*     */     else {
/* 409 */       sb.append("fitColumns:false,");
/*     */     }
/* 411 */     sb.append("columns:[[");
/* 412 */     getField(sb);
/* 413 */     sb.append("]],");
/* 414 */     if (StringUtil.isNotEmpty(this.onLoadSuccess)) {
/* 415 */       sb.append("onLoadSuccess:function(data){" + this.onLoadSuccess + "(data);},");
/*     */     }
/* 417 */     if (StringUtil.isNotEmpty(this.onDblClick)) {
/* 418 */       sb.append("onDblClickRow:function(rowIndex,rowData){" + this.onDblClick + "(rowIndex,rowData);},");
/*     */     }
/* 420 */     if (this.treegrid) {
/* 421 */       sb.append("onClickRow:function(rowData){");
/*     */     }
/*     */     else {
/* 424 */       sb.append("onClickRow:function(rowIndex,rowData){");
/*     */     }
/*     */ 
/* 427 */     sb.append("rowid=rowData.id;");
/* 428 */     sb.append("gridname='" + this.name + "';");
/* 429 */     if (StringUtil.isNotEmpty(this.onClick)) {
/* 430 */       sb.append(this.onClick + "(rowIndex,rowData);");
/*     */     }
/* 432 */     sb.append("}");
/* 433 */     sb.append("});");
/* 434 */     setPager(sb, grid);
/* 435 */     sb.append("});");
/* 436 */     sb.append("function reloadTable(){");
/* 437 */     sb.append("$('#'+gridname)." + grid + "('reload');" + "}");
/* 438 */     sb.append("function reload" + this.name + "(){" + "$('#" + this.name + "')." + grid + "('reload');" + "}");
/* 439 */     sb.append("function get" + this.name + "Selected(field){return getSelected(field);}");
/* 440 */     sb.append("function getSelected(field){var row = $('#'+gridname)." + grid + "('getSelected');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value='';" + "}" + "return value;" + "}");
/* 441 */     sb.append("function get" + this.name + "Selections(field){" + "var ids = [];" + "var rows = $('#" + this.name + "')." + grid + "('getSelections');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(',');" + "return ids" + "};");
/* 442 */     if (this.columnList.size() > 0) {
/* 443 */       sb.append("function " + this.name + "search(){");
/* 444 */       sb.append("var queryParams=$('#" + this.name + "').datagrid('options').queryParams;");
/* 445 */       for (DateGridColumn col : this.columnList) {
/* 446 */         if (col.isQuery()) {
/* 447 */           sb.append("queryParams." + col.getField() + "= $('#" + col.getField() + "').val();");
/*     */         }
/*     */       }
/* 450 */       sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.fields + "'});" + "}");
/*     */ 
/* 452 */       sb.append("function dosearch(params){");
/* 453 */       sb.append("var jsonparams=$.parseJSON(params);");
/* 454 */       sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.fields + "',queryParams:jsonparams});" + "}");
/* 455 */       if (this.toolBarList.size() > 0)
/*     */       {
/* 458 */         searchboxFun(sb, grid);
/*     */       }
/*     */     }
/* 461 */     sb.append("</script>");
/* 462 */     sb.append("<table width=\"100%\"   id=\"" + this.name + "\" toolbar=\"#" + this.name + "tb\"></table>");
/* 463 */     if (this.toolBarList.size() > 0)
/*     */     {
/* 466 */       sb.append("<div id=\"" + this.name + "tb\" style=\"padding:3px; height: 25px\">");
/* 467 */       sb.append("<div style=\"float: left;\">");
/* 468 */       for (DateGridUrl toolBar : this.toolBarList) {
/* 469 */         sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
/* 470 */         if (StringUtil.isNotEmpty(toolBar.getOnclick()))
/*     */         {
/* 472 */           sb.append("onclick=" + toolBar.getOnclick());
/*     */         }
/*     */         else {
/* 475 */           sb.append("onclick=\"" + toolBar.getFunname() + "(");
/* 476 */           if (!toolBar.getFunname().equals("doSubmit"))
/*     */           {
/* 478 */             sb.append("'" + toolBar.getTitle() + "',");
/*     */           }
/* 480 */           sb.append("'" + toolBar.getUrl() + "','" + this.name + "')\"");
/*     */         }
/* 482 */         sb.append(">" + toolBar.getTitle() + "</a>");
/*     */       }
/* 484 */       sb.append("</div>");
/* 485 */       sb.append("<div align=\"right\">");
/* 486 */       sb.append("<input id=\"" + this.name + "searchbox\"></input>");
/* 487 */       sb.append("<div id=\"" + this.name + "mm\" style=\"width:120px\">");
/* 488 */       for (DateGridColumn col : this.columnList) {
/* 489 */         if (col.isQuery()) {
/* 490 */           sb.append("<div data-options=\"name:'" + col.getField() + "',iconCls:'icon-ok'\">" + col.getTitle() + "</div>  ");
/*     */         }
/*     */       }
/* 493 */       sb.append("</div>");
/* 494 */       sb.append("</div>");
/* 495 */       sb.append("</div>");
/*     */     }
/* 497 */     return sb;
/*     */   }
/*     */ 
/*     */   protected void getOptUrl(StringBuffer sb)
/*     */   {
/* 506 */     List<DateGridUrl> list = this.urlList;
/* 507 */     sb.append("var href='';");
/* 508 */     for (DateGridUrl dateGridUrl : list) {
/* 509 */       String url = dateGridUrl.getUrl();
/* 510 */       MessageFormat formatter = new MessageFormat("");
/* 511 */       if (dateGridUrl.getValue() != null) {
/* 512 */         String[] testvalue = dateGridUrl.getValue().split(",");
/* 513 */         List value = new ArrayList();
/* 514 */         for (String string : testvalue) {
/* 515 */           value.add("\"+rec." + string + " +\"");
/*     */         }
/* 517 */         url = MessageFormat.format(url, value.toArray());
/*     */       }
/* 519 */       if ((url != null) && (dateGridUrl.getValue() == null))
/*     */       {
/* 521 */         url = formatUrl(url);
/*     */       }
/* 523 */       String exp = dateGridUrl.getExp();
/* 524 */       if (StringUtil.isNotEmpty(exp)) {
/* 525 */         String[] ShowbyFields = exp.split("&&");
/* 526 */         for (String ShowbyField : ShowbyFields) {
/* 527 */           int beginIndex = ShowbyField.indexOf("#");
/* 528 */           int endIndex = ShowbyField.lastIndexOf("#");
/* 529 */           String exptype = ShowbyField.substring(beginIndex + 1, endIndex);
/* 530 */           String field = ShowbyField.substring(0, beginIndex);
/* 531 */           String[] values = ShowbyField.substring(endIndex + 1, ShowbyField.length()).split(",");
/* 532 */           String value = "";
/* 533 */           for (int i = 0; i < values.length; i++) {
/* 534 */             value = value + "'" + values[i] + "'";
/* 535 */             if (i < values.length - 1) {
/* 536 */               value = value + ",";
/*     */             }
/*     */           }
/* 539 */           if ("eq".equals(exptype)) {
/* 540 */             sb.append("if($.inArray(rec." + field + ",[" + value + "])>=0){");
/*     */           }
/* 542 */           if ("ne".equals(exptype)) {
/* 543 */             sb.append("if($.inArray(rec." + field + ",[" + value + "])<0){");
/*     */           }
/* 545 */           if (("empty".equals(exptype)) && (value.equals("'true'"))) {
/* 546 */             sb.append("if(rec." + field + "==''){");
/*     */           }
/* 548 */           if (("empty".equals(exptype)) && (value.equals("'false'"))) {
/* 549 */             sb.append("if(rec." + field + "!=''){");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 554 */       if (OptTypeDirection.Confirm.equals(dateGridUrl.getType())) {
/* 555 */         sb.append("href+=\"[<a href='#' onclick=confirm('" + url + "','" + dateGridUrl.getMessage() + "')> \";");
/*     */       }
/* 557 */       if (OptTypeDirection.Del.equals(dateGridUrl.getType())) {
/* 558 */         sb.append("href+=\"[<a href='#' onclick=delObj('" + url + "','" + this.name + "')>\";");
/*     */       }
/* 560 */       if (OptTypeDirection.Fun.equals(dateGridUrl.getType())) {
/* 561 */         String name = TagUtil.getFunction(dateGridUrl.getFunname());
/* 562 */         String parmars = TagUtil.getFunParams(dateGridUrl.getFunname());
/* 563 */         sb.append("href+=\"[<a href='#' onclick=" + name + "(" + parmars + ")>\";");
/*     */       }
/* 565 */       if (OptTypeDirection.OpenWin.equals(dateGridUrl.getType())) {
/* 566 */         sb.append("href+=\"[<a href='#' onclick=openwindow('" + dateGridUrl.getTitle() + "','" + url + "','" + dateGridUrl.getWidth() + "','" + dateGridUrl.getHeight() + "')>\";");
/*     */       }
/* 568 */       if (OptTypeDirection.Deff.equals(dateGridUrl.getType())) {
/* 569 */         sb.append("href+=\"[<a href='" + url + "' title='" + dateGridUrl.getTitle() + "'>\";");
/*     */       }
/* 571 */       sb.append("href+=\"" + dateGridUrl.getTitle() + "</a>]\";");
/*     */ 
/* 573 */       if (StringUtil.isNotEmpty(exp)) {
/* 574 */         for (int i = 0; i < exp.split("&&").length; i++) {
/* 575 */           sb.append("}");
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 580 */     sb.append("return href;");
/*     */   }
/*     */ 
/*     */   protected void getFun(StringBuffer sb, DateGridColumn column)
/*     */   {
/* 590 */     String url = column.getUrl();
/* 591 */     url = formatUrl(url);
/* 592 */     sb.append("var href=\"<a style='color:red' href='#' onclick=" + column.getFunname() + "('" + column.getTitle() + "','" + url + "')>\";");
/* 593 */     sb.append("return href+value+'</a>';");
/*     */   }
/*     */ 
/*     */   protected String formatUrl(String url)
/*     */   {
/* 603 */     MessageFormat formatter = new MessageFormat("");
/* 604 */     String parurlvalue = "";
/* 605 */     if (url.indexOf("&") >= 0) {
/* 606 */       String beforeurl = url.substring(0, url.indexOf("&"));
/* 607 */       String parurl = url.substring(url.indexOf("&") + 1, url.length());
/* 608 */       String[] pras = parurl.split("&");
/* 609 */       List value = new ArrayList();
/* 610 */       int j = 0;
/* 611 */       for (int i = 0; i < pras.length; i++) {
/* 612 */         if ((pras[i].indexOf("{") >= 0) || (pras[i].indexOf("#") >= 0)) {
/* 613 */           String field = pras[i].substring(pras[i].indexOf("{") + 1, pras[i].lastIndexOf("}"));
/* 614 */           parurlvalue = parurlvalue + "&" + pras[i].replace(new StringBuilder("{").append(field).append("}").toString(), new StringBuilder("{").append(j).append("}").toString());
/* 615 */           value.add("\"+rec." + field + " +\"");
/* 616 */           j++;
/*     */         } else {
/* 618 */           parurlvalue = parurlvalue + "&" + pras[i];
/*     */         }
/*     */       }
/* 621 */       url = MessageFormat.format(beforeurl + parurlvalue, value.toArray());
/*     */     }
/* 623 */     return url;
/*     */   }
/*     */ 
/*     */   protected void getField(StringBuffer sb)
/*     */   {
/* 634 */     if (this.checkbox) {
/* 635 */       sb.append("{field:'ck',checkbox:'true'},");
/*     */     }
/* 637 */     int i = 0;
/* 638 */     for (DateGridColumn column : this.columnList) {
/* 639 */       i++;
/*     */       String field;
/* 641 */       if (this.treegrid)
/* 642 */         field = column.getTreefield();
/*     */       else {
/* 644 */         field = column.getField();
/*     */       }
/* 646 */       sb.append("{field:'" + field + "',title:'" + column.getTitle() + "',");
/* 647 */       int colwidth = column.getWidth() == null ? column.getTitle().length() * 15 : column.getWidth().intValue();
/* 648 */       sb.append("width:" + colwidth);
/*     */ 
/* 651 */       if (!column.isHidden()) {
/* 652 */         sb.append(",hidden:true");
/*     */       }
/* 654 */       if (!this.treegrid)
/*     */       {
/* 656 */         if ((column.isSortable()) && (field.indexOf("_") <= 0) && (field != "opt")) {
/* 657 */           sb.append(",sortable:" + column.isSortable());
/*     */         }
/*     */       }
/*     */ 
/* 661 */       if (column.isImage()) {
/* 662 */         sb.append(",formatter:function(value,rec,index){");
/* 663 */         sb.append(" return '<img border=\"0\" src=\"'+value+'\"/>'}");
/*     */       }
/*     */ 
/* 666 */       if (column.getUrl() != null) {
/* 667 */         sb.append(",formatter:function(value,rec,index){");
/* 668 */         getFun(sb, column);
/* 669 */         sb.append("}");
/*     */       }
/* 671 */       if (column.getFormatter() != null)
/*     */       {
/* 673 */         sb.append(",formatter:function(value,rec,index){");
/* 674 */         sb.append(" return new Date().format('" + column.getFormatter() + "',value);}");
/*     */       }
/*     */ 
/* 677 */       if (column.getField().equals("opt")) {
/* 678 */         sb.append(",formatter:function(value,rec,index){");
/*     */ 
/* 680 */         getOptUrl(sb);
/* 681 */         sb.append("}");
/*     */       }
/*     */ 
/* 684 */       if ((this.columnValueList.size() > 0) && (!column.getField().equals("opt"))) {
/* 685 */         String testString = "";
/* 686 */         for (ColumnValue columnValue : this.columnValueList) {
/* 687 */           if (columnValue.getName().equals(column.getField())) {
/* 688 */             String[] value = columnValue.getValue().split(",");
/* 689 */             String[] text = columnValue.getText().split(",");
/* 690 */             sb.append(",formatter:function(value,rec,index){");
/* 691 */             for (int j = 0; j < value.length; j++) {
/* 692 */               testString = testString + "if(value=='" + value[j] + "'){return '" + text[j] + "'}";
/*     */             }
/* 694 */             sb.append(testString);
/* 695 */             sb.append("else{return value}");
/* 696 */             sb.append("}");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 701 */       sb.append("}");
/*     */ 
/* 703 */       if (i < this.columnList.size())
/* 704 */         sb.append(",");
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void setPager(StringBuffer sb, String grid)
/*     */   {
/* 715 */     sb.append("$('#" + this.name + "')." + grid + "('getPager').pagination({");
/* 716 */     sb.append("beforePageText:'',afterPageText:'/{pages}',");
/* 717 */     if (this.showText)
/* 718 */       sb.append("displayMsg:'{from}-{to}共{total}条',");
/*     */     else {
/* 720 */       sb.append("displayMsg:'',");
/*     */     }
/* 722 */     if (this.showPageList) {
/* 723 */       sb.append("showPageList:true,");
/* 724 */       sb.append("pageList:[" + this.pageSize * 1 + "," + this.pageSize * 2 + "," + this.pageSize * 3 + "],");
/*     */     } else {
/* 726 */       sb.append("showPageList:false,");
/*     */     }
/* 728 */     sb.append("showRefresh:" + this.showRefresh);
/* 729 */     sb.append("});");
/* 730 */     sb.append("$('#" + this.name + "')." + grid + "('getPager').pagination({");
/* 731 */     sb.append("onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination('loading');$(this).pagination('loaded'); }");
/* 732 */     sb.append("});");
/*     */   }
/*     */ 
/*     */   protected void searchboxFun(StringBuffer sb, String grid)
/*     */   {
/* 737 */     sb.append("function " + this.name + "searchbox(value,name){");
/* 738 */     sb.append("var queryParams=$('#" + this.name + "').datagrid('options').queryParams;");
/* 739 */     sb.append("queryParams[name]=value;queryParams.searchfield=name;$('#" + this.name + "')." + grid + "('reload');}");
/* 740 */     sb.append("$('#" + this.name + "searchbox').searchbox({");
/* 741 */     sb.append("searcher:function(value,name){");
/* 742 */     sb.append(this.name + "searchbox(value,name);");
/* 743 */     sb.append("},");
/* 744 */     sb.append("menu:'#" + this.name + "mm',");
/* 745 */     sb.append("prompt:'请输入查询关键字'");
/* 746 */     sb.append("});");
/*     */   }
/*     */   public boolean isFitColumns() {
/* 749 */     return this.fitColumns;
/*     */   }
/*     */ 
/*     */   public void setFitColumns(boolean fitColumns) {
/* 753 */     this.fitColumns = fitColumns;
/*     */   }
/*     */ 
/*     */   public String getSortName() {
/* 757 */     return this.sortName;
/*     */   }
/*     */ 
/*     */   public void setSortName(String sortName) {
/* 761 */     this.sortName = sortName;
/*     */   }
/*     */ 
/*     */   public String getSortOrder() {
/* 765 */     return this.sortOrder;
/*     */   }
/*     */ 
/*     */   public void setSortOrder(String sortOrder) {
/* 769 */     this.sortOrder = sortOrder;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.DateGridTag
 * JD-Core Version:    0.6.0
 */