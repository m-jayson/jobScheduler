package com.promethium.jobscheduler.controller;

import com.promethium.jobscheduler.record.SchedulerReqRecordDto;
import com.promethium.jobscheduler.service.SchedulerService;
import com.promethium.jobscheduler.task.TaskEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.promethium.jobscheduler.task.TaskEvent.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduler")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SchedulerController {
    SchedulerService schedulerService;
    ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public ResponseEntity all(){
        return ResponseEntity.ok(this.schedulerService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity attachSchedule(@RequestBody final SchedulerReqRecordDto schedulerReqRecordDto){
        var schedule = this.schedulerService.attachSchedule(schedulerReqRecordDto);

        this.applicationEventPublisher.publishEvent(of(this, new TaskEventMessage("attachSchedule",schedule)));

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSchedule(@RequestParam("id") Long id,
                                         @RequestBody final SchedulerReqRecordDto schedulerReqRecordDto){
        var schedule = this.schedulerService.modifySchedule(id,schedulerReqRecordDto);

        this.applicationEventPublisher.publishEvent(of(this,new TaskEventMessage("updateSchedule",schedule)));

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/cronExpressions/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCronSchedule(@RequestParam("id") Long id,
                                             @RequestBody final Map<String,Object> requestBody){
        var schedule = this.schedulerService.updateCronSchedule(id,requestBody);

        this.applicationEventPublisher.publishEvent(of(this,new TaskEventMessage("updateCronSchedule",schedule)));

        return ResponseEntity.ok().build();
    }
}
