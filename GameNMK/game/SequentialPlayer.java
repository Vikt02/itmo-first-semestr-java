package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    private int mover = 0;
    @Override
    public Move move(final Position position, final Cell cell, final int no, int n, int m) {
        final Move move = new Move(mover / n, mover % m, cell);
        mover++;
        return move;
    }
}
