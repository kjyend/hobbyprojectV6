package com.hobbyproject.dto.notification;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class NotificationMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userId;
    private String message;
    private LocalDateTime createdAt;
    private boolean read;

    public NotificationMessage() {
    }

    public NotificationMessage(Long id, String userId, String message, LocalDateTime createdAt, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.read = read;
    }
}
