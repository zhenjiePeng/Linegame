package model;
import java.io.Serializable;
public class Board implements Serializable {
    private int rows;
    private int cols;
    private Tile[][] tiles;
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.tiles = new Tile[rows][cols];
    }
    public Tile getTile(int row, int col) {//获取指定位置的方块
        if (!isInside(row, col)) {
            return null;
        }
        return tiles[row][col];
    }
    public void setTile(int row, int col, Tile tile) {//设置指定位置的方块
        if (!isInside(row, col)) {
            return;
        }
        tiles[row][col] = tile;
    }
    public boolean isInside(int row, int col) {//判断坐标是否在棋盘范围内
        return row >= 0 && row < rows &&
                col >= 0 && col < cols;
    }
    public boolean isEmpty(int row, int col) {//判断某个位置是否为空
        if (!isInside(row, col)) {//越界也算
            return true;
        }
        Tile tile = tiles[row][col];
        if (tile == null) {
            return true;
        }
        return tile.isRemoved();
    }
    public boolean isCleared() {//判断棋盘是否全部清空
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = tiles[r][c];
                if (tile != null && !tile.isRemoved()) {//为什么要有null的判断呢？因为可能我这里并不想放tile，让他空着，增加游戏多样性
                    return false;
                }
            }
        }
        return true;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Tile[][] getTiles() {//统计当前剩余未消除的方块数量
        return tiles;
    }   public int countRemainingTiles() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = tiles[r][c];
                if (tile != null && !tile.isRemoved()) {
                    count++;
                }
            }
        }
        return count;
    }
    public void clearSelection() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = tiles[r][c];
                if (tile != null) {
                    tile.setSelected(false);
                }
            }
        }
    }
}
