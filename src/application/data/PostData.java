package application.data;

import java.util.ArrayList;

import application.models.Post;

public interface PostData <T extends Post> {
	public ArrayList<T> getAllPosts();
	public ArrayList<T> getNewestPosts();
	ArrayList<T> getPostDataByKeyWord(String keyword, int limit);
	boolean containsKeyword(String[] baseDataArray, String keyword);
}
