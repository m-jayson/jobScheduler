package com.promethium.jobscheduler.service;

import com.promethium.jobscheduler.record.JobReqRecordDto;

public interface JobService {
    void add(JobReqRecordDto jobReqRecordDto);
    void remove(Long id);
}
