package application.models;

import java.util.List;

public class Tweet {
    private String userImage;
    private String user;
    private String userName;
    private String timeStamps;
    private String tweetText;
    private List<String> tweetImage;
    private String replys;
    private String reTweets ;
    private String likes;

    public Tweet(String userImage, String user, String userName, String timeStamps, String tweetText, List<String> tweetImage, String replys, String reTweets, String likes) {
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
