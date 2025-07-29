package game;

public class Move {

    private Player player;
    private Cell cell;

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player= player;
    }

    public Cell getCell(){
        return cell;
    }

    public Player getPlayer(){
        return player;
    }
}


// Now I can do this in GameEngine-

// Move move = new Move(new Cell(1, 1));
// Cell c = move.getCell();  // controlled access
