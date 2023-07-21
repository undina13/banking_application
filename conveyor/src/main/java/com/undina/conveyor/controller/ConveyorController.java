package com.undina.conveyor.controller;

import com.undina.conveyor.service.ConveyorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/conveyor")
@RequiredArgsConstructor
public class ConveyorController {
    private final ConveyorService conveyorService;


}
