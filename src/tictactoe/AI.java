package tictactoe;

public class AI {

    public static void computerMove() {
        int[] square = AI.selectSquare();
        Logic.whoWin = Sign.EMPTY;
        Logic.makeMove(square[0], square[1]);
    }

    public static int[] selectSquare() {
        if (isTableEmpty()) {
            return selectCorner();

        }
        int[] square = winningMoves();
        if (square[0] < 3) {
            return square;
        }
        square = blockOpponent();
        if (square[0] < 3) {
            return square;
        }

        if (!Logic.isPlayerVsPlayer && Logic.whoStart % 2 == 0) {
            int[] cornerTactic = tactic();
            if (cornerTactic[0] < 3) {
                return cornerTactic;
            }
        }

        int[] coordinates;
        do {
            coordinates = getSquare();
        } while (!isItEmpty(coordinates[0], coordinates[1]));
        return coordinates;

    }

    private static int[] getSquare() {
        int i = (int) (Math.random() * 2);
        int j = (int) (Math.random() * 2);
        return new int[]{i, j};
    }

    private static boolean isItEmpty(int i, int j) {
        return (Logic.boardStatus[i][j] == Sign.EMPTY);
    }

    private static int[] blockOpponent() {
        Sign newSignTable[][];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] squareCoordinates = new int[]{i, j};
                if (isItEmpty(i, j)) {
                    newSignTable = rewriteTable();
                    newSignTable[i][j] = Sign.O;
                    if (Logic.isWinner(newSignTable)) {
                        return squareCoordinates;
                    }
                }
            }
        }
        return new int[]{3, 3};
    }

    private static int[] winningMoves() {
        Sign newSignTable[][];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] squareCoordinates = new int[]{i, j};
                if (isItEmpty(i, j)) {
                    newSignTable = rewriteTable();
                    newSignTable[i][j] = Sign.X;
                    if (Logic.isWinner(newSignTable)) {
                        return squareCoordinates;
                    }
                }
            }
        }
        return new int[]{3, 3};
    }

    private static Sign[][] rewriteTable() {
        Sign[][] newSignTable = new Sign[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newSignTable[i][j] = Logic.boardStatus[i][j];
            }
        }
        return newSignTable;
    }

    private static int[] tactic() {
        if (Logic.boardStatus[2][0] == Sign.EMPTY) {
            return new int[]{2, 0};
        } else if (Logic.boardStatus[0][2] == Sign.EMPTY) {
            return new int[]{0, 2};
        } else if (Logic.boardStatus[0][0] == Sign.EMPTY) {
            return new int[]{0, 0};
        } else if (Logic.boardStatus[2][2] == Sign.EMPTY) {
            return new int[]{2, 2};
        }
        return new int[]{3, 3};

    }

    private static int[] selectCorner() {
        int i = (int) (Math.random() * 4 + 1);
        switch (i) {
            case 1:
                return new int[]{0, 0};
            case 2:
                return new int[]{0, 2};
            case 3:
                return new int[]{2, 0};
            case 4:
                return new int[]{2, 2};
        }
        return new int[]{3, 3};
    }

    private static boolean isTableEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Logic.boardStatus[i][j] != Sign.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
