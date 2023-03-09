package todolist.dto.authDto;


import org.springframework.stereotype.Component;
import todolist.entity.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthMapper {
    public AuthResponse view(String token, String message, Person person){
        var authResponse = new AuthResponse();

        authResponse.setJWTToken(token);
        authResponse.setMessage(message);
        return authResponse;
    }


}


