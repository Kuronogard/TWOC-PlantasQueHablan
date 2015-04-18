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
	ConfigurationBuilder cb;
    
    protected Sender(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
    	cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(tokenSecret);
    }

    public static Sender getInstance(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        if (isInstanceNull()) {
            instance = new Sender(consumerKey, consumerSecret, accessToken, tokenSecret);
        }
        return instance;
    }

    public void connect() {
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void toTweet(String message) {
        try {
            twitter.updateStatus(message);
            this.resetAttemps();
        } catch (TwitterException e) {
            if (canSend()) {
                this.doAttemp();
                System.out.println(e.getErrorMessage());
                this.toTweet(message);
            } else {
                resetAttemps();
            }
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
