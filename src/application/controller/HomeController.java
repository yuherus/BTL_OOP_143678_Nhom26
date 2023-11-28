package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.models.Blog;
import application.models.Collection;
import application.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class HomeController implements Initializable{
	@FXML
	private Button view_all_collections_btn;
	
	@FXML
    private ListView<String> tagList;
	
	@FXML
	private HBox newestBlog;
	
	private ObservableList<String> tagListData;

	public int text = 1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		view_all_collections_btn.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			ViewFactory viewFactory = new ViewFactory();
			appBorderPane.setCenter(viewFactory.getAllCollectionView());
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
			"#degods",
			"#degods",
			"#degods",
			"#degods"
		);
		tagList.setItems(tagListData);
		
		// set Action for tagList
		tagList.setOnMouseClicked(event ->{
			
		});
		//set màu nền cho tagList
		tagList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                setStyle("-fx-background-color: transparent;");
            }
        });
		
		ArrayList<Blog> newestBlogList =  new ArrayList<>();
		newestBlogList.add(new Blog("CR7 ForeverZone Box Utilities: What They Are and How to Claim","Learn more about what\u2019s inside the 50,000 free mystery boxes being given away as part of Binance and Cristiano Ronaldo's third NFT drop.","Rarible Blog","Content","November 12 2023","https://public.bnbstatic.com/image/cms/blog/20231009/50a7eb04-e152-47a0-aa53-d04fc92c1678.png","https://public.bnbstatic.com/image/cms/blog/20231009/50a7eb04-e152-47a0-aa53-d04fc92c1678.png"));
		newestBlogList.add(new Blog("CR7 ForeverZone Box Utilities: What They Are and How to Claim","Learn more about what\u2019s inside the 50,000 free mystery boxes being given away as part of Binance and Cristiano Ronaldo's third NFT drop.","Rarible Blog","Content","1/1/2023","https://rarible.com/blog/content/images/size/w600/2023/10/RaribleX-Press-Release--1-.png","https://public.bnbstatic.com/image/cms/blog/20231009/50a7eb04-e152-47a0-aa53-d04fc92c1678.png"));
		newestBlogList.add(new Blog("CR7 ForeverZone Box Utilities: What They Are and How to Claim","Learn more about what\u2019s inside the 50,000 free mystery boxes being given away as part of Binance and Cristiano Ronaldo's third NFT drop.","Rarible Blog","Content","1/1/2023","https://rarible.com/blog/content/images/size/w600/2023/10/RaribleX-Press-Release--1-.png","https://public.bnbstatic.com/image/cms/blog/20231009/50a7eb04-e152-47a0-aa53-d04fc92c1678.png"));
		newestBlogList.add(new Blog("CR7 ForeverZone Box Utilities: What They Are and How to Claim","Learn more about what\u2019s inside the 50,000 free mystery boxes being given away as part of Binance and Cristiano Ronaldo's third NFT drop.","Rarible Blog","Content","1/1/2023","https://rarible.com/blog/content/images/size/w600/2023/10/RaribleX-Press-Release--1-.png","https://public.bnbstatic.com/image/cms/blog/20231009/50a7eb04-e152-47a0-aa53-d04fc92c1678.png"));
		newestBlogList.add(new Blog("CR7 ForeverZone Box Utilities: What They Are and How to Claim","Learn more about what\u2019s inside the 50,000 free mystery boxes being given away as part of Binance and Cristiano Ronaldo's third NFT drop.","Content","Rarible Blog","1/1/2023","https://rarible.com/blog/content/images/size/w600/2023/10/RaribleX-Press-Release--1-.png","https://public.bnbstatic.com/image/cms/blog/20231009/50a7eb04-e152-47a0-aa53-d04fc92c1678.png"));

		
        ArrayList<VBox> vBoxList = creteVBoxWithData(newestBlogList);
            for (VBox vBox : vBoxList) {
				newestBlog.getChildren().add(vBox);
			}
	}
	
	private ArrayList<VBox> creteVBoxWithData(ArrayList<Blog> newestBlogList) {
		ArrayList<VBox> vBoxList = new ArrayList<>();
		for (Blog blog : newestBlogList) {
			VBox vBox = new VBox();
	        vBox.setPrefHeight(200.0);
	        vBox.setPrefWidth(100.0);
	        vBox.setSpacing(10.0);

	        ImageView imageView = new ImageView(new Image(blog.getImageUrl()));
	        imageView.setFitWidth(150.0);
	        imageView.setPreserveRatio(true);

	        Label titleLabel = new Label(blog.getTitle());
	        titleLabel.setPrefHeight(40.0);
	        titleLabel.setPrefWidth(148.0);
	        titleLabel.setWrapText(true);

	        Label dateLabel = new Label(blog.getPostDate());
	        dateLabel.setPrefWidth(150);
	        dateLabel.setAlignment(Pos.CENTER_RIGHT);
	  

	        Label authorLabel = new Label(blog.getAuthor());
	        FontAwesomeIconView userIcon = new FontAwesomeIconView();
	        userIcon.setGlyphName("USER");
	        authorLabel.setGraphic(userIcon);

	        vBox.getChildren().addAll(imageView, titleLabel, dateLabel, authorLabel);

	        vBox.setOpaqueInsets(new Insets(3.0));
	        vBoxList.add(vBox);
		}
		return vBoxList;
	}
	
	
}
