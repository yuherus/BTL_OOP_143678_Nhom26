package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.data.BlogData;
import application.models.Blog;
import application.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class BlogListController implements Initializable{
	@FXML
	private VBox blogListPane;
	
	@FXML
	private Button viewMoreBtn;
	
	private ArrayList<Blog> blogList;
	
	private int startIndex = 6;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		BlogData blogData = new BlogData();
		blogList = blogData.getAllPosts();
		ImageView imageView = new ImageView(new Image(blogList.get(0).getImageUrl().get(0)));
        imageView.setFitHeight(340.0);
        imageView.setFitWidth(698.0);
        imageView.setLayoutX(230.0);
        imageView.setLayoutY(63.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Tạo Label cho tiêu đề
        Label titleLabel = new Label(blogList.get(0).getTitle());
        titleLabel.setLayoutX(200.0);
        titleLabel.setLayoutY(418.0);
        titleLabel.setPrefHeight(55.0);
        titleLabel.setPrefWidth(663.0);
        titleLabel.setStyle("-fx-font-size: 32.0;");

        // Tạo Text cho mô tả
        Text descriptionText = new Text(blogList.get(0).getContent());
        descriptionText.setLayoutX(200.0);
        descriptionText.setLayoutY(495.0);
        descriptionText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        descriptionText.setStrokeWidth(0.0);
        descriptionText.setWrappingWidth(655);
        descriptionText.setStyle("-fx-font-size: 20.0;");

        // Tạo Text cho ngày đăng
        Text dateText = new Text(blogList.get(0).getLocalDate().toString());
        dateText.setFill(javafx.scene.paint.Color.valueOf("#0000009b"));
        dateText.setLayoutX(200.0);
        dateText.setLayoutY(559.0);
        dateText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        dateText.setStrokeWidth(0.0);
        dateText.setWrappingWidth(125);

        // Tạo Line
        Line line = new Line();
        line.setStartX(-80.0);
        line.setEndX(800.0);
        line.setLayoutX(170.0);
        line.setLayoutY(582.0);
        
        AnchorPane firstBlog = new AnchorPane();
        // Thêm các thành phần vào AnchorPane
        firstBlog.getChildren().addAll(imageView, titleLabel, descriptionText, dateText, line);
        firstBlog.setOnMouseClicked(event -> {
            BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
            appBorderPane.setCenter(Model.getInstance().getViewFactory().getBlogView(blogList.get(0).getUrl()));
        });
        blogListPane.getChildren().add(firstBlog);
        blogList.remove(0);
        setBlogData(blogList);
	}
	
	 public void setBlogData(List<Blog> blogLists) {
	        // Tạo GridPane để chứa các bài đăng
	        GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10); 
	        gridPane.setVgap(10);

	        int columnCount = 3; // Số cột trong grid

	        // Duyệt qua danh sách bài đăng và thêm chúng vào GridPane
	        for (int i = 0; i < 6; i++) {
	            Blog blog = blogLists.get(i);

	            AnchorPane blogHBox = createBlogPane(blog);

	            int column = i % columnCount;
	            int row = i / columnCount;
	            gridPane.add(blogHBox, column, row);
	        }
	        
	        viewMoreBtn.setOnAction(event -> {
	        	for (int i = startIndex; i < Math.min(startIndex + 6, blogLists.size()); i++) {
		            Blog blog = blogLists.get(i);

		            AnchorPane blogHBox = createBlogPane(blog);

		            int column = i % columnCount;
		            int row = i / columnCount;
		            gridPane.add(blogHBox, column, row);
		        }
	        	startIndex += 6;
	        });

	        // Thêm GridPane vào AnchorPane
	        blogListPane.getChildren().add(gridPane);
	    }


	    private AnchorPane createBlogPane(Blog blog) {
	        // Tạo AnchorPane
	        AnchorPane anchorPane = new AnchorPane();
	        anchorPane.setPrefWidth(281.0); // Thiết lập chiều rộng theo nhu cầu của bạn

	        // Tạo ImageView
	        ImageView imageView = new ImageView(new Image(blog.getImageUrl().get(0)));
	        imageView.setFitHeight(133.0);
	        imageView.setFitWidth(241.0);
	        AnchorPane.setTopAnchor(imageView, 26.0);
	        AnchorPane.setLeftAnchor(imageView, 21.0);

	        // Tạo Label cho tiêu đề
	        Label titleLabel = new Label(blog.getTitle());
	        titleLabel.setPrefWidth(236.0); // Thiết lập chiều rộng theo nhu cầu của bạn
	        AnchorPane.setTopAnchor(titleLabel, 165.0);
	        AnchorPane.setLeftAnchor(titleLabel, 23.0);

	        // Tạo Text cho mô tả
	        Label descriptionText = new Label(blog.getContent());
	        descriptionText.setPrefHeight(60.0);
	        descriptionText.setPrefWidth(236.0);
	        descriptionText.setWrapText(true);
	        AnchorPane.setTopAnchor(descriptionText, 190.0);
	        AnchorPane.setLeftAnchor(descriptionText, 23.0);

	        // Tạo Text cho ngày đăng
	        Text dateText = new Text(blog.getLocalDate().toString());
	        AnchorPane.setTopAnchor(dateText, 267.0);
	        AnchorPane.setLeftAnchor(dateText, 23.0);

	        // Thêm các thành phần vào AnchorPane
	        anchorPane.getChildren().addAll(imageView, titleLabel, descriptionText, dateText);
	        anchorPane.setOnMouseClicked(event -> {
                BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
                appBorderPane.setCenter(Model.getInstance().getViewFactory().getBlogView(blog.getUrl()));
            });
	        return anchorPane;
	    }

}
