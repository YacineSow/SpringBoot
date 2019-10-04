package com.example.djibyette.controller;

import com.example.djibyette.model.*;
import com.example.djibyette.repository.CompteRepository;
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
@RequestMapping(value = "/partenaire")
public class PartenaireController {
    @Autowired
    private PartenaireRepository partenaireRepository;
    @GetMapping(value = "/liste")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Partenaire> liste(){
        return partenaireRepository.findAll();
    }

    @Autowired
     private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private CompteRepository compteRepository;
    @PostMapping(value = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String add(@RequestBody(required = false) RegisterPart r){
        Partenaire p = new Partenaire();
        p.setEntreprise(r.getEntreprise());
        p.setRaisonsocial(r.getRaisonsocial());
        p.setNinea(r.getNinea());
        p.setAdressesociete(r.getAdressesociete());
        p.setStatus(r.getStatus());

        partenaireRepository.save(p);

        Compte c = new Compte();
        c.setNumcompte(r.getNumcompte());
        c.setSolde(r.getSolde());
        c.setPartenaire(p);
        compteRepository.save(c);

        User u = new User();
        u.setUsername(r.getUsername());
        u.setPassword(encoder.encode(r.getPassword()));
        u.setPrenom(r.getPrenom());
        u.setNom(r.getNom());
        u.setMail(r.getMail());
        u.setTelephone(r.getTelephone());
        u.setAdresse(r.getAdresse());
        u.setStatut(r.getStatut());
        u.setCni(r.getCni());
        u.setPartenaire(p);
        u.setCompte(c);

        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        role.setId(r.getProfil());
        roles.add(role);
        u.setRoles(roles);

        userRepository.save(u);



        return "Partenaire succés";
    }
}
