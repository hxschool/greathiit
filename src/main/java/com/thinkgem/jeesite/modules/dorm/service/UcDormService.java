/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormBuildDao;
import com.thinkgem.jeesite.modules.dorm.dao.UcDormDao;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormBuild;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 宿舍管理Service
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Service
@Transactional(readOnly = true)
public class UcDormService extends CrudService<UcDormDao, UcDorm> {
	
	@Autowired
	private UcDormBuildDao ucDormBuildDao;
	@Autowired
	private UserDao userDao;
	public UcDorm get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void addDorm(User user) {
		UcDorm dorm = get(user.getDorm().getId());
		UcDormBuild ucDormBuild=dorm.getUcDormBuild();
		if(userDao.update(user)>0) {
			int cnt = Integer.valueOf(dorm.getCnt())+1;
			dorm.setCnt(String.valueOf(cnt));
			save(dorm);
			int dormBuildCnt = Integer.valueOf(ucDormBuild.getDormBuildCnt())+1;
			ucDormBuild.setDormBuildCnt(String.valueOf(dormBuildCnt));
			ucDormBuildDao.update(ucDormBuild);
		}
	}
	
	public void removeDorm(User user) {
		UcDorm dorm = get(user.getDorm().getId());
		user.setDorm(null);
		UcDormBuild ucDormBuild=dorm.getUcDormBuild();
		if(userDao.update(user)>0) {
			int cnt = Integer.valueOf(dorm.getCnt())-1;
			dorm.setCnt(String.valueOf(cnt));
			save(dorm);
			int dormBuildCnt = Integer.valueOf(ucDormBuild.getDormBuildCnt())-1;
			ucDormBuild.setDormBuildCnt(String.valueOf(dormBuildCnt));
			ucDormBuildDao.update(ucDormBuild);
		}
	}
	
	public List<UcDorm> findList(UcDorm ucDorm) {
		return super.findList(ucDorm);
	}
	
	public Page<UcDorm> findPage(Page<UcDorm> page, UcDorm ucDorm) {
		return super.findPage(page, ucDorm);
	}
	
	@Transactional(readOnly = false)
	public void save(UcDorm ucDorm) {
		super.save(ucDorm);
	}
	
	@Transactional(readOnly = false)
	public void delete(UcDorm ucDorm) {
		super.delete(ucDorm);
	}
	
}