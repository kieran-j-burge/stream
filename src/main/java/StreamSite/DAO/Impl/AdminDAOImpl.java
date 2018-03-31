package StreamSite.DAO.Impl;

import StreamSite.DAO.AdminDAO;
import StreamSite.DAO.StreamDAO;
import StreamSite.DTO.Channel;
import StreamSite.DTO.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}
