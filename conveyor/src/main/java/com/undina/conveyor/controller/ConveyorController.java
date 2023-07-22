package com.undina.conveyor.controller;

import com.undina.conveyor.dto.LoanApplicationRequestDTO;
import com.undina.conveyor.dto.LoanOfferDTO;
import com.undina.conveyor.service.ConveyorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/conveyor")
@RequiredArgsConstructor
public class ConveyorController {
    private final ConveyorService conveyorService;

    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers: " + loanApplicationRequestDTO.toString());
        return ResponseEntity.ok()
                .body( conveyorService.getLoanOffers(loanApplicationRequestDTO));
    }
}
