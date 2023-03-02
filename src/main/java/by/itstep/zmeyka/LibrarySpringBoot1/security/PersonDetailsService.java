package by.itstep.zmeyka.LibrarySpringBoot1.security;


import by.itstep.zmeyka.LibrarySpringBoot1.Model.Person;
import by.itstep.zmeyka.LibrarySpringBoot1.Model.Role;
import by.itstep.zmeyka.LibrarySpringBoot1.repositories.PeopleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class PersonDetailsService implements UserDetailsService {

    private PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = peopleRepository.findByEmail(email);

        if (person != null) {
            return new org.springframework.security.core.userdetails.User(person.getEmail(),
                    person.getPassword(),
                    mapRolesToAuthorities(person.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

