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

public class BlogData extends JsonToData<Blog> implements PostData<Blog> {

	public ArrayList<Blog> getNewestPosts() {
		ArrayList<Blog> blogList = this.getAllPosts();
		Collections.sort(blogList, Comparator.comparing(Blog::getLocalDate));
		ArrayList<Blog> newestBlogs = new ArrayList<>();
		for (int i = blogList.size() - 1; i >= blogList.size() - 5; i--) {
			newestBlogs.add(blogList.get(i));
		}
		return newestBlogs;
	}

	@Override
	public ArrayList<Blog> getAllPosts() {
		String fileName = "./src/resources/data/blog.json";
		getDataToObject(fileName, Blog.class);
		return getDataArrayList();
	}

	@Override
	public List<Blog> getRelatedPosts(Collection collection) {
		List<Blog> relatedBlogs = new ArrayList<>();
		relatedBlogs = this.getPostDataByKeyWord(collection.getName(), 4);
		return relatedBlogs;
	}

	@Override
	public boolean containsKeyword(String[] baseDataArray, String keyword) {
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

	@Override
	public ArrayList<Blog> getPostDataByKeyWord(String keyword, int limit) {
		ArrayList<Blog> blogList = getAllPosts();
		ArrayList<Blog> blogSearchList = new ArrayList<>();
		for (Blog blog : blogList) {

			String[] baseDataNeedCheck = { blog.getTitle(), blog.getContent(), blog.getDescription() };
			boolean checkCondition = containsKeyword(baseDataNeedCheck, keyword);

			if (checkCondition) {
				blogSearchList.add(blog);
				if (limit > 0 && blogSearchList.size() >= limit) {
					break;
				}
			}
		}

		return blogSearchList;
	}
}
