package com.demo.tx.repository;

import com.demo.tx.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuditLogsRepository extends JpaRepository<AuditLogs, String> {
}
