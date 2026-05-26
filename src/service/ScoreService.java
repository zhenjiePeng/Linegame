package service;

public class ScoreService {
    private static final int BASE_SCORE=10;
    private int score;
    public ScoreService(){
        score=0;
    }
    public void addMatchScore(){
        score+=BASE_SCORE;
    }
    public void addScore(int value){
        if(value<0){
            return;
        }
        score+=value;
    }
    public void subtractScore(int value){
        if(value<0){
            return;
        }
        score-=value;
        if(score<0){
            score=0;
        }
    }
    public int getScore(){
        return score;
    }
    public void reset(){
        score=0;
    }
    public void setScore(int score){
        if(score<0){
            this.score=0;
            return;
        }
        this.score=score;
    }
}
