package com.didem.playertwo.service;


import com.didem.playertwo.constant.GameConstants;
import com.didem.playertwo.exception.RestApiCallException;
import com.didem.playertwo.model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class PlayerTwoService implements IPlayerTwoService{
    private static final Logger logger = LogManager.getLogger(PlayerTwoService.class);

    @Autowired
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Override
    public Result playNumber(int number, String message){
        logger.debug("Player One Starting....");
        Result result = new Result(number, message);
        int quotient = number/ GameConstants.DIVISOR;

        if (quotient == 1){
            logger.debug("Player One WON....");
            result.setMessage(GameConstants.WINNER_MESSAGE);
            result.setNumber(1);
        } else {
            int remainingPlusOne = (number + 1) % GameConstants.DIVISOR;
            int remainingMinusOne = (number - 1) % GameConstants.DIVISOR;
            result.setMessage(GameConstants.KEEP_MESSAGE);

            if (remainingPlusOne == 0) {
                logger.debug("Player One Added 1", () -> (number + 1) / GameConstants.DIVISOR);
                result.setNumber((number + 1) / GameConstants.DIVISOR);
            } else if (remainingMinusOne == 0) {
                logger.debug("Player One Extract 1", () -> (number - 1) / GameConstants.DIVISOR);
                result.setNumber((number - 1) / GameConstants.DIVISOR);
            }else{
                logger.debug("Player One Add Zero", () -> number / GameConstants.DIVISOR);
                result.setNumber(number / GameConstants.DIVISOR);
            }
            callPlayerTwo(result.getNumber(), result.getMessage());
        }
        if(result.getMessage().equals(GameConstants.WINNER_MESSAGE))
            System.out.println(GameConstants.WINNER_MESSAGE);
        return result;
    }

    @Override
    public void callPlayerTwo(int number, String message){
        try{
            StringBuilder urlStringBuilder = new StringBuilder();
            if (Optional.ofNullable(message).isPresent()
                    && message.equals(GameConstants.KEEP_MESSAGE)) {
                urlStringBuilder.append(GameConstants.URL);
                urlStringBuilder.append(number);
                urlStringBuilder.append(GameConstants.SLASH);
                urlStringBuilder.append(message);
                logger.debug("Player Two's URL",() -> urlStringBuilder);
                getRestTemplate().getForObject(urlStringBuilder.toString(), Result.class);
            }
            logger.debug("Player One Called Player Two");
        }catch (Exception e){
            throw new RestApiCallException("Player Two Could not Call Player One " + e);
        }
    }

}
