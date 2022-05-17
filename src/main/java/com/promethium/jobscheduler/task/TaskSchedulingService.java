package com.promethium.jobscheduler.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TaskSchedulingService {
    TaskScheduler taskScheduler;

    Map<String, ScheduledFuture<?>> jobsMap = new ConcurrentHashMap<>();

    public void scheduleATask(String jobId, String cronExpression,String jobRunCommand) {
        log.info("Scheduling task with job id: {} and cronExpressions expression: {}",jobId,cronExpression);

        var taskDefinitionBean = new TaskDefinition(jobRunCommand);

        var scheduledTask = taskScheduler.schedule(taskDefinitionBean, getCronTrigger(cronExpression));
        jobsMap.put(jobId, scheduledTask);
    }

    private CronTrigger getCronTrigger(String cronExpression) {
        return new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
    }

    public void removeScheduledTask(String jobId) {
        var scheduledTask = jobsMap.get(jobId);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }

    @Slf4j
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
    private class TaskDefinition implements Runnable{
        String jobRunCommand;

        @Override
        public void run() {
            log.info("Running action: :: {}", jobRunCommand);
        }
    }
}
