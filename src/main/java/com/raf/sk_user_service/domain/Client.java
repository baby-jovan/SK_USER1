package com.raf.sk_user_service.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Random;

@Setter
@Getter
@Entity //ovo podrazumeva da ce ova klasa zapravo da bude tabela i da ce spring da vidi da je Entity i da kreira bazu sa atributima koje ima ova klasa
@EntityListeners(AuditingEntityListener.class)//ovo nam omogucava da koristimo @CreateDate i @LastModifiedDate
@Table(indexes = {@Index(columnList =  "username", unique = true), @Index(columnList = "email", unique = true)})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ovo obezbedjuje da mi ne dodajemo rucno, vec automatski, za jedan veci od proslog
    private Long id;

    private String username, password, email, name, lastName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numOfCard;

    private Integer numOfTermins;

    private String dateOfBirth;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Role role;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;

    private Long isZabrana;


    @PrePersist
    public void prePersist() {
        if (this.numOfCard == null) {
            this.numOfCard = generateNumOfCard();
        }
    }

    private int generateNumOfCard() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000) + 1;
        return randomNumber;
    }


}
