package StreamSite.DAO.Impl;

import StreamSite.DAO.StreamDAO;
import StreamSite.DTO.Channel;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StreamDAOImpl implements StreamDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StreamDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stream> getStreamsByGameId(int id) {
        List<Stream> streamList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM `streams` WHERE `game_id` = ? ORDER BY vote DESC; ",
                new Object[]{id},
                (rs, rowNum) -> streamList.add(new Stream(
                        rs.getInt("stream_id"),
                        rs.getString("url"),
                        rs.getInt("vote"))


                ));
        return streamList;
    }

    @Override
    public String voteDown(int game, int stream) {
        jdbcTemplate.update("UPDATE streams SET vote = vote - 1 WHERE game_id = ? AND stream_id = ?", new Object[]{game, stream});
        return "Thanks For The Feedback";
    }

    @Override
    public String voteUp(int game, int stream) {
        jdbcTemplate.update("UPDATE streams SET vote = vote + 1 WHERE game_id = ? AND stream_id = ?", new Object[]{game, stream});
        return "Thanks For The Feedback";
    }

    @Override
    public Stream getStreamById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM `streams` WHERE `stream_id` = ?; ",
                new Object[]{id},
                (rs, rowNum) -> new Stream(
                        rs.getInt("stream_id"),
                        rs.getString("url"),
                        rs.getInt("vote"))
                );
    }

    @Override
    public List<Channel> getStreamChannel(int id) {
        List<Channel> channelList = new ArrayList<>();
        jdbcTemplate.query("SELECT `img` FROM `channel` WHERE channel_id IN (SELECT `channel_id` FROM `stream_channel` WHERE `stream_id` = ?); ",
                new Object[]{id},
                (rs, rowNum) -> channelList.add(new Channel(
                        rs.getString("img"))


                ));
        return channelList;
    }

    @Override
    public List<Stream> getGenStreamsByGameId(int id) {
        List<Stream> streamList = new ArrayList<>();
        jdbcTemplate.query("SELECT  * from gen_streams where gen_stream_id in (Select gen_stream_id from gen_cat_gen_stream where gen_stream_cat_id = (select generic_stream_id from generic_stream_cat where league_id = (SELECT league_id from leagues where league_id = (SELECT league_id from game where game_id = ?)))); ",
                new Object[]{id},
                (rs, rowNum) -> streamList.add(new Stream(
                        rs.getInt("gen_stream_id"),
                        rs.getString("url"),
                        rs.getInt("vote"))


                ));
        return streamList;
    }
}
