package tictactoe;

public class Logic {

    public static Sign[][] boardStatus = Logic.createSignTable();
    public static boolean isPlayerVsPlayer = true;
    public static int counter = 0;
    public static Sign whoWin = Sign.EMPTY;
    public static int whoStart = 1;

    public static void makeMove(int wiersz, int kolumna) {

        if (boardStatus[wiersz][kolumna] == Sign.EMPTY && whoWin == Sign.EMPTY) {
            counter++;
            if (counter % 2 == 1) {
                boardStatus[wiersz][kolumna] = Sign.O;
            } else {
                boardStatus[wiersz][kolumna] = Sign.X;
            }
        }
        if (!isWinner(boardStatus) && !isPlayerVsPlayer && !Logic.isDraw() && counter % 2 == 1) {
            AI.computerMove();
        }
    }

    public static Sign coJestNaPolu(int row, int column) {
        return boardStatus[row][column];
    }

    public static void setPlayerVsComputer() {
        isPlayerVsPlayer = true;
    }

    public static void setPlayerVsPlayer() {
        isPlayerVsPlayer = false;
    }

    public static void resetGame() {
        boardStatus = createSignTable();
        counter = whoStart;
        whoStart++;
        whoWin = Sign.EMPTY;

        if (!Logic.isPlayerVsPlayer && Logic.counter % 2 == 1) {
            AI.computerMove();
        }
    }

    public static Sign[][] createSignTable() {
        boardStatus = new Sign[3][3];
        for (int i = 0; i < boardStatus.length; i++) {
            for (int j = 0; j < boardStatus[i].length; j++) {
                boardStatus[i][j] = Sign.EMPTY;
            }
        }
        return boardStatus;
    }

    public static boolean isRowWin(Sign table[]) {
        return (table[0] == table[1] && table[1] == table[2] && table[1] != Sign.EMPTY);
    }

    public static boolean isRowWin(Sign signTable[][]) {
        for (int i = 0; i < 3; i++) {
            Sign[] table = new Sign[3];
            for (int j = 0; j < 3; j++) {
                table[j] = signTable[i][j];
            }
            if (Logic.isRowWin(table)) {
                whoWin = table[0];
                return true;
            }
        }
        return false;
    }

    public static boolean isColumnWin(Sign signTable[][]) {
        for (int i = 0; i < 3; i++) {
            Sign[] table = new Sign[3];
            for (int j = 0; j < 3; j++) {
                table[j] = signTable[j][i];
            }
            if (Logic.isRowWin(table)) {
                whoWin = table[0];
                return true;
            }
        }
        return false;
    }

    public static boolean isCrossWin(Sign signTable[][]) {
        Sign[] table = new Sign[3];
        for (int i = 0; i < 3; i++) {
            table[i] = signTable[i][i];
        }
        if (Logic.isRowWin(table)) {
            whoWin = table[0];
            return true;
        }
        table[0] = signTable[0][2];
        table[1] = signTable[1][1];
        table[2] = signTable[2][0];

        if (Logic.isRowWin(table)) {
            Logic.whoWin = table[0];
            return true;
        }
        return false;
    }

    public static boolean isWinner(Sign signTable[][]) {
        return (isRowWin(signTable) || isColumnWin(signTable) || isCrossWin(signTable));
    }

    public static boolean isDraw() {
        if (whoWin == Sign.X || whoWin == Sign.O) {
            return false;
        }
        Sign sign = Sign.X;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (whoWin != Sign.X && whoWin != Sign.O) {
                    if (boardStatus[i][j] == Sign.EMPTY) {
                        sign = boardStatus[i][j];
                    }
                }
            }
        }
        return (sign != Sign.EMPTY);
    }

}
