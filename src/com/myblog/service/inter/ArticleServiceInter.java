package com.myblog.service.inter;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.myblog.basic.BasicServiceInter;
import com.myblog.domain.Article;
import com.myblog.domain.User;

public interface ArticleServiceInter extends BasicServiceInter{
	/**
	 * 
	 * @author Switch
	 * @function 通过用户获取他的所有博客(无序的)
	 * @param user
	 * @return article集合
	 */
	public Set<Article> getArticlesByUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 通过用户获取博客总数
	 * @param user
	 * @return article总数
	 */
	public Integer getArticleCountByUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 通过用户获取博文总点击数
	 * @param user
	 * @return 总点击数
	 */
	public Integer getClickTotalCountByUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 通过用户获取博文总评论数
	 * @param user
	 * @return 总评论数
	 */
	public Integer getCritiqueTotalCountByUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 通过用户获取他的博客(分页)(按时间排序)
	 * @param user
	 * @param pageNow
	 * @param pageSize
	 * @return Article集合
	 */
	public List<Article> getArticlesByUserByPageOrderByTime(User user, int pageNow, int pageSize);
	
	/**
	 * 
	 * @author Switch
	 * @function 获取博客总页数
	 * @param user
	 * @param pageSize
	 * @return 总页数
	 */
	public int getArticlePageCount(User user, int pageSize);
	
	/**
	 * 
	 * @author Switch
	 * @function 获取最新更新的博客文章时间
	 * @param user
	 * @return Date对象
	 */
	public Date getLastestUpdateDateByUser(User user);
	
	/**
	 * 
	 * @author Switch
	 * @function 通过文章ID获取用户
	 * @param articleId
	 * @return User对象
	 */
	public User getUserByArticleId(String articleId);
	
	/**
	 * 
	 * @author Switch
	 * @function 通过用户ID,标题,内容保存文章
	 * @param userId
	 * @param title
	 * @param content
	 * @return 文章ID
	 */
	public String saveArticleByUserIdAndTitleAndContent(String userId, String title, String content)  throws Exception;
	
	/**
	 * 
	 * @author Switch
	 * @function 获取包含关键字的文章
	 * @param searchKey
	 * @return 文章List
	 */
	public List<Article> getArticleBySearchKeyByPage(String searchKey, int pageNow, int pageSize);
	
	/**
	 * 
	 * @author Switch
	 * @function 获取包含关键字的文章的页数
	 * @param searchKey
	 * @param pageSize
	 * @return
	 */
	public int getArticlePageCountBySearchKey(String searchKey, int pageSize);
}
