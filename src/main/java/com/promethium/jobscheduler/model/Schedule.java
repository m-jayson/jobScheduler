package com.promethium.jobscheduler.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String cronSchedule;

    @OneToMany(mappedBy = "schedule",cascade = CascadeType.REFRESH)
    List<Job> jobs;

    @OneToOne(mappedBy = "schedule",cascade = CascadeType.REFRESH)
    Task task;
}
