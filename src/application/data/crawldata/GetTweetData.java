package application.data.crawldata;

import application.models.Tweet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetTweetData {
    private static String apiKey = "d0ba958996msh9dc90fce37d2f3dp1523bbjsn19c95f20f0f0";

    public static void main(String[] args) {

    }

    public static ArrayList<Tweet> getTweets(int numRequests, int tweetsPerPage) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        String continuationToken = null;

        try {
            for (int i = 0; i < numRequests; i++) {
            	// Tạo request đến Api
                HttpRequest request = createRequest(continuationToken, tweetsPerPage);

                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                int responseCode = response.statusCode();

                if (responseCode == 200) {
                    String tweetData = response.body();

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(tweetData);
                    JsonNode resultNode = jsonNode.get("results");

                    if (resultNode != null && resultNode.isArray()) {
                        for (JsonNode itemNode : resultNode) {
                            // Trích xuất thông tin từ JSON và tạo đối tượng Tweet
                            String userImage = itemNode.path("user").path("profile_pic_url").asText();
                            String user = itemNode.path("user").path("name").asText();
                            String userName = itemNode.path("user").path("username").asText();
                            String timeStamps = itemNode.path("creation_date").asText();
                            String tweetText = itemNode.path("text").asText();
                            String replys = itemNode.path("reply_count").asText();
                            String reTweets = itemNode.path("retweet_count").asText();
                            String likes = itemNode.path("favorite_count").asText();

                            // Lấy giá trị của trường "media_url"
                            List<String> tweetImage = new ArrayList<>();
                            JsonNode mediaUrlNode = itemNode.path("media_url");

                            if (mediaUrlNode.isArray()) {
                                for (JsonNode urlNode : mediaUrlNode) {
                                    tweetImage.add(urlNode.asText());
                                }
                            }

                            Tweet tweet = new Tweet(userImage, user, userName, timeStamps, tweetText, tweetImage, replys, reTweets, likes);

                            // Thêm tweet vào danh sách
                            tweets.add(tweet);
                            System.out.println(tweets.size());
                        }

                        // Lấy continuation_token cho lần kết tiếp
                        continuationToken = jsonNode.path("continuation_token").asText();
                    } else {
                        System.out.println("No results found.");
                        break;  // Kết thúc nếu không có kết quả
                    }
                } else {
                    System.out.println("Fetch Data Error: " + responseCode);
                    break;  // Kết thúc nếu có lỗi khi lấy dữ liệu
                }
            }

            // Ghi danh sách tweets vào file JSON
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("tweets.json"))) {
                ObjectMapper objectMapper = new ObjectMapper();
                writer.write(objectMapper.writeValueAsString(tweets));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tweets;
    }

    private static HttpRequest createRequest(String continuationToken, int tweetsPerPage) {
        String apiUrl = "https://twitter154.p.rapidapi.com/search/search?query=%23nft&section=top&min_retweets=1&min_likes=1&limit="
                + tweetsPerPage + "&start_date=2022-01-01&language=en";

        if (continuationToken != null) {
            apiUrl += "&continuation_token=" + continuationToken;
        }

        return HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "twitter154.p.rapidapi.com")
                .GET()
                .build();
    }
}
