package ee.taltech.iti0202.recursion;
public class StudentStrategy implements Strategy {

    private int number;
    private Board board;

    public StudentStrategy() {
        this.board = new Board();
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void moveOpponent(int x, int y) {
        this.board.move(x, y);
    }

    @Override
    public int getMove() {
        Board copiedBoard = new Board(board);
        int move = getBestMove(copiedBoard);
        this.board.move(move / 3, move % 3);
        return move;
    }

    public int getBestMove(Board board) {
        // Iterate through all legal moves and get the value of each move with getMinValue, then return the move with the highest value.
        return 0;
    }

    public int getMinValue(Board board) {
        // Iterate through all legal moves, recursively (using getMaxValue) get value of each move and return the min of those values
        return 0;
    }

    public int getMaxValue(Board board) {
        // Iterate through all legal moves, recursively (using getMinValue) get value of each move and return the max of those values
        return 0;
    }
}