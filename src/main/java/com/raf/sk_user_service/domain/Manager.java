package com.raf.sk_user_service.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {@Index(columnList =  "username", unique = true), @Index(columnList = "email", unique = true)})
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username, password, email, name, lastName;

    private String dateOfBirth, dateOfEmpl;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Role role;

    private Long isZabrana;

//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager", orphanRemoval = true)
//    private List<Gym> gyms = new ArrayList<>();

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;
}
