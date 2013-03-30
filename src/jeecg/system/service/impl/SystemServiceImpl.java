package jeecg.system.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jeecg.system.pojo.base.TSBusConfig;
import jeecg.system.pojo.base.TSLog;
import jeecg.system.pojo.base.TSPrjstatus;
import jeecg.system.pojo.base.TSType;
import jeecg.system.pojo.base.TSTypegroup;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService {
	public TSUser checkUserExits(TSUser user) throws Exception {
		return this.commonDao.getUserByUserIdAndUserNameExits(user);
	}

	/**
	 * 添加日志
	 */
	public void addLog(String logcontent, Short loglevel, Short operatetype) {
		HttpServletRequest request=ContextHolderUtils.getRequest();
		String broswer=BrowserUtils.checkBrowse(request);
		TSLog log = new TSLog();
		log.setLogcontent(logcontent);
		log.setLoglevel(loglevel);
		log.setOperatetype(operatetype);
		log.setNote(oConvertUtils.getIp());
		log.setBroswer(broswer);
		log.setOperatetime(DataUtils.gettimestamp());
		log.setTSUser(ResourceUtil.getSessionUserName());
		commonDao.save(log);
	}

	/**
	 * 根据角色编码和状态值获取审批状态
	 * 
	 * @param prjstate
	 * @param rolecode
	 * @return
	 */
	public TSPrjstatus getTBPrjstatusByCode(String prjstate, String rolecode) {
		return commonDao.getTBPrjstatusByCode(prjstate, rolecode);
	}

	/**
	 * 根据流程编码和业务类名获取业务配置类
	 */
	public TSBusConfig getTSBusConfig(Class classname, String processkey) {
		TSBusConfig tsBusConfig = null;
		String hql = "from TSBusConfig where TSTable.entityName='" + classname.getName() + "' and TPProcess.processkey='" + processkey + "'";
		List<TSBusConfig> tsBusConfigList = commonDao.findByQueryString(hql);
		if (tsBusConfigList.size() > 0) {
			tsBusConfig = tsBusConfigList.get(0);
		}
		return tsBusConfig;

	}
	/**
	 * 根据类型编码和类型名称获取Type,如果为空则创建一个
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSType getType(String typecode,String typename,TSTypegroup tsTypegroup)
	{
		TSType actType = commonDao.findUniqueByProperty(TSType.class, "typecode",typecode);
		if (actType == null) {
			actType = new TSType();
			actType.setTypecode(typecode);
			actType.setTypename(typename);
			actType.setTSTypegroup(tsTypegroup);
			commonDao.save(actType);
		}
		return actType;
		
	}
	/**
	 * 根据类型分组编码和名称获取TypeGroup,如果为空则创建一个
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSTypegroup getTypeGroup(String typegroupcode,String typgroupename)
	{
		TSTypegroup tsTypegroup = commonDao.findUniqueByProperty(TSTypegroup.class, "typegroupcode",typegroupcode);
		if (tsTypegroup == null) {
			tsTypegroup = new TSTypegroup();
			tsTypegroup.setTypegroupcode(typegroupcode);
			tsTypegroup.setTypegroupname(typgroupename);
			commonDao.save(tsTypegroup);
		}
		return tsTypegroup;	
	}
}
