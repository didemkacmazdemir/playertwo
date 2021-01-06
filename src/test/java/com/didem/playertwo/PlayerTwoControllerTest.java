package com.didem.playertwo;

import com.didem.playertwo.controller.PlayerTwoController;
import com.didem.playertwo.model.Result;
import com.didem.playertwo.service.PlayerTwoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerTwoController.class)
public class PlayerTwoControllerTest {
    @MockBean
    PlayerTwoService playerTwoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void playerTest(){
        Result result = new Result();
        result.setNumber(19);
        result.setMessage("KEEP");


        when(playerTwoService.playNumber(56,"KEEP")).thenReturn(result);

        Assert.assertEquals(result.getNumber(), 19);
        Assert.assertEquals(result.getMessage(), "KEEP");

    }
}
