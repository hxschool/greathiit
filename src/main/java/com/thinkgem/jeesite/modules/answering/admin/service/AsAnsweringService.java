/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.answering.admin.dao.AsAnsweringDao;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;

/**
 * 答辩抽签Service
 * @author 赵俊飞
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class AsAnsweringService extends CrudService<AsAnsweringDao, AsAnswering> {
	public static String  READY = "0";
	public static String  HAVE_IN_HAND = "1";
	public static String  COMPLETE = "2";
	
	public AsAnswering get(String id) {
		return super.get(id);
	}
	
	public List<AsAnswering> findList(AsAnswering asAnswering) {
		return super.findList(asAnswering);
	}
	
	public Page<AsAnswering> findPage(Page<AsAnswering> page, AsAnswering asAnswering) {
		return super.findPage(page, asAnswering);
	}
	
	@Transactional(readOnly = false)
	public void save(AsAnswering asAnswering) {
		super.save(asAnswering);
	}
	
	@Transactional(readOnly = false)
	public void delete(AsAnswering asAnswering) {
		super.delete(asAnswering);
	}
	
}