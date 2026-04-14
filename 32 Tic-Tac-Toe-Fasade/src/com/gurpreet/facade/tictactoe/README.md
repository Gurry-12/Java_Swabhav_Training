# Tic-Tac-Toe Application - UML Class Diagram

## Overview

This document represents the class structure of the Tic-Tac-Toe game implemented using the **Facade Design Pattern**.

---

## Class Diagram

```mermaid
classDiagram
    direction TB

    %% Main Entry Point
    class TicTacToeMain {
        +main(args: String[]) void
    }

    %% Facade Layer
    class GameFacade {
        -GameEngine engine
        -GameMenu menu
        -Scanner scanner
        +GameFacade()
        +start()
        -initializeGame(int) void
        -startHumanVsAI() void
        -startHumanVsHuman() void
    }

    %% Core Engine
    class GameEngine {
        -Board board
        -ResultAnalyzer analyzer
        -Player playerX
        -Player playerO
        -Player currentPlayer
        +GameEngine(Board, ResultAnalyzer, Player, Player)
        +gameStart() void
        +processMove(row: int, col: int) void
        +switchTurn() void
        +getStatus() GameStatus
    }

    %% Board
    class Board {
        -Cell[][] cells
        -size int
        +Board(size : int)
        -createBoard(size : int) void
        +isValidMove(row: int, col: int) boolean
        +markCell(row: int, col: int, state: State) boolean
        +isFull() boolean
    }


    %% Result Analyzer
    class ResultAnalyzer {
        -GameStatus status
        +ResultAnalyzer()
        +checkWinner(Board) void
        -isLineWin(Board, int, int, int, int, int) boolean
        -isDiagonalWin(Board, int, boolean) boolean
        +getStatus() GameStatus
    }

    %% Player Hierarchy
    class Player {
        <<abstract>>
        #String name
        #State state
        +getNextMove(Board) int[]
        +getState() State
        +getName() String
    }

    class HumanPlayer {
        -Scanner scanner
        +HumanPlayer(String, State, Scanner)
        +getNextMove(Board) int[]
    }

    class AIPlayer {
        -Random random
        +AIPlayer(String, State)
        +getNextMove(Board) int[]
    }

    %% Utility
    class TicTacToeUtil {
        <<utility>>
        -DASH_LENGTH : int
        -ONE : int
        +display(Board) void
        -getSymbol(State) : String
    }

    %% Enumerations
    class State {
        <<enumeration>>
        X
        O
        EMPTY
    }

    class GameStatus {
        <<enumeration>>
        WINNER
        ONGOING
        DRAW
    }

    %% Exceptions
    class InvalidModeException {
        <<exception>>
    }

    class InvalidMoveException {
        <<exception>>
    }

    %% Relationships
    TicTacToeMain ..> GameFacade : "creates and calls start()"

    GameFacade ..> GameEngine : "configures & executes"
    GameFacade ..> Board : "creates"
    GameFacade ..> ResultAnalyzer : "creates"
    GameFacade ..> Player : "creates (Human/AI)"

    GameEngine "1" *--> "1" Board : "has-a"
    GameEngine "1" *--> "1" ResultAnalyzer : "has-a"
    GameEngine "1" *--> "2" Player : "has-a"

    GameEngine --> GameStatus : "returns"

    GameEngine ..> TicTacToeUtil : "uses (static)"

    HumanPlayer --|> Player : "extends"
    AIPlayer --|> Player : "extends"

    Board ..> State : "uses"
    Player ..> State : "uses"
    ResultAnalyzer ..> GameStatus : "manages"

    GameFacade ..> InvalidModeException : "throws/catches"
    GameEngine ..> InvalidMoveException : "throws/catches"

    class GameMenu {
        -Scanner scanner
        +GameMenu(Scanner)
        +showMainMenu() int
        +chooseGameMode() int
        +getPlayerName(prompt: String) String
    }

    GameFacade ..> GameMenu : "uses"
```
