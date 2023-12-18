package application.views;

import application.controller.BlogController;
import application.controller.CollectionController;
import application.controller.SearchController;
import application.data.CollectionData;
import application.models.Collection;
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
	private AnchorPane blogView;
	
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
	
	public AnchorPane getBlogView(String url) {
		if (blogView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Blog.fxml"));
                blogView = loader.load();
                BlogController blogController = loader.getController();
                blogController.setBlogUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
	
	public void showAllCollectionView(Stage stage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/AllCollection.fxml"));
		changeScene(loader,stage);
	}
	
	public void changeScene(FXMLLoader loader, Stage stage) {
		Scene scene = null;
		try {
			scene = new Scene(loader.load());
		} catch (Exception e) {
			e.printStackTrace();
		}
		stage.setScene(scene);
		stage.setTitle("App");
		stage.show();
	}
	
	public void showCollection(Stage stage, Collection collection) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Collection.fxml"));
		Scene scene = null;
		try {
			scene = new Scene(loader.load());
			CollectionController collectionController = loader.getController();
			collectionController.setCollectionData(collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		stage.setScene(scene);
		stage.setTitle("Colection");
		stage.show();
	}
	
	public void showSearch(Stage stage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Search.fxml"));
		changeScene(loader,stage);
	}
	

}
