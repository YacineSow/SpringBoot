package com.example.djibyette.controller;

import com.example.djibyette.model.Compte;
import com.example.djibyette.model.Partenaire;
import com.example.djibyette.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/compte")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;
    @GetMapping(value = "/listecompte")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Compte> liste(){
        return compteRepository.findAll();
    }

    @PostMapping(value = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String add(@RequestBody(required = false) Compte cp){
        Compte c = new Compte();
        SimpleDateFormat formater = null;

        formater = new SimpleDateFormat("ssyyyyMMddHHmm");
        Date now=new Date();
        String numcompte = formater.format(now);
        c.setNumcompte(numcompte);
        c.setSolde(cp.getSolde());
        Partenaire partenaire = new Partenaire();
        partenaire.setId(cp.getId());
        c.setPartenaire(partenaire);
        compteRepository.save(c);

        return "Compte succes";
    }

}
