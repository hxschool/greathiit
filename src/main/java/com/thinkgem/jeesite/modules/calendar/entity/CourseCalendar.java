/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 校历校准Entity
 * @author 赵俊飞
 * @version 2017-12-14
 */
public class CourseCalendar extends DataEntity<CourseCalendar> {
	
	private static final long serialVersionUID = 1L;
	private String calendarMonth;		// 月
	private String calendarDay;		// 日
	private String calendarYear;		// 年
	
	public CourseCalendar() {
		super();
	}

	public CourseCalendar(String id){
		super(id);
	}

	@Length(min=1, max=2, message="月长度必须介于 1 和 2 之间")
	public String getCalendarMonth() {
		return calendarMonth;
	}

	public void setCalendarMonth(String calendarMonth) {
		this.calendarMonth = calendarMonth;
	}
	
	@Length(min=1, max=2, message="日长度必须介于 1 和 2 之间")
	public String getCalendarDay() {
		return calendarDay;
	}

	public void setCalendarDay(String calendarDay) {
		this.calendarDay = calendarDay;
	}
	
	@Length(min=1, max=4, message="年长度必须介于 1 和 4 之间")
	public String getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(String calendarYear) {
		this.calendarYear = calendarYear;
	}
	
}