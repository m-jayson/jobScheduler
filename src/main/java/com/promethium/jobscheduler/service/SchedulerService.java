package com.promethium.jobscheduler.service;

import com.promethium.jobscheduler.model.Schedule;
import com.promethium.jobscheduler.record.SchedulerReqRecordDto;
import com.promethium.jobscheduler.record.SchedulerRespRecordDto;

import java.util.List;
import java.util.Map;

public interface SchedulerService {
    Schedule attachSchedule(SchedulerReqRecordDto schedulerReqRecordDto);
    Schedule modifySchedule(Long id, SchedulerReqRecordDto schedulerReqRecordDto);

    Schedule updateCronSchedule(Long id, Map<String, Object> requestBody);

    List<SchedulerRespRecordDto> findAll();
}
