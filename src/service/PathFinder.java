package service;

import model.Board;
import model.Tile;
import java.util.ArrayDeque;
import java.util.Queue;
public class PathFinder {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};//表示四个方向所带来的坐标影响
    private static class State {
        int row; // 当前所在行
        int col; // 当前所在列
        int direction;//0上1下2左3右,当前移动方向：0~3
        int turns;
        State(int row, int col, int direction, int turns) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.turns = turns;
        }
    }
    public boolean canConnect(Board board,Tile a,Tile b){
        if (board == null || a == null || b == null) {
            return false;
        }
        if (a == b) {
            return false;
        }
        if(!a.sameType(b)){
            return false;
        }
        int rows = board.getRows();
        int cols = board.getCols();
        int extRows = rows + 2;
        int extCols = cols + 2;//识别从外围绕行，上下各加一格
        int[][][] visited = new int[extRows][extCols][4];//visited[r][c][dir]：记录到达 (r, c) 且最后的移动方向为dir时所用转折数，（dir：0上1下2左3右）
        for (int r = 0; r < extRows; r++) {
            for (int c = 0; c < extCols; c++) {
                for (int d = 0; d < 4; d++) {
                    visited[r][c][d] = Integer.MAX_VALUE;//初始时，还没有任何方式到达这里
                }
            }
        }// 初始化为一个不可能的较大值
        int startRow = a.getRow() + 1;
        int startCol = a.getCol() + 1;
        int targetRow = b.getRow() + 1;
        int targetCol = b.getCol() + 1;
        Queue<State> queue = new ArrayDeque<>();
        for (int dir = 0; dir < 4; dir++) {
            visited[startRow][startCol][dir] = 0;
            queue.offer(new State(startRow, startCol, dir, 0));//向队列末尾添加元素添加成功返回true，队列满了不报错，返回false
        }//起点向四个方向出发，初始转折数为 0
        while(!queue.isEmpty()){
            State current = queue.poll();//队列取出并删除队首元素（初始queue元素：四个方向的初始state）
            //先从起点沿着当前方向先走一格，试探一下
            int nextRow = current.row + DR[current.direction];
            int nextCol = current.col + DC[current.direction];
            while(nextRow >= 0 && nextRow < extRows && nextCol >= 0 && nextCol < extCols){
                if(nextRow==targetRow && nextCol==targetCol){
                    return true;
                }
                if(!isPassable(board,nextRow,nextCol)){
                    break;//next表明已经按指定方向走了一格，如果这个方向前面一格就是tile（就是靠着的意思），那这个方向就不需要再走了，直接换其他方向考虑
                }
                for(int newDir = 0; newDir < 4; newDir++){// 尝试从当前位置转向四个方向
                    int newTurns=current.turns;
                    // 方向变化则增加一次转折
                    if (newDir != current.direction) {
                        newTurns++;
                    }
                    if (newTurns > 2) {
                        continue;//如果转折数不够了就不转了
                    }
                    if (newTurns < visited[nextRow][nextCol][newDir]) {
                        visited[nextRow][nextCol][newDir] = newTurns;
                        queue.offer(
                                new State(nextRow, nextCol, newDir, newTurns)
                        );
                    }
                }
                nextRow += DR[current.direction];
                nextCol += DC[current.direction];//如果遮住转弯的for循环不看，这里就是一直向前走，直到碰到边界或障碍停止
            }
        }
        return false;
    }
    private boolean isPassable(Board board, int extRow, int extCol) {
        int rows = board.getRows();
        int cols = board.getCols();
        if (extRow == 0 || extRow == rows + 1 ||
                extCol == 0 || extCol == cols + 1) {
            return true;
        }// 外围一圈始终视为空白，可通行
        int row = extRow - 1;
        int col = extCol - 1;
        return board.isEmpty(row,col);
    }
}
