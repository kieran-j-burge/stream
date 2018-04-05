package StreamSite.Controllers;

import StreamSite.DTO.Account;
import StreamSite.DTO.Admin;
import StreamSite.Services.AdminService;
import StreamSite.Services.GameService;
import StreamSite.Services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminController {

    private GameService gameService;
    private StreamService streamService;
    private AdminService adminService;

    @Autowired
    public AdminController(GameService gameService, StreamService streamService,AdminService adminService) {
        this.gameService = gameService;
        this.streamService = streamService;
        this.adminService = adminService;
    }

//    @RequestMapping(value = "/login", method = GET)
//    public String loginPage(Model model, HttpSession session){
//
//
//        return "webpage/login";
//    }

    @RequestMapping(value = "/admin", method = GET)
    public String logIn(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            return "webpage/admin";
        }
        else{
            return "redirect:/";

        }

    }

    @RequestMapping(value = "/admin/add-games", method = GET)
    public String addGames(Model model, HttpSession session){
        model.addAttribute("game_list", gameService.getHomePageGames());

        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            return "webpage/add-games";
        }
        else{
            return "redirect:/";

        }
    }

    @RequestMapping(value = "/admin/add-links", method = GET)
    public String addLinks(Model model, HttpSession session){
        model.addAttribute("game_list", gameService.getHomePageGames());
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            return "webpage/add-links";
        }
        else{
            return "redirect:/";

        }
    }

    @RequestMapping(value = "/admin/game/streams/{gameId}", method = GET)
    public String addLinksToGamePage(Model model, HttpSession session, @PathVariable("gameId") int gameId){

        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            model.addAttribute("game_info", gameService.getGameById(gameId));
            return "webpage/add-links-game";
        }
        else{
            return "redirect:/";

        }
    }



    @RequestMapping(value = "/admin/add-stream/{gameId}/{link}", method = GET)
    public String addLinksToGame(Model model, HttpSession session, @PathVariable("gameId") int gameId,
                                 @PathVariable("link") String link){
        link = link.replaceAll("£",".");
        link = link.replaceAll("`","/");

        link = "https://" + link;
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            adminService.addStreamToGame(gameId,link);
            return "fragments :: stream-added-msg";
        }
        else{
            return "redirect:/";

        }
    }



    @RequestMapping(value = "/admin/remove-games", method = GET)
    public String removeGames(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            model.addAttribute("game_list",gameService.getHomePageGames());
            return "webpage/remove-games";

        }
        else{
            return "redirect:/";

        }

    }

    @RequestMapping(value = "/admin/remove-game/{gameId}", method = GET)
    public String removeGameById( Model model, HttpSession session, @PathVariable("gameId") int gameId){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            adminService.removeGameById(gameId);
            model.addAttribute("rmv-game-msg","Game Removed");
            return "fragments :: rmv-game-msg";
        }
        else{
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/admin/add-clubs", method = GET)
    public String addClubs(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            return "webpage/add-clubs";

        }
        else{
            return "redirect:/";

        }

    }


    @RequestMapping(value = "/admin/add-club/{club}/{img}", method = POST)
    public void addClubs(HttpSession session, @PathVariable("club") String club
    , @PathVariable("img") int img){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
        }
        else if (account.getPermissions() == 1){
            adminService.addClub(club,img);

        }
        else{

        }


    }

    @RequestMapping(value = "/search/club/{club}", method = GET)
    public String searchClubs(Model model, HttpSession session, @PathVariable("club") String club
            ){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("club_list", adminService.searchForClub(club));

            return "fragments :: club-return-list";

        }
        else{
            return "redirect:/";
        }

    }



    @RequestMapping(value = "/league/search/admin/{league}", method = GET)
    public String searchLeagues(Model model, HttpSession session, @PathVariable("league") String league
    ){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("league_list", adminService.searchForLeague(league));

            return "fragments :: league-return-list";

        }
        else{
            return "redirect:/";
        }

    }


    @RequestMapping(value = "/add/game/{home}&{away}&{league}&{dateTime}", method = POST)
    public String addGame(Model model, HttpSession session, @PathVariable("home") int home,
                          @PathVariable("away") int away , @PathVariable("league") int league,
                          @PathVariable("dateTime") String dateTime
    ){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){

            adminService.addGame(home,away,league,dateTime);
            return "fragments :: league-return-list";

        }
        else{
            return "redirect:/";
        }



    }


}
