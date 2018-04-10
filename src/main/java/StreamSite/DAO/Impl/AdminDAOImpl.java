package StreamSite.DAO.Impl;

import StreamSite.DAO.AdminDAO;
import StreamSite.DAO.StreamDAO;
import StreamSite.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDAOImpl implements AdminDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    @Autowired
    public AdminDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public void updatePageCount(int id) {
        jdbcTemplate.update("UPDATE page SET count = count + 1 WHERE page_id = ?", new Object[]{id});
    }

    @Override
    public void addClub(String club, int img) {
        jdbcTemplate.update("INSERT INTO clubs (name,image) VALUES (?,?) ",club, img);
    }

    @Override
    public List<Club> searchForClub(String club) {
        List<Club> clubSearchList = new ArrayList<>();
        jdbcTemplate.query("SELECT club_id, name, image from clubs where name like ?",
                new Object[]{club},
                (rs, rowNum) -> clubSearchList.add(new Club(
                        rs.getInt("club_id"),
                        rs.getString("name"),
                        rs.getString("image"))
                ));
        return clubSearchList;
    }

    @Override
    public List<League> searchForLeague(String league) {
        List<League> leagueSearchList = new ArrayList<>();
        jdbcTemplate.query("SELECT league_id, name, img from leagues where name like ?",
                new Object[]{league},
                (rs, rowNum) -> leagueSearchList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getString("img"))
                ));
        return leagueSearchList;
    }

    @Override
    public void addClub(int home, int away, int league, String dateTime) {

        jdbcTemplate.update("INSERT INTO game (home,away,ko_time,league_id,visable) VALUES (?,?,?,?,1) ",home, away, dateTime,league);

    }

    @Override
    public void removeGameById(int gameId) {
        jdbcTemplate.update("UPDATE game SET visable = 0 WHERE game_id = ? ",gameId);
    }

    @Override
    public void addStreamToGame(int gameId, String link) {
        jdbcTemplate.update("INSERT INTO streams (url,game_id,vote) VALUES (?,?,0) ",link,gameId);
    }

    @Override
    public void postFeedback(String msg) {
        jdbcTemplate.update("INSERT INTO feedback (message) VALUES (?) ",msg);
    }

    @Override
    public List<GameInfo> getTweet() {
        List<GameInfo> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.home, game.away, CAST(ko_time AS DATE) as ko_time_date , (SELECT `name` FROM clubs WHERE club_id = game.home) AS 'HomeTeam', (SELECT `name` FROM clubs WHERE club_id = game.away) AS ' AwayTeam', (SELECT image from clubs where club_id = game.home) AS 'HomeImg',(SELECT image from clubs where club_id = game.away) AS 'AwayImg', game.ko_time FROM game WHERE visable = 1 ORDER BY ko_time LIMIT 20;",
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
    public String getHTTweetMessageIntro() {
        return jdbcTemplate.queryForObject("SELECT msg FROM msg_b WHERE tweet_cat = 3 ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) ->
                        rs.getString("msg")
                );
    }

    @Override
    public String getHTTweetMessageEnd() {
        return jdbcTemplate.queryForObject("SELECT msg FROM msg_e WHERE tweet_cat = 3 ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) -> rs.getString("msg")
        );
    }

    @Override
    public String getThirtyMinuteMessageIntro() {
        return jdbcTemplate.queryForObject("SELECT msg FROM msg_b WHERE tweet_cat = 1 ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) -> rs.getString("msg")
        );
    }

    @Override
    public String getThirtyMinuteMessageEnd() {
        return jdbcTemplate.queryForObject("SELECT msg FROM msg_e WHERE tweet_cat = 1 ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) -> rs.getString("msg")
        );
    }

    @Override
    public String getFiveMinuteMessageIntro() {
        return jdbcTemplate.queryForObject("SELECT msg FROM msg_b WHERE tweet_cat = 2 ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) -> rs.getString("msg")
        );
    }

    @Override
    public String getFiveMinuteMessageEnd() {
        return jdbcTemplate.queryForObject("SELECT msg FROM msg_e WHERE tweet_cat = 2 ORDER BY RAND() LIMIT 1",
                new Object[]{},
                (rs, rowNum) -> rs.getString("msg")
        );
    }

    @Override
    public void setVisibilityToHidden(int id) {
        jdbcTemplate.update("UPDATE game SET visable = 0 WHERE game_id = ? ",id);

    }

    @Override
    public List<GameInfoShort> getGamesForVisCheck() {
        List<GameInfoShort> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.ko_time, game.league_id FROM game WHERE visable = 1 ORDER BY ko_time LIMIT 20;",
                new Object[]{},
                (rs, rowNum) -> gameInfoList.add(new GameInfoShort(
                        rs.getInt("game_id"),
                        rs.getTimestamp("ko_time"),
                        rs.getInt("league_id"))
                ));
        return gameInfoList;
    }

    @Override
    public void addMsgEnd(String msg,int id) {
        jdbcTemplate.update("INSERT INTO msg_e (msg,tweet_cat) VALUES (?,?) ", msg,id);

    }

    @Override
    public void addMsgBgn(String msg, int id) {
        jdbcTemplate.update("INSERT INTO msg_b (msg,tweet_cat) VALUES (?,?) ",msg,id);

    }

    @Override
    public List<GameInfoShort> getGamesForTweet() {
        List<GameInfoShort> gameInfoList = new ArrayList<>();
        jdbcTemplate.query("SELECT game.game_id, game.ko_time, game.league_id  FROM game WHERE visable = 1 ORDER BY ko_time LIMIT 20;",
                new Object[]{},
                (rs, rowNum) -> gameInfoList.add(new GameInfoShort(
                        rs.getInt("game_id"),
                        rs.getTimestamp("ko_time"),
                        rs.getInt("league_id"))
                ));
        return gameInfoList;
    }

    @Override
    public void setMainEvent(int id, String code) {
        jdbcTemplate.update("UPDATE main_event SET game_id = ?, code = ?,live = 1 WHERE main_event_id = 1", id,code);

    }

    @Override
    public void mainEventOff() {
        jdbcTemplate.update("UPDATE main_event SET live = 0 WHERE live = 1");
    }


}
