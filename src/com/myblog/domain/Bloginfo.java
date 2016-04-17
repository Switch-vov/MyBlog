package com.myblog.domain;

/**
 * Bloginfo entity. @author MyEclipse Persistence Tools
 */

public class Bloginfo implements java.io.Serializable {

	// Fields

	private Integer blogId;
	private User user;
	private String blogtitle;
	private String idiograph;

	// Constructors

	/** default constructor */
	public Bloginfo() {
	}

	/** minimal constructor */
	public Bloginfo(User user) {
		this.user = user;
	}

	/** full constructor */
	public Bloginfo(User user, String blogtitle, String idiograph) {
		this.user = user;
		this.blogtitle = blogtitle;
		this.idiograph = idiograph;
	}

	// Property accessors

	public Integer getBlogId() {
		return this.blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBlogtitle() {
		return this.blogtitle;
	}

	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}

	public String getIdiograph() {
		return this.idiograph;
	}

	public void setIdiograph(String idiograph) {
		this.idiograph = idiograph;
	}

}