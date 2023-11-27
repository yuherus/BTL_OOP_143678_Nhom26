package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Collection;
import application.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	@FXML
	private Button view_all_collections_btn;
	
	@FXML
    private ListView<String> tagList;
	
	private ObservableList<String> tagListData;

	public int text = 1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		view_all_collections_btn.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			ViewFactory viewFactory = new ViewFactory();
			appBorderPane.setCenter(viewFactory.getAllCollectionView());
		});
		
		//Nhập dữ liệu cho tagList
		tagListData = FXCollections.observableArrayList(
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods",
			"#degods"
		);
		tagList.setItems(tagListData);
		
		// set Action for tagList
		tagList.setOnMouseClicked(event ->{
			
		});
		//set màu nền cho tagList
		tagList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                setStyle("-fx-background-color: transparent;");
            }
        });
		
	}
	
	
}
