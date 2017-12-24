package com.thinkgem.jeesite.modules.course.web.param;

import java.util.List;

import com.thinkgem.jeesite.modules.course.entity.CourseMaterial;
import com.thinkgem.jeesite.modules.course.entity.CourseSpecificContent;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingtarget;

public class CourseRequestParam {
	private List<CourseTeachingtarget> targets;
	private List<CourseSpecificContent> csc;
	private List<CourseTeachingMode> ctm;
	
	private List<CourseMaterial> crb;

	public List<CourseTeachingtarget> getTargets() {
		return targets;
	}

	public void setTargets(List<CourseTeachingtarget> targets) {
		this.targets = targets;
	}
	
	

	public List<CourseMaterial> getCrb() {
		return crb;
	}

	public void setCrb(List<CourseMaterial> crb) {
		this.crb = crb;
	}

	public List<CourseSpecificContent> getCsc() {
		return csc;
	}

	public void setCsc(List<CourseSpecificContent> csc) {
		this.csc = csc;
	}

	public List<CourseTeachingMode> getCtm() {
		return ctm;
	}

	public void setCtm(List<CourseTeachingMode> ctm) {
		this.ctm = ctm;
	}
	
	
}
