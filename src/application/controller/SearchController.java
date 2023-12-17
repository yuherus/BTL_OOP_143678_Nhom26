package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchController implements Initializable {
	@FXML
    private Label labelKeyWord;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 
	}
	
	public void setLabel(String textField) {
		labelKeyWord.setText("Search results for \""+textField+"\"");
	}

}
