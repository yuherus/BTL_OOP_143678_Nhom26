package application.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class HeaderController implements Initializable {
	private static final Stage FXMLLoader = null;

	//Header
	@FXML
	private ToolBar home_menu_bar;
	
	@FXML
	private ComboBox<String> header_combobox;
	
	ObservableList<String> list = FXCollections.observableArrayList("NFTs", "Trending", "All ìnformation");
	
	public ToolBar getToolBar() {
		return home_menu_bar;
	}
	
	public ComboBox<String> getComboBox(){
		return header_combobox;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		header_combobox.setItems(list);
	}


}
