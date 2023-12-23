package application.controller;
import java.net.URL;
import java.util.ResourceBundle;

import application.models.Model;
import application.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.shape.SVGPath;

public class HeaderController implements Initializable {
	private static final Stage FXMLLoader = null;

	//Header
	@FXML
	private ToolBar home_menu_bar;
	
	@FXML
	private ComboBox<String> header_combobox;
	
	@FXML
	private AnchorPane homeBtn;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private TextField textFieldSearch;	
	
	@FXML
	private Button allNFT;
	
	@FXML
	private Button allBlog;
	
	@FXML
	private Button allTweet;
	

	ObservableList<String> list = FXCollections.observableArrayList("Update Data", "Help");
	
	public ToolBar getToolBar() {
		return home_menu_bar;
	}
	
	public ComboBox<String> getComboBox(){
		return header_combobox;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SVGPath svgPath = new SVGPath();
        svgPath.setContent("M 21 3 C 11.621094 3 4 10.621094 4 20 C 4 29.378906 11.621094 37 21 37 C 24.710938 37 28.140625 35.804688 30.9375 33.78125 L 44.09375 46.90625 L 46.90625 44.09375 L 33.90625 31.0625 C 36.460938 28.085938 38 24.222656 38 20 C 38 10.621094 30.378906 3 21 3 Z M 21 5 C 29.296875 5 36 11.703125 36 20 C 36 28.296875 29.296875 35 21 35 C 12.703125 35 6 28.296875 6 20 C 6 11.703125 12.703125 5 21 5 Z");
        resize(svgPath, 16, 16);
	    btnSearch.setGraphic(svgPath);
		header_combobox.setItems(list);
		homeBtn.setOnMouseClicked( event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getHomeView());
		});
		
		allBlog.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getAllBlogView());
		});
		
		allNFT.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getAllCollectionView());
		});
		
		allTweet.setOnAction(event -> {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getAllTweetView());
		});
	}
	
	public void searchChange(ActionEvent event) {
		String searchText = textFieldSearch.getText();
		if (!searchText.equals("")) {
			BorderPane appBorderPane = (BorderPane) ((Node) event.getSource()).getScene().lookup("#app_border_pane");
			appBorderPane.setCenter(Model.getInstance().getViewFactory().getSearch(searchText));
		}
	}
	
	private void resize(SVGPath svg, double width, double height) {

        double originalWidth = svg.prefWidth(-1);
        double originalHeight = svg.prefHeight(originalWidth);

        double scaleX = width / originalWidth;
        double scaleY = height / originalHeight;

        svg.setScaleX(scaleX);
        svg.setScaleY(scaleY);
    }

}
