package tns.shapechallenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tns.shapechallenge.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserService userService;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User appUser = this.userService.findByUserName(userName);
 
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
 
        List<String> roleNames = new ArrayList<>(appUser.getRoles());
        
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(appUser.getUsername(), //
                appUser.getPassword(), grantList);
 
        return userDetails;
    }
 
}