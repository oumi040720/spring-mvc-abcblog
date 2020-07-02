package vn.com.abcblog.model;

import java.io.Serializable;

public class Category extends Abstract implements Serializable {

	private static final long serialVersionUID = -5707034094751404586L;

	private String categoryName;

	private String categoryCode;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
