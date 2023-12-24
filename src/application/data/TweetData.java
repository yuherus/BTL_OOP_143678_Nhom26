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

import javax.management.remote.SubjectDelegationPermission;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Tweet;
import javafx.geometry.Pos;
import application.models.Blog;
import application.models.Collection;
import application.models.Post;
import application.models.Tweet;

public class TweetData extends JsonToData<Tweet> implements PostData<Tweet> {

	@Override
	public ArrayList<Tweet> getAllPosts() {
		getDataToObject("./src/resources/data/tweets.json", Tweet.class);
		return getDataArrayList();
	}

	public ArrayList<Tweet> getHotestTweets() {
		ArrayList<Tweet> tweetList = this.getAllPosts();
		Collections.sort(tweetList, Comparator.comparing(Tweet::getLikes));
		ArrayList<Tweet> hotesTweets = new ArrayList<>();
		for (int i = tweetList.size() - 1; i >= tweetList.size() - 5; i--) {
			hotesTweets.add(tweetList.get(i));
		}
		return hotesTweets;
	}

	@Override
	public List<Tweet> getRelatedPosts(Collection collection) {
		List<Tweet> relatedTweets = new ArrayList<>();
		relatedTweets = this.getPostDataByKeyWord(collection.getName(), 4);
		return relatedTweets;
	}

	@Override
	public boolean containsKeyword(String[] baseDataArray, String keyword) {
		String lowercasedKeyword = keyword.toLowerCase();

		for (String baseData : baseDataArray) {
			String lowercasedBaseData = baseData.toLowerCase();

			if (lowercasedBaseData.contains(lowercasedKeyword)) {
				return true;
			}
		}

		return false; 
	}


	@Override
	public ArrayList<Tweet> getPostDataByKeyWord(String keyword, int limit) {
		String jsonFile = "./src/resources/data/tweets.json";
		ArrayList<Tweet> tweetList = getAllPosts();
		ArrayList<Tweet> tweetSearchList = new ArrayList<>();
		for(Tweet tweet : tweetList) {
			String[] baseDataNeedCheck = { tweet.getUser(), tweet.getUserName(), tweet.getTweetText()};
			boolean checkCondition = containsKeyword(baseDataNeedCheck, keyword);

			if (checkCondition) {
				tweetSearchList.add(tweet);
				if (limit > 0 && tweetSearchList.size() >= limit) {
					break;
				}
			}
		}

		return tweetSearchList;

	}

	public Map<String, Integer> getTopHashtags(Integer limit) {
		Map<String, Integer> topHashtags = new HashMap<>();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File jsonFile = new File("./src/resources/data/tweets.json");
			JsonNode jsonNode = objectMapper.readTree(jsonFile);
			for (JsonNode tweetNode : jsonNode) {
				String tweetText = tweetNode.path("tweetText").asText();
				HashSet<String> hashtags = extractHashtags(tweetText);

				for (String hashtag : hashtags) {
					topHashtags.put(hashtag, topHashtags.getOrDefault(hashtag, 0) + 1);
				}
			}

			topHashtags = topHashtags.entrySet().stream()
					.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(limit).collect(
							LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return topHashtags;
	}

	private HashSet<String> extractHashtags(String tweetText) {
		HashSet<String> hashtags = new HashSet<>();

		int startIndex = tweetText.indexOf('#');
		while (startIndex != -1) {
			int endIndex = findNonAlphaNumericIndex(tweetText, startIndex + 1);

			String hashtag = tweetText.substring(startIndex, endIndex);
			hashtags.add(hashtag);

			startIndex = tweetText.indexOf('#', endIndex);
		}
		return hashtags;
	}

	private int findNonAlphaNumericIndex(String text, int startIndex) {
		int index = startIndex;
		while (index < text.length() && (Character.isLetterOrDigit(text.charAt(index)) || text.charAt(index) == '_')) {
			index++;
		}
		return index;
	}

	public ArrayList<String> mapToArrayList(Map<String, Integer> topHashtagsMap,
			ArrayList<String> topHashtagsList) {
		for (String key : topHashtagsMap.keySet()) {
			topHashtagsList.add(key);
		}
		return topHashtagsList;
	}
}
