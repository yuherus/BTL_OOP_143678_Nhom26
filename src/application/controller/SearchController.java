package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.data.BlogData;
import application.data.CollectionData;
import application.data.TweetData;
import application.models.Blog;
import application.models.Collection;
import application.models.Model;
import application.models.Tweet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchController implements Initializable {
	@FXML
    private Label labelKeyWord;
	
	private ArrayList<Blog> blogList;
	
	private ArrayList<Tweet> tweetList;
	
	@FXML
	private VBox tweetListVbox;
	
	@FXML
	private AnchorPane blogTab;
	
	@FXML
	private AnchorPane collectionTablePane;
	
	private ObservableList<Collection> collectionList;

	private ObservableList<String> comboBoxList = FXCollections.observableArrayList("1H", "1D", "7D", "30D");

	private ComboBox<String> trendingComboBox;
	
	private TableView<Collection> collectionTable;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setLabel(String textField) {
		
		for (Node node : collectionTablePane.getChildren()) {
            if (node instanceof TableView) {
                collectionTable = (TableView<Collection>) node;
            }
        }
		
		CollectionData collectionData = new CollectionData();
		ObservableList<Collection> top1DCollectionList =  FXCollections.observableArrayList(collectionData.getCollectionBySearchWord(textField, 1000, "D1"));
		ObservableList<Collection> top7DCollectionList =  FXCollections.observableArrayList(collectionData.getCollectionBySearchWord(textField, 1000, "D7"));
		ObservableList<Collection> top30DCollectionList =  FXCollections.observableArrayList(collectionData.getCollectionBySearchWord(textField, 1000, "D30"));
		ObservableList<Collection> top1HCollectionList =  FXCollections.observableArrayList(collectionData.getCollectionBySearchWord(textField, 1000, "H1"));		
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
		
		BlogData blogData = new BlogData();
		labelKeyWord.setText("Search results for \""+textField+"\"");
		blogList = blogData.getPostDataByKeyWord(textField, 20);
		setBlogData(blogList);
		
		TweetData tweetData = new TweetData();
		tweetList = tweetData.getPostDataByKeyWord(textField, 10);
		for (Tweet tweet : tweetList) {
			AnchorPane anchorPane = createTweetAnchorPane(tweet);
			tweetListVbox.getChildren().add(anchorPane);
		}
	}

	public void setBlogData(List<Blog> blogLists) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10); 
        gridPane.setVgap(10);

        int columnCount = 2; 

        for (int i = 0; i < blogLists.size(); i++) {
            Blog blog = blogLists.get(i);

            AnchorPane blogPane = createBlogPane(blog);

            int column = i % columnCount;
            int row = i / columnCount;
            gridPane.add(blogPane, column, row);
        }
        blogTab.getChildren().add(gridPane);
    }


    private AnchorPane createBlogPane(Blog blog) {
        // Tạo AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(400); 

        // Tạo ImageView
        ImageView imageView = new ImageView(new Image(blog.getImageUrl().get(0)));
        imageView.setFitHeight(100);
        imageView.setFitWidth(160);
        AnchorPane.setTopAnchor(imageView, 20.0);
        AnchorPane.setLeftAnchor(imageView, 20.0);

        // Tạo Label cho tiêu đề
        Label titleLabel = new Label(blog.getTitle());
        titleLabel.setPrefWidth(200); 
        AnchorPane.setTopAnchor(titleLabel, 20.0);
        AnchorPane.setLeftAnchor(titleLabel, 200.0);

        // Tạo Text cho mô tả
        Label descriptionText = new Label(blog.getContent());
        descriptionText.setPrefHeight(60.0);
        descriptionText.setPrefWidth(200);
        descriptionText.setWrapText(true);
        AnchorPane.setTopAnchor(descriptionText, 40.0);
        AnchorPane.setLeftAnchor(descriptionText, 200.0);

        // Tạo Text cho ngày đăng
        Text dateText = new Text(blog.getLocalDate().toString());
        AnchorPane.setTopAnchor(dateText, 100.0);
        AnchorPane.setLeftAnchor(dateText, 200.0);

        // Thêm các thành phần vào AnchorPane
        anchorPane.getChildren().addAll(imageView, titleLabel, descriptionText, dateText);
        anchorPane.setOnMouseClicked(event -> {
            BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
            appBorderPane.setCenter(Model.getInstance().getViewFactory().getBlogView(blog.getUrl()));
        });
        return anchorPane;
    }
    
    private AnchorPane createTweetAnchorPane(Tweet tweet) {
        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("pane_border_tweet");
        anchorPane.setPrefWidth(742.0);

        // Create ImageView
        ImageView imageView = new ImageView(new Image(tweet.getUserImage()));
        imageView.setFitHeight(52.0);
        imageView.setFitWidth(49.0);
        imageView.setLayoutX(70.0);
        imageView.setLayoutY(28.0);
        imageView.setPreserveRatio(true);

        // Create Labels and Text
        Label tweetNameLabel = new Label("@"+tweet.getUserName());
        tweetNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        tweetNameLabel.setLayoutX(128.0);
        tweetNameLabel.setLayoutY(26.0);

        Text textArea = new Text(tweet.getTweetText());
        textArea.setLayoutX(70.0);
        textArea.setLayoutY(102.0);
        textArea.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        textArea.setStrokeWidth(0.0);
        textArea.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        textArea.setWrappingWidth(604.296875);

        Label dateLabel = new Label(tweet.getTimeStamps());
        dateLabel.setAlignment(javafx.geometry.Pos.CENTER);
        dateLabel.setLayoutX(128.0);
        dateLabel.setLayoutY(52.0);
        dateLabel.setPrefHeight(22.0);
        dateLabel.setPrefWidth(102.0);
        dateLabel.setTextFill(javafx.scene.paint.Color.valueOf("#0000009c"));

        // Add children to AnchorPane
        anchorPane.getChildren().addAll(imageView, tweetNameLabel, textArea, dateLabel);
        
        // Set padding
        anchorPane.setPadding(new Insets(20.0, 0, 20.0, 0));
        anchorPane.setOnMouseClicked(event -> {
		        	Stage popupStage = new Stage();
		            popupStage.initModality(Modality.APPLICATION_MODAL);
		            popupStage.setTitle("Tweet");

		            // Set the FXML content to the pop-up stage
		            Scene popupScene = new Scene(Model.getInstance().getViewFactory().getTweetView(tweet), 800, 500);
		            popupStage.setScene(popupScene);

		            // Show the pop-up stage
		            popupStage.showAndWait();
		});
        return anchorPane;
    }
}	
