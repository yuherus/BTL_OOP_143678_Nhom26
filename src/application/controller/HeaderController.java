package application.controller;


import java.net.URL;
import java.util.ResourceBundle;

import application.models.Model;
import application.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HeaderController implements Initializable {
	private static final Stage FXMLLoader = null;

	//Header
	@FXML
	private ToolBar home_menu_bar;
	
	@FXML
	private ComboBox<String> header_combobox;
	
	@FXML
	private AnchorPane homeBtn;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private TextField textFieldSearch;	

	ObservableList<String> list = FXCollections.observableArrayList("Update Data", "Help");
	
	public ToolBar getToolBar() {
		return home_menu_bar;
	}
	
	public ComboBox<String> getComboBox(){
		return header_combobox;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		header_combobox.setItems(list);
		homeBtn.setOnMouseClicked( event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getHomeView());
		});
	}
	
	public void searchChange(ActionEvent event) {
		String searchText = textFieldSearch.getText();
		BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
		ViewFactory viewFactory = new ViewFactory();
		AnchorPane searchView = viewFactory.getSearch(searchText);
		appBorderPane.setCenter(searchView);
		
	}

	public TextField getTextFieldSearch() {
		return textFieldSearch;
	}

	public void setTextFieldSearch(TextField textFieldSearch) {
		this.textFieldSearch = textFieldSearch;
	}


}
