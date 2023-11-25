package application.controller;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class HeaderController {
	private static final Stage FXMLLoader = null;

	//Header
	@FXML
	private ToolBar home_menu_bar;
	
	@FXML
	private ComboBox<String> menu_box;
	
	public ToolBar getToolBar() {
		return home_menu_bar;
	}
	
	public ComboBox<String> getComboBox(){
		return menu_box;
	}


}
