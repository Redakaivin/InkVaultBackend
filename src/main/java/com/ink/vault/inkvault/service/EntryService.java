package com.ink.vault.inkvault.service;

import com.ink.vault.inkvault.exception.ConflictException;
import com.ink.vault.inkvault.exception.ForbiddenException;
import com.ink.vault.inkvault.exception.ResourceNotFoundException;
import com.ink.vault.inkvault.model.Entry;
import com.ink.vault.inkvault.model.EntryStatus;
import com.ink.vault.inkvault.model.EntryType;
import com.ink.vault.inkvault.model.User;
import com.ink.vault.inkvault.repository.EntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Transactional
    public Entry getEntryForUser(Long entryId, User requester) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found"));

        if (!entry.getOwner().getId().equals(requester.getId())) {
            throw new ForbiddenException("You do not own this entry");
        }

        return entry;
    }

    @Transactional
    public Entry createEntry(String title, EntryType type, String content, User owner) {
        Entry entry = new Entry(title, type, owner);
        entry.addVersion(content);
        return entryRepository.save(entry);
    }

    @Transactional
    public Entry updateEntry(Long entryId, String newContent, User requester) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found"));

        if (!entry.getOwner().getId().equals(requester.getId())) {
            throw new ForbiddenException("You do not own this entry");
        }

        if (entry.getStatus() == EntryStatus.LOCKED) {
            throw new ConflictException("Locked entries cannot be edited");
        }

        entry.addVersion(newContent);
        return entry;
    }

    @Transactional
    public void lockEntry(Long entryId, User requester) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found"));

        if (!entry.getOwner().getId().equals(requester.getId())) {
            throw new ForbiddenException("You do not own this entry");
        }

        entry.lock();
    }

    // ✅ THIS IS THE IMPORTANT ONE
    public List<Entry> getEntriesForUser(User user) {
        return entryRepository.findByOwner(user);
    }
}

