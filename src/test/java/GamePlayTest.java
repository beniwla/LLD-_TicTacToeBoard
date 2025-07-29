import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;

import org.junit.Before;   // For @Before annotation
import org.junit.Test;     // For @Test annotation

import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import static org.junit.Assert.*; // For assertions like assertEquals, assertTrue etc.

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    AIEngine aiEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiEngine = new AIEngine();
    }

    // User personas are targetted for unit testing
    @Test
    public void checkForRowWin(){

        Board board= gameEngine.start("TicTacToe");

        int moves[][]= new int[][]{{1,0}, {1,1}, {1,2}};
        int secondPlayerMoves[][]= new int[][]{{0,0},{0,1},{0,2}};
        playGame(board, moves, secondPlayerMoves);
        
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }
    

    @Test
    public void checkForColWin(){

        Board board= gameEngine.start("TicTacToe");

        int moves[][]= new int[][]{{0,0}, {1,0}, {2,0}};
        int secondPlayerMoves[][]= new int[][]{{0,1},{0,2},{1,1}};
        playGame(board, moves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin(){

        Board board= gameEngine.start("TicTacToe");

        int moves[][]= new int[][]{{0,0}, {1,1}, {2,2}};
        int secondPlayerMoves[][]= new int[][]{{0,1},{0,2},{1,0}};
        playGame(board, moves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin(){

        Board board= gameEngine.start("TicTacToe");

        int moves[][]= new int[][]{{0,2}, {1,1}, {2,0}};
        int secondPlayerMoves[][]= new int[][]{{0,0},{0,1},{1,0}};
        playGame(board, moves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForSecondPlayerWin(){

        Board board= gameEngine.start("TicTacToe");

        int moves[][]= new int[][]{{1,0}, {1,1}, {2,0}}; 
        int secondPlayerMoves[][]= new int[][]{{0,0},{0,1},{0,2}};
        playGame(board, moves, secondPlayerMoves);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves){
 
        int row, col;
        int next=0;

        while(!ruleEngine.getState(board).isOver()){

            Player first = new Player("X"), second= new Player("O");

            row= firstPlayerMoves[next][0]; 
            col= firstPlayerMoves[next][1];

            Move firstPlayerMove= new Move(new Cell(row,col), first);

            gameEngine.move(board, firstPlayerMove);

            if(!ruleEngine.getState(board).isOver()){
                int sRow= secondPlayerMoves[next][0];
                int sCol= secondPlayerMoves[next][1];
                Move secondPlayerMove= new Move( new Cell(sRow, sCol), second);
                gameEngine.move(board, secondPlayerMove);
            }

            next++;
            
        }
    }
}
