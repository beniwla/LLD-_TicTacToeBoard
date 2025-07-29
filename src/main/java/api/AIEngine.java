package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {
    
    public Move suggestMove(Player computer, Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            Move suggestion;

            if(startingMoves(board1, 3)){
                suggestion = getBasicMove(computer, board1);
            } else {
                suggestion = getSmartMove(computer, board1);
            }

            if( suggestion != null) return suggestion;

            throw new IllegalStateException("No empty cells available");
        }else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }
    private Move getSmartMove(Player computer, TicTacToeBoard board1){

        RuleEngine ruleEngine = new RuleEngine();

        //Attacking Moves
        for(int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                if(board1.getSymbol(i,j)== null){
                    Move move= new Move(new Cell(i,j), computer);

                    TicTacToeBoard boardCopy = board1.copy(); // this copy() method is part of Board, but still not something that
                    // can be described in the board so will be abstract

                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }

        //Defensive Moves
        for(int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                if(board1.getSymbol(i,j)== null){
                    Move move= new Move(new Cell(i,j), computer.flip());

                    TicTacToeBoard boardCopy= board1.copy();
                    boardCopy.move(move);  // To check the future state of the game, AI has made a move on the board which shouldn't happen
                    if (ruleEngine.getState(boardCopy).isOver()){
                        return new Move(new Cell(i,j), computer);
                    }
                }
            }
        }

        return getBasicMove(computer, board1);
    }

    private Move getBasicMove(Player computer, TicTacToeBoard board1){
        for(int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                if(board1.getSymbol(i,j)== null){
                    return new Move(new Cell(i,j), computer);
                }
            }
        }
        return null;
    }

    private boolean startingMoves( TicTacToeBoard board1, int threshold){
        int count=0;

        for(int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                if(board1.getSymbol(i,j)!= null){
                    count++;
                }
            }
        }
        return count<threshold;
    }
}
