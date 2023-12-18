package application.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Blog;
import application.models.Collection;

public class BlogData {
	public static void main(String[] args) {	
		ArrayList<Blog> blogList = getAllBlogs();
		ArrayList<Blog> blogs = getNewestBlogs();
		for (Blog blog : blogs) {
			System.out.println(blog.getLocalDate());
		}
	}
	
	public static ArrayList<Blog> getNewestBlogs() {
		ArrayList<Blog> blogList = getAllBlogs();
		Collections.sort(blogList, Comparator.comparing(Blog::getLocalDate));
		ArrayList<Blog> newestBlogs = new ArrayList<>();
		for (int i = blogList.size()-1 ; i >= blogList.size() - 5; i--) {
			newestBlogs.add(blogList.get(i));
		}
		return newestBlogs;
	}
	
	public static ArrayList<Blog> getAllBlogs() {
		ArrayList<Blog> blogList = new ArrayList<>();
		String fileName = "./src/resources/data/blog.json";
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode blogNode = elements.next();
                String postTitle = blogNode.get("post_title").asText();
				String postContent = blogNode.get("post_content").asText();
				String postDate = blogNode.get("post_date").asText();
				String postLink = blogNode.get("post_link").asText();
				String postDescription = blogNode.get("post_description").toString();
				String postImage;
				if (blogNode.get("post_image").get(0) != null) {
				postImage = blogNode.get("post_image").get(0).asText();
				} else {
				postImage = "/resources/images/Hinh-Nen-Trang-10.jpg";
				}
				String postAuthor = blogNode.get("post_author").asText();

				blogList.add(new Blog(postTitle, postDescription, postAuthor, postContent, postDate, postLink, postImage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return blogList;
	}
	
	public static List<Blog> getRelatedBlogs(Collection collection) {
		List<Blog> relatedBlogs = new ArrayList<>();
		relatedBlogs = getBlogDataByKeyWord(collection.getName(),4);
		return relatedBlogs;
	}
	
	public static boolean containsKeyword(String[] baseDataArray, String keyword) {
		// Convert the keyword to lowercase for case-insensitive comparison
		String lowercasedKeyword = keyword.toLowerCase();

		// Iterate through each string in the array
		for (String baseData : baseDataArray) {
			// Convert the current base data to lowercase for comparison
			String lowercasedBaseData = baseData.toLowerCase();

			// Check if the lowercased base data contains the lowercased keyword
			if (lowercasedBaseData.contains(lowercasedKeyword)) {
				return true; // Return true if any string contains the keyword
			}
		}

		return false; // Return false if none of the strings contain the keyword
	}

//	!If limit is 0, will return all data, if limit !=0. Will try to return number you want from result found
	public static List<Blog> getBlogDataByKeyWord(String keyword, int limit) {
		List<Blog> blogList = new ArrayList<>();
		File jsonFile = new File("./src/resources/data/blog.json");

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonFile);

			// Assuming your JSON file is an array, iterate over each object
			for (JsonNode objectNode : rootNode) {
				// Access individual fields of each object
				String postTitle = objectNode.get("post_title").asText();
				String postContent = objectNode.get("post_content").asText();
				String postDate = objectNode.get("post_date").asText();
				String postLink = objectNode.get("post_link").asText();
				String postDescription = objectNode.get("post_description").toString();
				String postImage;
				if (objectNode.get("post_image").get(0) != null) {
				postImage = objectNode.get("post_image").get(0).asText();
				} else {
				postImage = "/resources/images/Hinh-Nen-Trang-10.jpg";
				}
				String postAuthor = objectNode.get("post_author").asText();

				String[] baseDataNeedCheck = {postTitle, postContent, postDescription};
				boolean checkCondition = containsKeyword(baseDataNeedCheck, keyword);

				if (checkCondition) {
					blogList.add(new Blog(postTitle, postDescription, postAuthor, postContent, postDate, postLink, postImage));
					if (limit > 0 && blogList.size() >= limit) {
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return blogList;
	}
}
