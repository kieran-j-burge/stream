package StreamSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameInfoShort {

    private Integer game_id;
    private Timestamp ko_time_date;
    private int league_id;

}
