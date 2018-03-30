package StreamSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameInfo {

    private Integer game_id;
    private int homeID;
    private int awayID;
    private String home;
    private String away;
    private String homeImg;
    private String awayImg;
    private String ko_time;

}
