package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todolist.config.JwtTokenUtil;
import todolist.dto.authDto.AuthMapper;
import todolist.dto.authDto.AuthRequest;
import todolist.dto.authDto.AuthResponse;
import todolist.dto.authDto.ExceptionType;
import todolist.dto.request.PersonRequest;
import todolist.dto.response.PersonResponse;
import todolist.entity.Person;
import todolist.repository.PersonRepository;
import todolist.service.PersonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {
    private final PersonService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PersonRepository personrepository;
    private final AuthMapper authMapper;
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            Person person = personrepository.findByEmail(authenticationToken.getName()).get();
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(person.getUsername()), ExceptionType.SUCCESFULLY,person));
        }
        catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view(" ", ExceptionType.LOGIN_FAILED,null));
        }
    }
    @PostMapping("registration")
    public PersonResponse create(@RequestBody PersonRequest personRequest){
        return userService.create(personRequest);
    }
}
