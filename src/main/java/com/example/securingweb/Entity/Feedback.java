package com.example.securingweb.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity()
@Table(name = "feedback")
public class Feedback {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String nameOrg;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String type;


    @Getter
    @Setter
    private Integer rait;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Getter
    @Setter
    @ManyToOne
    private Review review;



    @Getter
    @Setter
    private LocalDateTime dateTime;

    public void inti(){
        dateTime = LocalDateTime.now();
    }


}
