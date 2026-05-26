package service;
import model.Board;
import java.io.Serializable;
public class GameData implements Serializable{
    private static final long serialVersionUID = 1L;
    private Board board;
    private int score;
    public GameData(Board board,int score){
        this.board=board;
        this.score=score;
    }

    public int getScore() {
        return score;
    }
    public Board getBoard(){
        return board;
    }
}