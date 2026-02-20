package com.ink.vault.inkvault.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "entries")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntryType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntryStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(
            mappedBy = "entry",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EntryVersion> versions = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Entry() {}

    public Entry(String title, EntryType type, User owner) {
        this.title = title;
        this.type = type;
        this.status = EntryStatus.DRAFT;
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
    }
    public EntryVersion getLatestVersion() {
        if (versions == null || versions.isEmpty()) {
            throw new IllegalStateException("Entry has no versions");
        }
        return versions.get(versions.size() - 1);
    }


    // ===== business methods =====

    public void addVersion(String content) {
        int nextVersion = versions.size() + 1;
        EntryVersion version = new EntryVersion(this, nextVersion, content);
        versions.add(version);
    }

    public void lock() {
        this.status = EntryStatus.LOCKED;
    }
}
