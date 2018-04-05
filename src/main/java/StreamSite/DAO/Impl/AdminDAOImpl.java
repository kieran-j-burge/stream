package StreamSite.DAO.Impl;

import StreamSite.DAO.AdminDAO;
import StreamSite.DAO.StreamDAO;
import StreamSite.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDAOImpl implements AdminDAO {

    private JdbcTemplate jdbcTemplate;

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
        jdbcTemplate.query("SELECT club_id, name from clubs where name like ?",
                new Object[]{club},
                (rs, rowNum) -> clubSearchList.add(new Club(
                        rs.getInt("club_id"),
                        rs.getString("name"))
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
}
