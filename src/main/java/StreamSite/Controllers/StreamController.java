package StreamSite.Controllers;

import StreamSite.Services.AdminService;
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
    private AdminService adminService;

    @Autowired
    public StreamController(GameService gameService, StreamService streamService, AdminService adminService) {

        this.gameService = gameService;
        this.streamService = streamService;
        this.adminService= adminService;


    }

    @RequestMapping(value = "/stream_page/{id}", method = GET)
    public String getStream(Model model, HttpSession session,
                                         @PathVariable("id") int id){

        try {
            model.addAttribute("stream_info", streamService.getStreamById(id));
            model.addAttribute("channel_list", streamService.getStreamChannel(id));

            return "webpage/stream_page";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/channel/{id}", method = GET)
    public String getChannelStream(Model model, HttpSession session,
                            @PathVariable("id") int id){

        try {
            if (streamService.getChannel(id).getLive() == 1){
                model.addAttribute("channel_info", streamService.getChannel(id));
                return "webpage/channel_stream";

            }
            else{
                return "webpage/no-channel-stream";
            }


        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/gen_stream_page/{id}", method = GET)
    public String getGenStream(Model model, HttpSession session,
                                         @PathVariable("id") int id){

//        try {
        model.addAttribute("stream_info", streamService.getGenStreamById(id));
        model.addAttribute("channel_list", streamService.getStreamChannel(id));

        return "webpage/stream_page";

//        } catch (Exception e){

//            return "webpage/error_page";
//        }

    }

}
