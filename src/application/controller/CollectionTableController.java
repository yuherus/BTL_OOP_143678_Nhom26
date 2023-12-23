package application.controller;

import application.models.*;
import application.views.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

import application.data.CollectionData;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CollectionTableController implements Initializable {
	private static final Stage FXMLLoader = null;
	
	@FXML
	TableView<Collection> collectionTable;
	
	@FXML
	TableColumn<Collection, Number> topColumn;
	
	
	@FXML
	TableColumn<Collection, String> collectionColumn;
	
	@FXML
	TableColumn<Collection, Double> floorPriceColumn;
	
	@FXML
	TableColumn<Collection, Double> volumeColumn;
	
	@FXML
	TableColumn<Collection, Double> volumeChangeColumn;
	
	@FXML
	private ComboBox<String> trendingComboBox;
	
	private ObservableList<String> comboBoxList = FXCollections.observableArrayList("1H", "1D", "7D", "30D");
	
	private ObservableList<Collection> collectionList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Đặt dữ liệu cho cột số thứ tự
        topColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(collectionTable.getItems().indexOf(column.getValue()) + 1));
		
	    collectionColumn.setCellValueFactory(new PropertyValueFactory<Collection, String>("name"));
		floorPriceColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("floorPrice"));
		volumeColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumeNative"));
		volumeChangeColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumeChange"));
//		collectionTable.setItems(collectionList);
		collectionTable.setOnMouseClicked(event -> {
			Collection selectedCollection = collectionTable.getSelectionModel().getSelectedItem();
			if (selectedCollection != null) {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
				appBorderPane.setCenter(Model.getInstance().getViewFactory().getCollectionView(selectedCollection));
			}
		});	
		
		

	} 	
		
	public void setAllCollectionList() {
		ObservableList<Collection> top1DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D1", 5000));
		ObservableList<Collection> top7DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D7", 5000));
		ObservableList<Collection> top30DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D30", 5000));
		ObservableList<Collection> top1HCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "H1", 5000));
		collectionList = top1DCollectionList;
		trendingComboBox.setItems(comboBoxList);
		trendingComboBox.setOnAction(event -> {
			trendingComboBox.setPromptText(trendingComboBox.getValue());

			if(trendingComboBox.getValue().equals("1H")) {
				collectionList = top1HCollectionList;
			}
			if(trendingComboBox.getValue().equals("1D")) {
				collectionList = top1DCollectionList;
			}

			if(trendingComboBox.getValue().equals("7D")) {
				collectionList = top7DCollectionList;
			}

			if(trendingComboBox.getValue().equals("30D")) {
				collectionList = top30DCollectionList;
			}
			collectionTable.setItems(collectionList);
		});
	}
	
	public void setTopCollectionList() {
		//Nhập dữ liệu của collectionTable
			ObservableList<Collection> top1DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D1", 20));
			ObservableList<Collection> top7DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D7", 20));
			ObservableList<Collection> top30DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D30", 20));
			ObservableList<Collection> top1HCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "H1", 20));
			collectionList = top1DCollectionList;
			trendingComboBox.setItems(comboBoxList);
			trendingComboBox.setOnAction(event -> {
				trendingComboBox.setPromptText(trendingComboBox.getValue());

				if(trendingComboBox.getValue().equals("1H")) {
					collectionList = top1HCollectionList;
				}
				if(trendingComboBox.getValue().equals("1D")) {
					collectionList = top1DCollectionList;
				}

				if(trendingComboBox.getValue().equals("7D")) {
					collectionList = top7DCollectionList;
				}

				if(trendingComboBox.getValue().equals("30D")) {
					collectionList = top30DCollectionList;
				}
				collectionTable.setItems(collectionList);
			});
	}
	
}
