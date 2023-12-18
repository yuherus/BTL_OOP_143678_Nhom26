package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.data.CollectionData;
import application.models.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AllCollectionController implements Initializable{
	@FXML
	private AnchorPane collectionTablePane;
	
	private ObservableList<Collection> collectionList;

	private ObservableList<String> comboBoxList = FXCollections.observableArrayList("1H", "1D", "7D", "30D");

	private ComboBox<String> trendingComboBox;
	
	private TableView<Collection> collectionTable;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (Node node : collectionTablePane.getChildren()) {
            if (node instanceof TableView) {
                collectionTable = (TableView<Collection>) node;
            }
        }
		
		ObservableList<Collection> top1DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D1", 5000));
		ObservableList<Collection> top7DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D7", 5000));
		ObservableList<Collection> top30DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D30", 5000));
		ObservableList<Collection> top1HCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "H1", 5000));		
		collectionList = top1DCollectionList;
		collectionTable.setItems(collectionList);
		
		for (Node node : collectionTablePane.getChildren()) {
            if (node instanceof ComboBox) {
                trendingComboBox = (ComboBox<String>) node;
            }
        }
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
