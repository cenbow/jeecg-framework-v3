package jeecg.system.service.impl;

import java.util.List;

import jeecg.system.pojo.base.TSAttachment;
import jeecg.system.service.DeclareService;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.workflow.pojo.bus.TBDeclaretable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("declareService")
@Transactional
public class DeclareServiceImpl extends CommonServiceImpl implements DeclareService {

	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description)
	{
		String hql="from TSAttachment t where t.businessKey='"+businessKey+"' and t.description='"+description+"'";
		return commonDao.findByQueryString(hql);
	}
	
}