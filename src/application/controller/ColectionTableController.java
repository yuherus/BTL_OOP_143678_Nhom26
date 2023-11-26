package application.controller;

import application.models.*;
import application.views.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.data.CollectionData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ColectionTableController implements Initializable {
	private static final Stage FXMLLoader = null;
	
	@FXML
	TableView<Collection> collectionTable;
	
	@FXML
	TableColumn<Collection, String> collectionColumn;
	
	@FXML
	TableColumn<Collection, Double> floorPriceColumn;
	
	@FXML
	TableColumn<Collection, Double> volumnColumn;
	
	@FXML
	TableColumn<Collection, Double> volumnChangeColumn;
	
	@FXML
	private ComboBox<String> trendingComboBox;
	
	private ObservableList<String> comboBoxList = FXCollections.observableArrayList("1H", "1D", "7D", "30D");
	
	private ObservableList<Collection> collectionList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Nhập dữ liệu của collectionTable
		collectionList = FXCollections.observableArrayList(
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1)
		);
		
		collectionColumn.setCellValueFactory(new PropertyValueFactory<Collection, String>("collection"));
		floorPriceColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("floorPrice"));
		volumnColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumn"));
		volumnChangeColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumnChange"));
		collectionTable.setItems(collectionList);
		collectionTable.setOnMouseClicked(event -> {
			Collection selectedCollection = collectionTable.getSelectionModel().getSelectedItem();
			if (selectedCollection != null) {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				ViewFactory viewFactory = new ViewFactory();
				viewFactory.showCollection(stage);
			}
		});	
		
		//set màu nền cho collectionTable 
//		 collectionTable.setRowFactory(tv -> {
//	            TableRow<String> row = new TableRow<>();
//	            row.setStyle("-fx-background-color: transparent;");
//	            return row;
//	        });
		
//		collectionTable.setRowFactory(tv -> new TableRow<>() {
//            @Override
//            protected void updateRow(String item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(item);
//                setStyle("-fx-background-color: transparent;");
//            }
//        });
		
		// set action to trendingComboBox
		trendingComboBox.setItems(comboBoxList);
		trendingComboBox.setOnAction(event -> {
			trendingComboBox.setPromptText(trendingComboBox.getValue());
			// thay đổi collectionTable theo trending
			  //Trending 1H
			if(trendingComboBox.getValue().equals("1H")) {
				collectionList = FXCollections.observableArrayList(
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1)
				);
			}
			
			  //Trending 1D
			if(trendingComboBox.getValue().equals("1D")) {
				collectionList = FXCollections.observableArrayList(
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1)
				);
			}
			
			  //Trending 7D
			if(trendingComboBox.getValue().equals("7D")) {
				collectionList = FXCollections.observableArrayList(
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1)
				);
			}
			
			  //Trending 30D
			if(trendingComboBox.getValue().equals("30D")) {
				collectionList = FXCollections.observableArrayList(
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
						new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1)
				);
			}
			collectionTable.setItems(collectionList);
		});

	} 	
		
	
	
}
