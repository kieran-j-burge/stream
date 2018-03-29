package StreamSite.DAO;

import StreamSite.DTO.League;

import java.util.List;

public interface LeagueDAO {
    List<League> getLeagueList();
    List<League> getLeaguesBySearch(String league);
    List<League> getHomePageGames();
}
