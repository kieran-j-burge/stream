package StreamSite.tasks;

import StreamSite.Services.GameService;
import StreamSite.Services.StreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;



@Component
public class tasks {

    private GameService gameService;
    private StreamService streamService;

    @Autowired
    public tasks(GameService gameService, StreamService streamService) {
        this.gameService = gameService;
        this.streamService = streamService;
    }


//    @Scheduled(cron =  "5 * * * * *")
//    public void reportCurrentTime() throws TwitterException {
//        String twtMessage= gameService.getTweetMessage();
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("zbZ2kZVlstVAw8LINGITkWbTw")
//                .setOAuthConsumerSecret("T8UqlZi8mfd1VbQzSt1TA6RsXCAt8UR07gYYXKWgV45Se1Vnac")
//                .setOAuthAccessToken("979741991754960896-27l8sNqq06zvYpK6k3QcgXFQrxoNvPH")
//                .setOAuthAccessTokenSecret("W3yNdka16WvAqF9AylxBlNx0OdKOFW9BXX8mWICmIAJ9H");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//
////        String latestStatus = "s";
////        String message="\"A Visit to Transylvania\" by Euromaxx: Lifestyle Europe (DW) \n http://bit.ly/1cHB7MH";
//        Status status = twitter.updateStatus(twtMessage);
//
//    }

}
