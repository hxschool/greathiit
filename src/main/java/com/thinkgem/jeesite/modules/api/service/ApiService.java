package com.thinkgem.jeesite.modules.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.api.dao.ApiDao;
import com.thinkgem.jeesite.modules.uc.dao.UcStudentDao;
import com.thinkgem.jeesite.modules.uc.entity.UcStudent;

/**
 * 学生基本信息Service
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Service
@Transactional(readOnly = true)
public class ApiService {
	@Autowired
	private UcStudentDao ucStudentDao;
	@Autowired
	private ApiDao apiDao;
	
	public List<Map<String,Object>> getMajor(){
		return apiDao.getMajor();
	}
	public List<Map<String,Object>> getDepartment(){
		return apiDao.getDepartment();
	}
	
	public String getOfficeId(String name){
		return apiDao.getOfficeId(name);
	}
	
	public String getStudentNumber(String username,String idCard){
		return ucStudentDao.findNumberByUsernameAndIdCard(username, idCard);
	}
	
	public UcStudent getStudentNumber(String username,String idCard,String number){
		return ucStudentDao.findNumberByUsernameAndIdCardAndNumber(username, idCard, number);
	}
}
