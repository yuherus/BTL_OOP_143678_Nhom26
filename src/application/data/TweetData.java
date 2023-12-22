package application.data;

<<<<<<< HEAD
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TweetData {
	
	public static void main(String[] args) {
		Map<String, Integer> topHashtags = getTopHashtags(1000);
		for (String key : topHashtags.keySet()) {
		    Integer value = topHashtags.get(key);
		    System.out.println(key + ": " + value);
		}
	}

	public static Map<String, Integer> getTopHashtags(Integer limit) {
        Map<String, Integer> topHashtags = new HashMap<>();

        try {
            // Đọc dữ liệu từ tệp tweets.json
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("./src/resources/data/tweets.json");
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            // Trích xuất tweetText và phân tích cú pháp để lấy danh sách các hashtag
            for (JsonNode tweetNode : jsonNode) {
                String tweetText = tweetNode.path("tweetText").asText();
                HashSet<String> hashtags = extractHashtags(tweetText);

                // Đếm tần suất xuất hiện của mỗi hashtag
                for (String hashtag : hashtags) {
                    topHashtags.put(hashtag, topHashtags.getOrDefault(hashtag, 0) + 1);
                }
            }

            // Bước 5: Xác định và trả về top hashtag dựa trên tần suất xuất hiện
            topHashtags = topHashtags.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(limit)
                    .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return topHashtags;
    }

    private static HashSet<String> extractHashtags(String tweetText) {
        // Trích xuất danh sách các hashtag từ một đoạn văn bản tweetText
    	HashSet<String> hashtags = new HashSet<>();

        // Tìm ký tự '#' trong chuỗi
        int startIndex = tweetText.indexOf('#');
        while (startIndex != -1) {
            // Tìm ký tự ' ' sau ký tự '#'
            int endIndex = tweetText.indexOf(' ', startIndex);

            // Nếu không tìm thấy ký tự ' ', lấy đến cuối chuỗi
            if (endIndex == -1) {
                endIndex = tweetText.length();
            }

            // Gán từ '#'
            String hashtag = tweetText.substring(startIndex, endIndex);
            hashtags.add(hashtag);

            // Tìm ký tự '#' tiếp theo
            startIndex = tweetText.indexOf('#', endIndex);
        }
        return hashtags;
    }
=======
public class TweetData {

>>>>>>> fc86d20838d9bb1d377d25cb75d71f58d045a67c
}
