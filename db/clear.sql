

INSERT INTO zz(id, name, enname)
  VALUES('01', '软件工程', '50000010');
INSERT INTO zz(id, name, enname)
  VALUES('02', '计算机科学与技术', '50000008');
INSERT INTO zz(id, name, enname)
  VALUES('03', '环境设计', 'c98465d225f74681bdee309f7ea3c85c');
INSERT INTO zz(id, name, enname)
  VALUES('04', '电子信息工程', 'adad311c85364f8390a6b94e38ff20cb');
INSERT INTO zz(id, name, enname)
  VALUES('05', '视觉传达设计', '09723fa8c9b449cbab1d991432321007');
INSERT INTO zz(id, name, enname)
  VALUES('06', '自动化', 'e250750317f74171bdaf9565d43ad4c9');
INSERT INTO zz(id, name, enname)
  VALUES('07', '电子商务', '8450faf0a6d447c0b9a75eb4219cdcee');
INSERT INTO zz(id, name, enname)
  VALUES('21', '软件技术', '50000011');
INSERT INTO zz(id, name, enname)
  VALUES('23', '动漫制作技术', '50000004');
INSERT INTO zz(id, name, enname)
  VALUES('24', '移动应用开发', '50000006');
INSERT INTO zz(id, name, enname)
  VALUES('31', '计算机网络技术', '50000009');
INSERT INTO zz(id, name, enname)
  VALUES('32', '云计算技术与应用', '946e255495734fe385e5bec2f3d4de39');
INSERT INTO zz(id, name, enname)
  VALUES('33', '大数据技术与应用', '50000005');
INSERT INTO zz(id, name, enname)
  VALUES('34', '信息安全与管理', '50000003');
INSERT INTO zz(id, name, enname)
  VALUES('41', '市场营销', '8747e3fd5db14d02ab5788eaffea1ca3');
INSERT INTO zz(id, name, enname)
  VALUES('43', '电子商务', '8450faf0a6d447c0b9a75eb4219cdcee');
INSERT INTO zz(id, name, enname)
  VALUES('44', '会计', '4e32c762dc744cbb942c6861f739d82a');
INSERT INTO zz(id, name, enname)
  VALUES('45', '计算机信息管理', '1d479ca34b2d4781b3d10ade7694e72f');
INSERT INTO zz(id, name, enname)
  VALUES('48', '审计', 'd9e6d197f9024a57a550597e43e5a05b');
INSERT INTO zz(id, name, enname)
  VALUES('51', '建筑室内设计', 'e3836681c98a467aa208dd4a54352d3e');
INSERT INTO zz(id, name, enname)
  VALUES('56', '工程造价', 'ba2c042967b64fffa18742cebe0aa1c3');
INSERT INTO zz(id, name, enname)
  VALUES('71', '动漫设计', 'ce97ee64d72f4d8da272bea3b63c1a74');
INSERT INTO zz(id, name, enname)
  VALUES('72', '游戏设计 ', '13beba636cbf44cc9ea366f7b1a81885');
INSERT INTO zz(id, name, enname)
  VALUES('74', '广告设计与制作', '6feff93029df4397b81f8fbb6ad1cf7b');
INSERT INTO zz(id, name, enname)
  VALUES('84', '计算机应用技术', '50000007');
INSERT INTO zz(id, name, enname)
  VALUES('93', '物联网应用技术', 'fc7458fee8d74e6ca433472015484eeb');
INSERT INTO zz(id, name, enname)
  VALUES('94', '电子信息工程技术', 'cb412bd1166141a9954b725aca886907');
INSERT INTO zz(id, name, enname)
  VALUES('96', '汽车检测与维修技术', '09e89d18730740b0b5f9d5158cd204b9');
INSERT INTO zz(id, name, enname)
  VALUES('98', '移动通信技术', 'c0f4d1dfd4c6464f8c3046489d15147a');
INSERT INTO zz(id, name, enname)
  VALUES('99', '汽车电子技术', '09cd82f8f6754e0d9ca24f2e6ff939ad');

  
  update zz a left join sys_office b on a.name=b.name set a.enname=b.id

where b.type=3 and b.grade=3
;


select * from sys_office where type=3 and grade=3

and name not  in (select name from zz)

;
update sys_office a left join zz b on a.parent_id=b.enname
set a.parent_id=b.id
 where a.type=4 and a.grade=4
 ;

 
 select concat(left(parent_ids,6),parent_id,',') from sys_office where type=4 and grade=4


update sys_office set parent_ids=concat(left(parent_ids,6),parent_id,',')  where type=4 and grade=4
;


select * from sys_user where no in (select h from old_user where h is not null)


INSERT INTO sys_user(id, company_id, office_id, clazz_id, dorm_id, login_name, password, no, name, email, phone, mobile, user_type, photo, login_ip, login_date, login_flag, create_by, create_date, update_by, update_date, remarks, del_flag, account_no) 
	VALUES('', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-3-19 17:56:52', '', '', '2018-3-19 17:56:52', '', '2018-3-19 17:56:52', '', '', '')
GO


INSERT INTO sys_user(id, company_id, office_id, clazz_id, dorm_id, login_name, password, no, name, email, phone, mobile, user_type, photo, login_ip, login_date, login_flag, create_by, create_date, update_by, update_date, remarks, del_flag, account_no) 

SELECT replace(UUID(),'-',''), i, 1, null, null, f, '0482a9c778894e9cc1b6afa4361f43406f1479dc69cd3303f43db174', h, b, g, f, f, 9, '','127.0.0.2', '2018-3-19 17:56:52', '', '', '2018-3-19 17:56:52', '', '2018-3-19 17:56:52', '', '', ''
	FROM old_user where h is not null
GO

select replace(UUID(),'-','')

select * from old_user where h is not null

select * from sys_user where no='010101'










