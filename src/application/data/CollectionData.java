package application.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Collection;

public class CollectionData {
    private static String apiKey = "f94465cb-3d33-4f60-b46c-4bbdb2bcf617"; 
	public static void main(String[] args) {
		List<Collection> trendingCollections= getTrendingCollections("", "D1", 20);
		for (Collection collection : trendingCollections) {
			System.out.println(collection.toString());
		}
	}
	
	public static Collection getCollectionById(String id) {
		Collection collection = new Collection();
        collection.setId(id);
		try {
            String apiUrl = "https://api.rarible.org/v0.1/collections/" + id;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-API-KEY", apiKey); 

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String collectionData = response.toString();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output1.json"))) {
                    writer.write(collectionData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode jsonNode = objectMapper.readTree(collectionData);

                // Extracting specific fields
                String collectionName = jsonNode.path("name").asText();
                collection.setCollection(collectionName);
                if (!jsonNode.path("meta").path("description").isMissingNode()) {
                String collectionDescription = jsonNode.path("meta").path("description").asText(); 
                collection.setDescription(collectionDescription);
                }
                
                if(jsonNode.path("meta").path("content").isArray()) {
                		JsonNode contentNode =  jsonNode.path("meta").path("content");   		
                		if (contentNode.get(0) != null ) {
	                		String imgUrl = contentNode.get(0).path("url").asText();
	                		collection.setImageUrl(imgUrl);
                		}
                }
                Thread.sleep(1000);
                
            } else {
                System.out.println("Fetch Data Error: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return collection;
	}

	public static ArrayList<Collection> getTrendingCollections(String blockchain, String period, Integer limit) {
		ArrayList<Collection> trendingCollections = new ArrayList<>();
		try {
            String apiUrl = "https://api.rarible.org/v0.1/data/rankings/collections/volume/?blockchain="+blockchain+"&period="+period+"&limit="+limit;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-API-KEY", apiKey); 

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String collectionData = response.toString();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.json"))) {
                    writer.write(collectionData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode jsonNode = objectMapper.readTree(collectionData);
	            	
	            JsonNode resultNode = jsonNode.get("result");
	            	
	            if (resultNode != null && resultNode.isArray()) {
	                for (JsonNode itemNode : resultNode) {
	                    String id = itemNode.get("id").asText();
	                    Collection collectionItem = getCollectionById(id);
	                    String collectionBlockchain = id.substring(0,id.indexOf(":"));
	                    collectionItem.setBlockchain(collectionBlockchain);
	                    double floorPrice = itemNode.get("floorPrice").get("value").asDouble();
	                    double volume =  itemNode.get("volumeNative").get("value").asDouble();
	                    double volumeChange =  itemNode.get("volumeUsd").get("changePercent").asDouble();
	                    int totalItem = itemNode.get("totalItemSupply").asInt();
	                    collectionItem.setFloorPrice(floorPrice);
	                    collectionItem.setVolume(volume);
	                    collectionItem.setVolumeChange(volumeChange);
	                    collectionItem.setItem(totalItem);
	                    trendingCollections.add(collectionItem);
	                }
	            } else {
	                System.out.println("Error");
	            }
	            
                
            } else {
                System.out.println("Fetch Data Error: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return trendingCollections;
	}
	
}
