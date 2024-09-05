package com.example.RegisterLogin.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity

@Table(name = "institution") // Optional, to specify table details
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "location", length = 255, nullable = false)
    private String location;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<Education> educations; // Assuming you have an Education entity
      // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }
}