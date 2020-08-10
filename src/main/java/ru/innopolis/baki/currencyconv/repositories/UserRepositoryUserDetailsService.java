package ru.innopolis.baki.currencyconv.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.baki.currencyconv.models.User;

import java.util.Optional;

@Service
@Qualifier("userDetailsService")
public class UserRepositoryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByLogin(username);
        if (user.isPresent()) {
            return user.map(User::new).get();
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

}
