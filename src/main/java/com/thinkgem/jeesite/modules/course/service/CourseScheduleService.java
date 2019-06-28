/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.course.dao.CourseScheduleDao;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;
import com.thinkgem.jeesite.modules.school.dao.SchoolRootDao;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;

/**
 * 计划教室Service
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class CourseScheduleService extends CrudService<CourseScheduleDao, CourseSchedule> {
	@Autowired
	private CourseScheduleDao courseScheduleDao;
	@Autowired
	private SchoolRootDao schoolRootDao;
	@Autowired
	private SysConfigService sysConfigService;
	
	public List<CourseScheduleExt> findCoursesByParam(CourseScheduleExt courseScheduleExt){
		return courseScheduleDao.findCoursesByParam(courseScheduleExt.getList(), courseScheduleExt.getCourseClass(), courseScheduleExt.getTeacherNumber());
	}
	public List<CourseScheduleExt> getCourseScheduleExt(CourseScheduleExt courseScheduleExt){
		return courseScheduleDao.getCourseScheduleExt(courseScheduleExt);
		
	}
	
	public CourseSchedule get(String id) {
		return super.get(id);
	}
	
	public List<CourseSchedule> auto(CourseSchedule courseSchedule) {
		return courseScheduleDao.auto(courseSchedule);
	}
	
	public List<CourseSchedule> getCourseScheduleByYearTerm(String yearTerm) {
		return courseScheduleDao.getCourseScheduleByYearTerm(yearTerm);
	}
	public List<CourseSchedule> getCourseScheduleByYearTermAndTeacherNumber(String yearTerm,String teacherNumber) {
		return courseScheduleDao.getCourseScheduleByYearTermAndTeacherNumber(yearTerm,teacherNumber);
	}
	
	
	public CourseSchedule getByAddTime(String timeAdd) {
		return courseScheduleDao.getByAddTime(timeAdd);
	}
	
	
	public List<CourseSchedule> findListByTimeAdd(String timeAdd) {
		return courseScheduleDao.findListByTimeAdd(timeAdd);
	}
	
	public List<CourseSchedule> findList(CourseSchedule courseSchedule) {
		return super.findList(courseSchedule);
	}
	
	public Page<CourseSchedule> findPage(Page<CourseSchedule> page, CourseSchedule courseSchedule) {
		return super.findPage(page, courseSchedule);
	}

	@Transactional(readOnly = false)
	@Async
	public void executeAsyncJsonAvailability(SysConfig sysConfig) throws Exception{
		
		
		String termYear = sysConfig.getTermYear();
		if(StringUtils.isEmpty(termYear)) {
			termYear = sysConfigService.getModule(Global.SYSCONFIG_COURSE).getTermYear();
		}

				
		if(!StringUtils.isEmpty(get(termYear.concat("011010111")))) {
			throw new Exception();
		}
	
		List<SchoolRoot> schoolRoots = schoolRootDao.findByParentId("0");
		for (SchoolRoot schoolRoot : schoolRoots) {
			List<SchoolRoot> roots = schoolRootDao.findByParentId(schoolRoot.getId());
			String schoolNumber = schoolRoot.getValue();
			for (SchoolRoot root : roots) {

				for (int $i = 1; $i <= 20; $i++) {
					for (int $j = 1; $j <= 6; $j++) {
						for (int $k = 1; $k <= 7; $k++) {
							
							CourseSchedule courseSchedule = new CourseSchedule();
							String rootNumber = root.getValue();
							String $id = termYear.concat(schoolNumber).concat(rootNumber);
							String timeAdd = "";

							if ($i <= 9)
								timeAdd = $id + '0' + $i + $j + $k;
							else
								timeAdd = $id + $i + $j + $k;
							courseSchedule.setIsNewRecord(true);
							courseSchedule.setId(timeAdd);
							courseSchedule.setTimeAdd(timeAdd);
							courseSchedule.setCourseId("00000000");
							courseSchedule.setScLock("1");
							courseSchedule.setCourseClass("");
							courseSchedule.setTips("");
							if(super.get(timeAdd)==null) {
								super.save(courseSchedule);
							}
						}
					}
				}
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void save(CourseSchedule courseSchedule) {
		super.save(courseSchedule);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseSchedule courseSchedule) {
		super.delete(courseSchedule);
	}
	
}