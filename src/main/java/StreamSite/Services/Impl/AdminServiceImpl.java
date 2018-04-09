package StreamSite.Services.Impl;

import StreamSite.DAO.AdminDAO;
import StreamSite.DAO.GameDAO;
import StreamSite.DTO.*;
import StreamSite.Services.AdminService;
import StreamSite.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;
    private GameDAO gameDAO;
    private String consumerKey = "Hyz9sN8QzIbXvcL2wzD1hAji6";
    private String consumerSecret = "bwrPBRGYvdiBdkcTmzfKGpBD2fw0Ku2YUtdb325MEorINfzrbB";
    private String accessToken = "982670710798802944-uxTjNPO04kOiiNL3HZXlPdQ3XXjgHlP";
    private String accessSecret = "Jje0qM0FYWCWnifDahYS9QG3nLt10AatxbZNnanV97NJ2";

    Random rand = new Random();



    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO, GameDAO gameDAO) {
        this.adminDAO= adminDAO;
        this.gameDAO = gameDAO;
    }


    @Override
    public void updatePageCount(int id) {
    adminDAO.updatePageCount(id);
    }

    @Override
    public void addClub(String club, int img) {
        adminDAO.addClub(club,img);
    }

    @Override
    public List<Club> searchForClub(String club) {
        club= "%"+club+"%";
        return adminDAO.searchForClub(club);
    }

    @Override
    public List<League> searchForLeague(String league) {
        league= "%"+league+"%";
        return adminDAO.searchForLeague(league);
    }

    @Override
    public void addGame(int home, int away, int league, String dateTime) {
        adminDAO.addClub(home,away,league,dateTime);
    }

    @Override
    public void removeGameById(int gameId) {
        adminDAO.removeGameById(gameId);
    }

    @Override
    public void addStreamToGame(int gameId, String link) {
        adminDAO.addStreamToGame(gameId,link);
    }

    @Override
    public void postFeedback(String msg) {
        adminDAO.postFeedback(msg);
    }

    @Override
    public void hideFinishedGames() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(sdf.format(timestamp));




        List<GameInfoShort> GameList = adminDAO.getGamesForVisCheck();
        System.out.println("Im doing HIDE FINISHED GAMES");
        System.out.println(GameList.size());
        for (GameInfoShort game : GameList){
            System.out.println(game.getKo_time_date());

            int difference = hoursDifference(game.getKo_time_date(),timestamp);
            System.out.println("Difference between game in hours for vis is--- " + difference);

            if (difference <= -3){
                setVisibilityStatus(game.getGame_id());
            }

        }
    }

    @Override
    public void checkForTweet() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(sdf.format(timestamp));

        System.out.println("Im doing Check for TWEeeeeeeeeeeeets");


        List<GameInfoShort> GameList = adminDAO.getGamesForTweet();
        System.out.println(GameList.size());

        for (GameInfoShort game : GameList){

            System.out.println(game.getKo_time_date());

            int difference = minutesDifference(game.getKo_time_date(),timestamp);
            System.out.println("Difference between times is--- " + difference);

            if (difference >=0 && difference <5){
                System.out.println("Im in the 5 minute tweet with a difference of - " +difference);
                performFiveMinuteTweet(getFiveMinuteTweet(game.getGame_id()));
            }

            else if (difference >= 25 && difference <30){
                System.out.println("Im in the 30 minutes time tweet with a difference of - " +difference);
                performThirtyMinuteTweet(getThirtyMinuteTweet(game.getGame_id()));
            }

            else if (difference <=-50 && difference > -60){
                System.out.println("Im in the half time tweet with a difference of - " +difference);
                performHTTweet(getHTTweet(game.getGame_id()));
            }
        }
    }

    @Override
    public void addMsgEnd(String msg,int id) {
        adminDAO.addMsgEnd(msg,id);
    }

    @Override
    public void addMsgBgn(String msg,int id) {
        adminDAO.addMsgBgn(msg,id);
    }

    private void setVisibilityStatus(int id) {
        adminDAO.setVisibilityToHidden(id);
    }



    private String getHTTweet(int id) {

        GameInfo game = gameDAO.getGameById(id);
        String twtMsg = (adminDAO.getHTTweetMessageIntro()) + " " +game.getHome()+" vs "+ game.getAway() +" | "+ (adminDAO.getHTTweetMessageEnd());

        return twtMsg;
    }

    private String getThirtyMinuteTweet(int id) {

        GameInfo game = gameDAO.getGameById(id);
        String twtMsg = (adminDAO.getThirtyMinuteMessageIntro()) + " " +game.getHome()+" vs "+ game.getAway() +" | "+ (adminDAO.getThirtyMinuteMessageEnd());

        return twtMsg;
    }

    private String getFiveMinuteTweet(int id) {

        GameInfo game = gameDAO.getGameById(id);
        String twtMsg = (adminDAO.getFiveMinuteMessageIntro()) + " " +game.getHome()+" vs "+ game.getAway() +" | "+ (adminDAO.getFiveMinuteMessageEnd());

        return twtMsg;
    }

    private void performHTTweet(String tweetMsg) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret);


        try
        {
            TwitterFactory factory = new TwitterFactory(cb.build());
            Twitter twitter = factory.getInstance();
            System.out.println("tweeeeeeeeeet ht");
            twitter.updateStatus(tweetMsg);
        }catch (TwitterException te) {
            te.printStackTrace();
            System.exit(-1);
        }
    }

    private void performThirtyMinuteTweet(String tweetMsg) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret);


        try
        {
            TwitterFactory factory = new TwitterFactory(cb.build());
            Twitter twitter = factory.getInstance();
            System.out.println("tweeeeeeeeeet 30");

            twitter.updateStatus(tweetMsg);
        }catch (TwitterException te) {
            te.printStackTrace();
            System.exit(-1);
        }
    }

    private void performFiveMinuteTweet(String tweetMsg) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret);


        try
        {
            TwitterFactory factory = new TwitterFactory(cb.build());
            Twitter twitter = factory.getInstance();
            System.out.println("tweeeeeeeeeet 5");

            twitter.updateStatus(tweetMsg);
        }catch (TwitterException te) {
            te.printStackTrace();
            System.exit(-1);
        }
    }

    private static int hoursDifference(Timestamp ko_time, Timestamp now) {

        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (int) (ko_time.getTime() - now.getTime()) / MILLI_TO_HOUR;
    }

    private static int minutesDifference(Timestamp ko_time, Timestamp now) {

        final int MILLI_TO_HOUR = 1000 * 60;
        return (int) (ko_time.getTime() - now.getTime()) / MILLI_TO_HOUR;
    }



}
