package game;

public class GameInfo {

    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private Cell forkCell;
    private int numberOfMoves;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Cell forkCell, Player player, int numberOfMoves){
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.forkCell = forkCell;
        this.numberOfMoves= numberOfMoves;
    }
}

