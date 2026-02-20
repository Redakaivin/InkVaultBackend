package com.ink.vault.inkvault.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "entry_versions")
public class EntryVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 THIS WAS MISSING
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @Column(name = "version_number", nullable = false)
    private int versionNumber;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected EntryVersion() {}

    public EntryVersion(Entry entry, int versionNumber, String content) {
        this.entry = entry;          // 🔥 ALSO MISSING
        this.versionNumber = versionNumber;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
