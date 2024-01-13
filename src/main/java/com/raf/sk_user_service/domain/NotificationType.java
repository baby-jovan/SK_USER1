package com.raf.sk_user_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notificationType, subject, body;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notificationType", orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    public NotificationType() {

    }

    public NotificationType(String notificationType, String subject, String body, List<Notification> notifications) {
        this.notificationType = notificationType;
        this.subject = subject;
        this.body = body;
        this.notifications = notifications;
    }

    public NotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
