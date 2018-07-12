/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.school.dao.SchoolCourseDao;
import com.thinkgem.jeesite.modules.school.dao.SchoolRootDao;
import com.thinkgem.jeesite.modules.school.entity.SchoolCourse;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;

/**
 * 楼宇Service
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class SchoolRootService extends CrudService<SchoolRootDao, SchoolRoot> {
	@Autowired
	private SchoolRootDao schoolRootDao;
	@Autowired
	private SchoolCourseDao schoolCourseDao;
	
	
	public List<TreeLink> treeLink() {
		List<SchoolRoot> list1 = schoolRootDao.findByParentId("0");
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for (SchoolRoot school1 : list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(school1.getValue());
			treeLink.setName(school1.getLabel());
			List<SchoolRoot> list2 = schoolRootDao.findByParentId(school1.getId());
			User user = UserUtils.getUser();
			if (!user.getId().equals("1")) {
				SchoolCourse schoolCourse = new SchoolCourse();
				schoolCourse.setCurrentUser(user);
				List<SchoolCourse> schoolCourses = schoolCourseDao.findList(schoolCourse);
				if (!org.springframework.util.StringUtils.isEmpty(schoolCourses) && schoolCourses.size() > 0) {
					List<SchoolRoot> schoolRoots = new ArrayList<SchoolRoot>();
					for (SchoolCourse sc : schoolCourses) {
						schoolRoots.add(sc.getSchoolRoot());
					}
					list2.retainAll(schoolRoots);
				}
			}

			List<TreeLink> treeLinks2 = new ArrayList<TreeLink>();
			for (SchoolRoot schoolRoot2 : list2) {
				TreeLink treeLink2 = new TreeLink();
				treeLink2.setValue(schoolRoot2.getValue());
				treeLink2.setName(schoolRoot2.getLabel());
				treeLinks2.add(treeLink2);
			}
			treeLink.setSub(treeLinks2);
			treeLinks1.add(treeLink);

		}
		return treeLinks1;
	}
	
	public List<TreeLink> treeLinkId(){
		List<SchoolRoot> list1 = schoolRootDao.findByParentId("0");
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(SchoolRoot school1:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(school1.getId());
			treeLink.setName(school1.getLabel());
			List<SchoolRoot> list2 = schoolRootDao.findByParentId(school1.getId());
			
			User user = UserUtils.getUser();
			if (!user.getId().equals("1")) {
				SchoolCourse schoolCourse = new SchoolCourse();
				schoolCourse.setCurrentUser(user);
				List<SchoolCourse> schoolCourses = schoolCourseDao.findList(schoolCourse);
				if (!org.springframework.util.StringUtils.isEmpty(schoolCourses) && schoolCourses.size() > 0) {
					List<SchoolRoot> schoolRoots = new ArrayList<SchoolRoot>();
					for (SchoolCourse sc : schoolCourses) {
						schoolRoots.add(sc.getSchoolRoot());
					}
					list2.retainAll(schoolRoots);
				}
			}
			
			List<TreeLink> treeLinks2 = new ArrayList<TreeLink>();
			for(SchoolRoot schoolRoot2:list2) {
				TreeLink treeLink2 = new TreeLink();
				treeLink2.setValue(schoolRoot2.getId());
				treeLink2.setName(schoolRoot2.getLabel());
				treeLinks2.add(treeLink2);
			}
			treeLink.setSub(treeLinks2);
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}

	public SchoolRoot get(String id) {
		return super.get(id);
	}
	
	public List<SchoolRoot> findList(SchoolRoot schoolRoot) {
		return super.findList(schoolRoot);
	}
	

	public List<SchoolRoot> findByParentId(String parentId) {
		return schoolRootDao.findByParentId(parentId);
	}
	
	
	public Page<SchoolRoot> findPage(Page<SchoolRoot> page, SchoolRoot schoolRoot) {
		return super.findPage(page, schoolRoot);
	}
	
	@Transactional(readOnly = false)
	public void save(SchoolRoot schoolRoot) {
		super.save(schoolRoot);
	}
	
	@Transactional(readOnly = false)
	public void delete(SchoolRoot schoolRoot) {
		super.delete(schoolRoot);
	}
	
}