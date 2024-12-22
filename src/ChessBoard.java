public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }
    public boolean castling0() {
        int row = nowPlayer.equals("White") ? 0 : 7; // Ряд короля
        if (board[row][0] == null || board[row][4] == null) return false; // Ладья и король должны быть на месте

        if (board[row][0] instanceof Rook && board[row][4] instanceof King
                && board[row][0].getColor().equals(nowPlayer)
                && board[row][4].getColor().equals(nowPlayer)
                && board[row][0].check && board[row][4].check // Фигуры не двигались
                && board[row][1] == null && board[row][2] == null && board[row][3] == null // Поля между королем и ладьей пусты
                && !new King(nowPlayer).isUnderAttack(this, row, 4) // Король не под шахом
                && !new King(nowPlayer).isUnderAttack(this, row, 3)
                && !new King(nowPlayer).isUnderAttack(this, row, 2)) {

            // Выполняем рокировку
            board[row][4] = null;
            board[row][0] = null;
            board[row][2] = new King(nowPlayer); // Перемещение короля
            board[row][2].check = false;
            board[row][3] = new Rook(nowPlayer); // Перемещение ладьи
            board[row][3].check = false;

            nowPlayer = nowPlayer.equals("White") ? "Black" : "White"; // Меняем ход
            return true;
        }

        return false; // Рокировка невозможна
    }
    public boolean castling7() {
        int row = nowPlayer.equals("White") ? 0 : 7; // Ряд короля
        if (board[row][7] == null || board[row][4] == null) return false; // Ладья и король должны быть на месте

        if (board[row][7] instanceof Rook && board[row][4] instanceof King
                && board[row][7].getColor().equals(nowPlayer)
                && board[row][4].getColor().equals(nowPlayer)
                && board[row][7].check && board[row][4].check // Фигуры не двигались
                && board[row][5] == null && board[row][6] == null // Поля между королем и ладьей пусты
                && !new King(nowPlayer).isUnderAttack(this, row, 4) // Король не под шахом
                && !new King(nowPlayer).isUnderAttack(this, row, 5)
                && !new King(nowPlayer).isUnderAttack(this, row, 6)) {

            // Выполняем рокировку
            board[row][4] = null;
            board[row][7] = null;
            board[row][6] = new King(nowPlayer); // Перемещение короля
            board[row][6].check = false;
            board[row][5] = new Rook(nowPlayer); // Перемещение ладьи
            board[row][5].check = false;

            nowPlayer = nowPlayer.equals("White") ? "Black" : "White"; // Меняем ход
            return true;
        }

        return false; // Рокировка невозможна
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
