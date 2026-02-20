package com.ink.vault.inkvault.controller;

import com.ink.vault.inkvault.dto.*;
import com.ink.vault.inkvault.model.Entry;
import com.ink.vault.inkvault.model.User;
import com.ink.vault.inkvault.service.EntryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    // 🔐 Extract authenticated user (set by JwtFilter)
    private User getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Unauthenticated");
        }
        return user;
    }

    // ✅ THIS WAS MISSING — LIST ALL ENTRIES
    @GetMapping
    public List<EntryResponse> getEntries(HttpServletRequest request) {
        User user = getCurrentUser(request);

        return entryService.getEntriesForUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ➕ CREATE ENTRY
    @PostMapping
    public EntryResponse createEntry(
            @RequestBody CreateEntryRequest request,
            HttpServletRequest httpRequest
    ) {
        Entry entry = entryService.createEntry(
                request.getTitle(),
                request.getType(),
                request.getContent(),
                getCurrentUser(httpRequest)
        );

        return toResponse(entry);
    }

    // ✏️ UPDATE ENTRY
    @PutMapping("/{id}")
    public EntryResponse updateEntry(
            @PathVariable Long id,
            @RequestBody UpdateEntryRequest request,
            HttpServletRequest httpRequest
    ) {
        Entry entry = entryService.updateEntry(
                id,
                request.getContent(),
                getCurrentUser(httpRequest)
        );

        return toResponse(entry);
    }

    // 🔒 LOCK ENTRY
    @PostMapping("/{id}/lock")
    public void lockEntry(
            @PathVariable Long id,
            HttpServletRequest httpRequest
    ) {
        entryService.lockEntry(id, getCurrentUser(httpRequest));
    }

    // 📄 GET SINGLE ENTRY
    @GetMapping("/{id}")
    public EntryDetailResponse getEntry(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        User user = getCurrentUser(request);

        Entry entry = entryService.getEntryForUser(id, user);

        return new EntryDetailResponse(
                entry.getId(),
                entry.getTitle(),
                entry.getType(),
                entry.getStatus(),
                entry.getLatestVersion().getContent(),
                entry.getCreatedAt()
        );
    }

    // 🔁 Mapper
    private EntryResponse toResponse(Entry entry) {
        return new EntryResponse(
                entry.getId(),
                entry.getTitle(),
                entry.getType(),
                entry.getStatus(),
                entry.getCreatedAt()
        );
    }
}

