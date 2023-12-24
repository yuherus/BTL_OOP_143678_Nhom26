package application.data;

import java.util.ArrayList;

import application.models.Content;

public interface Data <T extends Content> {
	public ArrayList<T> getAllPosts();
	public ArrayList<T> getPostDataByKeyWord(String keyword, int limit);
}
