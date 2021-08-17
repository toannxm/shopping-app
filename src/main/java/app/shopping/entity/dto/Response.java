package app.shopping.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class Response {
    private String jwt;
    private String message;
}
