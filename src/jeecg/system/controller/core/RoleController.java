package jeecg.system.controller.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSFunction;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleFunction;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sun.tools.jar.resources.jar;


/**
 * 角色处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoleController.class);
	private UserService userService;
	private SystemService systemService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 角色列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	public ModelAndView role() {
		return new ModelAndView("system/role/roleList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "roleGrid")
	public void roleGrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);;
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delRole")
	@ResponseBody
	public AjaxJson delRole(TSRole role,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//删除角色之前先删除角色权限关系
		delRoleFunction(role);
		role = systemService.getEntity(TSRole.class, role.getId());
		userService.delete(role);
		message = "角色: " + role.getRoleName() + "被删除成功";
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		
		return j;
	}
	/**
	 * 检查角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkRole")
	@ResponseBody
	public ValidForm checkRole(TSRole role,HttpServletRequest request,HttpServletResponse response) {
		ValidForm v = new ValidForm();
		String roleCode=oConvertUtils.getString(request.getParameter("param"));
		String code=oConvertUtils.getString(request.getParameter("code"));
		List<TSRole> roles=systemService.findByProperty(TSRole.class,"roleCode",roleCode);
		if(roles.size()>0&&!code.equals(roleCode))
		{
			v.setInfo("角色编码已存在");
			v.setStatus("n");
		}
		return v;
	}
	/**
	 * 删除角色权限
	 * @param role
	 */
	protected void delRoleFunction(TSRole role){
		List<TSRoleFunction> roleFunctions=systemService.findByProperty(TSRoleFunction.class,"TSRole.id",role.getId());	
		if (roleFunctions.size()>0) {
			for (TSRoleFunction tsRoleFunction : roleFunctions) {
			systemService.delete(tsRoleFunction);	
			}
		}
		List<TSRoleUser> roleUsers=systemService.findByProperty(TSRoleUser.class,"TSRole.id",role.getId());
	    for (TSRoleUser tsRoleUser : roleUsers) {
	    	systemService.delete(tsRoleUser);
		}	
	}
	/**
	 * 角色录入
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveRole")
	@ResponseBody
	public AjaxJson saveRole(TSRole role, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(role.getId())) {
			message = "角色: " + role.getRoleName() + "被更新成功";
			userService.saveOrUpdate(role);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "角色: " + role.getRoleName() + "被添加成功";
			userService.save(role);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 角色列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fun")
	public ModelAndView fun(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		return new ModelAndView("system/role/roleSet");
	}

	/**
	 * 设置权限
	 * 
	 * @param role
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "setAuthority")
	@ResponseBody
	public List<ComboTree> setAuthority(TSRole role, HttpServletRequest request,
			ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		if (comboTree.getId() != null) {
			cq.eq("TSFunction.id",
					comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSFunction");
		}
		cq.notEq("functionLevel",Short.parseShort("-1"));
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq,false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		String roleId = request.getParameter("roleId");
		List<TSFunction> loginActionlist = new ArrayList<TSFunction>();// 已有权限菜单
		role = this.systemService.get(TSRole.class, roleId);
		if (role != null) {
			List<TSRoleFunction> roleFunctionList=systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
			if (roleFunctionList.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = (TSFunction) roleFunction
							.getTSFunction();
					loginActionlist.add(function);
				}
			}
		}
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
		comboTrees = systemService.ComboTree(functionList,comboTreeModel,loginActionlist);
		return comboTrees;
	}

	/**
	 * 更新权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateAuthority")
	@ResponseBody
	public AjaxJson updateAuthority(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String roleId =request.getParameter("roleId");
		String rolefunction = request.getParameter("rolefunctions");
		TSRole role = this.systemService.get(TSRole.class, roleId);
		List<TSRoleFunction> roleFunctionList=systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
		systemService.deleteAllEntitie(roleFunctionList);
		String[] roleFunctions = null;
		if (rolefunction != "") {
			roleFunctions = rolefunction.split(",");
			for (String s : roleFunctions) {
				TSRoleFunction rf = new TSRoleFunction();
				TSFunction f = this.systemService.get(TSFunction.class,s);
				rf.setTSFunction(f);
				rf.setTSRole(role);
				this.systemService.save(rf);
			}
		}
		j.setMsg("权限更新成功");
		return j;
	}

	/**
	 * 角色页面跳转
	 * 
	 * @param role
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSRole role, HttpServletRequest req) {
		if (role.getId() != null) {
			role = systemService.getEntity(TSRole.class, role.getId());
			req.setAttribute("role", role);
		}
		return new ModelAndView("system/role/role");
	}

	/**
	 * 权限操作列表
	 * 
	 * @param role
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "setOperate")
	@ResponseBody
	public List<TreeGrid> setOperate(HttpServletRequest request,
			TreeGrid treegrid) {
		String roleId = request.getParameter("roleId");
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		if (treegrid.getId() != null) {
			cq.eq("TSFunction.id",
					treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSFunction");
		}
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq,false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		Collections.sort(functionList, new SetListSort());
		TreeGridModel treeGridModel=new TreeGridModel();
		treeGridModel.setRoleid(roleId);
		treeGrids = systemService.treegrid(functionList, treeGridModel);
		return treeGrids;

	}

	/**
	 * 操作录入
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveOperate")
	@ResponseBody
	public AjaxJson saveOperate(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String fop = request.getParameter("fp");
		String roleId = request.getParameter("roleId");
		//录入操作前清空上一次的操作数据
		clearp(roleId);
		String[] fun_op = fop.split(",");
		String aa = "";
		String bb = "";
		//只有一个被选中
		if (fun_op.length == 1) {
			bb = fun_op[0].split("_")[1];
			aa = fun_op[0].split("_")[0];
			savep( roleId,bb,aa);
		}else{
			//至少2个被选中
			for (int i = 0; i < fun_op.length; i++) {
				String cc = fun_op[i].split("_")[0];  //操作id
				if (i > 0 && bb.equals(fun_op[i].split("_")[1])) {
					aa += "," + cc;
					if (i== (fun_op.length - 1)) {
						savep( roleId,bb,aa);
					}
				} else if (i > 0) {
					    savep(roleId,bb,aa);
					    aa = fun_op[i].split("_")[0];   //操作ID
					    if (i==(fun_op.length-1)){
					    	bb = fun_op[i].split("_")[1]; //权限id
					    	savep(roleId,bb,aa);
						}
					    
				} else {
					aa = fun_op[i].split("_")[0]; //操作ID
				}
				bb = fun_op[i].split("_")[1]; //权限id

			}	
		}
		
		
		return j;
	}
     /**
      *更新操作
      * @param roleId
      * @param functionid
      * @param ids
      */
	public void savep(String roleId,String functionid, String ids) {
		//String hql = "from TSRoleFunction t where" + " t.TSRole.id=" + oConvertUtils.getInt(roleId,0)
		//		+ " " + "and t.TSFunction.id=" + oConvertUtils.getInt(functionid,0);		
		CriteriaQuery cq=new CriteriaQuery(TSRoleFunction.class);
		cq.eq("TSRole.id",roleId);
		cq.eq("TSFunction.id",functionid);
		cq.add();
		List<TSRoleFunction> rFunctions =systemService.getListByCriteriaQuery(cq,false);
		if (rFunctions.size() > 0) {
			TSRoleFunction roleFunction = rFunctions.get(0);
			roleFunction.setOperation(ids);
			systemService.saveOrUpdate(roleFunction);
		}
	}
	/**
	 * 清空操作
	 * @param roleId
	 */
	public void clearp(String roleId) {	
		List<TSRoleFunction> rFunctions = systemService.findByProperty(TSRoleFunction.class,"TSRole.id",roleId);
		if (rFunctions.size() > 0){
			for (TSRoleFunction tRoleFunction : rFunctions) {
				tRoleFunction.setOperation(null);
				systemService.saveOrUpdate(tRoleFunction);
			}
	 	}
	}
}
