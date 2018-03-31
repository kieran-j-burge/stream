package StreamSite.Services.Impl;

import StreamSite.DAO.GameDAO;
import StreamSite.DAO.StreamDAO;
import StreamSite.DTO.Channel;
import StreamSite.DTO.GameInfo;
import StreamSite.DTO.Stream;
import StreamSite.Services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamServiceImpl implements StreamService {

    private StreamDAO streamDAO;

    @Autowired
    public StreamServiceImpl(StreamDAO streamDAO) {
        this.streamDAO = streamDAO;
    }

    @Override
    public List<Stream> getStreamsByGameId(int id) {
        return streamDAO.getStreamsByGameId(id);
    }

    @Override
    public String voteDown(int game, int stream) {
        return streamDAO.voteDown(game,stream);
    }

    @Override
    public String voteUp(int game, int stream) {
        return streamDAO.voteUp(game,stream);
    }

    @Override
    public Stream getStreamById(int id) {
        return streamDAO.getStreamById(id);
    }

    @Override
    public List<Channel> getStreamChannel(int id) {
        return streamDAO.getStreamChannel(id);
    }


}
