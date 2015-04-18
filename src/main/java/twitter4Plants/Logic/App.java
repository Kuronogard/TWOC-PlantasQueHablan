package twitter4Plants.Logic;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws TwitterException {
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
              .setOAuthConsumerKey("y1zM2z2cnqfo80qLuMcdAGP4b")
              .setOAuthConsumerSecret("7GYuaitHZ5d0MmoSk2nTHf9NICOJv6hGxBRF1Bqb3v8CLpxIS9")
              .setOAuthAccessToken("3179195939-VwKPvlLgnkMFOpYqp8Mph9e9q4geuCZC3t6GcjA")
              .setOAuthAccessTokenSecret("pNDDi7kdb9BB14RrJGRz9ide9v3uVEqECtlEhJ6T5mt4g");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            // gets Twitter instance with default credentials
            User user = twitter.verifyCredentials();
            String message = "Soy una planta que habla y esto parece que marcha #twoc15";
            Status status = twitter.updateStatus(message);
            
//            List<Status> statuses = twitter.getHomeTimeline();
//            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
//            for (Status status : statuses) {
//                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
//            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
