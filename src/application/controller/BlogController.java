package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BlogController implements Initializable {
	 
    @FXML
    private WebView webView;
 
    private WebEngine engine;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the WebEngine
        engine = webView.getEngine();
    }
 
    public void setBlogUrl(String url) {
        // Load the specified URL into the WebView using the WebEngine
        engine.load(url);
    }
}
