package com.promethium.jobscheduler.controller;

import com.promethium.jobscheduler.record.JobReqRecordDto;
import com.promethium.jobscheduler.service.JobService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class JobController {

    JobService jobService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody final JobReqRecordDto jobReqRecordDto){
        this.jobService.add(jobReqRecordDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(final Long id){
        this.jobService.remove(id);
        return ResponseEntity.accepted().build();
    }
}
