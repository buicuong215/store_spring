package com.example.admin.config;

import com.example.lib.models.Admin;
import com.example.lib.repository.AdminRepository;
import com.example.lib.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;



public class AdminConfig implements UserDetailsService {
    @Autowired
    private  AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminRepository.findByUsername(username);
        if(admin==null){
            throw new UsernameNotFoundException("Could not find username!");
        }

        return new User(admin.getUsername(),
                admin.getPassword(),
                admin.getRoles()
                        .stream()
                        .map(role->new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }
}
