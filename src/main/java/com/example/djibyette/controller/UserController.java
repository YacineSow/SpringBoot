package com.example.djibyette.controller;

import com.example.djibyette.model.Partenaire;
import com.example.djibyette.model.Role;
import com.example.djibyette.model.User;
import com.example.djibyette.model.Utilisateur;
import com.example.djibyette.repository.PartenaireRepository;
import com.example.djibyette.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/liste")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> liste(){
        return userRepository.findAll();
    }

    @Autowired
    PasswordEncoder encoder;
    @PostMapping(value = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User add(@RequestBody(required = false) Utilisateur s){
        User u = new User();
        u.setUsername(s.getUsername());
        u.setPassword(encoder.encode(s.getPassword()));
        u.setPrenom(s.getPrenom());
        u.setNom(s.getNom());
        u.setMail(s.getMail());
        u.setTelephone(s.getTelephone());
        u.setAdresse(s.getAdresse());
        u.setStatut(s.getStatut());
        u.setCni(s.getCni());
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        role.setId(s.getProfil());
        roles.add(role);
        u.setRoles(roles);
        return userRepository.save(u);
    }

    //public User bloquer(User u){

    //}
}
