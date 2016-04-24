package com.myblog.service.inter;

import java.io.Serializable;

import com.myblog.basic.BasicServiceInter;

public interface ClickServiceInter extends BasicServiceInter{
	
	public Serializable saveClickRecordByArticleIdAndIPAddress(String ariticleId, String ip);

}
