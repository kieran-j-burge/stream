package StreamSite.Services;


import StreamSite.DTO.GameInfo;
import StreamSite.DTO.MainEvent;

import java.util.List;

public interface GameService {
   List<GameInfo> getHomePageGames();
   List<GameInfo> getHomePageGamesShort();
   GameInfo getGameById(int id);
   List<GameInfo> getGameByLeagueId(int id);
   List<GameInfo> getGamesBySearch(String game);
   String getTweetMessage();
   List<GameInfo> getGamesBySearchByLeague(String game, int id);
   List<GameInfo> getGameHistory();
   GameInfo getMainEventGameInfo();
    MainEvent getMainEvent();
    GameInfo checkMainEvent(int id);
}
