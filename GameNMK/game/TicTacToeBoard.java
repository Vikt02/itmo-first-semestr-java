package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.T, '-',
            Cell.F, '|'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int row;
    private int freeCoulombs;
    private final int col;
    private final int qua;
    private final int[][] steps = { 
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0},
        {1, 1},
        {-1, -1},
        {-1, 1},
        {1, -1}
    };

    public TicTacToeBoard(final int n, final int m, final int k) {
        this.cells = new Cell[n + 1][m + 1];
        this.row = n;
        this.col = m;
        this.qua = k;
        this.freeCoulombs = n * m;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell(final int no) {
        switch(no) {
        	case 1:
        		turn = Cell.X;
        		break;
        	case 2:
        		turn = Cell.O;
        		break;
        	case 3:
        		turn = Cell.T;
        		break;
        	case 4:
        		turn = Cell.F;
        		break;
        }
        return turn;
    }

    @Override
    public Result makeMove(final Move move, final int no) {

        if (!isValid(move, no)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        freeCoulombs--;

        int xs = move.getRow();
        int ys = move.getColumn(); 
        int[] direction = {-1, -1, -1, -1};
        for (int i = 0; i < 8; i++) {
            int x = xs;
            int y = ys;
            while (x < row && y < col && y >= 0 && x >= 0 && cells[x][y] == move.getValue()) {
                direction[i / 2]++;
                x += steps[i][0];
                y += steps[i][1];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (direction[i] >= qua){
                return Result.WIN;
            }
        }

        if (freeCoulombs == 0) {
            return Result.DRAW;
        }

        
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move, final int no) {
        return 0 <= move.getRow() && move.getRow() < row
                && 0 <= move.getColumn() && move.getColumn() < col
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell(no);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
    	StringBuilder prom = new StringBuilder();
    	int num = Math.max(Integer.toString(row).length(), Integer.toString(col).length());
    	for (int i = 0; i < num; i++) {
    		prom.append(" ");
    	}

        final StringBuilder sb = new StringBuilder(prom + " ");
        for (int i = 0; i < col; i++) {
        	sb.append(i + prom.substring(Integer.toString(i).length() - 1));
        }
        for (int r = 0; r < row; r++) {
            sb.append("\n");
            sb.append(r + prom.substring(Integer.toString(r).length() - 1));
            for (int c = 0; c < col; c++) {
                sb.append(SYMBOLS.get(cells[r][c]) + prom.toString());
            }
        }
        return sb.toString();
    }
}
