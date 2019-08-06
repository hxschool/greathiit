/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wechat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wechat.entity.WeixinUser;
import com.thinkgem.jeesite.modules.wechat.dao.WeixinUserDao;

/**
 * 绑定成功Service
 * @author 赵俊飞
 * @version 2018-09-25
 */
@Service
@Transactional(readOnly = true)
public class WeixinUserService extends CrudService<WeixinUserDao, WeixinUser> {

	public WeixinUser get(String id) {
		return super.get(id);
	}
	
	public List<WeixinUser> findByParentIdsLike(WeixinUser weixinUser) {
		return super.findByParentIdsLike(weixinUser);
	}
	
	public Page<WeixinUser> findPage(Page<WeixinUser> page, WeixinUser weixinUser) {
		return super.findPage(page, weixinUser);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinUser weixinUser) {
		super.save(weixinUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinUser weixinUser) {
		super.delete(weixinUser);
	}
	
}