package com.myblog.service.inter;

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
}
