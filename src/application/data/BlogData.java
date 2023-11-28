package application.data;

import java.util.ArrayList;
import java.util.List;

import application.models.Blog;
import application.models.Collection;

public class BlogData {
	public static List<Blog> getNewstBlogs() {
		return null;
	}
	
	public static List<Blog> getAllBlogs() {
		return null;
	}
	
	public static List<Blog> getRelatedBlogs(Collection collection) {
		List<Blog> relatedBlogs = new ArrayList<>();
		relatedBlogs = searchBlogs(collection.getCollection());
		return relatedBlogs.subList(0, Math.min(relatedBlogs.size(), 5));
	}
	
	public static List<Blog> searchBlogs(String keyword) {
		return null;
	}
}
