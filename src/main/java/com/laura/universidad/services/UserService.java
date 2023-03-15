package com.laura.universidad.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laura.universidad.models.Permission;
import com.laura.universidad.models.User;
import com.laura.universidad.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User usuario = userRepository.findByName(username);

        List<Permission> permisosUsuario = usuario.getPermissions();
        List<GrantedAuthority> permisos = new ArrayList<GrantedAuthority>(permisosUsuario.size());
        for (Permission permiso : permisosUsuario){
            permisos.add(new SimpleGrantedAuthority(permiso.getName()));
        }

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getName())
            .password(usuario.getPassword())
            .authorities(permisos)
            .build();
        
        return user;
    }
}
