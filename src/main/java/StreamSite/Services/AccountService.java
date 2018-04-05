package StreamSite.Services;

import StreamSite.DTO.Account;

public interface AccountService {

    Account findByEmailAndPassword(String email, String password);
    Boolean createAccount(String email, String password, Integer foreignKey);
    String generatePassword(Integer length);
    Boolean changePassword(String currentPassword, String newPassword, Integer accountId);
}
