package service;
import model.Board;
import model.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class BoardGenerator {
    private Random random=new Random();
    public Board generateBoard(int rows,int cols,int typeCount){
        int total=rows*cols;
        if (total % 2 != 0) {
            throw new IllegalArgumentException("棋盘格子数必须为偶数");
        }
        Board board=new Board(rows,cols);
        List<Integer> types=new ArrayList<>();
        int pairCount=total/2;//计算要多少对图案
        for(int i=0;i<pairCount;i++){// 生成图案编号,例如 typeCount = 5 时：1,1,2,2,3,3,4,4,5,5,1,1,2,2...周期性输出
            int type=(i%typeCount)+1;
            types.add(type);
            types.add(type);
        }
        Collections.shuffle(types,random);//对 List 集合进行随机打乱（洗牌），第二个参数指定自定义随机源(种子)。
        int index=0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int type = types.get(index++);//按顺序将随机type拉出来赋到tile里
                Tile tile = new Tile(row, col, type);
                board.setTile(row, col, tile);
            }
        }
        return board;
    }
}
//注意这里的Board只保证了偶数次出现，并没有保证一定可解，故下面要写一个方法来判断它是否可解，如果不可解再重新生成(也就是PathFinder）