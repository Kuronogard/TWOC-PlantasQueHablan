package Proyecto.Proyecto;

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
              .setOAuthConsumerKey("fS2atOXlJJ2xF36HrW2lzbkqP")
              .setOAuthConsumerSecret("aSKPHxZiz6kxHr4nnEeZqtoxUzlS4LqKM3zv4tLvlRgBlQrDhD")
              .setOAuthAccessToken("70995577-wKQEyAFhKToWgvX2GcaDWbOnPy4f96S0N8jvScvxS")
              .setOAuthAccessTokenSecret("OHp3Rl3cyPzZocHJv4ojsIuLuBp1T3Z3R7tqnZ2FLNaps");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            // gets Twitter instance with default credentials
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
