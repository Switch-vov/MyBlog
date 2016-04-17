package com.myblog.domain;

import java.util.Date;

/**
 * Click entity. @author MyEclipse Persistence Tools
 */

public class Click implements java.io.Serializable {

	// Fields

	private Integer clickId;
	private Article article;
	private String ip;
	private Date date;

	// Constructors

	/** default constructor */
	public Click() {
	}

	/** minimal constructor */
	public Click(Article article) {
		this.article = article;
	}

	/** full constructor */
	public Click(Article article, String ip, Date date) {
		this.article = article;
		this.ip = ip;
		this.date = date;
	}

	// Property accessors

	public Integer getClickId() {
		return this.clickId;
	}

	public void setClickId(Integer clickId) {
		this.clickId = clickId;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}