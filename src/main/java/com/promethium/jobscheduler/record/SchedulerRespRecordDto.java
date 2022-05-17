package com.promethium.jobscheduler.record;

import lombok.Builder;

import java.util.List;

@Builder
public record SchedulerRespRecordDto(Long id,String cron, List<ScheduleJobRespDto> scheduleJobRespDtos) {

    @Builder
    public record ScheduleJobRespDto(Long id,String jobRunCommand){}
}
