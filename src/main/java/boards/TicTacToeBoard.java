package boards;
import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard extends Board{


    String[][] cells= new String[3][3];

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
    public void move(Move move){
        setSymbol(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public TicTacToeBoard copy(){
        TicTacToeBoard board= new TicTacToeBoard();

        for(int i=0;i<3;i++){
            System.arraycopy(cells[i], 0, board.cells[i], 0, 3);
        }
        return board;

    }
}

// Can't do cells[cell.row][cell.col]= symbol; as row, col are in seperate file