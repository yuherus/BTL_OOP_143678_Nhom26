package application.data;

import java.util.ArrayList;
import java.util.List;

import application.models.Blog;
import application.models.Collection;
import application.models.Post;

public interface PostData <T extends Post> {
	public ArrayList<T> getAllPosts();
	public ArrayList<T> getPostDataByKeyWord(String keyword, int limit);
	boolean containsKeyword(String[] baseDataArray, String keyword);
	public List<T> getRelatedPosts(Collection collection);
}
