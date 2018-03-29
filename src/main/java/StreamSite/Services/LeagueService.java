package StreamSite.Services;

import StreamSite.DTO.GameInfo;
import StreamSite.DTO.League;

import java.util.List;

public interface LeagueService {
    List<League> getLeagueList();
    List<League> getLeaguesBySearch(String league);
    List<League> getHomePageGames();
}
