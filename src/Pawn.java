public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line == toLine && column == toColumn) return false;

        int direction = color.equals("White") ? 1 : -1;
        if (column == toColumn) {
            if (toLine - line == direction) {
                return chessBoard.board[toLine][toColumn] == null;
            }
            if ((color.equals("White") && line == 1 || color.equals("Black") && line == 6) &&
                    toLine - line == 2 * direction) {
                return chessBoard.board[toLine][toColumn] == null &&
                        chessBoard.board[line + direction][toColumn] == null;
            }
        } else if (Math.abs(column - toColumn) == 1 && toLine - line == direction) {
            return chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}