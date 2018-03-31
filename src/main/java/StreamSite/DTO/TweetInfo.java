package StreamSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetInfo {

    private String home;
    private String away;
    private String ko_time;
}
