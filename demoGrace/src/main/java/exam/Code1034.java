package exam;

public class Code1034 {
    public static void main(String[] args) {
        new Code1034().colorBorder(new int[][]{{1, 2, 1, 2, 1, 2}, {2, 2, 2, 2, 1, 2}, {1, 2, 2, 2, 1, 2}}, 1, 3, 1);
    }

    /**
     * 上下左右进行扩散，如果四个方向都有且自己不是方格边界则不是扩散边界
     *
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    static boolean[][] fillJudge = null;
    static boolean[][] checkJudge = null;
    static int totalRowIdx;
    static int totalColIdx;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int ordVal = grid[row][col];
        totalRowIdx = grid.length - 1;
        totalColIdx = grid[0].length - 1;
        fillJudge = new boolean[totalRowIdx + 1][totalColIdx + 1];
        checkJudge = new boolean[totalRowIdx + 1][totalColIdx + 1];
        fill(grid, row, col, color, ordVal);
        return grid;
    }

    /**
     * 返回是否可以被感染
     *
     * @param grid
     * @param myRow
     * @param myCol
     * @param color
     * @param ordVal
     * @return
     */
    public boolean fill(int[][] grid, int myRow, int myCol, int color, int ordVal) {
        if (myRow < 0 || myRow > totalRowIdx || myCol < 0 || myCol > totalColIdx) {
            return false;
        }
        if (fillJudge[myRow][myCol]) {
            if (grid[myRow][myCol] == color || grid[myRow][myCol] == ordVal) {
                return true;
            } else {
                return false;
            }
        }
        fillJudge[myRow][myCol] = true;
        int cur = grid[myRow][myCol];
        // 不是要感染的目标
        if (cur != ordVal) {
            return false;
        }
        checkJudge[myRow][myCol] = true;
        boolean doColor = false;
        int countFill = 0;
        // 向上感染
        if (myRow > 0) {
            fill(grid, myRow - 1, myCol, color, ordVal);
            countFill += checkJudge[myRow - 1][myCol] ? 1 : 0;
        } else {
            doColor = true;
        }
        // 向下感染
        if (myRow < totalRowIdx) {
            fill(grid, myRow + 1, myCol, color, ordVal);
            countFill += checkJudge[myRow + 1][myCol] ? 1 : 0;
        } else {
            doColor = true;
        }
        // 向左感染
        if (myCol > 0) {
            fill(grid, myRow, myCol - 1, color, ordVal);
            countFill += checkJudge[myRow][myCol - 1] ? 1 : 0;
        } else {
            doColor = true;
        }
        // 向右感染
        if (myCol < totalColIdx) {
            fill(grid, myRow, myCol + 1, color, ordVal);
            countFill += checkJudge[myRow][myCol + 1] ? 1 : 0;
        } else {
            doColor = true;
        }
        // 如果自己不是grid边界判断上下左右是否均可感染

        if (countFill < 4) {
            doColor = true;
        }
        if (doColor) {
            grid[myRow][myCol] = color;
        }
        return true;
    }
}
