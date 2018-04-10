package StreamSite.Services;


import StreamSite.DTO.Club;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.League;

import java.sql.Date;
import java.util.List;

public interface AdminService {
   void updatePageCount(int id);
   void addClub(String club,int img);
   List<Club> searchForClub(String club);
   List<League> searchForLeague(String league);
   void addGame(int home,int away,int league, String dateTime);
   void removeGameById(int gameId);
   void addStreamToGame(int gameId,String link);
   void postFeedback(String msg);
//   void getTweet();
   void hideFinishedGames();
   void addMsgEnd(String msg,int id);
   void addMsgBgn(String msg,int id);
   void checkForTweet();
   void setMainEvent(int id,String code);
   void mainEventOff();
}
