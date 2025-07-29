package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

public class RuleEngine {

    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            String firstCharacter= null;

            boolean rowComplete;
            for (int i=0;i<3;i++){
                firstCharacter= board1.getSymbol(i,0);
                rowComplete= firstCharacter != null ;
                for (int j=1;j<3;j++){
                    if( firstCharacter!= null && !firstCharacter.equals(board1.getSymbol(i,j))){
                        rowComplete=false;
                    }
                }
                if(rowComplete){
                    return new GameState(true, firstCharacter);
                } //Game is won
            }

            boolean colComplete;
            for (int i=0;i<3;i++){
                firstCharacter= board1.getSymbol(0,i);
                colComplete= firstCharacter != null ;
                for (int j=1;j<3;j++){
                    if(firstCharacter!= null && !firstCharacter.equals(board1.getSymbol(j,i))){
                        colComplete=false;
                    }
                }
                if(colComplete){
                    return new GameState(true, firstCharacter);
                }
            }

            firstCharacter= board1.getSymbol(0,0);
            boolean diagComplete= firstCharacter!= null;
            for (int i=1;i<3;i++){
                if(firstCharacter!= null && !firstCharacter.equals(board1.getSymbol(i,i))){
                    diagComplete=false;
                    break;
                }
            }
            if(diagComplete){
                return new GameState(true, firstCharacter);
            }

            firstCharacter= board1.getSymbol(0,2);
            boolean revdiagComplete= firstCharacter!= null;
            for (int i=1;i<3;i++){
                if(firstCharacter!= null && !firstCharacter.equals(board1.getSymbol(i,2-i))){
                    revdiagComplete=false;
                    break;
                }
            }
            if(revdiagComplete){
                return new GameState(true, firstCharacter);
            }

            int countofFilledCells=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getSymbol(i,j) != null){
                        countofFilledCells++;
                    }
                }
            }

            if(countofFilledCells==9){
                return new GameState(true, null);
            }else{
                return new GameState(false, null);
            }

        }
        else{
            throw new IllegalArgumentException();
        }
    } 
}
