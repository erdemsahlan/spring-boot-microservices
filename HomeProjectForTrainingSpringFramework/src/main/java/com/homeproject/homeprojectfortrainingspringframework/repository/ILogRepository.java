package com.homeproject.homeprojectfortrainingspringframework.repository;

import com.homeproject.homeprojectfortrainingspringframework.modals.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepository extends JpaRepository<Logs, Long> {
}
