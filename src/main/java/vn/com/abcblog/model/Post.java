package vn.com.abcblog.model;

import java.io.Serializable;

public class Post extends Abstract implements Serializable {

	private static final long serialVersionUID = -716328861638451893L;

	private String thumbnail;

	private String title;

	private String description;

	private String content;

	private String postCode;

	private Boolean flagDelete;

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Boolean getFlagDelete() {
		return flagDelete;
	}

	public void setFlagDelete(Boolean flagDelete) {
		this.flagDelete = flagDelete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
