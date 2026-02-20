package com.ink.vault.inkvault.repository;

import com.ink.vault.inkvault.model.Entry;
import com.ink.vault.inkvault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByOwner(User owner);
}
