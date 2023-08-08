package com.undina.deal.controller;

import com.undina.deal.dto.FinishRegistrationRequestDTO;
import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.dto.LoanOfferDTO;
import com.undina.deal.service.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deal")
@RequiredArgsConstructor
@Tag(name = "DealController", description = "Контроллер DealController")
public class DealController {
    private final DealService dealService;

    @Operation(summary = "Создание application и client, получение 4 кредитных предложений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LoanOfferDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content)})
    @PostMapping("/application")
    public ResponseEntity<List<LoanOfferDTO>> createApplication(@RequestBody LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication " + loanApplication);
        List<LoanOfferDTO> loanOfferDTOList = dealService.createApplication(loanApplication);
        log.info("createApplication result " + loanOfferDTOList);
        return ResponseEntity.ok(loanOfferDTOList);
    }

    @PutMapping("/offer")
    public ResponseEntity<Void> applyLoanOffer(@RequestBody LoanOfferDTO loanOffer) {
        log.info("applyLoanOffer " + loanOffer);
        dealService.applyOffer(loanOffer);
        log.info("applyLoanOffer result OK");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Завершение регистрации + полный подсчёт кредита")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Отказано в кредите",
                    content = @Content)})
    @PutMapping(("/calculate/{applicationId}"))
    public ResponseEntity<Void> getCalculation(@PathVariable Long applicationId,
                                               @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("getCalculation  applicationId = {}, scoringData = {}", applicationId, finishRegistrationRequestDTO);
        dealService.calculateCredit(applicationId, finishRegistrationRequestDTO);
        log.info("getCalculation result OK");
        return ResponseEntity.ok().build();
    }
}
