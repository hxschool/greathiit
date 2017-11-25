/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.visitor.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.visitor.entity.TmVisitor;

/**
 * 访客信息DAO接口
 * @author 赵俊飞
 * @version 2017-11-25
 */
@MyBatisDao
public interface TmVisitorDao extends CrudDao<TmVisitor> {
	
}