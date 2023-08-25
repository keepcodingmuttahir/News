package com.redmath.database.application.user;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    //we will have to implement this function here because user will login through name , not id.
    public User findByUserName (String userName)
    {
        return repository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        if( user == null)
        {
            throw new UsernameNotFoundException("This user is not found" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), true, true , true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
    }
}
