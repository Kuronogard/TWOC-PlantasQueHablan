package twitter4Plants.Logic;

import twitter4Plants.TwitterSender.Sender;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Sender sender = Sender.getInstance();
        sender.connect();
        sender.toTweet("Seguimos probando para que las plantas hablen #twoc15");
    }
}
