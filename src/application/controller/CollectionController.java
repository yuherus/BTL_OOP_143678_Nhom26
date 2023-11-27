package application.controller;

import application.models.Collection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class CollectionController extends Controller{
	@FXML
	Label collectionName;
	
	@FXML
	Text collectionDescription;
	
	@FXML
	ImageView collectionBackgroundUrl;
	
	@FXML
	ImageView collectionImgUrl;
	
	@FXML
	Text floorPrice;
	
	@FXML
	Text volume;
	
	@FXML
	Text item;
	
	@FXML
	Text blockchain;
	
	public void setCollectionData(Collection collection){
		collectionName.setText(collection.getCollection());
		collectionDescription.setText(collection.getDescription());
		collectionImgUrl.setImage(new Image(collection.getImageUrl()));
		floorPrice.setText(Double.toString(collection.getFloorPrice()));
		volume.setText(Double.toString(collection.getVolume()));
		item.setText(Integer.toString(collection.getItem()));
		blockchain.setText(collection.getBlockchain());
	}
}
