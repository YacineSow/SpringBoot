package com.example.djibyette.repository;

import com.example.djibyette.model.Compte;
import com.example.djibyette.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRepository  extends JpaRepository<Depot, Long> {
}
