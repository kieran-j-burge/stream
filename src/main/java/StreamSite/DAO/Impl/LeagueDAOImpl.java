package StreamSite.DAO.Impl;


import StreamSite.DAO.LeagueDAO;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.League;
import StreamSite.DTO.TweetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LeagueDAOImpl implements LeagueDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LeagueDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<League> getLeagueList() {

        List<League> leagueList= new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM `leagues`; ",
                new Object[]{},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getString("img"))
                ));
        return leagueList;

    }

    @Override
    public List<League> getLeaguesBySearch(String league) {
        List<League> leagueSearchList = new ArrayList<>();
        jdbcTemplate.query("SELECT `league_id`, `name`, `img` FROM leagues WHERE `name` LIKE ?;",
                new Object[]{league},
                (rs, rowNum) -> leagueSearchList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getString("img"))
                ));
        return leagueSearchList;
    }

    @Override
    public List<League> getHomePageGames() {
        List<League> leagueList = new ArrayList<>();
        jdbcTemplate.query("SELECT `league_id`, `name`, `img` FROM leagues;",
                new Object[]{},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getString("img"))
                ));
        return leagueList;
    }

    @Override
    public League getLeagueById(int id) {
        return jdbcTemplate.queryForObject("SELECT league_id,name,img FROM leagues WHERE league_id =? ",
                new Object[]{id},
                (rs, rowNum) -> new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getString("img"))
                );
    }
}
