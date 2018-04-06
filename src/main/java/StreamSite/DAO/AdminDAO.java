package StreamSite.DAO;


import StreamSite.DTO.Club;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.League;
import StreamSite.DTO.TweetInfo;

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
}
