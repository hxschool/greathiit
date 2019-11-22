/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentStatusLog;
import com.thinkgem.jeesite.modules.student.service.StudentStatusLogService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.dao.UcStudentDao;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 学生基本信息Service
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Service
@Transactional(readOnly = true)
public class UcStudentService extends CrudService<UcStudentDao, UcStudent> {
	@Autowired
	private SystemService systemService;
	@Autowired
	private StudentStatusLogService studentStatusLogService;
	@Autowired
	private UcStudentDao ucStudentDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private OfficeDao officeDao;
	public UcStudent findByIdCard(String idCard) {
		return ucStudentDao.findByIdCard(idCard);
	}
	
	public UcStudent findBystudentNumber(String studentNumber) {
		return ucStudentDao.findBystudentNumber(studentNumber);
	}
	
	public UcStudent get(String id) {
		return super.get(id);
	}
	
	public List<UcStudent> findByParentIdsLike(UcStudent ucStudent) {
		return super.findByParentIdsLike(ucStudent);
	}
	
	public Page<UcStudent> findPage(Page<UcStudent> page, UcStudent ucStudent) {
		return super.findPage(page, ucStudent);
	}
	
	@Transactional(readOnly = false)
	public void saveUser(UcStudent ucStudent) {
		try
		{
		save(ucStudent);
		User user = new User();
		String password = StringUtils.right(ucStudent.getIdCard().toLowerCase(), 6);
		user.setNo(ucStudent.getStudentNumber());
		user.setName(ucStudent.getName());
		user.setLoginName(ucStudent.getIdCard());
		user.setMobile(ucStudent.getPhone());
		user.setPhone(ucStudent.getPhone());
		user.setPassword(SystemService.entryptPassword(password));
		Role role = new Role("99");
		List<Role> rs = new ArrayList<Role>();
		rs.add(role);
		user.setRole(role);
		user.setRoleList(rs);
		user.setLoginFlag("1");
		user.setUserType("99");
		
		String department = ucStudent.getDepartmentName();
		String major = ucStudent.getMajorName();
		String clazz = ucStudent.getClassNumber();
		
		Office company = officeDao.getOfficeByName(department);
		Office office = officeDao.getOfficeByName(major);
		Office cls = officeDao.getOfficeByName(clazz);
		user.setCompany(company);
		user.setOffice(office);
		user.setClazz(cls);
		User u = UserUtils.get("1");
		user.setCreateBy(u);
		user.setCreateDate(new Date());
		user.setDelFlag("0");
		user.setUpdateBy(u);
		user.setUpdateDate(new Date());
		user.setRemarks("黑龙江省招生办导入学生信息");
		systemService.saveUser(user);
		if (!org.springframework.util.StringUtils.isEmpty(ucStudent.getStudentNumber())
				&& org.springframework.util.StringUtils
						.isEmpty(studentDao.getStudentByStudentNumber(ucStudent.getStudentNumber()))) {
			Student student = new Student();
			student.setName(ucStudent.getName());
			student.setIdCard(ucStudent.getIdCard());
			Date birthday = DateUtils.parseDate(IdcardUtils.getBirthByIdCard(ucStudent.getIdCard()));
			student.setBirthday(birthday);
			student.setGender(IdcardUtils.getGender(ucStudent.getIdCard()));
			student.setNation(ucStudent.getNation());
			student.setPolitical(ucStudent.getPolitical());
			student.setPhone(ucStudent.getPhone());
			student.setAddress(ucStudent.getAddressee());
			student.setEdu(ucStudent.getEdu());
			student.setStudentLength(ucStudent.getStudentLength());
			student.setClazz(cls);
			student.setStudentNumber(ucStudent.getStudentNumber());
			studentDao.insert(student);
		}
		
		super.save(ucStudent);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Transactional(readOnly = false)
	public void save(UcStudent student) {
		
		UcStudent entity = null;
		if (!org.springframework.util.StringUtils.isEmpty(student)
				&& !org.springframework.util.StringUtils.isEmpty(student.getId())) {
			entity = get(student);
		}
		//如果数据停用,是否通知修改系统用户信息为不可用
		super.save(student);
		
		if(!org.springframework.util.StringUtils.isEmpty(entity)) {
			String before = entity.getLearning();
			String status = student.getLearning();
			StudentStatusLog studentStatusLog = new StudentStatusLog();
			studentStatusLog.setModule("uc_student");
			studentStatusLog.setModuleId(entity.getId());
			studentStatusLog.setBefore(before);
			studentStatusLog.setStatus(status);
			studentStatusLog.setDescription("操作前状态:" + DictUtils.getDictLabel(before, "student_learning", "") + ",操作后状态:" + DictUtils.getDictLabel(status, "student_learning", ""));
			studentStatusLogService.save(studentStatusLog);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(UcStudent ucStudent) {
		super.delete(ucStudent);
	}
	
	public List<Map<String,Object>> studentGroup(UcStudent ucStudent){
		return ucStudentDao.studentGroup(ucStudent);
	}
	
	public List<Map<String,Object>> studentSex(UcStudent ucStudent){
		return ucStudentDao.studentSex(ucStudent);
	}
	
	
	public List<Map<String,Object>> studentRegion(UcStudent ucStudent){
		return ucStudentDao.studentRegion(ucStudent);
	}
	
	public List<Map<String,Object>> studentGengerRegion(UcStudent ucStudent){
		return ucStudentDao.studentGengerRegion(ucStudent);
	}
	
	public List<Map<String,Object>> studentEdu(UcStudent ucStudent){
		return ucStudentDao.studentEdu(ucStudent);
	}
	
	public List<Map<String,Object>> studentDepartment(UcStudent ucStudent){
		return ucStudentDao.studentDepartment(ucStudent);
	}
	
	public List<Map<String,Object>> studentMajor(UcStudent ucStudent){
		return ucStudentDao.studentMajor(ucStudent);
	}
	
	public List<Map<String,Object>> studentUser(){
		return ucStudentDao.studentUser();
	}
	public List<UcStudent> exportList(UcStudent ucStudent){
		return ucStudentDao.exportList(ucStudent);
	}

	
	public Page<UcStudent> completePage(Page<UcStudent> page, UcStudent ucStudent) {
		ucStudent.setPage(page);
		page.setList(ucStudentDao.completeList(ucStudent));
		return page;
	}
	
	public List<UcStudent> fenban(UcStudent ucStudent){
		return ucStudentDao.fenban(ucStudent);
	}
}