package com.promethium.jobscheduler.repository;

import com.promethium.jobscheduler.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
