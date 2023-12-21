package application.controller;

import java.util.ArrayList;

import application.data.BlogData;
import application.models.Blog;
import application.models.Collection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


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
		
		ArrayList<Blog> relatedBlogList = BlogData.getBlogDataByKeyWord(collection.getName(), 4);
		for (Blog blog : relatedBlogList) {
            AnchorPane blogAnchorPane = createBlogAnchorPane(blog);
            relatedBlog.getChildren().add(blogAnchorPane);
        }
	}
	
	private AnchorPane createBlogAnchorPane(Blog blog) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("BlogPane"); 
        anchorPane.setPrefSize(343, 71);

        ImageView imageView = new ImageView(new Image(blog.getImageUrl()));
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
        return anchorPane;
    }
}
