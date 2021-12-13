package exam;

public class Code794 {
    public static void main(String[] args) {
        System.out.println(new Code794().validTicTacToe(new String[]{"XXX", "   ", "OOO"}));
    }

    /**
     * 1.判断x与O的数量，X比O多1个或相等。X最多只能选下5个，O最多4个
     * 2.判断当前胜利条件是否只有X和O其中1个达成
     * 3.判断胜利条件互相不冲突，横向最多只有1个，纵向最多只有1个。X胜利条件最多只有2个，O最多只有1个
     * 4.判断如果x胜利则x-o=1,如果o胜利则o-x=0
     *
     * @param board
     * @return
     */
    public boolean validTicTacToe(String[] board) {
        // 判断条件1
        int countX = 0;
        int countO = 0;
        for (String s : board) {
            for (int j = 0; j < s.length(); j++) {
                char tempC = s.charAt(j);
                if (tempC == 'X') {
                    countX++;
                }
                if (tempC == 'O') {
                    countO++;
                }
            }
        }
        if (countX - countO > 1 || countO - countX > 0) {
            return false;
        }
        if (countX > 5 || countO > 4) {
            return false;
        }
        // 判断条件2
        boolean xWin = false;
        boolean oWin = false;

        // 判断行胜利
        int rowWin = 0;
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            String s = board[i];
            if (' ' == s.charAt(0)) {
                continue;
            }
            char tempC = s.charAt(0);
            int countWin = 0;
            for (int j = 0; j < s.length(); j++) {
                if (tempC == s.charAt(j)) {
                    countWin++;
                }
            }
            if (countWin == 3) {
                if (tempC == 'X') {
                    xWin = true;
                }
                if (tempC == 'O') {
                    oWin = true;
                }
                rowWin++;
            }
        }

        if (rowWin > 1) {
            return false;
        }
        // 判断列胜利
        int colWin = 0;
        for (int i = 0; i < 3; i++) {
            char tempC = board[0].charAt(i);
            int countWin = 0;
            if (' ' == tempC) {
                continue;
            }
            for (int j = 0; j < 3; j++) {
                if (tempC == board[j].charAt(i)) {
                    countWin++;
                }
            }
            if (countWin == 3) {
                if (tempC == 'X') {
                    xWin = true;
                }
                if (tempC == 'O') {
                    oWin = true;
                }
                colWin++;
            }
        }
        if (colWin > 1) {
            return false;
        }
        // 判断斜胜利
        if (true) {
            char tempC = board[0].charAt(0);
            if (tempC != ' ') {
                if (tempC == board[1].charAt(1) && tempC == board[2].charAt(2)) {
                    if (tempC == 'X') {
                        xWin = true;
                    }
                    if (tempC == 'O') {
                        oWin = true;
                    }
                }
            }
            tempC = board[0].charAt(2);
            if (tempC != ' ') {
                if (tempC == board[1].charAt(1) && tempC == board[2].charAt(0)) {
                    if (tempC == 'X') {
                        xWin = true;
                    }
                    if (tempC == 'O') {
                        oWin = true;
                    }
                }
            }
        }
        if (xWin && oWin) {
            return false;
        }
        // 判断4
        if (xWin && countX - countO != 1) {
            return false;
        }
        if (oWin && countX - countO != 0) {
            return false;
        }
        return true;
    }
}
