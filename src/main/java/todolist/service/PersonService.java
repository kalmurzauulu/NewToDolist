package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import todolist.dto.request.PersonRequest;
import todolist.dto.response.PersonResponse;
import todolist.entity.Person;
import todolist.repository.PersonRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) personRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User with email not found"));

    }

    public PersonResponse create(PersonRequest personRequest){
        Person person = mapToEntity(personRequest);
        personRepository.save(person);
        return mapToResponse(person);
    }
    public PersonResponse update(long id,PersonRequest personRequest){
        Optional<Person> person = personRepository.findById(id);
        mapToUpdate(person.get(),personRequest);
        return mapToResponse(personRepository.save(person.get()));
    }

    public PersonResponse getById(long id){
        Optional<Person> person = personRepository.findById(id);
        return mapToResponse(personRepository.findById(id).get());
    }

    public PersonResponse delete(long id){
        Person person = personRepository.findById(id).get();
        personRepository.deleteById(id);
        return mapToResponse(person);
    }

   public Person mapToEntity(PersonRequest personRequest){
       Person person = new Person();
       person.setUsername(personRequest.getUsername());
       person.setPassword(personRequest.getPassword());
       return person;
   }

   public Person mapToUpdate(Person person,PersonRequest personRequest){
       person.setUsername(personRequest.getUsername());
       person.setPassword(personRequest.getPassword());
       return person;
   }

   public PersonResponse mapToResponse(Person person){
       PersonResponse personResponse = new PersonResponse();
       personResponse.setUsername(person.getUsername());
       personResponse.setPassword(person.getPassword());
       return personResponse;
   }
}
