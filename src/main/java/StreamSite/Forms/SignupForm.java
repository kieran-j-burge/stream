package summit.Forms;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignupForm {

    @NotNull
    @Size(min = 3, message = "Enter a name that's 3 characters or longer")
    private String name;

    @NotNull
    @Email
    @Size(min = 3, message = "Enter an email with the correct format example@example.com")
    private String email;

    @NotNull
    @Size(min = 5, message = "Enter a password that's 5 characters or longer")
    private String password;
}
