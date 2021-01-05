package com.didem.playertwo.service;

import com.didem.playertwo.model.Result;

public interface IPlayerTwoService {
    public Result playNumber(int number, String message);
    public void callPlayerTwo(int number, String message);
}
