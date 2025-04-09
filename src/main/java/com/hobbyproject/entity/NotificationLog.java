package com.hobbyproject.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private String userId;
    private String message;
    private LocalDateTime createdAt;
    @Column(name = "is_read")
    private boolean read = false;

    public NotificationLog(Long postId, String userId, String message, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.read = false;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return read;
    }

    public void markAsRead() {
        this.read = true;
    }
}
