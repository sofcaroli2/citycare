package it.unibo.citycare.reports1.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class Users {
    @Id
    private UUID user_id;

    private String username;

    private String name;

    private String surname;

    private String residence;

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

}
