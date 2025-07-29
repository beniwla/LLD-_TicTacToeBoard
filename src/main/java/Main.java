
import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import java.util.Scanner;

import game.Board;
import game.Cell;
import game.Move;
import game.Player;


public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();

        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();

        Board board= gameEngine.start("TicTacToe");


        //moves

        Scanner scn = new Scanner(System.in);

        while(!ruleEngine.getState(board).isOver()){

            Player computer = new Player("O"), human= new Player("X");

            System.out.println("Make your move!");

            System.out.println(board);

            System.out.print("Enter row: ");
            int row= scn.nextInt();

            System.out.print("Enter col: ");
            int col= scn.nextInt();

            Move oppMove= new Move(new Cell(row,col), human);

            gameEngine.move(board, oppMove);

            if(!ruleEngine.getState(board).isOver()){
                Move computerMove= aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
            
        }
        System.out.println("GameResult: "+ ruleEngine.getState(board));
        System.out.println(board);
    }
}
