package StreamSite.Controllers;

import StreamSite.Services.GameService;
import StreamSite.Services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminController {

    private GameService gameService;
    private StreamService streamService;

    @Autowired
    public AdminController(GameService gameService, StreamService streamService) {
        this.gameService = gameService;
        this.streamService = streamService;
    }

    @RequestMapping(value = "/login", method = GET)
    public String loginPage(Model model, HttpSession session){


        return "webpage/login";
    }

    @RequestMapping(value = "/login", method = POST)
    public String logIn(Model model, HttpSession session){


        return "webpage/admin";
    }

    @RequestMapping(value = "/admin/add-games", method = GET)
    public String addGames(Model model, HttpSession session){

        return "webpage/add-games";
    }

    @RequestMapping(value = "/admin/add-links", method = GET)
    public String addLinks(Model model, HttpSession session){
        model.addAttribute("game_list", gameService.getHomePageGames());
        return "webpage/add-links";
    }

    @RequestMapping(value = "/admin/remove-games", method = GET)
    public String removeGames(Model model, HttpSession session){

        return "webpage/remove-games";
    }

    @RequestMapping(value = "/admin/add-clubs", method = GET)
    public String addClubs(Model model, HttpSession session){

        return "webpage/add-clubs";
    }



}
