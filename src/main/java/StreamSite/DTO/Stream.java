package StreamSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stream {

    private int stream_id;
    private String url;
    private int vote;

}
