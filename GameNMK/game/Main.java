package game;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Enter n, m, k and how many players do you want");
        Scanner scan = new Scanner(System.in);
        String[] validPlayers = {"Random", "Sequent", "Human"};
        int n;
        int m;
        int k;
        int players;
        while(true) {
            try{
                if(!scan.hasNextLine()) {
                    return;
                }
            	n = scan.nextInt();
            	m = scan.nextInt();
            	k = scan.nextInt();
                players = scan.nextInt();
                if (k > n && k > m) {
                    System.out.println("To big k");
                }
                break;
                //scan.nextLine();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Incorrect enter");
                scan.nextLine();
            } 
        }
        scan.nextLine();
        String[] play = new String[players];

        System.out.println("Choose kind of players");
        for (int j = 0; j < 3; j++) {
            System.out.print(validPlayers[j] + " ");
        }
        System.out.println();
        try{
            for (int i = 0; i < players; i++) {
                play[i] = scan.nextLine();
                boolean valid = false;
                for (int j = 0; j < 3; j++) {
                    if(play[i].equals(validPlayers[j])) {
                        valid = true;
                    }
                }
                if (!valid) {
                    System.out.println("Invalid player");
                    i--;
                }
            } 
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Goodbye");
            //scan.nextLine();
            return;
        } 

        final Game game = new Game(true, n, m, play);
        int result;
        int t = 1;
        do {
        	t++;
            result = game.play(new TicTacToeBoard(n, m, k));
            System.out.println("Game result: " + result);
        } while (t < 1);
    }
}
