package com.myblog.service.inter;

import java.util.Set;

import com.myblog.basic.BasicServiceInter;
import com.myblog.domain.Bloginfo;
import com.myblog.domain.User;

public interface BlogInfoServiceInter extends BasicServiceInter {
	
	/**
	 * 
	 * @author Switch
	 * @function 通过User对象获取其Bloginfo对象
	 * @param user
	 * @return
	 */
	public Bloginfo getBlogInfoByUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 为User对象初始化BlogInfo对象
	 * @param user
	 * @return 本来应该是BlogInfo对象，但因为前期设计问题，使用集合
	 */
	public Set<Bloginfo> initBlogInfoByUser(User user);
}
