package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.models.Tweet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TweetController implements Initializable{
	@FXML
	private VBox tweetVBox;
	
    @FXML
    private ImageView tweetImage;

    @FXML
    private Text tweetText;

    @FXML
    private Label tweetTime;

    @FXML
    private Label tweetUserName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub 
	}
	
	public void setTweetData(Tweet tweet) {
		tweetImage.setImage(new Image(tweet.getUserImage()));
		tweetTime.setText(tweet.getTimeStamps());
		tweetUserName.setText("@"+tweet.getUserName());
		tweetText.setText(tweet.getTweetText());
		if (tweet.getTweetImage().size() != 0) {
			GridPane imageGridPane = new GridPane();
			List<String> tweetImageList = tweet.getTweetImage();
			for (int i = 0; i < tweetImageList.size(); i++) {
				Image img = new Image(tweetImageList.get(i));
				ImageView imgView = new ImageView(img);
				imgView.setFitWidth(200);
				imgView.setPreserveRatio(true);
				imageGridPane.add(imgView, i % 2, i / 2);
			}
			tweetVBox.getChildren().add(imageGridPane);
		}
	}
	
}
