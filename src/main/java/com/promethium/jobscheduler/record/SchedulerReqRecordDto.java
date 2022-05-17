package com.promethium.jobscheduler.record;

import java.util.List;

public record SchedulerReqRecordDto(String cronExpressions, List<ScheduleJobReqDto> jobIds) {

    public record ScheduleJobReqDto(Long id){}
}
