package com.myblog.web.struts.form;

import org.apache.struts.action.ActionForm;
public class ArticleForm extends ActionForm {
	private String userId;
	private String title;
	private String content;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}