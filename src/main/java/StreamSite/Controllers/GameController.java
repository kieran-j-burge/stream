package StreamSite.Controllers;

import StreamSite.Services.AdminService;
import StreamSite.Services.GameService;
import StreamSite.Services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class GameController {

    private GameService gameService;
    private StreamService streamService;
    private AdminService adminService;

    @Autowired
    public GameController(GameService gameService, StreamService streamService, AdminService adminService) {
        this.gameService = gameService;
        this.streamService = streamService;
        this.adminService = adminService;
    }


    @RequestMapping(value = "/game/{id}", method = GET)
    public String retreiveGamePage(Model model, HttpSession session,
                                   @PathVariable("id") int id){
        adminService.updatePageCount(2);
        try {
            model.addAttribute("game_info", gameService.getGameById(id));
            model.addAttribute("stream_list", streamService.getStreamsByGameId(id));

            return "webpage/game";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/search/{game}", method = GET)
    public String retreiveGamePage(Model model, HttpSession session,
                                   @PathVariable("game") String game){

        try {
            model.addAttribute("game_list", gameService.getGamesBySearch(game));

            return "fragments :: game_list";

        } catch (Exception e){

            return "webpage/error_page";
        }


    }

    @RequestMapping(value = "/load_home", method = GET)
    public String retreiveHomeGames(Model model, HttpSession session){
        try {

            model.addAttribute("game_list", gameService.getHomePageGames());
            return "fragments :: game_list";

        } catch (Exception e){

            return "webpage/error_page";
        }



    }

    @RequestMapping(value = "/admin/search/{game}", method = GET)
    public String searchAdminGame(Model model, HttpSession session,
                                   @PathVariable("game") String game){
        try {

            model.addAttribute("game_list", gameService.getGamesBySearch(game));

            return "fragments :: admin_page_game_list";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }



    @RequestMapping(value = "/admin/load_home", method = GET)
    public String retreiveAdminHomeGames(Model model, HttpSession session){

        try {

            model.addAttribute("game_list", gameService.getHomePageGames());
            return "fragments :: admin_page_game_list";

        } catch (Exception e){

            return "webpage/error_page";
        }


    }



}
