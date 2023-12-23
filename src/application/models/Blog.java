package application.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Blog extends Post {
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
	
		public LocalDate getLocalDate() {
	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd, yyyy");

	        try {
	            return LocalDate.parse(postDate, formatter1);
	        } catch (Exception e1) {
	            try {
	                return LocalDate.parse(postDate, formatter2);
	            } catch (Exception e2) {
	                return null;
	            }
	        }
	    }
		@Override
		public String toString() {
			return "Blog [title=" + title + ", description=" + description + ", author=" + author + ", content="
					+ content + ", postDate=" + postDate + ", url=" + url + ", imageUrl=" + imageUrl + "]";
		}


	
}
