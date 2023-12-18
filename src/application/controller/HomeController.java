package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.data.BlogData;
import application.data.CollectionData;
import application.models.Blog;
import application.models.Collection;
import application.models.Model;
import application.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class HomeController implements Initializable{
	@FXML
	private AnchorPane homePane;
	
	@FXML
	private Button viewAllCollections;
	
	@FXML
	private Button viewAllBlogs;
	
	@FXML
	private Button viewMoreTweets;
	
	@FXML
    private ListView<String> tagList;
	
	@FXML
	private HBox newestBlog;
	
	@FXML
	private AnchorPane collectionTablePane;
	
	private ObservableList<String> tagListData;
	
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
		
		ObservableList<Collection> top1DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D1", 20));
		ObservableList<Collection> top7DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D7", 20));
		ObservableList<Collection> top30DCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "D30", 20));
		ObservableList<Collection> top1HCollectionList =  FXCollections.observableArrayList(CollectionData.getTrendingCollections("ALL", "H1", 20));		
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
		
		viewAllCollections.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getAllCollectionView());
		});
		
		viewAllBlogs.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getAllBlogView());
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
			"#degods"
		);
		tagList.setItems(tagListData);
		
		tagList.setOnMouseClicked(event ->{
			
		});
		
		tagList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                setStyle("-fx-background-color: transparent;-fx-padding: 9px; -fx-border-color: #B4B4B4; -fx-border-width: 0.5px 0 0 0;");      
            }
        });
		
		ArrayList<Blog> newestBlogList = BlogData.getNewestBlogs();
		
		ArrayList<VBox> vBoxList = createVBoxWithData(newestBlogList);
		for (VBox vBox : vBoxList) {
			newestBlog.getChildren().add(vBox);
		}        
	}
	
	private ArrayList<VBox> createVBoxWithData(ArrayList<Blog> newestBlogList) {
		ArrayList<VBox> vBoxList = new ArrayList<>();
		for (Blog blog : newestBlogList) {
			VBox vBox = new VBox();
	        vBox.setPrefHeight(200.0);
	        vBox.setPrefWidth(100.0);
	        vBox.setSpacing(10.0);
	        
	        Image image = new Image(blog.getImageUrl());
	        ImageView imageView = new ImageView(image);
	        
	        imageView.setFitWidth(150.0);
	        imageView.setFitHeight(120.0);
	        imageView.setPreserveRatio(false);
	        
	        
	        Label titleLabel = new Label(blog.getTitle());
	        titleLabel.setPrefHeight(40.0);
	        titleLabel.setPrefWidth(148.0);
	        titleLabel.setWrapText(true);

	        Label dateLabel = new Label(blog.getLocalDate().toString());
	        dateLabel.setPrefWidth(150);
	        dateLabel.setAlignment(Pos.CENTER_RIGHT);
	  

	        Label authorLabel = new Label(blog.getAuthor());
	        FontAwesomeIconView userIcon = new FontAwesomeIconView();
	        userIcon.setGlyphName("USER");
	        authorLabel.setGraphic(userIcon);

	        vBox.getChildren().addAll(imageView, titleLabel, dateLabel, authorLabel);

	        vBox.setOpaqueInsets(new Insets(3.0));
	        vBox.setOnMouseClicked(event -> {
                BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
                appBorderPane.setCenter(Model.getInstance().getViewFactory().getBlogView(blog.getUrl()));
            });
	        vBoxList.add(vBox);
		}
		return vBoxList;
	}

}
