package controller;
import model.Board;
import model.Tile;
import service.BoardGenerator;
import service.MatchService;
public class GameController {
    private boolean surrendered=false;
    private Board board;
    private  MatchService matchService;
    private BoardGenerator boardGenerator;
    private Tile firstSelected;
    private int rows;
    private int cols;
    private int typeCount;
    public void surrender() {
        surrendered = true;
    }
    public GameController(int rows, int cols, int typeCount) {
        this.rows = rows;
        this.cols = cols;
        this.typeCount = typeCount;
        this.boardGenerator = new BoardGenerator();
        // 初始化游戏
        startNewGame();
    }
    public void startNewGame(){
        board=boardGenerator.generateBoard(rows,cols,typeCount);
        matchService=new MatchService(board);
        firstSelected=null;
        board.clearSelection();
    }
    public boolean selectTile(int row, int col){
        if(!board.isInside(row,col)){
            return false;
        }
        Tile current=board.getTile(row,col);
        if(current==null){
            return false;
        }
        if(current.isRemoved()){
            return false;
        }
        if(firstSelected==null){
            firstSelected=current;
            current.setSelected(true);
            return false;
        }
        if(firstSelected==current){
            current.setSelected(false);
            firstSelected=null;
            return false;
        }
        boolean success=matchService.match(firstSelected,current);
        if(success){
            firstSelected.setSelected(false);
            current.setSelected(false);
            firstSelected=null;
            return true;
        }
        firstSelected.setSelected(false);
        current.setSelected(false);
        firstSelected=null;
        return false;
    }
    public boolean isGameOver(){
        return surrendered || matchService.isGameFinished();
    }
    public Board getBoard(){
        return board;
    }
    public Tile getFirstSelected(){
        return firstSelected;
    }
    public void shuffleBoard(){
        board = boardGenerator.generateBoard(rows, cols, typeCount);
        matchService = new MatchService(board);
        firstSelected = null;
    }
}
