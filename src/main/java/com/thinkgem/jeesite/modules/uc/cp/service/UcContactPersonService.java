/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.cp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uc.cp.dao.UcContactPersonDao;
import com.thinkgem.jeesite.modules.uc.cp.entity.UcContactPerson;

/**
 * 紧急联系人Service
 * @author 赵俊飞
 * @version 2017-10-11
 */
@Service
@Transactional(readOnly = true)
public class UcContactPersonService extends CrudService<UcContactPersonDao, UcContactPerson> {

	public UcContactPerson get(String id) {
		return super.get(id);
	}
	
	public List<UcContactPerson> findList(UcContactPerson ucContactPerson) {
		return super.findList(ucContactPerson);
	}
	
	public Page<UcContactPerson> findPage(Page<UcContactPerson> page, UcContactPerson ucContactPerson) {
		return super.findPage(page, ucContactPerson);
	}
	
	@Transactional(readOnly = false)
	public void save(UcContactPerson ucContactPerson) {
		super.save(ucContactPerson);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcContactPerson ucContactPerson) {
		super.delete(ucContactPerson);
	}
	
}