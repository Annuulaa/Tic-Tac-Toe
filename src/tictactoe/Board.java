package tictactoe;

import javax.swing.ImageIcon;
import static tictactoe.Logic.isRowWin;

public class Board {

    public ImageIcon o = new ImageIcon(getClass().getResource("/icon/Kółko.png"));
    public ImageIcon oHorizontally = new ImageIcon(getClass().getResource("/icon/KółkoPoziom.png"));
    public ImageIcon oVertically = new ImageIcon(getClass().getResource("/icon/KółkoPion.png"));
    public ImageIcon oCross1 = new ImageIcon(getClass().getResource("/icon/KółkoSkos1.png"));
    public ImageIcon oCross2 = new ImageIcon(getClass().getResource("/icon/KółkoSkos2.png"));
    public ImageIcon x = new ImageIcon(getClass().getResource("/icon/Krzyżyk.png"));
    public ImageIcon xHorizontally = new ImageIcon(getClass().getResource("/icon/KrzyżykPoziom.png"));
    public ImageIcon xVertically = new ImageIcon(getClass().getResource("/icon/KrzyżykPion.png"));
    public ImageIcon xCross1 = new ImageIcon(getClass().getResource("/icon/KrzyżykSkos1.png"));
    public ImageIcon xCross2 = new ImageIcon(getClass().getResource("/icon/KrzyżykSkos2.png"));
    public ImageIcon empty = new ImageIcon(getClass().getResource("/icon/PustePole.png"));

    public ImageIcon[][] iconsBoardStatus() {
        ImageIcon[][] iconsOnBoard = new ImageIcon[3][3];

        ifNoWinner(iconsOnBoard);
        ifHorizontalWin(iconsOnBoard);
        ifVerticalWin(iconsOnBoard);
        ifCrossWin(iconsOnBoard);
        return iconsOnBoard;
    }

    public void ifNoWinner(ImageIcon[][] iconsOnBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Logic.boardStatus[i][j] == Sign.EMPTY) {
                    iconsOnBoard[i][j] = empty;
                } else if (Logic.boardStatus[i][j] == Sign.X) {
                    iconsOnBoard[i][j] = x;
                } else if (Logic.boardStatus[i][j] == Sign.O) {
                    iconsOnBoard[i][j] = o;
                }
            }
        }
    }

    public void ifHorizontalWin(ImageIcon[][] iconsOnBoard) {
        for (int i = 0; i < 3; i++) {
            if (Logic.isRowWin(Logic.boardStatus[i])) {
                for (int j = 0; j < 3; j++) {
                    if (Logic.whoWin == Sign.O) {
                        iconsOnBoard[i][j] = oHorizontally;
                    } else {
                        iconsOnBoard[i][j] = xHorizontally;
                    }
                }
            }
        }
    }

    public void ifVerticalWin(ImageIcon[][] iconsOnBoard) {
        if (Logic.isColumnWin(Logic.boardStatus)) {
            int j;
            for (int i = 0; i < 3; i++) {
                Sign[] table = new Sign[3];
                for (j = 0; j < 3; j++) {
                    table[j] = Logic.boardStatus[j][i];
                }
                if (Logic.isRowWin(table)) {
                    if (Logic.whoWin == Sign.O) {
                        for (int k = 0; k < 3; k++) {
                            iconsOnBoard[k][i] = oVertically;
                        }
                    } else {
                        for (int k = 0; k < 3; k++) {
                            iconsOnBoard[k][i] = xVertically;
                        }
                    }
                }
            }
        }
    }

    public void ifCrossWin(ImageIcon[][] iconsOnBoard) {
        if (Logic.isCrossWin(Logic.boardStatus)) {
            Sign[] table = new Sign[3];
            for (int i = 0; i < 3; i++) {
                table[i] = Logic.boardStatus[i][i];
            }
            if (isRowWin(table)) {
                if (Logic.whoWin == Sign.O) {
                    for (int i = 0; i < 3; i++) {
                        iconsOnBoard[i][i] = oCross1;
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        iconsOnBoard[i][i] = xCross1;
                    }
                }
            }
            int j = 2;
            for (int i = 0; i < 3; i++) {
                table[i] = Logic.boardStatus[i][j];
                j--;
            }

            if (isRowWin(table)) {
                j = 2;
                for (int i = 0; i < 3; i++) {
                    if (Logic.whoWin == Sign.O) {
                        iconsOnBoard[i][j] = oCross2;
                    } else {
                        iconsOnBoard[i][j] = xCross2;
                    }
                    j--;
                }
            }
        }
    }
}
