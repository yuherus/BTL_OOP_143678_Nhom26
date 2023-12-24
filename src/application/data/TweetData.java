package application.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Tweet;
import javafx.geometry.Pos;
import application.models.Blog;
import application.models.Collection;
import application.models.Post;
import application.models.Tweet;

public class TweetData implements PostData <Tweet> {
	public static void main(String[] args) {
		TweetData tweetData = new TweetData();
		ArrayList<Tweet> searchTweets = tweetData.getPostDataByKeyWord("Celes",10);
		for (Tweet collection : searchTweets) {
		System.out.println(collection.toString());
		}
	}
	
	@Override
	public ArrayList<Tweet> getAllPosts() {
		ArrayList<Tweet> tweetList = new ArrayList<>();
		String fileName = "./src/resources/data/tweets.json";
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode tweetNode = elements.next();
                String userImage = tweetNode.get("userImage").asText();
				String user = tweetNode.get("user").asText();
				String userName = tweetNode.get("userName").asText();
				String timeStamps = tweetNode.get("timeStamps").asText();
				String tweetText = tweetNode.get("tweetText").asText();
				List<String> tweetImage = new ArrayList<>();
				if (tweetNode.get("tweetImage").get(0) != null) {
					for(JsonNode node:tweetNode.get("tweetImage")) {
						tweetImage.add(node.asText());
					}
				}
				String replys = tweetNode.get("replys").asText();
				String reTweets = tweetNode.get("reTweets").asText();
				String likes = tweetNode.get("likes").asText();

				tweetList.add(new Tweet(userImage, user, userName, timeStamps, tweetText, tweetImage, replys,reTweets, likes ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return tweetList;
	}
	
	public ArrayList<Tweet> getHotestTweets() {
		ArrayList<Tweet> tweetList = this.getAllPosts();
		Collections.sort(tweetList, Comparator.comparing(Tweet::getLikes));
		ArrayList<Tweet> hotesTweets = new ArrayList<>();
		for (int i = tweetList.size()-1 ; i >= tweetList.size() - 5; i--) {
			hotesTweets.add(tweetList.get(i));
		}
		return hotesTweets;
	}
	
	@Override
	public List<Tweet> getRelatedPosts(Collection collection) {
		List<Tweet> relatedTweets = new ArrayList<>();
		relatedTweets = this.getPostDataByKeyWord(collection.getName(),4);
		return relatedTweets;
	}
	
	@Override
	public boolean containsKeyword(String[] baseDataArray, String keyword) {
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
	
	@Override
	public ArrayList<Tweet> getPostDataByKeyWord(String keyword, int limit) {
		ArrayList<Tweet> TweetList = new ArrayList<>();
		File jsonFile = new File("./src/resources/data/tweets.json");

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonFile);

			// Assuming your JSON file is an array, iterate over each object
			for (JsonNode objectNode : rootNode) {
                String userImage = objectNode.get("userImage").asText();
				String user = objectNode.get("user").asText();
				String userName = objectNode.get("userName").asText();
				String timeStamps = objectNode.get("timeStamps").asText();
				String tweetText = objectNode.get("tweetText").asText();
				List<String> tweetImage = new ArrayList<>();
				if (objectNode.get("tweetImage").get(0) != null) {
					for(JsonNode node:objectNode.get("tweetImage")) {
						tweetImage.add(node.asText());
					}
				}
				String replys = objectNode.get("replys").asText();
				String reTweets = objectNode.get("reTweets").asText();
				String likes = objectNode.get("likes").asText();
				String[] baseDataNeedCheck = {user, userName, tweetText};
				boolean checkCondition = containsKeyword(baseDataNeedCheck, keyword);

				if (checkCondition) {
					TweetList.add(new Tweet(userImage, user, userName, timeStamps, tweetText, tweetImage, replys,reTweets, likes ));
					if (limit > 0 && TweetList.size() >= limit) {
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return TweetList;
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
	            // Tìm ký tự không phải chữ cái, chữ số, hoặc dấu '_' sau ký tự '#'
	            int endIndex = findNonAlphaNumericIndex(tweetText, startIndex + 1);

	            // Gán từ '#'
	            String hashtag = tweetText.substring(startIndex, endIndex);
	            hashtags.add(hashtag);

	            // Tìm ký tự '#' tiếp theo
	            startIndex = tweetText.indexOf('#', endIndex);
	        }
	        return hashtags;
	    }

	    private static int findNonAlphaNumericIndex(String text, int startIndex) {
	        int index = startIndex;
	        while (index < text.length() && (Character.isLetterOrDigit(text.charAt(index)) || text.charAt(index) == '_')) {
	            index++;
	        }
	        return index;
	    }

	    public static ArrayList<String> mapToArrayList(Map<String, Integer> topHashtagsMap, ArrayList<String> topHashtagsList){
	        for (String key : topHashtagsMap.keySet()) {
	            topHashtagsList.add(key);
	        }
	        return topHashtagsList;
	    }
}
