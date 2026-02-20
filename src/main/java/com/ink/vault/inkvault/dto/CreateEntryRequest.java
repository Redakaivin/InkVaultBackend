package com.ink.vault.inkvault.dto;

import com.ink.vault.inkvault.model.EntryType;

public class CreateEntryRequest {

    private String title;
    private EntryType type;
    private String content;

    public String getTitle() {
        return title;
    }

    public EntryType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
