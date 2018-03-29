package StreamSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;
    private String email;
    private String password;

    public Account(Integer id, String email, String password, Integer fkType) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}