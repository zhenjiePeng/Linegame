package service;
import model.Board;
import model.Tile;
public class MatchService {
    private PathFinder pathFinder;
    private Board board;
    public MatchService(Board board) {
        this.board = board;
        this.pathFinder = new PathFinder();
    }
    public boolean canMatch(Tile a,Tile b){
        if(a==null || b==null){
            return false;
        }
        if(a==b){
            return false;
        }
        if(a.isRemoved() || b.isRemoved()){
            return false;
        }
        if(!a.sameType(b)){
            return false;
        }
        boolean canConnect= pathFinder.canConnect(board,a,b);
        if(!canConnect){
            return false;
        }
        return true;
    }
    public boolean match(Tile a, Tile b) {
        if (!canMatch(a, b)) {
            return false;
        }
        a.remove();
        b.remove();
        return true;
    }
    public  boolean isGameFinished(){
        return board.isCleared();
    }
    public Board getBoard(){
        return board;
    }
    public PathFinder getPathFinder() {
        return pathFinder;
    }
    public int getRemainingTileCount() {
        return board.countRemainingTiles();
    }
    public boolean hasAvailableMatch(){//判断当前棋盘是否还存在可消除的一对
        Tile[][] tiles=board.getTiles();
        int rows=board.getRows();
        int cols = board.getCols();
        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {

                Tile a = tiles[r1][c1];

                // 跳过空格或已消除方块
                if (a == null || a.isRemoved()) {
                    continue;
                }

                for (int r2 = 0; r2 < rows; r2++) {
                    for (int c2 = 0; c2 < cols; c2++) {

                        Tile b = tiles[r2][c2];

                        // 不能是同一个方块
                        if (a == b) {
                            continue;
                        }

                        // 跳过空格或已消除方块
                        if (b == null || b.isRemoved()) {
                            continue;
                        }

                        // 类型必须相同
                        if (!a.sameType(b)) {
                            continue;
                        }

                        // 判断能否连接
                        if (pathFinder.canConnect(board, a, b)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
