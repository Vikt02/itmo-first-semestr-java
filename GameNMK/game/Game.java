package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player[] players = new Player[10];
    private final int team;
    private final int row;
    private final int col;
    private final boolean[] losedPlayers = new boolean[10];
    private int playersInGame;

    public Game(final boolean log,
                int n,
                int m,
                final String... plays) {
        for (int i = 0; i < plays.length; i++) {
            switch (plays[i]) {
                case "Random" -> {
                    players[i] = new RandomPlayer();
                }
                case "Human" -> {
                    players[i] = new HumanPlayer();
                }
                case "Sequent" -> {
                    players[i] = new SequentialPlayer();
                }
                default -> {}
            };
            losedPlayers[i + 1] = false;
        }
        this.team = plays.length;
        this.playersInGame = plays.length;
        this.log = log;
        this.row = n;
        this.col = m;
    }

    public int play(Board board) {
    	int count = 0;
        while (true) {
        	final int result = move(board, players[count], count + 1);
            if (result != -1) {
                return result;
            }
            count++;
            count %= team;
        }
    }

    private int move(final Board board, final Player player, final int no) {
        if (losedPlayers[no]) {
            return -1;
        }
        if (playersInGame == 1) {
            log("Player " + no + " won");
            return no;
        }
        
        final Move move = player.move(board.getPosition(), board.getCell(no), no, row, col);
        if (move == null) {
            log("Player " + no + " lose");
            losedPlayers[no] = true;
            playersInGame--;
            return -1;
        }
        final Result result = board.makeMove(move, no);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            losedPlayers[no] = true;
            playersInGame--;
            return -1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
