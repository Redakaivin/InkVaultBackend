package com.ink.vault.inkvault.dto;

import com.ink.vault.inkvault.model.EntryStatus;
import com.ink.vault.inkvault.model.EntryType;

import java.time.LocalDateTime;

public class EntryDetailResponse {

    private Long id;
    private String title;
    private EntryType type;
    private EntryStatus status;
    private String latestContent;
    private LocalDateTime createdAt;

    public EntryDetailResponse(
            Long id,
            String title,
            EntryType type,
            EntryStatus status,
            String latestContent,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.status = status;
        this.latestContent = latestContent;
        this.createdAt = createdAt;
    }

    // getters
}
