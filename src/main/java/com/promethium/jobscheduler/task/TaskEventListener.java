package com.promethium.jobscheduler.task;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TaskEventListener implements ApplicationListener<TaskEvent> {

    TaskSchedulingService taskSchedulingService;

    @Override
    public void onApplicationEvent(TaskEvent event) {
        var taskEventMessage = event.getTaskEventMessage();
        log.info("Received event ::{}"taskEventMessage.message());
    }
}
