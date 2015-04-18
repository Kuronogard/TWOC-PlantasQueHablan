package twitter4Plants.TwitterSender;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Sender {
    private static Sender instance = null;
    private final int MAX_NUMBER_OF_ATTEMPTS = 3;
    private Twitter twitter;
    private int attemps = 0;

    protected Sender() {
    }

    public static Sender getInstance() {
        if (isInstanceNull()) {
            instance = new Sender();
        }
        return instance;
    }

    public void connect() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("y1zM2z2cnqfo80qLuMcdAGP4b")
                .setOAuthConsumerSecret(
                        "7GYuaitHZ5d0MmoSk2nTHf9NICOJv6hGxBRF1Bqb3v8CLpxIS9")
                .setOAuthAccessToken(
                        "3179195939-VwKPvlLgnkMFOpYqp8Mph9e9q4geuCZC3t6GcjA")
                .setOAuthAccessTokenSecret(
                        "pNDDi7kdb9BB14RrJGRz9ide9v3uVEqECtlEhJ6T5mt4g");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void toTweet(String message) {
        try {
            if (this.canSend()) {
                twitter.updateStatus(message);
                this.resetAttemps();
            }
        } catch (TwitterException e) {
            this.doAttemp();
            this.toTweet(message);
        }
    }

    private static boolean isInstanceNull() {
        return instance == null;
    }

    private void doAttemp() {
        attemps += 1;
    }

    private void resetAttemps() {
        attemps = 0;

    }

    private boolean canSend() {
        return this.attemps != MAX_NUMBER_OF_ATTEMPTS;
    }

}
