/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.student.entity.StudentItem;
import com.thinkgem.jeesite.modules.student.dao.StudentItemDao;

/**
 * 获奖信息Service
 * @author 赵俊飞
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class StudentItemService extends CrudService<StudentItemDao, StudentItem> {

	public StudentItem get(String id) {
		return super.get(id);
	}
	
	public List<StudentItem> findList(StudentItem studentItem) {
		return super.findList(studentItem);
	}
	
	public Page<StudentItem> findPage(Page<StudentItem> page, StudentItem studentItem) {
		return super.findPage(page, studentItem);
	}
	
	@Transactional(readOnly = false)
	public void save(StudentItem studentItem) {
		super.save(studentItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentItem studentItem) {
		super.delete(studentItem);
	}
	
}