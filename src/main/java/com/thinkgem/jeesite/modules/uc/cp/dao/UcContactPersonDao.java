/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.cp.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.uc.cp.entity.UcContactPerson;

/**
 * 紧急联系人DAO接口
 * @author 赵俊飞
 * @version 2017-10-11
 */
@MyBatisDao
public interface UcContactPersonDao extends CrudDao<UcContactPerson> {
	
}