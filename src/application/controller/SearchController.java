package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.data.BlogData;
import application.models.Blog;
import application.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchController implements Initializable {
	@FXML
    private Label labelKeyWord;
	
	private ArrayList<Blog> blogList;
	
	@FXML
	private AnchorPane blogTab;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setLabel(String textField) {
		BlogData blogData = new BlogData();
		labelKeyWord.setText("Search results for \""+textField+"\"");
		blogList = blogData.getPostDataByKeyWord(textField, 20);
		setBlogData(blogList);
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
        ImageView imageView = new ImageView(new Image(blog.getImageUrl()));
        imageView.setFitHeight(100);
        imageView.setFitWidth(160);
        AnchorPane.setTopAnchor(imageView, 20.0);
        AnchorPane.setLeftAnchor(imageView, 20.0);

        // Tạo Label cho tiêu đề
        Label titleLabel = new Label(blog.getTitle());
        titleLabel.setPrefWidth(200); // Thiết lập chiều rộng theo nhu cầu của bạn
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
}	
