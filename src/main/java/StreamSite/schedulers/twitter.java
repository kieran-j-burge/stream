package StreamSite.schedulers;


import StreamSite.Services.AdminService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.ConfigurationContext;

@Component
public class twitter {

    private AdminService adminService;

    public twitter(AdminService adminService) {
        this.adminService = adminService;
    }


    @Scheduled(cron="0 */5 * * * *")
    public void doTweet() {
        try {
            adminService.checkForTweet();

        } catch (Exception e){

        }
    }


    @Scheduled(cron="0 */10 * * * *")
    public void doVisabilityCheck() {
        try {
            adminService.hideFinishedGames();

        } catch (Exception e){

        }
    }

    @Scheduled(cron="0 */50 * * * *")
    public void doChannelTweet() {
        try {
            adminService.tweetChannel();

        } catch (Exception e){

        }
    }

}
