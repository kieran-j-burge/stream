package StreamSite.Services.Impl;

import StreamSite.DAO.GameDAO;
import StreamSite.DAO.LeagueDAO;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.League;
import StreamSite.Services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    private LeagueDAO leagueDAO;
    private GameDAO gameDAO;

    @Autowired
    public LeagueServiceImpl(GameDAO gameDAO, LeagueDAO leagueDAO) {
        this.gameDAO = gameDAO;
        this.leagueDAO = leagueDAO;
    }


    @Override
    public List<League> getLeagueList() {
        return leagueDAO.getLeagueList();
    }

    @Override
    public List<League> getLeaguesBySearch(String league) {
        league = "%"+league+"%";
        return leagueDAO.getLeaguesBySearch(league);
    }

    @Override
    public List<League> getHomePageGames() {
        return leagueDAO.getHomePageGames();
    }

    @Override
    public League getLeagueById(int id) {
        return leagueDAO.getLeagueById(id);
    }


}
