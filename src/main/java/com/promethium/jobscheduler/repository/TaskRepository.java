package com.promethium.jobscheduler.repository;

import com.promethium.jobscheduler.model.Job;
import com.promethium.jobscheduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
