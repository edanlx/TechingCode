package exam;

public class Code419 {
    public static void main(String[] args) {
        System.out.println(new Code419().countBattleships(new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}));
        ;
    }

    public int countBattleships(char[][] board) {
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char cur = board[i][j];
                if (cur != 'X') {
                    continue;
                }
                if (i == 0 && j == 0) {
                    result++;
                    continue;
                }
                if (i == 0) {
                    if (board[i][j - 1] != 'X') {
                        result++;
                    }
                    continue;
                }
                if (j == 0) {
                    if (board[i - 1][j] != 'X') {
                        result++;
                    }
                    continue;
                }
                if (board[i - 1][j] != 'X' && board[i][j - 1] != 'X') {
                    result++;
                }
            }
        }
        return result;
    }

    public int countBattleships2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < col && board[i][k] == 'X'; ++k) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < row && board[k][j] == 'X'; ++k) {
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }
        return ans;
    }
}
