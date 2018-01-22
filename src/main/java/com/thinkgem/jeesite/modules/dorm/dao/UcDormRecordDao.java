/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormRecord;

/**
 * 查寝记录DAO接口
 * @author 赵俊飞
 * @version 2018-01-22
 */
@MyBatisDao
public interface UcDormRecordDao extends CrudDao<UcDormRecord> {
	
}