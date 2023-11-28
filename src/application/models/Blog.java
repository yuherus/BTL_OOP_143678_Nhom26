package application.models;

public class Blog {
	private String title;
	private String description;
	private String author;
	private String content;
	private String postDate;
	private String url;
	private String imageUrl;
	
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
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Blog(String title, String description, String author, String content, String postDate, String url,
			String imageUrl) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.content = content;
		this.postDate = postDate;
		this.url = url;
		this.imageUrl = imageUrl;
	}
	
	
}
