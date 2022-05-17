package com.promethium.jobscheduler.task;

import com.promethium.jobscheduler.model.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TaskEvent extends ApplicationEvent {

    TaskEventMessage taskEventMessage;

    public TaskEvent(Object source,  TaskEventMessage taskEventMessage) {
        super(source);
        this.taskEventMessage = taskEventMessage;
    }

    public record TaskEventMessage(String message,Schedule schedule){}

    public static TaskEvent of(Object source,TaskEventMessage taskEventMessage){
        return new TaskEvent(source,taskEventMessage);
    }
}
