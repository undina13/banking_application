package com.undina.deal.service;

import com.undina.deal.dto.*;
import com.undina.deal.model.Application;
import com.undina.deal.model.Client;
import com.undina.deal.repository.ApplicationRepository;
import com.undina.deal.repository.ClientRepository;
import com.undina.deal.util.ClientMapper;
import com.undina.deal.util.MyFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealService {
    private final ClientMapper clientMapper;

    private final ClientRepository clientRepository;

    private final ApplicationRepository applicationRepository;

    private final ApplicationService applicationService;

    private final MyFeignClient myFeignClient;

    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication: {}", loanApplication);
        Client client = clientMapper.toClient(loanApplication);
        client = clientRepository.save(client);
        log.info("savingClient: {}", client);
        Application application = new Application();
        application.setClient(client);
        application.setCreationDate(LocalDate.now());
        application = applicationService.updateStatus(application, ApplicationStatus.PREAPPROVAL, ChangeType.MANUAL);
        log.info("save application: {}", application);
        application = applicationRepository.save(application);
        List<LoanOfferDTO> loanOffers = myFeignClient.getOffers(loanApplication).getBody();
        if (loanOffers != null) {
            Application finalApplication = application;
            loanOffers.forEach(loanOfferDTO -> loanOfferDTO.setApplicationId(finalApplication.getApplicationId()));
        }
        log.info("createApplication result: {}", loanOffers);
        return loanOffers;
    }

    public void applyOffer(LoanOfferDTO loanOffer) {
    }

    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
    }
}
