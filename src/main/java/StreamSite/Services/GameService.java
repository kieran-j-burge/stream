package StreamSite.Services;


import StreamSite.DTO.GameInfo;

import java.util.List;

public interface GameService {
   List<GameInfo> getHomePageGames();
   GameInfo getGameById(int id);
   List<GameInfo> getGameByLeagueId(int id);
   List<GameInfo> getGamesBySearch(String game);
}
