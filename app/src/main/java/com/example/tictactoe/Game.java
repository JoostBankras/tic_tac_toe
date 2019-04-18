package com.example.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    public TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        TileState tile = board[row][column];

        if (tile == TileState.BLANK){
            if (playerOneTurn == true){
                playerOneTurn = false;
                board[row][column] = TileState.CROSS;
                return TileState.CROSS;
            }
            else{
                playerOneTurn = true;
                board[row][column] = TileState.CIRCLE;
                return TileState.CIRCLE;
            }
        }
        else{
            return TileState.INVALID;
        }
    }

    public GameState won(){
        for (int i = 0; i < 3; i++){
            int ROW1 = 0;
            int ROW2 = 0;
            for (int j = 0; j < 3; j++){
                TileState tile = board[i][j];
                if (tile == TileState.CROSS){
                    ROW1++;                   
                }
                if (tile == TileState.CIRCLE){
                    ROW2++;
                }
                if (ROW1 == 3){
                    return GameState.PLAYER_ONE;
                }
                if (ROW2 == 3){
                    return GameState.PLAYER_TWO;
                }
            }


        }
        for (int i = 0; i < 3; i++) {
            int ROW1 = 0;
            int ROW2 = 0;
            for (int j = 0; j < 3; j++) {
                TileState tile = board[j][i];
                if (tile == TileState.CROSS) {
                    ROW1++;
                }
                if (tile == TileState.CIRCLE) {
                    ROW2++;
                }
                if (ROW1 == 3) {
                    return GameState.PLAYER_ONE;
                }
                if (ROW2 == 3) {
                    return GameState.PLAYER_TWO;
                }
            }
        }
        if (board[0][0] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        if (board[0][0] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        if (board[0][2] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][0] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        if (board[0][2] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][0] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        int count = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                TileState tile = board[i][j];
                if (tile != TileState.BLANK){
                    count++;
                }
            }
        }
        if (count == 9){
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }

    public TileState OldState(int one, int two){
        TileState the_state = board[one][two];
        return the_state;
    }
}
