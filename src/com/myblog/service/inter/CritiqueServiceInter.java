package com.myblog.service.inter;

import java.util.List;

import com.myblog.basic.BasicServiceInter;
import com.myblog.domain.Critique;

public interface CritiqueServiceInter extends BasicServiceInter{
	
	/**
	 * 
	 * @author Switch
	 * @function 通过文章ID获取该文章所有评论
	 * @param articleId
	 * @return 评论List
	 */
	public List<Critique> getAllCritiqueByArticleId(String articleId) throws Exception;
}
