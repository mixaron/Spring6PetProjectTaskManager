package ru.mixaron.spring.taskmanager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mixaron.spring.taskmanager.models.Person;
import ru.mixaron.spring.taskmanager.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailService  implements UserDetailsService {

    private final PersonService personService;


    public PersonDetailService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personService.SearchByName(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(person.get());
    }
}
