/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.answering.admin.dao.AsAnsweringStudentDao;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnsweringStudent;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 答辩Service
 * @author 赵俊飞
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class AsAnsweringStudentService extends CrudService<AsAnsweringStudentDao, AsAnsweringStudent> {
	
	@Autowired
	private AsAnsweringStudentDao asAnsweringStudentDao;
	@Autowired
	private UserDao userDao;
	 
	public AsAnsweringStudent get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void batchInsert( AsAnswering asAnswering) {
		Office office = asAnswering.getOffice();
		if(!org.springframework.util.StringUtils.isEmpty(office)) {
			String[] ids = office.getId().split(",");
			for(String clazzId : ids) {
				User user = new User();
				Office clazz = new Office();
				clazz.setId(clazzId);
				user.setClazz(clazz);
				List<User> users  = userDao.findAllList(user);
				String asAnsweringId = asAnswering.getId();
				for(User u : users) {
					AsAnsweringStudent asAnsweringStudent = new AsAnsweringStudent();
					asAnsweringStudent.setAsAnsweringId(asAnsweringId);
					asAnsweringStudent.setStatus("0");
					asAnsweringStudent.setStudentNumber(u.getNo());
					asAnsweringStudent.setUsername(u.getName());
					super.save(asAnsweringStudent);
				}
			}
		}
	}
	
	public List<AsAnsweringStudent> findByParentIdsLike(AsAnsweringStudent asAnsweringStudent) {
		return super.findByParentIdsLike(asAnsweringStudent);
	}
	
	
	
	public int count(AsAnsweringStudent asAnsweringStudent) {
		return asAnsweringStudentDao.count(asAnsweringStudent);
	}
	
	public Page<AsAnsweringStudent> findPage(Page<AsAnsweringStudent> page, AsAnsweringStudent asAnsweringStudent) {
		return super.findPage(page, asAnsweringStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(AsAnsweringStudent asAnsweringStudent) {
		super.save(asAnsweringStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(AsAnsweringStudent asAnsweringStudent) {
		super.delete(asAnsweringStudent);
	}
	
}