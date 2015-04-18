package twitter4Plants.Logic;

import java.util.List;

import twitter4Plants.TwitterSender.Sender;
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
    public static void main( String[] args ) {
        Sender sender = Sender.getInstance();
        sender.connect();
        sender.toTweet("Seguimos probando para que las plantas hablen #twoc15");
    }
}
