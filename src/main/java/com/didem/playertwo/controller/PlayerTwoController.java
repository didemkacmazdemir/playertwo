package com.didem.playertwo.controller;

import com.didem.playertwo.model.Result;
import com.didem.playertwo.service.PlayerTwoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class PlayerTwoController {
    private static final Logger logger = LogManager.getLogger(PlayerTwoController.class);

    @Autowired
    private PlayerTwoService playerTwoService;

    @GetMapping("/v1/players/player-two/{number}/{message}")
    public ResponseEntity<Result> gameBegin(@PathVariable int number, @PathVariable String message){
        logger.debug("PlayOneController");
        Result result = playerTwoService.playNumber(number, message);
        return ResponseEntity.ok(result);
    }
}
