package application.views;

import application.controller.BlogController;
import application.controller.CollectionController;
import application.controller.SearchController;
import application.controller.TweetController;
import application.data.CollectionData;
import application.models.Collection;
import application.models.Tweet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewFactory {
	private ScrollPane appView;
	private AnchorPane allCollectionView;
	private AnchorPane homeView;
	private VBox allBLogView;
	private AnchorPane allTweetView;	
	
	public ViewFactory() {}
	
	public ScrollPane getAppView() {
		if(appView == null) {
			try {
				appView = new FXMLLoader(getClass().getResource("/resources/FXML/App.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return appView;
	}
	
	public AnchorPane getAllCollectionView() {
		if (allCollectionView == null) {
			try {
				allCollectionView = new FXMLLoader(getClass().getResource("/resources/FXML/AllCollection.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return allCollectionView;
	}
	
	public AnchorPane getCollectionView(Collection collection) {
		AnchorPane collectionView = null;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Collection.fxml"));
				collectionView = loader.load();
				CollectionController collectionController = loader.getController();
				collectionController.setCollectionData(collection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return collectionView;
	}
	
	public AnchorPane getHomeView() {
		if (homeView == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Home.fxml"));
				homeView = loader.load();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return homeView;
	}
	
	public VBox getAllBlogView() {
		if (allBLogView == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Bloglist.fxml"));
				allBLogView = loader.load();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allBLogView;
	}
	
	public AnchorPane getAllTweetView() {
		if (allTweetView == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/TweetList.fxml"));
				allTweetView = loader.load();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allTweetView;
	}
	
	public AnchorPane getBlogView(String url) {
		AnchorPane blogView = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Blog.fxml"));
                blogView = loader.load();
                BlogController blogController = loader.getController();
                blogController.setBlogUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return blogView;
    }
	
	public AnchorPane getSearch(String textField) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Search.fxml"));
			AnchorPane searchView = loader.load();
			SearchController searchController = loader.getController();
			searchController.setLabel(textField);
			return searchView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AnchorPane getTweetView(Tweet tweet) {
		AnchorPane tweetView = null;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Tweet.fxml"));
				tweetView = loader.load();
				TweetController tweetController = loader.getController();
				tweetController.setTweetData(tweet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return tweetView;
	}

}
