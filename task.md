1.迎新相关数据修复 未完成
2.学籍科数据 未完成
3.公共课 导出学生信息导入学生信息
点名册、成绩报表都可以合在一起的，都能分别按课程，按学生所在专业行政班导出就行
不用太多，不用太复杂


select b.id,b.curs_name,s.name,sc.time_add,b.lower_limit,b.upper_limit,count(b.id) from select_course a left join course b on a.course_id=b.id left join sys_user s on b.teacher_number=s.no
 left join course_schedule  sc on a.course_id=sc.course_id
 where b.curs_learning_model='01'
group by b.id,b.curs_name

select a.no,left(a.no,8), substring(a.no,5,2), b.parent_ids from sys_user a left join sys_office b on left(no,8)=b.id where a.create_date>"2019-12-10 09:59:59" and length(no)=10
;

update sys_user a left join sys_office b on left(no,8)=b.id 
set a.clazz_id=concat('20',left(no,6)),a.office_id=substring(a.no,3,2),a.company_id=substring(,5,1) where a.create_date>"2019-12-10 09:59:59" and length(no)!=10

;
update student a left join sys_user b on a.student_number =b.no  set a.clazz_id=b.clazz_id  where a.create_date>"2019-12-10 09:59:59"
; 