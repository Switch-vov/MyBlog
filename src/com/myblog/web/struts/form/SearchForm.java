package com.myblog.web.struts.form;

import org.apache.struts.action.ActionForm;
public class SearchForm extends ActionForm {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8190053684697524911L;
	private String searchKey;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
}