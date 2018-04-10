package StreamSite.DAO.Impl;

import StreamSite.DAO.GameDAO;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.MainEvent;
import StreamSite.DTO.TweetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Autowired
    public GameDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<GameInfo> getHomePageGames() {

        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', (SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game WHERE visable = 1 ORDER BY ko_time LIMIT 100;",
                new Object[]{},
                (rs, rowNum) -> gameInfoList.add(new GameInfo(
                                rs.getInt("game_id"),
                                rs.getInt("home"),
                                rs.getInt("away"),
                                rs.getString("HomeTeam"),
                                rs.getString("AwayTeam"),
                                rs.getString("HomeImg"),
                                rs.getString("AwayImg"),
                                sdf.format(rs.getTime("ko_time")),
                                rs.getString("ko_time_date"))
                ));
        return gameInfoList;
    }

    @Override
    public GameInfo getGameById(int id) {

        return jdbcTemplate.queryForObject("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam',(SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time  FROM game WHERE game_id = ? ORDER BY ko_time",
                new Object[]{id},
                (rs, rowNum) -> new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date")
                ));

    }

    @Override
    public List<GameInfo> getGameByLeagueId(int id) {
        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam',(SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game WHERE `league_id` = ? AND visable = 1 ORDER BY ko_time",
                new Object[]{id},
                (rs, rowNum) -> gameInfoList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date"))


                ));
        return gameInfoList;
    }

    @Override
    public List<GameInfo> getGamesBySearch(String game) {
        List<GameInfo> gameSearchList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam',(SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game WHERE visable = 1 AND home IN (SELECT club_id from clubs where name like ? ) OR away IN (SELECT club_id from clubs where name like ?) AND visable = 1 ORDER BY ko_time",
                new Object[]{game,game},
                (rs, rowNum) -> gameSearchList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date"))
                ));
        return gameSearchList;
    }

    @Override
    public List<GameInfo> getGamesBySearchByLeague(String game, int id) {
        List<GameInfo> gameSearchList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam',(SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game WHERE visable = 1 AND league_id =? AND  home IN (SELECT club_id from clubs where name like ? ) OR  visable = 1 AND league_id =? AND away IN (SELECT club_id from clubs where name like ?) ORDER BY ko_time;",
                new Object[]{id,game,id,game},
                (rs, rowNum) -> gameSearchList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date"))
                ));
        return gameSearchList;
    }

    @Override
    public List<GameInfo> getGameHistory() {
        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', (SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game ORDER BY ko_time;",
                new Object[]{},
                (rs, rowNum) -> gameInfoList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date"))
                ));
        return gameInfoList;
    }

    @Override
    public GameInfo getMainEventGameInfo(Integer id) {
        return jdbcTemplate.queryForObject("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', (SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game  WHERE game_id = ?",
                new Object[]{id},
                (rs, rowNum) -> new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date"))
                );
    }

    @Override
    public MainEvent findMainEvent() {
        try {

            return jdbcTemplate.queryForObject("SELECT game_id,code FROM main_event WHERE live = 1",
                    new Object[]{},
                    (rs, rowNum) -> new MainEvent(
                            rs.getInt("game_id"),
                            rs.getString("code"))
            );

            } catch (Exception e){
            return null;
            }

    }

    @Override
    public TweetInfo getTweetMessage() {
        return jdbcTemplate.queryForObject("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', game.ko_time, date(game.ko_time) AS game_date FROM game ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) -> new TweetInfo(
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        sdf.format(rs.getTime("ko_time"))
                ));
    }

    @Override
    public List<GameInfo> getHomePageGamesShort() {
        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', (SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game WHERE visable = 1 ORDER BY ko_time LIMIT 10;",
                new Object[]{},
                (rs, rowNum) -> gameInfoList.add(new GameInfo(
                        rs.getInt("game_id"),
                        rs.getInt("home"),
                        rs.getInt("away"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("HomeImg"),
                        rs.getString("AwayImg"),
                        sdf.format(rs.getTime("ko_time")),
                        rs.getString("ko_time_date"))
                ));
        return gameInfoList;
    }
}
