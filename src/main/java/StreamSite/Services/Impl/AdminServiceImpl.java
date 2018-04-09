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




        List<GameInfoShort> GameList = adminDAO.getGamesForTweet();

        for (GameInfoShort game : GameList){

            System.out.println(game.getKo_time_date());

            int difference = minutesDifference(game.getKo_time_date(),timestamp);
            System.out.println("Difference between times is--- " + difference);

            if (difference >=0 && difference <5){
                System.out.println("Im in the 5 minute tweet with a difference of - " +difference);
//                performFiveMinuteTweet(getFiveMinuteTweet(game.getGame_id()));
            }

            else if (difference >= 25 && difference <30){
                System.out.println("Im in the 30 minutes time tweet with a difference of - " +difference);
//                performThirtyMinuteTweet(getThirtyMinuteTweet(game.getGame_id()));
            }

            else if (difference <=-50 && difference > -60){
                System.out.println("Im in the half time tweet with a difference of - " +difference);
//                performHTTweet(getHTTweet(game.getGame_id()));
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


//    @Override
//    public void getTweet() {
//        List<GameInfo> gameList = adminDAO.getTweet();
//        Calendar cal = Calendar.getInstance();
//        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
//        String dayOfMonthStr = String.valueOf(dayOfMonth);
//        System.out.println(gameList.size());
//
//        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        String time_nowStr = sdf.format(cal.getTime());
//
//        String time_now_hours = time_nowStr.substring(0,time_nowStr.length() - 6);
//        String time_now_minutes = time_nowStr.substring(3,time_nowStr.length() - 3);
//        int time_now_minutes_int = Integer.parseInt(time_now_minutes) ;
//        int time_now_hours_int = Integer.parseInt(time_now_hours);
//
//        System.out.println(time_now_hours +":" +time_now_minutes);
//
////        Change date format to dd
//        for (GameInfo game : gameList){
//            System.out.println("inloop");
//            game.setKo_time_date(game.getKo_time_date().substring(8));
//            System.out.println(game.getKo_time_date());
//        }
//        System.out.println("out loop");
//        System.out.println(dayOfMonth);
//
//
//
//        for (GameInfo game : gameList){
//            System.out.println("in loooooop");
//            if (Integer.parseInt(dayOfMonthStr) == Integer.parseInt(game.getKo_time_date())){
//                System.out.println("day worked");
//                int timeLeftUntilKo = 0;
//                String ko_time = game.getKo_time();
//                System.out.println(ko_time);
//                String ko_time_hours = ko_time.substring(0, ko_time.length() -3 );
//                String ko_time_minutes = ko_time.substring(3);
//                System.out.println("substring");
//
//                int ko_time_hours_int = Integer.parseInt(ko_time_hours);
//                int ko_time_minutes_int = Integer.parseInt(ko_time_minutes);
//                System.out.println(ko_time_hours_int + ":" + ko_time_minutes_int);
//                if (ko_time_hours_int == time_now_hours_int){
//                    System.out.println("first if");
//
////                    if (ko_time_minutes_int > time_now_minutes_int){
//                    timeLeftUntilKo = ko_time_minutes_int - time_now_minutes_int;
//
//                        if (timeLeftUntilKo <= 5){
//                            performFiveMinuteTweet(getFiveMinuteTweet(game));
//                        }
//                        else if (timeLeftUntilKo <=30 && timeLeftUntilKo >25){
//                            int  n = rand.nextInt(6) + 1;
//                            if (n == 1){
//                                performThirtyMinuteTweet(getThirtyMinuteTweet(game));
//                            }
//                        }
//                        else if (timeLeftUntilKo <= -45 && timeLeftUntilKo > -50){
//                            performHTTweet(getHTTweet(game));
//                        }
//
////                    }
////                    else if (ko_time_minutes_int < time_now_minutes_int){
////                        timeLeftUntilKo = ko_time_minutes_int - time_now_minutes_int;
////                    }
//                }
//
//                else if(ko_time_hours_int == time_now_hours_int +1){
//
//                    timeLeftUntilKo = (ko_time_minutes_int + 60) - time_now_minutes_int;
//
//                    if (timeLeftUntilKo <= 5){
//                        performFiveMinuteTweet(getFiveMinuteTweet(game));
//                    }
//                    else if (timeLeftUntilKo <=30 && timeLeftUntilKo >=25){
//                        int  n = rand.nextInt(6) + 1;
//                        if (n == 1){
//                            performThirtyMinuteTweet(getThirtyMinuteTweet(game));
//                        }
//                    }
//
//                }
//                else if (ko_time_hours_int +1 == time_now_hours_int){
//
//                    timeLeftUntilKo = ko_time_minutes_int - (time_now_minutes_int + 60);
//
//                    if (timeLeftUntilKo <= -45 && timeLeftUntilKo > -50){
//                        performHTTweet(getHTTweet(game));
//                    }
//                }
//
//            }
//        }
//    }

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
