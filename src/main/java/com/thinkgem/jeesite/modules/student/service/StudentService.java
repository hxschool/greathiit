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
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
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
	public void saveUser(Student student) {
		save(student);
		User user = new User();
		String password = StringUtils.right(student.getIdCard().toLowerCase(), 6);
		user.setNo(student.getStudentNumber());
		user.setName(student.getName());
		user.setLoginName(student.getIdCard());
		user.setMobile(student.getPhone());
		user.setPhone(student.getPhone());
		user.setPassword(SystemService.entryptPassword(password));
		Role role = new Role("99");
		List<Role> rs = new ArrayList<Role>();
		rs.add(role);
		user.setRole(role);
		user.setRoleList(rs);
		user.setLoginFlag("1");
		user.setUserType("99");
		String clazz = student.getClassno();		
		Office cls = officeDao.getOfficeByName(clazz);
		user.setClazz(cls);
		User u = UserUtils.get("1");
		user.setCreateBy(u);
		user.setCreateDate(new Date());
		user.setDelFlag("0");
		user.setUpdateBy(u);
		user.setUpdateDate(new Date());
		user.setRemarks("黑龙江省招生办导入学生信息");
		systemService.saveUser(user);
		super.save(student);
	}
	@Transactional(readOnly = false)
	public void save(Student student) {
		super.save(student);
	}
	
	@Transactional(readOnly = false)
	public void delete(Student student) {
		super.delete(student);
	}
	
}