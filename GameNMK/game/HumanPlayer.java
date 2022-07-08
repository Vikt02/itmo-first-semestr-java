package game;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell, final int no, int n, int m) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");

            try{
	            final Move move = new Move(in.nextInt(), in.nextInt(), cell);
                in.nextLine();
	            if (position.isValid(move, no)) {
	                return move;
	            }
	            final int row = move.getRow();
	            final int column = move.getColumn();
            	out.println("Move " + move + " is invalid");
        	} catch (java.util.InputMismatchException e) {
        		System.out.println("Incorrect address, please try again");
        		in.nextLine();
        	} catch (java.util.NoSuchElementException e) {
                System.out.println("You lose the game");
                return null;
            }
        }
    }
}
