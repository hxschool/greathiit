/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentStatusLog;
import com.thinkgem.jeesite.modules.student.export.StudentAction;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 学生信息Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class StudentService extends CrudService<StudentDao, Student> {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentStatusLogService studentStatusLogService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeDao officeDao;
	public Student getStudentByStudentNumber(String studentNumber) {
		return studentDao.getStudentByStudentNumber(studentNumber);
	}
	
	public Student get(String id) {
		return super.get(id);
	}
	
	public List<Student> findByParentIdsLike(Student student) {
		return super.findByParentIdsLike(student);
	}
	
	public Page<Student> findPage(Page<Student> page, Student student) {
		return super.findPage(page, student);
	}
	@Transactional(readOnly = false)
	public void saveUser(Student student) throws Exception {
		try {
			logger.info("准备导入数据~");
			User user = new User();
			
			user.setNo(student.getStudentNumber());
			user.setName(student.getName());
			user.setLoginName(student.getIdCard());
			user.setMobile(student.getPhone());
			user.setPhone(student.getPhone());
			user.setPassword(SystemService.entryptPassword("888888"));
			Role role = new Role("99");
			List<Role> rs = new ArrayList<Role>();
			rs.add(role);
			user.setRole(role);
			user.setRoleList(rs);
			user.setLoginFlag("1");
			user.setUserType("99");		
			Office cls = student.getClazz();
			user.setClazz(cls);
			if(!org.springframework.util.StringUtils.isEmpty(cls)) {
				Office zy = officeDao.get(cls.getParent());
				user.setOffice(zy);
				if(!org.springframework.util.StringUtils.isEmpty(zy)) {
					Office xy = officeDao.get(zy.getParent());
					user.setCompany(xy);
				}
			}
			if(org.springframework.util.StringUtils.isEmpty(user.getCompany())) {
				Office company = new Office();
				company.setId("1");
				user.setCompany(company);
			}
			User u = UserUtils.get("1");
			user.setCreateBy(u);
			user.setCreateDate(new Date());
			user.setDelFlag("0");
			user.setUpdateBy(u);
			user.setUpdateDate(new Date());
			user.setRemarks("黑龙江省招生办导入学生信息");
			systemService.saveUser(user);
			if(!org.springframework.util.StringUtils.isEmpty(student.getClazz())) {
				Office clazz = StudentUtil.getClassNo(student.getZy(),student.getClazz().getName());
				student.setClazz(clazz);
			}
			
			super.save(student);
		}catch (Exception e) {
			logger.error("导入错误:["+e+"]");
			
			throw e;
		}
		

	}
	@Transactional(readOnly = false)
	public void save(Student student) {
		Student entity = null;
		if (!org.springframework.util.StringUtils.isEmpty(student)
				&& !org.springframework.util.StringUtils.isEmpty(student.getId())) {
			entity = get(student);
		}
		super.save(student);
		
		if(!org.springframework.util.StringUtils.isEmpty(entity)) {
			String before = entity.getStatus();
			String status = student.getStatus();
			StudentStatusLog studentStatusLog = new StudentStatusLog();
			studentStatusLog.setModule("student");
			studentStatusLog.setModuleId(entity.getId());
			StudentAction studentAction = student.getStudentAction();
			switch(studentAction) {
				case tracked:
				{
					Office clazz = entity.getClazz();
					if(!org.springframework.util.StringUtils.isEmpty(clazz)) {
						studentStatusLog.setBefore(clazz.getName());
					}
					clazz = student.getClazz();
					if(!org.springframework.util.StringUtils.isEmpty(clazz)) {
						studentStatusLog.setStatus(clazz.getName());
					}
					studentStatusLog.setAction(studentAction.getCode());
					studentStatusLog.setDescription(studentAction.getName());
					break;
				}
				case imp:
				{
					
					break;
				}
				case exp:
				{
					break;
				}
				default :
				{
					studentStatusLog.setBefore(before);
					studentStatusLog.setStatus(status);
					studentStatusLog.setDescription("操作前状态:" + DictUtils.getDictLabel(before, "student_status", "学籍状态") + ",操作后状态:" + DictUtils.getDictLabel(status, "student_status", "学籍状态"));
					break;
				}
			}
			studentStatusLogService.save(studentStatusLog);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Student student) {
		super.delete(student);
	}
	
	public Student getStudentByIdCard(String idCard) {
		return studentDao.getStudentByIdCard(idCard);
	}
	public Page<Student> trackedPage(Page<Student> page,Student student){
		student.setPage(page);
		page.setList(studentDao.tracked(student));
		return page;
	}
	
	public Page<Student> completePage(Page<Student> page,Student student){
		List<Student> students = studentDao.complete(student);
		student.setPage(page);
		page.setList(students);
		return page;
	}

}