package app.shopping.entity.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class LoginRequest {
    private String username;
    private String password;
}
