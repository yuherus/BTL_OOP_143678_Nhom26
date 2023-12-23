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
				String tweetText = tweetNode.get("tweetText").toString();
				List<String> tweetImage = new ArrayList<>();
				if (tweetNode.get("tweetImage").get(0) != null) {
					for(JsonNode node:tweetNode.get("tweetImage")) {
						tweetImage.add(node.toString());
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
	
	@Override
	public ArrayList<Tweet> getNewestPosts() {
		ArrayList<Tweet> tweetList = this.getAllPosts();
		Collections.sort(tweetList, Comparator.comparing(Tweet::getLikes));
		ArrayList<Tweet> newestTweets = new ArrayList<>();
		for (int i = tweetList.size()-1 ; i >= tweetList.size() - 5; i--) {
			newestTweets.add(tweetList.get(i));
		}
		return newestTweets;
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
				String tweetText = objectNode.get("tweetText").toString();
				List<String> tweetImage = new ArrayList<>();
				if (objectNode.get("tweetImage").get(0) != null) {
					for(JsonNode node:objectNode.get("tweetImage")) {
						tweetImage.add(node.toString());
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

}
