package com.undina.conveyor.controller;

import com.undina.conveyor.model.CreditDTO;
import com.undina.conveyor.model.LoanApplicationRequestDTO;
import com.undina.conveyor.model.LoanOfferDTO;
import com.undina.conveyor.model.ScoringDataDTO;
import com.undina.conveyor.service.ConveyorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/conveyor")
@RequiredArgsConstructor
@Tag(name = "ConveyorController", description = "Контроллер ConveyorController")
public class ConveyorController {
    private final ConveyorService conveyorService;

    @Operation(summary = "Прескоринг данных и получениеи 4 кредитных предложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LoanOfferDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content)})
    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(@RequestBody @Parameter(required = true) @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers: " + loanApplicationRequestDTO);
        List<LoanOfferDTO> loanOfferDTOS = conveyorService.getLoanOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return ResponseEntity.ok()
                .body(loanOfferDTOS);
    }

    @Operation(summary = "Скоринг данных, высчитывание ставки(rate), полной стоимости кредита(psk), " +
            "размер ежемесячного платежа(monthlyPayment), график ежемесячных платежей ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Отказано в кредите",
                    content = @Content)})
    @PostMapping("/calculation")
    public ResponseEntity<CreditDTO> getCalculation(@RequestBody @Parameter(required = true) @Valid ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation: " + scoringDataDTO);
        CreditDTO creditDTO = conveyorService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return ResponseEntity.ok()
                .body(creditDTO);
    }
}
