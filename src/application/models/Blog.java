package application.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.scene.media.Track;

public class Blog extends Post {
	
	@JsonProperty("post_title")
    private String title;

    @JsonProperty("post_description")
    private String description;

    @JsonProperty("post_author")
    private String author;

    @JsonProperty("post_content")
    private String content;

    @JsonProperty("post_date")
    private String postDate;

    @JsonProperty("post_link")
    private String url;

    @JsonProperty("post_image")
    private List<String> imageUrl;
    
    @JsonProperty("type")
    private String type;

    @JsonProperty("post_length")
    private String length;

    @JsonProperty("post_tags")
    private String tags;
    
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
	public List<String> getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@JsonCreator
    public Blog(@JsonProperty("post_title") String title,
                @JsonProperty("post_description") String description,
                @JsonProperty("post_author") String author,
                @JsonProperty("post_content") String content,
                @JsonProperty("post_date") String postDate,
                @JsonProperty("post_link") String url,
                @JsonProperty("post_image") List<String> imageUrl,
                @JsonProperty("type") String type,
                @JsonProperty("post_length") String length,
                @JsonProperty("post_tags") String tags) {
        super();
        this.title = title;
        this.description = description;
        this.author = author;
        this.content = content;
        this.postDate = postDate;
        this.url = url;
        this.imageUrl = imageUrl;
        this.type = type;
        this.length = length;
        this.tags = tags;
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
