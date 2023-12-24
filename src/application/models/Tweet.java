package application.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet extends Post {
	@JsonProperty("userImage")
    private String userImage;
	
	@JsonProperty("user")
    private String user;
	
	@JsonProperty("userName")
    private String userName;
	
	@JsonProperty("timeStamps")
    private String timeStamps;
	
	@JsonProperty("tweetText")
    private String tweetText;
	
	@JsonProperty("tweetImage")
    private List<String> tweetImage;
	
	@JsonProperty("replys")
    private String replys;
	
	@JsonProperty("reTweets")
    private String reTweets ;
	
	@JsonProperty("likes")
    private String likes;
	
	@JsonCreator
    public Tweet(@JsonProperty("userImage")String userImage,
	@JsonProperty("user")String user,
	@JsonProperty("userName") String userName,
	@JsonProperty("timeStamps") String timeStamps,	
	@JsonProperty("tweetText") String tweetText,
	@JsonProperty("tweetImage") List<String> tweetImage,
	@JsonProperty("replys") String replys,
	@JsonProperty("reTweets") String reTweets,
	@JsonProperty("likes") String likes) {
        this.userImage = userImage;
        this.user = user;
        this.userName = userName;
        this.timeStamps = timeStamps;
        this.tweetText = tweetText;
        this.tweetImage = tweetImage;
        this.replys = replys;
        this.reTweets = reTweets;
        this.likes = likes;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(String timeStamps) {
        this.timeStamps = timeStamps;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public List<String> getTweetImage() {
        return tweetImage;
    }

    public void setTweetImage(List<String> tweetImage) {
        this.tweetImage = tweetImage;
    }

    public String getReplys() {
        return replys;
    }

    public void setReplys(String replys) {
        this.replys = replys;
    }

    public String getReTweets() {
        return reTweets;
    }

    public void setReTweets(String reTweets) {
        this.reTweets = reTweets;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
