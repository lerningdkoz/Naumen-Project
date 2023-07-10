package com.example.securingweb.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String nameOrg;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String description;


    @Getter
    @Setter
    private String image;

    @Getter
    @Setter
    @ManyToOne
    private User author;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Feedback> feedbackList;


}
