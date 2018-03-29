package StreamSite.DAO.Impl;

import StreamSite.DAO.GameDAO;
import StreamSite.DTO.GameInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<GameInfo> getHomePageGames() {

        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', game.ko_time FROM game LIMIT 100;",
                new Object[]{},
                (rs, rowNum) -> gameInfoList.add(new GameInfo(
                                rs.getInt("game_id"),
                                rs.getInt("home"),
                                rs.getInt("away"),
                                rs.getString("HomeTeam"),
                                rs.getString("AwayTeam"),
                                rs.getString("ko_time"))
                ));
        return gameInfoList;
    }

    @Override
    public GameInfo getGameById(int id) {

        return jdbcTemplate.queryForObject("SELECT game.game_id, game.home, game.away, (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', game.ko_time FROM game WHERE game_id = ?",
                new Object[]{id},
                (rs, rowNum) -> new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("ko_time")
                ));

    }

    @Override
    public List<GameInfo> getGameByLeagueId(int id) {
        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', game.ko_time FROM game WHERE `league_id` = ? ORDER BY `ko_time`",
                new Object[]{id},
                (rs, rowNum) -> gameInfoList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("ko_time"))


                ));
        return gameInfoList;
    }

    @Override
    public List<GameInfo> getGamesBySearch(String game) {
        List<GameInfo> gameSearchList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', game.ko_time FROM game WHERE home IN (SELECT club_id from clubs where name like ? ) OR away IN (SELECT club_id from clubs where name like ?)",
                new Object[]{game,game},
                (rs, rowNum) -> gameSearchList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("ko_time"))
                ));
        return gameSearchList;
    }
}
