/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.out.system.dao.SystemStudentDao;
import com.thinkgem.jeesite.modules.out.system.entity.SystemStudent;

/**
 * 单招报名申请表Service
 * @author 赵俊飞
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class SystemStudentService extends CrudService<SystemStudentDao, SystemStudent> {
	@Autowired
	private SystemStudentDao systemStudentDao;
	public SystemStudent get(String id) {
		return super.get(id);
	}
	
	public SystemStudent getByUsernameAndIdCard(String username,String idCard) {
		return systemStudentDao.getByUsernameAndIdCard(username,idCard);
	}
	
	public SystemStudent getByIdCard(String idCard) {
		return systemStudentDao.getByIdCard(idCard);
	}
	
	public List<SystemStudent> findList(SystemStudent systemStudent) {
		return super.findList(systemStudent);
	}
	
	public Page<SystemStudent> findPage(Page<SystemStudent> page, SystemStudent systemStudent) {
		return super.findPage(page, systemStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemStudent systemStudent) {
		super.save(systemStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemStudent systemStudent) {
		super.delete(systemStudent);
	}

	public List<Map<String,Object>> tj(){
		return systemStudentDao.tj();
	}
	public List<Map<String,Object>> kl(){
		return systemStudentDao.kl();
	}
	public List<Map<String,Object>> lb(){
		return systemStudentDao.lb();
	}
	public List<Map<String,Object>> xb(){
		return systemStudentDao.xb();
	}
	public List<Map<String,Object>> zy(){
		return systemStudentDao.zy();
	}
}