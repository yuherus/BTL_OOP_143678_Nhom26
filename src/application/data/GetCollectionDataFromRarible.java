package application.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetCollectionDataFromRarible {
    private static final String apiKey = "24f563aa-d9b1-4df4-8e33-552ef5575b49";

    public static void main(String[] args) {
    			getTopCollectionByTime("H1");
    			getTopCollectionByTime("D1");
    			getTopCollectionByTime("D7");
    			getTopCollectionByTime("D30");
    }

    private static void getTopCollectionByTime(String period){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/resources/data/collection"+period+".json",false))) {
            writer.write("[");
        
	        String continuation = "";
	        int i = 0;
	        try {
	            do {
	            		if (!continuation.equals("")) {
	            			writer.write(",");
	            			writer.newLine();
	            		}
	                String apiUrl = "https://api.rarible.org/v0.1/data/rankings/collections/volume?limit=1000&period="+period+continuation;
	                URL url = new URL(apiUrl);
	                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	
	                // Thiết lập phương thức yêu cầu và thêm header chứa API key
	                connection.setRequestMethod("GET");
	                connection.setRequestProperty("Content-Type", "application/json");
	                connection.setRequestProperty("X-API-KEY", apiKey); // Sử dụng header X-API-KEY
	
	                int responseCode = connection.getResponseCode();
	                // get respone message
	                String responseMessage = connection.getResponseMessage();
	                // Nếu mã trạng thái là 200 OK, đọc dữ liệu từ response
	                if (responseCode == HttpURLConnection.HTTP_OK) {
	                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                    StringBuilder response = new StringBuilder();
	                    String line;
	
	                    while ((line = reader.readLine()) != null) {
	                        response.append(line);
	                    }
	                    reader.close();
	
	                    // Xử lý dữ liệu JSON
	                    String jsonData = response.toString();
	                        // Xử lý dữ liệu bên trong resul bằng jsonNode
	                        ObjectMapper objectMapper = new ObjectMapper();
	
	                        // Read JSON string to JsonNode
	                        JsonNode jsonNode = objectMapper.readTree(jsonData);
	
	                        // Access fields in JsonNode
	                        continuation ="&continuation=" + jsonNode.get("continuation").asText();
	                        System.out.println("Continuation: " + continuation);
	
	                        JsonNode resultNode = jsonNode.get("result");
	                        if (resultNode.isArray()) {
	                            for (JsonNode item : resultNode) {
	                                String id = item.get("id").asText();
	                                // Lấy ra volume native, đơn vị cho volume native, floor price, volume change
	                                // value là con của volume native
	                                String VolumeChange = item.get("volumeUsd").get("changePercent").asText();
	                                String volumeNative = item.get("volumeNative").get("value").asText();
	                                String volumeNativeUnit = item.get("volumeNative").get("currency").asText();
	                                String floorPrice;
	                                if (item.get("floorPrice").get("value") == null) {
	                                    floorPrice = null;
	                                } else {
	                                    floorPrice = item.get("floorPrice").get("value").asText();
	                                }
	                                String floorPriceUnit;
	                                if (item.get("floorPrice").get("currency") == null) {
	                                    floorPriceUnit = null;
	                                } else {
	                                    floorPriceUnit = item.get("floorPrice").get("currency").asText();
	                                }
	                                String collecionMetadata = getCollectionById(id);
	//                              Viết vào file json 1 collection từ những data trên
	                                writer.write("{\"id\": \"" + id + "\", \"volumeNative\": \"" + volumeNative + "\", \"volumeNativeUnit\": \"" + volumeNativeUnit + "\", \"floorPrice\": \"" + floorPrice + "\", \"floorPriceUnit\": \"" + floorPriceUnit + "\", \"VolumeChange\": \"" + VolumeChange + "\"," + collecionMetadata + "}");
	                                if (item != resultNode.get(resultNode.size() - 1)) {
	                                    writer.write(",");
		                                writer.newLine();
	                                }
	                            }
	                        }
	                } else {
	                    System.out.println("Error: " + responseCode);
	                }
	
	                // Đóng kết nối
	                connection.disconnect();
	            } while ((continuation.equals("&continuation=null") == false) & (++i < 5 ));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	            writer.write("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getCollectionById(String id){
        try {
            String apiDataUrl = "https://api.rarible.org/v0.1/collections/"+id;
            URL url = new URL(apiDataUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Thiết lập phương thức yêu cầu và thêm header chứa API key
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-API-KEY", apiKey); // Sử dụng header X-API-KEY

            int responseCode = connection.getResponseCode();

            // Nếu mã trạng thái là 200 OK, đọc dữ liệu từ response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Xử lý dữ liệu JSON
                String collectionMetaData = response.toString();
                // Ghi dữ liệu vào tệp
                ObjectMapper objectMapper = new ObjectMapper();

                // Read JSON string to JsonNode
                JsonNode jsonNode = objectMapper.readTree(collectionMetaData);
                String blockchain = jsonNode.get("blockchain").asText();
                String name = jsonNode.get("name").toString();
                String description;
                String imageUrl;
                if (jsonNode.get("meta") != null) {
                    if (jsonNode.get("meta").get("description") == null) {
                        description = null;
                    }  else {
                        description = jsonNode.get("meta").get("description").toString();
                    }
                    if (jsonNode.get("meta").get("content").get(0) == null) {
                        imageUrl = "/resources/images/collectionlogo.png";
                    } else {
                        imageUrl = jsonNode.get("meta").get("content").get(0).get("url").asText();
                    }
                } else {
                    description = null;
                    imageUrl = "/resources/images/collectionlogo.png";
                }
                return "\"blockchain\": \"" + blockchain + "\", \"name\": " + name + ", \"description\": " + description + ", \"imageUrl\": \"" + imageUrl + "\"";
            } else {
                System.out.println("Error: " + responseCode);
            }

            // Đóng kết nối
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
