package StreamSite.Services.Impl;

import StreamSite.DAO.GameDAO;
import StreamSite.DTO.GameInfo;
import StreamSite.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameDAO gameDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }


    @Override
    public List<GameInfo> getHomePageGames() {

        return gameDAO.getHomePageGames();
    }

    @Override
    public GameInfo getGameById(int id) {
        return gameDAO.getGameById(id);
    }

    @Override
    public List<GameInfo> getGameByLeagueId(int id) {
        return gameDAO.getGameByLeagueId(id);
    }

    @Override
    public List<GameInfo> getGamesBySearch(String game) {
        game = "%"+game+"%";
        return gameDAO.getGamesBySearch(game);
    }


}
