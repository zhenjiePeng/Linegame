package model;
import java.io.Serializable;
public class Tile implements Serializable {
    private int row;
    private int col;
    private int type;//图案类型，相同类型消去
    private boolean removed;//是否被消去
    private boolean selected;//是否被选中
    public Tile(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.removed = false;
        this.selected = false;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getType() {
        return type;
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean isSelected() {
        return selected;
    }

    // ===== Setter 方法 =====

    public void setType(int type) {
        this.type = type;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isEmpty(){
        return removed;
    }
    public void remove(){
        this.removed=true;
        this.selected=false;
    }
    public void restore(){//恢复该方块（主要用于读档或调试）
        this.removed=false;
    }
    public boolean sameType(Tile other) {//用来识别两个相同的方块
        if (other == null) {
            return false;
        }
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return "Tile(" + row + "," + col + ",type=" + type + ")";
    }
}
