package com.thinkgem.jeesite.modules.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.modules.api.dao.ApiDao;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.sys.dao.AreaDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.dao.TeacherDao;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.uc.student.dao.UcStudentDao;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 学生基本信息Service
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Service
@Transactional(readOnly = true)
public class ApiService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private UcStudentDao ucStudentDao;
	@Autowired
	private ApiDao apiDao;
	@Autowired
	private AreaDao areaDao;
	
	
	public List<Student> getInClass(String classno){
		Student entity = new Student();
		Office clazz = new Office();
		clazz.setId(classno);
		entity.setClazz(clazz);
		return studentDao.findAllList(entity);
	}
	public Map<String,Object> getClass(String classno){
		return apiDao.getClass(classno);
	}
	
	public List<Student> getStudents(Student student) {
		return studentDao.findList(student);
	}
	
	public Student getStudent(String studentNumber) {
		return studentDao.getStudentByStudentNumber(studentNumber);
	}
	
	public List<Teacher> getTeachers(Teacher teacher) {
		return teacherDao.findAllList(teacher);
	}
	
	public Teacher getTeacher(String teacherNumber) {
		return teacherDao.getTeacherByTeacherNumber(teacherNumber);
	}
	
	public List<Map<String,Object>> getMajor(){
		return apiDao.getMajor();
	}
	public List<Map<String,Object>> getCollege(){
		return apiDao.getCollege();
	}
	
	public String getClazzName(String parentId,String name){
		return apiDao.getClazzName(parentId,name);
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
	
	public List<Area> getArea(String parentId){
		if(StringUtils.isEmpty(parentId)){
			return UserUtils.getAreaList();
		}
		return areaDao.findAreaByParentId(parentId);
	}
	
}
