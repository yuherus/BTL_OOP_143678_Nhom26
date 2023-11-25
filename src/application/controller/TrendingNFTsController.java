package application.controller;

import application.models.*;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TrendingNFTsController implements Initializable {
	private static final Stage FXMLLoader = null;

	@FXML
	TableView<Collection> home_tableCollectionNFTs;
	
	@FXML
	TableColumn<Collection, String> collectionColumn;
	
	@FXML
	TableColumn<Collection, Double> floorPriceColumn;
	
	@FXML
	TableColumn<Collection, Double> volumnColumn;
	
	@FXML
	TableColumn<Collection, Double> volumnChangeColumn;
	
	private ObservableList<Collection> collectionNFTsList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		collectionNFTsList = FXCollections.observableArrayList(
				//Nhap du lieu cua Trending NFTs
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1),
				new Collection("Bored Ape Yacht Club", 33.78, 438.4, -10.1)

				);
		collectionColumn.setCellValueFactory(new PropertyValueFactory<Collection, String>("collection"));
		floorPriceColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("floorPrice"));
		volumnColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumn"));
		volumnChangeColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumnChange"));
		home_tableCollectionNFTs.setItems(collectionNFTsList);
		home_tableCollectionNFTs.setOnMouseClicked(event -> {
			Collection selectedCollection = home_tableCollectionNFTs.getSelectionModel().getSelectedItem();
			if (selectedCollection != null) {
				
			}
		});

	}
	
	
}
