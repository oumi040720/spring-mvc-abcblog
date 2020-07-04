package vn.com.abcblog.model;

public class Tag extends Abstract {

	private Long postID;

	private Long categoryID;

	public Long getPostID() {
		return postID;
	}

	public void setPostID(Long postID) {
		this.postID = postID;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

}
