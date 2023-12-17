package application.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Collection;
import javafx.scene.image.Image;

public class CollectionData {
    private static String apiKey = "f94465cb-3d33-4f60-b46c-4bbdb2bcf617"; 
	public static void main(String[] args) {
		List<Collection> trendingCollections= getTrendingCollections("POLYGON", "H1", 10);
		for (Collection collection : trendingCollections) {
			System.out.println(collection.toString());
		}
		
	}
	

	public static ArrayList<Collection> getTrendingCollections(String blockchain, String period, Integer limit) {
		ArrayList<Collection> trendingCollections = new ArrayList<>();
		String fileName = "./src/resources/data/collection" + period + ".json";
		try {
            // Sử dụng ObjectMapper để đọc dữ liệu từ tệp JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));

            // Sử dụng JsonNode để lấy danh sách các đối tượng Collection
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode collectionNode = elements.next();
                Collection collection = objectMapper.treeToValue(collectionNode, Collection.class);
                if (blockchain.equals("ALL")) {
                    trendingCollections.add(collection);
                } else {
	                if (collection.getBlockchain().equals(blockchain)){
	                    trendingCollections.add(collection);
	                }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		if (limit != null && limit > 0 && trendingCollections.size() > limit) {
            return new ArrayList<>(trendingCollections.subList(0, limit));
        } else {
            return trendingCollections;
        }
	}
	
}
