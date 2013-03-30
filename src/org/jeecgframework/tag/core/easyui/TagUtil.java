/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import jeecg.system.pojo.base.TSRole;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.codehaus.jackson.JsonGenerationException;
/*     */ import org.codehaus.jackson.map.JsonMappingException;
/*     */ import org.codehaus.jackson.map.ObjectMapper;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.PageList;
/*     */ import org.jeecgframework.core.common.model.json.ComboBox;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.util.ReflectHelper;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.vo.datatable.DataTableReturn;
/*     */ import org.jeecgframework.tag.vo.easyui.Autocomplete;
/*     */ 
/*     */ public class TagUtil
/*     */ {
/*     */   public static Field[] getFiled(Class objClass)
/*     */     throws ClassNotFoundException
/*     */   {
/*  47 */     Field[] field = (Field[])null;
/*  48 */     if (objClass != null) {
/*  49 */       Class class1 = Class.forName(objClass.getName());
/*  50 */       field = class1.getDeclaredFields();
/*  51 */       return field;
/*     */     }
/*  53 */     return field;
/*     */   }
/*     */ 
/*     */   public static Object fieldNametoValues(String FiledName, Object o)
/*     */   {
/*  63 */     Object value = "";
/*  64 */     String fieldName = "";
/*  65 */     String childFieldName = null;
/*  66 */     ReflectHelper reflectHelper = new ReflectHelper(o);
/*  67 */     if (FiledName.indexOf("_") == -1) {
/*  68 */       fieldName = FiledName;
/*     */     } else {
/*  70 */       fieldName = FiledName.substring(0, FiledName.indexOf("_"));
/*  71 */       childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);
/*     */     }
/*  73 */     value = reflectHelper.getMethodValue(fieldName) == null ? "" : reflectHelper.getMethodValue(fieldName);
/*  74 */     if ((value != "") && (value != null) && (FiledName.indexOf("_") != -1)) {
/*  75 */       value = fieldNametoValues(childFieldName, value);
/*     */     }
/*  77 */     return value;
/*     */   }
/*     */ 
/*     */   protected static Object[] field2Values(String[] fields, Object o)
/*     */     throws Exception
/*     */   {
/*  89 */     Object[] values = new Object[fields.length];
/*  90 */     for (int i = 0; i < fields.length; i++) {
/*  91 */       String fieldName = fields[i].toString();
/*  92 */       values[i] = fieldNametoValues(fieldName, o);
/*     */     }
/*  94 */     return values;
/*     */   }
/*     */ 
/*     */   private static String listtojson(String[] fields, int total, List list)
/*     */     throws Exception
/*     */   {
/* 103 */     Object[] values = new Object[fields.length];
/* 104 */     String jsonTemp = "{'total':" + total + ",'rows':[";
/* 105 */     for (int j = 0; j < list.size(); j++) {
/* 106 */       jsonTemp = jsonTemp + "{'state':'closed',";
/* 107 */       for (int i = 0; i < fields.length; i++) {
/* 108 */         String fieldName = fields[i].toString();
/* 109 */         values[i] = fieldNametoValues(fieldName, list.get(j));
/* 110 */         jsonTemp = jsonTemp + "'" + fieldName + "'" + ":'" + values[i] + "'";
/* 111 */         if (i != fields.length - 1) {
/* 112 */           jsonTemp = jsonTemp + ",";
/*     */         }
/*     */       }
/* 115 */       if (j != list.size() - 1)
/* 116 */         jsonTemp = jsonTemp + "},";
/*     */       else {
/* 118 */         jsonTemp = jsonTemp + "}";
/*     */       }
/*     */     }
/* 121 */     jsonTemp = jsonTemp + "]}";
/* 122 */     return jsonTemp;
/*     */   }
/*     */ 
/*     */   public static String getAutoList(Autocomplete autocomplete, List list)
/*     */     throws Exception
/*     */   {
/* 132 */     String field = autocomplete.getLabelField() + "," + autocomplete.getValueField();
/* 133 */     String[] fields = field.split(",");
/* 134 */     Object[] values = new Object[fields.length];
/* 135 */     String jsonTemp = "{'totalResultsCount':1,'geonames':[";
/* 136 */     if (list.size() > 0)
/*     */     {
/* 138 */       for (int j = 0; j < list.size(); j++) {
/* 139 */         jsonTemp = jsonTemp + "{'nodate':'yes',";
/* 140 */         for (int i = 0; i < fields.length; i++) {
/* 141 */           String fieldName = fields[i].toString();
/* 142 */           values[i] = fieldNametoValues(fieldName, list.get(j));
/* 143 */           jsonTemp = jsonTemp + "'" + fieldName + "'" + ":'" + values[i] + "'";
/* 144 */           if (i != fields.length - 1) {
/* 145 */             jsonTemp = jsonTemp + ",";
/*     */           }
/*     */         }
/* 148 */         if (j != list.size() - 1)
/* 149 */           jsonTemp = jsonTemp + "},";
/*     */         else {
/* 151 */           jsonTemp = jsonTemp + "}";
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 156 */       jsonTemp = jsonTemp + "{'nodate':'数据不存在'}";
/*     */     }
/* 158 */     jsonTemp = jsonTemp + "]}";
/* 159 */     return JSONObject.fromObject(jsonTemp).toString();
/*     */   }
/*     */ 
/*     */   private static String datatable(String field, int total, List list)
/*     */     throws Exception
/*     */   {
/* 168 */     String[] fields = field.split(",");
/* 169 */     Object[] values = new Object[fields.length];
/* 170 */     String jsonTemp = "{'iTotalDisplayRecords':" + total + ",'iTotalRecords':" + total + ",'aaData':[";
/* 171 */     for (int j = 0; j < list.size(); j++) {
/* 172 */       jsonTemp = jsonTemp + "{";
/* 173 */       for (int i = 0; i < fields.length; i++) {
/* 174 */         String fieldName = fields[i].toString();
/* 175 */         values[i] = fieldNametoValues(fieldName, list.get(j));
/* 176 */         jsonTemp = jsonTemp + "'" + fieldName + "'" + ":'" + values[i] + "'";
/* 177 */         if (i != fields.length - 1) {
/* 178 */           jsonTemp = jsonTemp + ",";
/*     */         }
/*     */       }
/* 181 */       if (j != list.size() - 1)
/* 182 */         jsonTemp = jsonTemp + "},";
/*     */       else {
/* 184 */         jsonTemp = jsonTemp + "}";
/*     */       }
/*     */     }
/* 187 */     jsonTemp = jsonTemp + "]}";
/* 188 */     return jsonTemp;
/*     */   }
/*     */ 
/*     */   private static JSONObject getJson(DataGrid dg)
/*     */   {
/* 198 */     JSONObject jObject = null;
/*     */     try {
/* 200 */       jObject = JSONObject.fromObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getReaults()));
/*     */     } catch (Exception e) {
/* 202 */       e.printStackTrace();
/*     */     }
/* 204 */     return jObject;
/*     */   }
/*     */ 
/*     */   private static JSONObject getJson(DataTableReturn dataTable, String field)
/*     */   {
/* 214 */     JSONObject jObject = null;
/*     */     try {
/* 216 */       jObject = JSONObject.fromObject(datatable(field, dataTable.getiTotalDisplayRecords().intValue(), dataTable.getAaData()));
/*     */     } catch (Exception e) {
/* 218 */       e.printStackTrace();
/*     */     }
/* 220 */     return jObject;
/*     */   }
/*     */ 
/*     */   public static String getColumnType(String fileName, Field[] fields)
/*     */   {
/* 233 */     String type = "";
/* 234 */     if (fields.length > 0) {
/* 235 */       for (int i = 0; i < fields.length; i++) {
/* 236 */         String name = fields[i].getName();
/* 237 */         String filedType = fields[i].getGenericType().toString();
/* 238 */         if (fileName.equals(name)) {
/* 239 */           if (filedType.equals("class java.lang.Integer")) {
/* 240 */             filedType = "int";
/* 241 */             type = filedType;
/* 242 */           } else if (filedType.equals("class java.lang.Short")) {
/* 243 */             filedType = "short";
/* 244 */             type = filedType;
/* 245 */           } else if (filedType.equals("class java.lang.Double")) {
/* 246 */             filedType = "double";
/* 247 */             type = filedType;
/* 248 */           } else if (filedType.equals("class java.util.Date")) {
/* 249 */             filedType = "date";
/* 250 */             type = filedType;
/* 251 */           } else if (filedType.equals("class java.lang.String")) {
/* 252 */             filedType = "string";
/* 253 */             type = filedType;
/* 254 */           } else if (filedType.equals("class java.sql.Timestamp")) {
/* 255 */             filedType = "Timestamp";
/* 256 */             type = filedType;
/* 257 */           } else if (filedType.equals("class java.lang.Character")) {
/* 258 */             filedType = "character";
/* 259 */             type = filedType;
/* 260 */           } else if (filedType.equals("class java.lang.Boolean")) {
/* 261 */             filedType = "boolean";
/* 262 */             type = filedType;
/* 263 */           } else if (filedType.equals("class java.lang.Long")) {
/* 264 */             filedType = "long";
/* 265 */             type = filedType;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 271 */     return type;
/*     */   }
/*     */ 
/*     */   protected static String getSortColumnIndex(String fileName, String[] fieldString)
/*     */   {
/* 283 */     String index = "";
/* 284 */     if (fieldString.length > 0) {
/* 285 */       for (int i = 0; i < fieldString.length; i++) {
/* 286 */         if (fileName.equals(fieldString[i])) {
/* 287 */           int j = i + 1;
/* 288 */           index = oConvertUtils.getString(j);
/*     */         }
/*     */       }
/*     */     }
/* 292 */     return index;
/*     */   }
/*     */ 
/*     */   public static void ListtoView(HttpServletResponse response, PageList pageList)
/*     */   {
/* 298 */     response.setContentType("application/json");
/* 299 */     response.setHeader("Cache-Control", "no-store");
/* 300 */     Map map = new HashMap();
/* 301 */     map.put("total", Integer.valueOf(pageList.getCount()));
/* 302 */     map.put("rows", pageList.getResultList());
/* 303 */     ObjectMapper mapper = new ObjectMapper();
/* 304 */     JSONObject jsonObject = JSONObject.fromObject(map);
/*     */     try {
/* 306 */       mapper.writeValue(response.getWriter(), map);
/*     */     } catch (JsonGenerationException e) {
/* 308 */       e.printStackTrace();
/*     */     } catch (JsonMappingException e) {
/* 310 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 312 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void datagrid(HttpServletResponse response, DataGrid dg)
/*     */   {
/* 323 */     response.setContentType("application/json");
/* 324 */     response.setHeader("Cache-Control", "no-store");
/* 325 */     JSONObject object = getJson(dg);
/*     */     try {
/* 327 */       PrintWriter pw = response.getWriter();
/* 328 */       pw.write(object.toString());
/* 329 */       pw.flush();
/*     */     } catch (IOException e) {
/* 331 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void datatable(HttpServletResponse response, DataTableReturn dataTableReturn, String field)
/*     */   {
/* 341 */     response.setContentType("application/json");
/* 342 */     response.setHeader("Cache-Control", "no-store");
/* 343 */     JSONObject object = getJson(dataTableReturn, field);
/*     */     try {
/* 345 */       response.getWriter().write(object.toString());
/*     */     } catch (IOException e) {
/* 347 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getComboBoxJson(List<TSRole> list, List<TSRole> roles)
/*     */   {
/* 355 */     StringBuffer buffer = new StringBuffer();
/* 356 */     buffer.append("[");
/* 357 */     for (TSRole node : list) {
/* 358 */       if (roles.size() > 0) {
/* 359 */         buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"");
/* 360 */         for (TSRole node1 : roles) {
/* 361 */           if (node.getId() == node1.getId()) {
/* 362 */             buffer.append(",\"selected\":true");
/*     */           }
/*     */         }
/* 365 */         buffer.append("},");
/*     */       } else {
/* 367 */         buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"},");
/*     */       }
/*     */     }
/*     */ 
/* 371 */     buffer.append("]");
/*     */ 
/* 375 */     String tmp = buffer.toString();
/* 376 */     tmp = tmp.replaceAll(",]", "]");
/* 377 */     return tmp;
/*     */   }
/*     */ 
/*     */   public static List<ComboBox> getComboBox(List all, List in, ComboBox comboBox)
/*     */   {
/* 389 */     List comboxBoxs = new ArrayList();
/* 390 */     String[] fields = { comboBox.getId(), comboBox.getText() };
/* 391 */     Object[] values = new Object[fields.length];
/* 392 */     for (Iterator localIterator = all.iterator(); localIterator.hasNext(); ) { Object node = localIterator.next();
/* 393 */       ComboBox box = new ComboBox();
/* 394 */       ReflectHelper reflectHelper = new ReflectHelper(node);
/*     */       String fieldName;
/* 395 */       for (int i = 0; i < fields.length; i++) {
/* 396 */         fieldName = fields[i].toString();
/* 397 */         values[i] = reflectHelper.getMethodValue(fieldName);
/*     */       }
/* 399 */       box.setId(values[0].toString());
/* 400 */       box.setText(values[1].toString());
/* 401 */       if (in != null) {
/* 402 */         for (Iterator it = in.iterator(); it.hasNext(); ) 
					{ Object node1 = it.next();
/* 403 */           ReflectHelper reflectHelper2 = new ReflectHelper(node);
/* 404 */           if (node1 != null) {
/* 405 */              fieldName = fields[0].toString();
/* 406 */             String test = reflectHelper2.getMethodValue(fieldName).toString();
/* 407 */             if (values[0].toString().equals(test)) {
/* 408 */               box.setSelected(true);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 413 */       comboxBoxs.add(box);
/*     */     }
/* 415 */     return comboxBoxs;
/*     */   }
/*     */ 
/*     */   public static String getFunction(String functionname)
/*     */   {
/* 425 */     int index = functionname.indexOf("(");
/* 426 */     if (index == -1) {
/* 427 */       return functionname;
/*     */     }
/* 429 */     return functionname.substring(0, functionname.indexOf("("));
/*     */   }
/*     */ 
/*     */   public static String getFunParams(String functionname)
/*     */   {
/* 440 */     int index = functionname.indexOf("(");
/* 441 */     String param = "";
/* 442 */     if (index != -1) {
/* 443 */       String testparam = functionname.substring(functionname.indexOf("(") + 1, 
/* 444 */         functionname.length() - 1);
/* 445 */       if (StringUtil.isNotEmpty(testparam))
/*     */       {
/* 447 */         String[] params = testparam.split(",");
/* 448 */         for (String string : params) {
/* 449 */           param = param + "'\"+rec." + string + "+\"',";
/*     */         }
/*     */       }
/*     */     }
/* 453 */     param = param + "'\"+index+\"'";
/* 454 */     return param;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.TagUtil
 * JD-Core Version:    0.6.0
 */