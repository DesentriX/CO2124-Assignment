package com.example.part1.repo;

import com.example.part1.domain.MRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MRecordRepo extends JpaRepository<MRecord, Long> {
}
