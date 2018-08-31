/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormRepair;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormRepairDao;

/**
 * 报修管理Service
 * @author 赵俊飞
 * @version 2018-03-21
 */
@Service
@Transactional(readOnly = true)
public class UcDormRepairService extends CrudService<UcDormRepairDao, UcDormRepair> {

	public UcDormRepair get(String id) {
		return super.get(id);
	}
	
	public List<UcDormRepair> findList(UcDormRepair ucDormRepair) {
		return super.findList(ucDormRepair);
	}
	
	public Page<UcDormRepair> findPage(Page<UcDormRepair> page, UcDormRepair ucDormRepair) {
		return super.findPage(page, ucDormRepair);
	}
	
	@Transactional(readOnly = false)
	public void save(UcDormRepair ucDormRepair) {
		super.save(ucDormRepair);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcDormRepair ucDormRepair) {
		super.delete(ucDormRepair);
	}
	
}