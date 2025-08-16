package boards;
import java.util.function.BiFunction;
import java.util.function.Function;

import api.Rule;
import api.RuleSet;
import game.Cell;
import game.GameState;
import game.Move;
import game.Board;

import java.util.ArrayList;
import java.util.List;


public class TicTacToeBoard implements CellBoard{


    String[][] cells= new String[3][3];

    History history = new History();

    public static RuleSet getRules(){
        RuleSet rules = new RuleSet();
        rules.add(new Rule(board -> straightTraversals((i,j) -> board.getSymbol(i, j))));
        rules.add(new Rule(board -> straightTraversals((i,j) -> board.getSymbol(j, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, 2-i))));
        rules.add(new Rule(board -> {
            int countofFilledCells=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board.getSymbol(i,j) != null){
                        countofFilledCells++;
                    }
                }
            }

            if(countofFilledCells==9){
                return new GameState(true, null);
            }
            return new GameState(false, null);
        }));

        return rules;
    }

    public String getSymbol(int row, int col){
        return cells[row][col]; 
    }

    public void setSymbol(Cell cell, String symbol){
        if(cells[cell.getRow()][cell.getCol()] == null)
        {
            cells[cell.getRow()][cell.getCol()]= symbol;
        }else{ 
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        for (int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                result.append(cells[i][j]== null ? "-" : cells[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    // Now this TicTacToeBoard class has to implement move as we can't have concrete implementation
    // of an abstract class without concrete implementation of the abstract function inside of it

    //We are using override for move method because, we want to override the method in the superclass
    
    @Override
    public TicTacToeBoard move(Move move){
        history.add(new Representation(this));
        TicTacToeBoard b= copy();
        b.setSymbol(move.getCell(), move.getPlayer().symbol());
        return b;
    }


    @Override
    public TicTacToeBoard copy(){
        TicTacToeBoard board= new TicTacToeBoard();

        for(int i=0;i<3;i++){
            System.arraycopy(cells[i], 0, board.cells[i], 0, 3);
        }

        // whenever we're making copy of a board, its history is getting lost
        board.history = history;
        return board;
    }

    //findStreak function for Row ,Col
    private static GameState straightTraversals(BiFunction<Integer, Integer, String> next)
    {
        GameState result = new GameState(false, "-");

        for (int i=0;i<3;i++){
            final int ii= i;

            Function<Integer, String> traversal = j -> next.apply(ii, j);
            GameState traversal1 = traverse(traversal);
            if(traversal1.isOver()) 
            {
                result = traversal1;
                break;
            }
        }

        return result;
    }

    // below function was made cause findStreak and findDiagStreak were almost similar to the down
    private static GameState traverse(Function<Integer, String> traversal){

        GameState result = new GameState(false, "-");
        if(traversal.apply(0) == null)return result;
        boolean possibleStreak= true ;
        for (int j=1;j<3;j++){
            if( traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))){
                possibleStreak=false;
                break;
            }
        }
        if(possibleStreak){
            result = new GameState(true, traversal.apply(0));
        } //Game is won

        return result;
    }

    public static enum Symbol{
        X("X"), O("O");

        String marker;

        Symbol(String marker){
            this.marker = marker;
        }

        public String marker(){
            return marker;
        }
    }
}

// Can't do cells[cell.row][cell.col]= symbol; as row, col are in seperate file

class History {

    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex) {
        for(int i=0 ; i< boards.size() - (moveIndex+1) ; i++){
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }

    public Representation undo() {
        if(boards.size() == 0) {
            throw new IllegalStateException();
        }
        boards.remove(boards.size()-1);
        return boards.get(boards.size()-1); 
    }

    public void add(Representation representation) {
        boards.add(representation);
    } 

}

class Representation {
    String representation;

    public Representation (TicTacToeBoard board) {
        representation = board.toString();
    }
}