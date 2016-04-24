package com.myblog.service.inter;

import java.io.Serializable;
import java.util.List;

import com.myblog.basic.BasicServiceInter;
import com.myblog.domain.User;

public interface UserServiceInter extends BasicServiceInter{
	/**
	 * 
	 * @author Switch
	 * @function 通过ID获取用户
	 * @param id
	 * @return User对象
	 */ 
	public User getUserById(Serializable id);
	
	/**
	 * 
	 * @author Switch
	 * @function 检查是否为注册用户
	 * @param user
	 * @return 登录成功用户，如果不存在则返回null
	 */
	public User checkUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 检查是否为注册用户
	 * @param user
	 * @return 登录成功用户，如果不存在则返回null
	 */
	public User checkUserNotEncrypt(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 注册用户
	 * @param user
	 * @return false失败，true成功
	 */
	public boolean register(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 获取所有用户
	 * @return 用户List
	 */
	public List<User> getAllUser();
	
	/**
	 * 
	 * @author Switch
	 * @function 通过用户名获取用户信息
	 * @param userName
	 * @return User对象
	 */
	public User getUserByUserName(String userName);
	
	/**
	 * 
	 * @author Switch
	 * @function 获取所有的已发表博客的博客用户(分页)(按最后一篇文章逆序排序)
	 * @param pageNow
	 * @param pageSize
	 * @return User集合
	 */
	public List<User> getAllUserByPageOrderByTime(int pageNow, int pageSize);
	
	/**
	 * 
	 * @author Switch
	 * @function 获取所有已发表博客的博客用户总数
	 * @param pageSize
	 * @return 
	 */
	public int getWriterBlogUserPageCount(int pageSize);
	
}
