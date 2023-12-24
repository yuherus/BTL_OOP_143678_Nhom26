package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.data.BlogData;
import application.data.TweetData;
import application.models.Blog;
import application.models.Collection;
import application.models.Model;
import application.models.Tweet;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CollectionController extends Controller{
	@FXML
	private Label collectionName;
	
	@FXML
	private Text collectionDescription;
	
	@FXML
	private ImageView collectionBackgroundUrl;
	
	@FXML
	private ImageView collectionImgUrl;
	
	@FXML
	private Text floorPrice;
	
	@FXML
	private Text volume;
	
	@FXML
	private Text address;
	
	@FXML
	private Text blockchain;
	
	@FXML
	private VBox relatedBlog;
	
	@FXML
	private VBox relatedTweet;
	
	public void setCollectionData(Collection collection){
		collectionName.setText(collection.getName());
		collectionDescription.setText(collection.getDescription());
		collectionImgUrl.setImage(new Image(collection.getImageUrl()));
		floorPrice.setText(Double.toString(collection.getFloorPrice())+" "+collection.getFloorPriceUnit());
		volume.setText(Double.toString(collection.getVolumeNative())+" "+collection.getVolumeNativeUnit());
		blockchain.setText(collection.getBlockchain());
		address.setText(collection.getId().substring(collection.getId().indexOf(":")+1));
		
		BlogData blogData = new BlogData();
		List<Blog> relatedBlogList = blogData.getRelatedPosts(collection);
		if (relatedBlogList.size() < 4) {
			relatedBlogList.addAll(blogData.getPostDataByKeyWord(collection.getBlockchain(), 4 - relatedBlogList.size()));
		}
		for (Blog blog : relatedBlogList) {
            AnchorPane blogAnchorPane = createBlogAnchorPane(blog);
            relatedBlog.getChildren().add(blogAnchorPane);
        }
		
		TweetData tweetData = new TweetData();
		List<Tweet> relatedTweetList = tweetData.getRelatedPosts(collection);
		if (relatedTweetList.size() < 4) {
			relatedTweetList.addAll(tweetData.getPostDataByKeyWord(collection.getBlockchain(), 4 - relatedTweetList.size()));
		}
		for (Tweet tweet : relatedTweetList) {
            AnchorPane tweetAnchorPane = createTweetAnchorPane(tweet);
            relatedTweet.getChildren().add(tweetAnchorPane);
        }
	}
	
	private AnchorPane createBlogAnchorPane(Blog blog) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("BlogPane"); 
        anchorPane.setPrefSize(343, 71);

        ImageView imageView = new ImageView(new Image(blog.getImageUrl().get(0)));
        imageView.setFitHeight(43);
        imageView.setFitWidth(50);
        imageView.setLayoutX(12);
        imageView.setLayoutY(12);

        Label titleText = new Label(blog.getTitle());
        titleText.setLayoutX(87);
        titleText.setLayoutY(10);
        titleText.setPrefWidth(160);
        titleText.setWrapText(true);
        titleText.setPrefHeight(20);

        Label descriptionText = new Label(blog.getContent());
        descriptionText.setLayoutX(87);
        descriptionText.setLayoutY(30);
        descriptionText.setWrapText(true);
        descriptionText.setPrefWidth(240);
        descriptionText.setPrefHeight(40);

        Label dateText = new Label(blog.getLocalDate().toString());
        dateText.setLayoutX(260);
        dateText.setLayoutY(10);
        dateText.setPrefWidth(70);

        anchorPane.getChildren().addAll(imageView, titleText, descriptionText, dateText);
        anchorPane.setOnMouseClicked(event -> {
                BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
                appBorderPane.setCenter(Model.getInstance().getViewFactory().getBlogView(blog.getUrl()));
            });
        return anchorPane;
    }
	
	private AnchorPane createTweetAnchorPane(Tweet tweet) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("BlogPane"); 
        anchorPane.setPrefWidth(343.0);
        anchorPane.setPrefHeight(71.0);

        // Create ImageView
        ImageView imageView = new ImageView(new Image(tweet.getUserImage()));
        imageView.setFitHeight(43.0);
        imageView.setFitWidth(50.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(12.0);
        imageView.setPreserveRatio(true);

        // Create Labels
        Label usernameLabel = new Label("@"+tweet.getUserName());
        usernameLabel.setLayoutX(80.0);
        usernameLabel.setLayoutY(7.0);

        Label nameLabel = new Label(tweet.getUser());
        nameLabel.setLayoutX(81.0);
        nameLabel.setLayoutY(25.0);

        Label textLabel = new Label(tweet.getTweetText());
        textLabel.setLayoutX(80.0);
        textLabel.setLayoutY(42.0);
        textLabel.setPrefHeight(20.0);
        textLabel.setPrefWidth(245.0);

        // Add children to AnchorPane
        anchorPane.getChildren().addAll(imageView, usernameLabel, nameLabel, textLabel);
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
