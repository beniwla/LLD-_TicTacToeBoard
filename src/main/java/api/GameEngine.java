package api;
import boards.TicTacToeBoard;
import game.Board;
import game.Move;

public class GameEngine {

    // GameEngine: start(...) & move(...)
    // AIPlayer: suggestMove(...)
    // RuleEngine: isComplete(...)

    // below methods are all public APIs and we're trying to make them simple

    // So ideally GameEngine should not be responsible for isComplete(...) and suggestMoves(...) methods 
    // cause it looks like there's some intelligence to these 2 functions

    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        } else{
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move){
        if(board instanceof TicTacToeBoard){
            board.move(move);
        } else{
            throw new IllegalArgumentException();
        }
    }
}


// Class: GameEngine-- Any changes in the board
// Class: AIPlayer-- Smartness or Artifically Intelligent Decision
// Class: RuleEngine-- Making sure all the rules are followed in the game