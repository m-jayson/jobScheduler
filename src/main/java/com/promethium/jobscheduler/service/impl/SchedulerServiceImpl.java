package com.promethium.jobscheduler.service.impl;

import com.promethium.jobscheduler.model.Job;
import com.promethium.jobscheduler.model.Schedule;
import com.promethium.jobscheduler.record.SchedulerReqRecordDto;
import com.promethium.jobscheduler.record.SchedulerRespRecordDto;
import com.promethium.jobscheduler.repository.JobRepository;
import com.promethium.jobscheduler.repository.ScheduleRepository;
import com.promethium.jobscheduler.service.SchedulerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SchedulerServiceImpl implements SchedulerService {

    ScheduleRepository scheduleRepository;

    JobRepository jobRepository;

    @Override
    @Transactional
    public Schedule attachSchedule(SchedulerReqRecordDto schedulerReqRecordDto) {
        log.info("Configuring New Schedule :: {}",schedulerReqRecordDto.toString());
        var jobs = filterRequest(schedulerReqRecordDto);
        return this.scheduleRepository.save(Schedule.builder()
                        .cronSchedule(schedulerReqRecordDto.cronExpressions())
                        .jobs(jobs)
                .build());
    }

    @Override
    @Transactional
    public Schedule modifySchedule(Long id, SchedulerReqRecordDto schedulerReqRecordDto) {
        log.info("Configuring Existing Schedule :: {}",schedulerReqRecordDto.toString());
        var schedule = this.scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule does not exists."));

        var jobs = filterRequest(schedulerReqRecordDto);

        schedule.setJobs(jobs);

        return this.scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public Schedule updateCronSchedule(Long id, Map<String, Object> requestBody) {
        var schedule = this.scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule does not exists."));

        schedule.setCronSchedule((String) requestBody.get("cronExpressions"));

        return this.scheduleRepository.save(schedule);
    }

    @Override
    public List<SchedulerRespRecordDto> findAll() {
        return this.scheduleRepository.findAll()
                .stream()
                .map(schedule -> {
                    var jobs = schedule.getJobs()
                            .stream()
                            .map(job -> SchedulerRespRecordDto.ScheduleJobRespDto.builder().build())
                            .collect(Collectors.toList());

                    return SchedulerRespRecordDto.builder()
                            .id(schedule.getId())
                            .scheduleJobRespDtos(jobs)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<Job> filterRequest(SchedulerReqRecordDto schedulerReqRecordDto) {
        var list = schedulerReqRecordDto.jobIds()
                .stream()
                .map(SchedulerReqRecordDto.ScheduleJobReqDto::id)
                .collect(Collectors.toList());
        return this.jobRepository.findAllById(list);
    }
}
