package com.undina.deal.service;

import com.undina.deal.exception.NotFoundException;
import com.undina.deal.mapper.ApplicationMapper;
import com.undina.deal.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ApplicationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;


    public List<ApplicationDTO> getApplications() {
        log.info("getApplications - start");
        List<ApplicationDTO> applicationDTOList = applicationMapper.toListApplicationDTO(applicationRepository.findAll());
        log.info("getAllApplications - result: applicationDTOList = {}", applicationDTOList);
        return applicationDTOList;
    }

    public ApplicationDTO getApplication(Long applicationId) {
        log.info("getApplication - start: applicationId = {}", applicationId);
        ApplicationDTO applicationDTO = applicationMapper.toApplicationDTO(applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId)));
        log.info("getApplication - result: applicationDTO = {}", applicationDTO);
        return applicationDTO;
    }
}
