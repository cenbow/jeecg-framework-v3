package jeecg.system.controller.bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ProjectDeclare;
import org.jeecgframework.core.common.model.json.ProjectInfo;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.workflow.pojo.bus.MXingzhengqujie;
import org.jeecgframework.workflow.pojo.bus.TMBuilding;
import org.jeecgframework.workflow.pojo.bus.TMUserdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 地图查询处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/mapController")
public class MapController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MapController.class);
	private UserService userService;
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		this.systemService = systemService;
	}

	public UserService getUserService() {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		this.userService = userService;
	}

	/**
	 * 地图页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "map")
	public ModelAndView map() {
		return new ModelAndView("main/map");
	}

	/**
	 * 地图页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "synthetic")
	public ModelAndView synthetic(HttpServletRequest request) {
		Integer id = oConvertUtils.getInt(request.getParameter("id"), 0);
		ModelAndView modelAndView = null;
		if (id.equals(1)) {
			modelAndView = new ModelAndView("main/result");
		}
		if (id.equals(2)) {
			modelAndView = new ModelAndView("main/search");
		}
		if (id.equals(3)) {
			modelAndView = new ModelAndView("main/mark");
		}
		if (id.equals(4)) {
			modelAndView = new ModelAndView("main/other");
		}
		return modelAndView;
	}

	/**
	 * 地图查询结果列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "querymap")
	public void querymap(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {	
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		String param = oConvertUtils.getString(request.getParameter("param"));
		String tcxz = oConvertUtils.getString(request.getParameter("tcxz"));
		if (tcxz == null || tcxz.equals("")) {//若没有图层，默认为建筑图层，参数为null
			tcxz = "m_building";
			param = null;
		}
		String sqlStr = "";
		if (tcxz.equals("m_building")) {// 若为房屋
			sqlStr = "SELECT t.gid as gid, t.name as name FROM m_building t where t.name like '"
					+ param + "%'";
		} else if (tcxz.equals("t_projectdetial")) {// 若为地下室
			sqlStr = "SELECT t.detialid as gid, d.projectname as name"
					+ " FROM t_projectdetial t ,t_declaretable d"
					+ " where t.declareid=d.declareid and d.projectname like '"
					+ param + "%'";
		}else{
			sqlStr = "SELECT t.gid as gid, t.name as name FROM "+tcxz+" t where t.gid in ("+ param + ")";			 	
		}
		HqlQuery hqlQuery = new HqlQuery(ProjectInfo.class, sqlStr, dataGrid);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, true);
		TagUtil.ListtoView(response, pageList);
	}

	/**
	 * 从地图查询的数据返回到列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "queryFromMap")
	public void queryFromMap(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		String param = oConvertUtils.getString(request.getParameter("param"));
		String tcxz = oConvertUtils.getString(request.getParameter("tcxz"));
		if (param == null || param.equals("")) {
			param = "-1";
		}
		if (tcxz == null || tcxz.equals("")) {//若没有图层则设置建筑为默认图层
			tcxz="m_building";
		}
		String sqlStr = "";
		if (tcxz.equals("m_building")) {// 若为房屋
			sqlStr = "SELECT t.gid as gid, t.name as name FROM m_building t where t.gid in ("+ param + ")";
			
		} else if (tcxz.equals("t_projectdetial")) {// 若为地下室
			sqlStr = "SELECT t.detialid as gid, d.projectname as name"
					+ " FROM t_projectdetial t ,t_declaretable d"
					+ " where t.declareid=d.declareid and t.detialid in ("
					+ param + ")";
		}else{
			sqlStr = "SELECT t.gid as gid, t.name as name FROM "+tcxz+" t where t.gid in ("+ param + ")";			 	
		}	 
		HqlQuery hqlQuery = new HqlQuery(ProjectInfo.class, sqlStr, dataGrid);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, true);
		TagUtil.ListtoView(response, pageList);

	}

	/**
	 * 获得地图buffer信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "getBuffer")
	public void getBuffer(HttpServletRequest request,
			HttpServletResponse response) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		String retstr = "";
		String radius = oConvertUtils.getString(request.getParameter("radius"));
		String geomstr = oConvertUtils.getString(request
				.getParameter("geomstr"));
		String sqlStr = "select st_asgeojson(st_buffer(st_geometryfromtext('"
				+ geomstr + "' ,2362 )," + Double.parseDouble(radius) + "))";
		List<String> list = systemService.findListbySql(sqlStr);
		if (list != null && !list.isEmpty()) {
			retstr = list.get(0);
		}
		response.setContentType("text/html");
		try {
			response.getWriter().write(retstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得区域列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "queryRegion")
	public void queryRegion(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		CriteriaQuery cq = new CriteriaQuery(MXingzhengqujie.class, dataGrid);
		PageList pageList = this.systemService.getPageList(cq, true);
		TagUtil.ListtoView(response, pageList);
	}

	/**
	 * 获得项目详细信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "getProjectDetail")
	@ResponseBody
	public List<ProjectDeclare> getProjectDetail(HttpServletRequest request,
			HttpServletResponse response) {
		Integer declareid = oConvertUtils.getInt(
				request.getParameter("declareid"), 0);
		Integer detialid = oConvertUtils.getInt(
				request.getParameter("detialid"), 0);
		String sqlhwere = " ";
		if (declareid != 0) {
			sqlhwere = " and t.declareid=" + declareid;
		} else if (detialid != 0) {
			sqlhwere = " and p.detialid=" + detialid;
		}
		// String sqlStr =
		// "SELECT p.detialid,t.declareid,  t.constructionunit, t.projectname, "
		// +
		// " s.description, p.buildingno, p.undergroundconstructionarea, st_asgeojson(st_transform(st_geomfromtext(st_astext(p.geom),2362),4326))"
		// + " FROM t_declaretable t,t_projectdetial p,t_prjstatus s"
		// + " where t.declareid=p.declareid and t.statusid=s.statusid "
		// + sqlhwere;
		String sqlStr = "SELECT p.detialid,t.declareid,  t.constructionunit, t.projectname, "
				+ " s.description, p.buildingno, p.undergroundconstructionarea, st_asgeojson(p.geom)"
				+ " FROM t_declaretable t,t_projectdetial p,t_prjstatus s"
				+ " where t.declareid=p.declareid and t.statusid=s.statusid "
				+ sqlhwere;
		List<Object[]> list = systemService.findListbySql(sqlStr);
		List<ProjectDeclare> Declares = new ArrayList<ProjectDeclare>();
		ProjectDeclare projectDeclare = new ProjectDeclare();
		if (list.size() > 0) {
			for (Object[] object : list) {
				projectDeclare.setDetialid(oConvertUtils.getInt(
						object[0].toString(), 0));
				projectDeclare.setDeclareid(oConvertUtils.getInt(
						object[1].toString(), 0));
				projectDeclare.setConstructionunit(object[2].toString());
				projectDeclare.setProjectname(object[3].toString());
				projectDeclare.setStatusid(object[4].toString());
				projectDeclare.setBuildingno(object[5].toString());
				projectDeclare.setUndergroundconstructionarea(oConvertUtils
						.getDouble(object[6].toString(), 0));
				if (object[7] != null) {
					projectDeclare.setGeom(object[7].toString());
				}

				Declares.add(projectDeclare);
			}
		}
		return Declares;
	}

	/**
	 * 保存项目
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveProjectGeom")
	public void saveProjectGeom(HttpServletRequest request,
			HttpServletResponse response) {
		Integer detailid = oConvertUtils.getInt(
				request.getParameter("detailid"), 0);
		String geomstr = oConvertUtils.getString(request
				.getParameter("geomstr"));
		String sql = "update t_projectdetial set geom= st_geometryfromtext('"
				+ geomstr + "','2362') where detialid=" + detailid;
		systemService.updateBySqlString(sql);
	}

	@RequestMapping(params = "getRegionInfo")
	public void getRegionInfo(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.mapdataSource);
		Integer param = oConvertUtils.getInt(request.getParameter("param"), 0);
		String tcxz = oConvertUtils.getString(request.getParameter("tcxz"));
		String sqlStr = "";
		String sqlhwere = " ";
		if (param != 0) {
			sqlhwere = " and m.gid=" + param;
		}
		if (tcxz.equals("m_building")) {// 若为房屋
			sqlStr = "SELECT b.gid as gid,b.name as name "
					+ " FROM m_building b ,m_xingzhengqujie m"
					+ " where ST_Contains(m.geom,b.geom)=TRUE " + sqlhwere;
		}
		if (sqlStr == null || sqlStr.equals("")) {
			return;
		}
		HqlQuery hqlQuery = new HqlQuery(TMBuilding.class, sqlStr, dataGrid);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, true);
		TagUtil.ListtoView(response, pageList);

	}

	/**
	 * 保存绘画层
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveDraws")
	@ResponseBody
	public AjaxJson saveDraws(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		//String userid = user.getId();
		String drawid = oConvertUtils.getString(request.getParameter("draws"));
		String drawjsonstr = oConvertUtils.getString(request.getParameter("drawjsonstr"));
		String lablejsonstr = oConvertUtils.getString(request.getParameter("lablejsonstr"));
		String drawName = oConvertUtils.getString(request.getParameter("drawName"));
		String note = oConvertUtils.getString(request.getParameter("note"));
		//String sql = "";
		/*if (drawid != null && !drawid.equals("")) {
			sql = "UPDATE t_m_userdraw SET  drawtime='" + DataUtils.getTimestamp() + "', drawname='" + drawName + "', drawcontent='" + drawjsonstr + "', markercontent='" + lablejsonstr + "', note='" + note + "' WHERE id=" + drawid;
		} else {
			sql = "INSERT INTO t_m_userdraw(userid, drawtime, drawname, drawcontent,markercontent, note) " + "VALUES (" + userid + ", '" + DataUtils.getTimestamp() + "', '" + drawName + "', '" + drawjsonstr + "', '"+ lablejsonstr + "', '" + note + "')";
		}*/		
		TMUserdraw userdraw=new TMUserdraw(); 
		if (drawid != null && !drawid.equals("")) {
			 userdraw=systemService.getEntity(TMUserdraw.class,drawid); 
			 userdraw.setDrawtime(DataUtils.getTimestamp());
			 userdraw.setDrawname(drawName);
			 userdraw.setDrawcontent(drawjsonstr);
			 userdraw.setMarkercontent(lablejsonstr);
			 userdraw.setNote(note);
			 userdraw.setDrawDate(DataUtils.formatDate());
		} else {
			 userdraw.setTSUser(user);
			 userdraw.setDrawtime(DataUtils.getTimestamp());
			 userdraw.setDrawname(drawName);
			 userdraw.setDrawcontent(drawjsonstr);
			 userdraw.setMarkercontent(lablejsonstr);
			 userdraw.setNote(note);	
			 userdraw.setDrawDate(DataUtils.formatDate());
		}
		systemService.saveOrUpdate(userdraw);
		
		return j;
	}

	/**
	 * 获得规划历史信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "queryDrawHistory")
	public void queryDrawHistory(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		String userid = user.getId();
		/*String sqlStr = "select id,drawname,to_char(drawtime,'yyyy-MM-dd') as drawDate from t_m_userdraw where userid='" + userid+"'";
		//String sqlStr = "select drawid,drawname,CONVERT(varchar(100), drawtime, 24) as drawDate from t_m_userdraw where userid='" + userid+"'";
		HqlQuery hqlQuery = new HqlQuery(TMUserdraw.class, sqlStr, dataGrid);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, true);
		TagUtil.ListtoView(response, pageList);*/
		CriteriaQuery cq=new CriteriaQuery(TMUserdraw.class,dataGrid);
		cq.eq("TSUser.id",userid);
		cq.add();		 
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);;
	}

	/**
	 * 删除绘画层
	 * 
	 * @return
	 */
	@RequestMapping(params = "deleteDraws")
	@ResponseBody
	public AjaxJson deleteDraws(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String drawid = oConvertUtils.getString(request.getParameter("drawids"));
		//String sql = "DELETE FROM t_m_userdraw WHERE id in (" + drawid + ")";
		//systemService.updateBySqlString(sql);
		TMUserdraw userdraw=systemService.getEntity(TMUserdraw.class,drawid);
		systemService.delete(userdraw);
		
		return j;
	}

	/**
	 * 根基绘画编号获得屏幕信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "getpingmu")
	@ResponseBody
	public TMUserdraw getpingmu(HttpServletRequest request, HttpServletResponse response) {
		String drawid = request.getParameter("drawid");
		/*String sqlStr = "SELECT drawname,drawcontent,markercontent,note FROM t_m_userdraw where id= " + drawid;
		List<Object[]> list = systemService.findListbySql(sqlStr);
		TMUserdraw userdraw = new TMUserdraw();
		if (list.size() > 0) {
			userdraw.setDrawname((String) list.get(0)[0]);
			userdraw.setDrawcontent((String) list.get(0)[1]);
			userdraw.setMarkercontent((String) list.get(0)[2]);
			userdraw.setNote((String) list.get(0)[3]);
		}*/
		TMUserdraw userdraw = new TMUserdraw();
		TMUserdraw userdraw1=systemService.getEntity(TMUserdraw.class,drawid);
		if (userdraw1!=null) {
			userdraw.setDrawname(userdraw1.getDrawname());
			userdraw.setDrawcontent(userdraw1.getDrawcontent());
			userdraw.setMarkercontent(userdraw1.getMarkercontent());
			userdraw.setNote(userdraw1.getNote());
		}
		return userdraw;
	}
}
