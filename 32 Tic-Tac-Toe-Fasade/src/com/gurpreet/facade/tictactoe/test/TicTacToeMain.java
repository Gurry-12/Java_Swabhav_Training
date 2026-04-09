package com.gurpreet.facade.tictactoe.test;

import com.gurpreet.facade.tictactoe.model.facade.GameFacade;

public class TicTacToeMain {

    public static void main(String[] args) {
    	 GameFacade facade = new GameFacade();
         facade.start();
    }
}