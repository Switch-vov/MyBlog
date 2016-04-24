package com.myblog.web.struts.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7169615001083188763L;
	private String userName;
	private String password;
	private String repassword;
	private String nickName;
	private String question;
	private String answer;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}