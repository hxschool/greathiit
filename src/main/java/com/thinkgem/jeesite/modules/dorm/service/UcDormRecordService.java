/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormRecord;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormRecordDao;

/**
 * 查寝记录Service
 * @author 赵俊飞
 * @version 2018-01-22
 */
@Service
@Transactional(readOnly = true)
public class UcDormRecordService extends CrudService<UcDormRecordDao, UcDormRecord> {

	public UcDormRecord get(String id) {
		return super.get(id);
	}
	
	public List<UcDormRecord> findByParentIdsLike(UcDormRecord ucDormRecord) {
		return super.findByParentIdsLike(ucDormRecord);
	}
	
	public Page<UcDormRecord> findPage(Page<UcDormRecord> page, UcDormRecord ucDormRecord) {
		return super.findPage(page, ucDormRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(UcDormRecord ucDormRecord) {
		super.save(ucDormRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcDormRecord ucDormRecord) {
		super.delete(ucDormRecord);
	}
	
}