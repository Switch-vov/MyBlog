package com.myblog.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private String nickName;
	private String question;
	private String answer;
	private Set articles = new HashSet(0);
	private Set bloginfos = new HashSet(0);
	private Set critiques = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String password, String question, String answer) {
		this.userName = userName;
		this.password = password;
		this.question = question;
		this.answer = answer;
	}

	/** full constructor */
	public User(String userName, String password, String nickName,
			String question, String answer, Set articles, Set bloginfos,
			Set critiques) {
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.question = question;
		this.answer = answer;
		this.articles = articles;
		this.bloginfos = bloginfos;
		this.critiques = critiques;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public Set getBloginfos() {
		return this.bloginfos;
	}

	public void setBloginfos(Set bloginfos) {
		this.bloginfos = bloginfos;
	}

	public Set getCritiques() {
		return this.critiques;
	}

	public void setCritiques(Set critiques) {
		this.critiques = critiques;
	}

}