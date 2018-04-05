package StreamSite.Controllers;

import StreamSite.Services.GameService;
import StreamSite.Services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class LeagueController {

    private LeagueService leagueService;
    private GameService gameService;

    @Autowired
    public LeagueController( LeagueService leagueService, GameService gameService) {

        this.leagueService = leagueService;
        this.gameService = gameService;

    }


    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    public String getLeagueList(Model model, HttpSession session){

        try {
            model.addAttribute("league_list", leagueService.getLeagueList());

            return "webpage/leagues";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/league/{id}", method = GET)
    public String retreiveGamesForLeague(Model model, HttpSession session,
                                   @PathVariable("id") int id){

        try {
            model.addAttribute("game_list", gameService.getGameByLeagueId(id));
            model.addAttribute("league_info",leagueService.getLeagueById(id));

            return "webpage/game_list_league";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/league/search/{league}", method = GET)
    public String retreiveGamePage(Model model, HttpSession session,
                                   @PathVariable("league") String league){

        try {
            model.addAttribute("league_list", leagueService.getLeaguesBySearch(league));

            return "fragments :: league_list";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }

    @RequestMapping(value = "/league/load_home", method = GET)
    public String retreiveHomeGames(Model model, HttpSession session){

        try {
            model.addAttribute("league_list", leagueService.getHomePageGames());
            return "fragments :: league_list";

        } catch (Exception e){

            return "webpage/error_page";
        }

    }
}
