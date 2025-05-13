package com.travelq.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public NotificationEntity(String message, boolean isRead, UserEntity user) {
        this.message = message;
        this.isRead = isRead;
        this.user = user;
    }
}
