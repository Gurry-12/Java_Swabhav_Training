package com.gurpreet.facade.tictactoe.model.facade;

import java.util.Scanner;

import com.gurpreet.facade.tictactoe.model.exception.InvalidModeException;
import com.gurpreet.facade.tictactoe.model.helpers.Helpers;

public class GameMenu {

	private final Scanner scanner;

	public GameMenu(Scanner scanner) {
		this.scanner = scanner;
	}

	public int showMainMenu() throws InvalidModeException {
		System.out.println("=== Welcome to Tic Tac Toe ===");
		System.out.println("1. Play Game");
		System.out.println("2. Exit");
		System.out.print("Enter choice: ");

		int mode = Helpers.validateIntPositive(scanner);
		if (mode != 1 && mode != 2) {
			throw new InvalidModeException("Invalid mode ");
		}

		return mode;
	}

	public int chooseGameMode() throws InvalidModeException {
		System.out.println("=== Tic Tac Toe Game Started ===\n");
		System.out.println("Choose game mode:");
		System.out.println("1. Human vs Human");
		System.out.println("2. Human vs AI");
		System.out.print("Enter your choice: ");

		int mode = Helpers.validateIntPositive(scanner);
		if (mode != 1 && mode != 2) {
			throw new InvalidModeException("Invalid mode ");
		}

		return mode;
	}

	public String getPlayerName(String prompt) {
		System.out.print(prompt);
		return Helpers.validateStringLettersOnly(scanner);
	}
}
