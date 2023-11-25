package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.views.ViewFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	@FXML
	private Button view_all_collections_btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		view_all_collections_btn.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			ViewFactory viewFactory = new ViewFactory();
			appBorderPane.setCenter(viewFactory.getAllCollectionView());
		});
		
	}
	
}
