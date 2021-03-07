package app.shopping.entity.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AccountDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
