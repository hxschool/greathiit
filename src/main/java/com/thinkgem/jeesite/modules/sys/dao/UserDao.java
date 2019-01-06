/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	public User getUserSingleByName(@Param("name")String name);
	
	public User getCasByLoginName(@Param("loginname")String loginname);
	
	public User findUserbyMobileOrStudentNumberOrMail(@Param("loginname")String loginname);
	
	public User findUserByNumber(@Param("no")String no);

	/**
	 * 根据角色id获取教师信息
	 * @param roleId
	 * @return
	 */
	public  List<User> findUserByRoleId(@Param("roleId")String roleId);
	
	
	
	public User getUserByNameAndIdCard(User user);
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	/**
	 * 一个寝室里面多少人
	 * @param dormId
	 * @return
	 */
	public List<User> findUserByUserPojo(User pojo);
	//nassigned
	public List<User> nassignedStudentNumberByMajorId(User pojo);
	
	
	
	public List<User> findListByOfficeIdAndClazzId(@Param("officeId")String officeId,@Param("clazzId")String clazzId);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public long exist(User user);
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

}
