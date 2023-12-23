package application.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	public static void main(String[] args) {
//		List<Collection> trendingCollections= getTrendingCollections("POLYGON", "H1", 10);
//		for (Collection collection : trendingCollections) {
//			System.out.println(collection.toString());
//		}
		List<Collection> searchCollections = getCollectionBySearchWord("Other", 5, "D1");
		for (Collection collection : searchCollections) {
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
	
	public static List<Collection> getCollectionBySearchWord(String SearchWord, int limit, String period ){
		List<Collection> collectList = new ArrayList<>();
		File jsonFile = new File("./src/resources/data/collection"+period+".json");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonFile);

			// Assuming your JSON file is an array, iterate over each object
			for (JsonNode objectNode : rootNode) {
				// Access individual fields of each object
				String id = objectNode.get("id").asText();
				double volumeNative = objectNode.get("volumeNative").asDouble();
				String volumeNativeUnit = objectNode.get("volumeNativeUnit").asText();
				double floorPrice = objectNode.get("floorPrice").asDouble();
				String floorPriceUnit = objectNode.get("floorPriceUnit").asText();
				double VolumeChange = objectNode.get("VolumeChange").asDouble();
				String blockchain = objectNode.get("blockchain").asText();
				String name = objectNode.get("name").asText();
				String description = objectNode.get("description").toString();
				String imageUrl = objectNode.get("imageUrl").asText();
				
				String[] baseDataNeedCheck = {name, blockchain, volumeNativeUnit,description};
				for(String DNC:baseDataNeedCheck) {
					boolean checkCondition = DNC.contains(SearchWord);
					if (checkCondition) {
						collectList.add(new Collection(id, volumeNative, volumeNativeUnit, floorPrice, floorPriceUnit, VolumeChange, blockchain, name, description, imageUrl));
						break;
					}
				}
				if (limit > 0 && collectList.size() >= limit) {
					break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return collectList;

	}	
}
