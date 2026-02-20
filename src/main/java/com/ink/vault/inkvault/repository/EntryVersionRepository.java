package com.ink.vault.inkvault.repository;

import com.ink.vault.inkvault.model.EntryVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryVersionRepository extends JpaRepository<EntryVersion, Long> {

    List<EntryVersion> findByEntryIdOrderByVersionNumberDesc(Long entryId);
}
