package com.myblog.domain;

/**
 * Critique entity. @author MyEclipse Persistence Tools
 */

public class Critique implements java.io.Serializable {

	// Fields

	private Integer critiqueId;
	private User user;
	private Article article;
	private String content;

	// Constructors

	/** default constructor */
	public Critique() {
	}

	/** full constructor */
	public Critique(User user, Article article, String content) {
		this.user = user;
		this.article = article;
		this.content = content;
	}

	// Property accessors

	public Integer getCritiqueId() {
		return this.critiqueId;
	}

	public void setCritiqueId(Integer critiqueId) {
		this.critiqueId = critiqueId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}