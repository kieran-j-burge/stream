package StreamSite.Controllers;

import StreamSite.Services.GameService;
import StreamSite.Services.LeagueService;
import StreamSite.Services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class StreamController {

    private GameService gameService;
    private StreamService streamService;

    @Autowired
    public StreamController(GameService gameService, StreamService streamService) {

        this.gameService = gameService;
        this.streamService = streamService;


    }

    @RequestMapping(value = "/stream_page/{id}", method = GET)
    public String retreiveGamesForLeague(Model model, HttpSession session,
                                         @PathVariable("id") int id){

        try {
            model.addAttribute("stream_info", streamService.getStreamById(id));
            model.addAttribute("channel_list", streamService.getStreamChannel(id));

            return "webpage/stream_page";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

}
