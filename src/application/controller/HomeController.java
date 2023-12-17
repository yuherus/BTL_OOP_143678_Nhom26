package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.models.Blog;
import application.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
	
	private ObservableList<String> tagListData;

	public int text = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
		viewAllCollections.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			ViewFactory viewFactory = new ViewFactory();
			appBorderPane.setCenter(viewFactory.getAllCollectionView());
		});
		
		viewAllBlogs.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			ViewFactory viewFactory = new ViewFactory();
			appBorderPane.setCenter(viewFactory.getAllBlogView());
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
		
		// set Action for tagList
		tagList.setOnMouseClicked(event ->{
			
		});
		//set màu nền cho tagList
		tagList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                setStyle("-fx-background-color: transparent;-fx-padding: 9px; -fx-border-color: #B4B4B4; -fx-border-width: 0.5px 0 0 0;");      
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

	public static boolean containsKeyword(String[] baseDataArray, String keyword) {
		// Convert the keyword to lowercase for case-insensitive comparison
		String lowercasedKeyword = keyword.toLowerCase();

		// Iterate through each string in the array
		for (String baseData : baseDataArray) {
			// Convert the current base data to lowercase for comparison
			String lowercasedBaseData = baseData.toLowerCase();

			// Check if the lowercased base data contains the lowercased keyword
			if (lowercasedBaseData.contains(lowercasedKeyword)) {
				return true; // Return true if any string contains the keyword
			}
		}

		return false; // Return false if none of the strings contain the keyword
	}

//	!If limit is 0, will return all data, if limit !=0. Will try to return number you want from result found
	public List<JsonNode> getData(String keyword, int limit) {
		List<JsonNode> resultNodes = new ArrayList<>();
		File jsonFile = new File("src/resources/data/data.json");

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonFile);

			// Assuming your JSON file is an array, iterate over each object
			for (JsonNode objectNode : rootNode) {
				// Access individual fields of each object
				String postTitle = objectNode.get("post_title").asText();
				String postContent = objectNode.get("post_content").asText();
				String postDate = objectNode.get("post_date").asText();
				String postLink = objectNode.get("post_link").asText();
				String postDescription = objectNode.get("post_description").asText();

				String[] baseDataNeedCheck = {postTitle, postContent, postDescription};
				boolean checkCondition = containsKeyword(baseDataNeedCheck, keyword);

				if (checkCondition) {
					resultNodes.add(objectNode);

					// Check if the limit is reached
					if (limit > 0 && resultNodes.size() >= limit) {
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultNodes;
	}
	public static void main(String[] args) {
		HomeController test1 = new HomeController();
		List<JsonNode> resultNodes = test1.getData("", 0);

		// Process or return the resultNodes as needed
		for (JsonNode resultNode : resultNodes) {
//			resultNode.get + name of field you want to get and print data
			System.out.println(resultNode.get("post_title"));
		}
	}
}
