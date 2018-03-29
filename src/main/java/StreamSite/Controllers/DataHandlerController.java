package StreamSite.Controllers;

import StreamSite.Services.GameService;
import StreamSite.Services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class DataHandlerController {

    private StreamService streamService;

    @Autowired
    public DataHandlerController(StreamService streamService) {
        this.streamService = streamService;
    }


    @RequestMapping(value = "/vote/up/{game}/{stream}", method = POST)
    public String voteStreamUp(Model model, HttpSession session,
                                  @PathVariable("game") int game, @PathVariable("stream") int stream){
        try {
            model.addAttribute("message", streamService.voteUp(game,stream));

            return "fragments :: vote_message";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/vote/down/{game}/{stream}", method = POST)
    public String voteStreamDown(Model model, HttpSession session,
                                  @PathVariable("game") int game, @PathVariable("stream") int stream){
        try {
            model.addAttribute("message", streamService.voteDown(game,stream));

            return "fragments :: vote_message";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

}
