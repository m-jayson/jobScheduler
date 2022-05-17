package com.promethium.jobscheduler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String jobRunCommand;

    @ManyToOne(cascade = CascadeType.REFRESH)
    Schedule schedule;
}
