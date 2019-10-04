package com.example.djibyette.controller;

import com.example.djibyette.model.Compte;
import com.example.djibyette.model.Depot;
import com.example.djibyette.model.Partenaire;
import com.example.djibyette.repository.CompteRepository;
import com.example.djibyette.repository.DepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/depot")
public class DepotController {
    @Autowired
    private DepotRepository depotRepository;
    @Autowired
    private  CompteRepository compteRepository;
    @GetMapping(value = "/liste")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Depot> liste(){
        return depotRepository.findAll();
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String add( RegisterDepot rd) throws Exception
    {
        if (rd.getMontant()>= 75000){
            Depot d = new Depot();
            d.setDate(new Date());
            d.setMontant(rd.getMontant());
            Compte compte = compteRepository.findCompteByNumcompte(rd.getNumcompte()).orElseThrow(
                    () -> new UsernameNotFoundException("numcompte n'existe pas"));
            d.setCompte(compte);
            compte.setSolde(rd.getMontant()+compte.getSolde());
            compteRepository.save(compte);
            depotRepository.save(d);
            return "Dépot effectué";
        }
        else {
            return "le montant doit etre superieur à 75000";
        }
    }
}
