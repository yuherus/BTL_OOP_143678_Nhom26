package application.views;

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
				allCollectionView = new FXMLLoader(getClass().getResource("/resources/FXML/AllNFT.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return allCollectionView;
	}
	
	public void showAllCollectionView(Stage stage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/AllNFT.fxml"));
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
	

}
