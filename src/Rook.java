public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line == toLine && column == toColumn) return false;

        if (line == toLine || column == toColumn) {
            int stepLine = line == toLine ? 0 : (toLine - line) / Math.abs(toLine - line);
            int stepColumn = column == toColumn ? 0 : (toColumn - column) / Math.abs(toColumn - column);

            for (int i = 1; i < Math.max(Math.abs(toLine - line), Math.abs(toColumn - column)); i++) {
                if (chessBoard.board[line + i * stepLine][column + i * stepColumn] != null) {
                    return false;
                }
            }
            return chessBoard.board[toLine][toColumn] == null ||
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

