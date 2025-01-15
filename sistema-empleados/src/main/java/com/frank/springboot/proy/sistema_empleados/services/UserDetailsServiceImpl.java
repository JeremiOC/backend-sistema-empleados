package com.frank.springboot.proy.sistema_empleados.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frank.springboot.proy.sistema_empleados.entities.Usuario;
import com.frank.springboot.proy.sistema_empleados.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsername(username)
                    .orElseThrow(()-> new RuntimeException("Usuario "+username+" no encontrado"));
        Collection<? extends GrantedAuthority> authorities = usuario.getRoles()
                                .stream()
                                .map(role-> new SimpleGrantedAuthority(role.getNombre_rol()))
                                .collect(Collectors.toSet());
        return new User(usuario.getUsername(),usuario.getPassword()
                        ,true
                        ,true
                        ,true
                        ,true
                        ,authorities);



    }

}
