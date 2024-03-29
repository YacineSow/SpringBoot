package com.example.djibyette.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private com.example.djibyette.model.RoleName name;

    public Role() {}

    public Role(com.example.djibyette.model.RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.example.djibyette.model.RoleName getName() {
        return name;
    }

    public void setName(com.example.djibyette.model.RoleName name) {
        this.name = name;
    }
}