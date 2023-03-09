package todolist.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponse {
    private Long id;
    private String username;
    private String password;
}
