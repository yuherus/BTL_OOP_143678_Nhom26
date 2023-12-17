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
	Text address;
	
	@FXML
	Text blockchain;
	
	public void setCollectionData(Collection collection){
		collectionName.setText(collection.getName());
		collectionDescription.setText(collection.getDescription());
		collectionImgUrl.setImage(new Image(collection.getImageUrl()));
		floorPrice.setText(Double.toString(collection.getFloorPrice())+" "+collection.getFloorPriceUnit());
		volume.setText(Double.toString(collection.getVolumeNative())+" "+collection.getVolumeNativeUnit());
		blockchain.setText(collection.getBlockchain());
		address.setText(collection.getId().substring(collection.getId().indexOf(":")+1));
	}
}
