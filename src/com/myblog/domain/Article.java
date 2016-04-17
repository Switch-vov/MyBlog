package com.myblog.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article implements java.io.Serializable {

	// Fields

	private Integer articleId;
	private User user;
	private String title;
	private String content;
	private Date date;
	private Integer hasread;
	private Set critiques = new HashSet(0);
	private Set clicks = new HashSet(0);

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(User user, String title, String content) {
		this.user = user;
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public Article(User user, String title, String content, Date date,
			Integer hasread, Set critiques, Set clicks) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.date = date;
		this.hasread = hasread;
		this.critiques = critiques;
		this.clicks = clicks;
	}

	// Property accessors

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getHasread() {
		return this.hasread;
	}

	public void setHasread(Integer hasread) {
		this.hasread = hasread;
	}

	public Set getCritiques() {
		return this.critiques;
	}

	public void setCritiques(Set critiques) {
		this.critiques = critiques;
	}

	public Set getClicks() {
		return this.clicks;
	}

	public void setClicks(Set clicks) {
		this.clicks = clicks;
	}

}