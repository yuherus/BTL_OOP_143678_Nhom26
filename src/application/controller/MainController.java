package application.controller;
//đây là class mẫu

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Model;
import application.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{

	@FXML
	private BorderPane app_border_pane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		app_border_pane.setCenter(Model.getInstance().getViewFactory().getHomeView());
	}

}
