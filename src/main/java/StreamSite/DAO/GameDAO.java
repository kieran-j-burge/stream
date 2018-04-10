package StreamSite.DAO;


import StreamSite.DTO.GameInfo;
import StreamSite.DTO.MainEvent;
import StreamSite.DTO.TweetInfo;

import java.util.List;

public interface GameDAO {

    List<GameInfo> getHomePageGames();
    GameInfo getGameById(int id);
    List<GameInfo> getGameByLeagueId(int id);
    List<GameInfo> getGamesBySearch(String game);
    TweetInfo getTweetMessage();
    List<GameInfo> getHomePageGamesShort();
    List<GameInfo> getGamesBySearchByLeague(String game, int id);
    List<GameInfo> getGameHistory();
    GameInfo getMainEventGameInfo(Integer id);
    MainEvent findMainEvent();
}
