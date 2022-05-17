package com.promethium.jobscheduler.service.impl;

import com.promethium.jobscheduler.model.Job;
import com.promethium.jobscheduler.record.JobReqRecordDto;
import com.promethium.jobscheduler.repository.JobRepository;
import com.promethium.jobscheduler.service.JobService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    @Override
    @Transactional
    public void add(JobReqRecordDto jobReqRecordDto) {
        this.jobRepository.save(Job.builder()
                        .jobRunCommand(jobReqRecordDto.jobRunCommand())
                .build());
    }

    @Override
    public void remove(Long id) {

    }
}
