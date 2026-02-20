package com.ink.vault.inkvault.dto;

import com.ink.vault.inkvault.model.EntryStatus;
import com.ink.vault.inkvault.model.EntryType;

import java.time.LocalDateTime;

public class EntryResponse {

    private Long id;
    private String title;
    private EntryType type;
    private EntryStatus status;
    private LocalDateTime createdAt;

    public EntryResponse(Long id, String title, EntryType type, EntryStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public EntryType getType() {
        return type;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
