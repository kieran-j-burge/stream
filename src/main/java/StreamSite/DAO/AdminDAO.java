package StreamSite.DAO;


import StreamSite.DTO.*;

import java.sql.Date;
import java.util.List;

public interface AdminDAO {

    void updatePageCount(int id);
    void addClub(String club,int img);
    List<Club> searchForClub(String club);
    List<League> searchForLeague(String league);
    void addClub(int home,int away,int league,String dateTime);
    void  removeGameById(int gameId);
    void addStreamToGame(int gameId,String link);
    void postFeedback(String msg);
    List<GameInfo> getTweet();
    String getHTTweetMessageIntro();
    String getHTTweetMessageEnd();
    String getThirtyMinuteMessageIntro();
    String getThirtyMinuteMessageEnd();
    String getFiveMinuteMessageIntro();
    String getFiveMinuteMessageEnd();
    void setVisibilityToHidden(int id);
    List<GameInfoShort> getGamesForVisCheck();
    void addMsgEnd(String msg,int id);
    void addMsgBgn(String msg,int id);
    List<GameInfoShort> getGamesForTweet();
}
