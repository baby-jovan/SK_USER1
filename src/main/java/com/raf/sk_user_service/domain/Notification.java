package com.raf.sk_user_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String emailTo;

    @JsonIgnore
    @ManyToOne(optional = false)
    private NotificationType notificationType;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    public Notification(){

    }

    public Notification(Long userId, String emailTo, NotificationType notificationType) {
        this.userId = userId;
        this.emailTo = emailTo;
        this.notificationType = notificationType;
    }
}
