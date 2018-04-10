package StreamSite.Services.Impl;

import StreamSite.DAO.GameDAO;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.MainEvent;
import StreamSite.DTO.TweetInfo;
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

        return splitDate(gameDAO.getHomePageGames());
    }

    @Override
    public List<GameInfo> getHomePageGamesShort() {
        return splitDate(gameDAO.getHomePageGamesShort());
    }

    @Override
    public GameInfo getGameById(int id) {
        return gameDAO.getGameById(id);
    }

    @Override
    public List<GameInfo> getGameByLeagueId(int id) {
        return splitDate(gameDAO.getGameByLeagueId(id));
    }

    @Override
    public List<GameInfo> getGamesBySearch(String game) {
        game = "%"+game+"%";
        return splitDate(gameDAO.getGamesBySearch(game));
    }

    @Override
    public String getTweetMessage() {
        TweetInfo game = gameDAO.getTweetMessage();
        String msg = ("Find a link to watch | " + game.getHome() +" vs "+ game.getAway() +" | on http://www.cleanstreams.co.uk/ " );
        return msg;
    }

    @Override
    public List<GameInfo> getGamesBySearchByLeague(String game, int id) {
        game = "%"+game+"%";
        return splitDate(gameDAO.getGamesBySearchByLeague(game,id));
    }

    @Override
    public List<GameInfo> getGameHistory() {
        return splitDate(gameDAO.getGameHistory());
    }

    @Override
    public GameInfo getMainEventGameInfo() {
        try{
            return gameDAO.getMainEventGameInfo(gameDAO.findMainEvent().getGame_id());

        } catch (Exception e){
        return null;
     }
    }

    @Override
    public MainEvent getMainEvent() {
        return gameDAO.findMainEvent();
    }

    private List<GameInfo> splitDate(List<GameInfo> games){

        for (GameInfo game : games){
            game.setKo_time_date(game.getKo_time_date().substring(5).replaceAll("-","/"));
        }

        return games;
    }


}
