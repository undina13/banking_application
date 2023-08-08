package com.undina.deal.service;


import com.undina.deal.model.Application;
import com.undina.deal.model.ApplicationStatus;
import com.undina.deal.model.ChangeType;
import com.undina.deal.model.StatusHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationService {
    public Application updateStatus(Application application, ApplicationStatus status, ChangeType changeType) {
        log.info("updateStatus: {}, {}, {}", application, status, changeType);
        application.setStatus(status);
        List<StatusHistory> statusHistories = application.getStatusHistory();
        if (statusHistories == null) {
            statusHistories = new ArrayList<>();
        }
        statusHistories.add(new StatusHistory(status, LocalDateTime.now(), changeType));
        application.setStatusHistory(statusHistories);
        log.info("updateStatus result: {}", application);
        return application;
    }
}
