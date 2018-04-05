package StreamSite.Services.Impl;

import StreamSite.DAO.AdminDAO;
import StreamSite.DAO.GameDAO;
import StreamSite.DTO.Club;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.League;
import StreamSite.DTO.TweetInfo;
import StreamSite.Services.AdminService;
import StreamSite.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO= adminDAO;
    }


    @Override
    public void updatePageCount(int id) {
    adminDAO.updatePageCount(id);
    }

    @Override
    public void addClub(String club, int img) {
        adminDAO.addClub(club,img);
    }

    @Override
    public List<Club> searchForClub(String club) {
        club= "%"+club+"%";
        return adminDAO.searchForClub(club);
    }

    @Override
    public List<League> searchForLeague(String league) {
        league= "%"+league+"%";
        return adminDAO.searchForLeague(league);
    }

    @Override
    public void addGame(int home, int away, int league, String dateTime) {
        adminDAO.addClub(home,away,league,dateTime);
    }

    @Override
    public void removeGameById(int gameId) {
        adminDAO.removeGameById(gameId);
    }

    @Override
    public void addStreamToGame(int gameId, String link) {
        adminDAO.addStreamToGame(gameId,link);
    }

    @Override
    public void postFeedback(String msg) {
        adminDAO.postFeedback(msg);
    }
}
