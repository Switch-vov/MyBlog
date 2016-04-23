package com.myblog.service.impl;

import java.util.List;

import com.myblog.basic.BasicService;
import com.myblog.domain.Critique;
import com.myblog.service.inter.CritiqueServiceInter;

public class CritiqueService extends BasicService implements CritiqueServiceInter{

	@Override
	public List<Critique> getAllCritiqueByArticleId(String articleId) throws Exception{
		String hql = "from Critique c where c.article.articleId=?";
		Object[] parameters = {Integer.parseInt(articleId)};
		return this.executeQuery(hql, parameters);
	}
	

}
