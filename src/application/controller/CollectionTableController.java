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
	TableColumn<Collection, String> imageColumn;
	
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
		//Nhập dữ liệu của collectionTable
		collectionList = FXCollections.observableArrayList(CollectionData.getTrendingCollections("", "D1", 20));
		// Đặt dữ liệu cho cột số thứ tự
        topColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(collectionTable.getItems().indexOf(column.getValue()) + 1));
		// Đặt giá trị cho cột ảnh
	    imageColumn.setCellValueFactory(new PropertyValueFactory<Collection, String>("imageUrl"));

	    // Tạo một cột tùy chỉnh để hiển thị ảnh
	    imageColumn.setCellFactory(column -> {
	        return new TableCell<Collection, String>() {
	            private final ImageView imageView = new ImageView();
	            {
	                // Thiết lập kích thước hình ảnh (tùy chọn)
	                imageView.setFitWidth(25);
	                imageView.setFitHeight(25);
	            }

	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                if (item == null || empty) {
	                    // Nếu dữ liệu rỗng, đặt ô cell thành trống
	                    setGraphic(null);
	                } else {
	                    // Nếu có dữ liệu, tải và hiển thị ảnh từ đường dẫn URL
	                    Image image = new Image(item);
	                    imageView.setImage(image);
	                    setGraphic(imageView);
	                }
	            }
	        };
	    });
	    collectionColumn.setCellValueFactory(new PropertyValueFactory<Collection, String>("collection"));
		floorPriceColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("floorPrice"));
		volumeColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volume"));
		volumeChangeColumn.setCellValueFactory(new PropertyValueFactory<Collection, Double>("volumeChange"));
		collectionTable.setItems(collectionList);
		collectionTable.setOnMouseClicked(event -> {
			Collection selectedCollection = collectionTable.getSelectionModel().getSelectedItem();
			if (selectedCollection != null) {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
				ViewFactory viewFactory = new ViewFactory();
				appBorderPane.setCenter(viewFactory.getCollectionView(selectedCollection));
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
