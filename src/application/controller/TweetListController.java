package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.data.TweetData;
import application.models.Model;
import application.models.Tweet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TweetListController implements Initializable{
	
	@FXML
	private VBox tweetListVbox;
	
	@FXML
	private Button viewMoreBtn;
	
	private ArrayList<Tweet> tweetList;
	
	private int startIndex = 10;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tweetList = TweetData.getAllTweets();
		System.out.println(tweetList.size());
		for (int i = 0; i < 10; i++) {
			AnchorPane anchorPane = createTweetAnchorPane(tweetList.get(i));
			tweetListVbox.getChildren().add(anchorPane);
		}
		viewMoreBtn.setOnAction(event -> {
			 for (int i = startIndex; i < Math.min(startIndex + 10, tweetList.size()); i++) {
		            AnchorPane anchorPane = createTweetAnchorPane(tweetList.get(i));
		            tweetListVbox.getChildren().add(anchorPane);
		        }
		      startIndex += 10; 
		});
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
