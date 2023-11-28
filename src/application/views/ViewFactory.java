package application.views;

import application.controller.CollectionController;
import application.data.CollectionData;
import application.models.Collection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewFactory {
	private ScrollPane appView;
	private AnchorPane allCollectionView;
	
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
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/Home.fxml"));
				AnchorPane homeView = loader.load();
				return homeView;
	
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

}
