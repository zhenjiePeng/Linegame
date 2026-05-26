package service;
import model.Board;
import model.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class SkillService {
    private int hintCount = 3;
    private int shuffleCount = 2;
    private int raceCount = 1;
    private int swapCount = 2;
    private final Board board;
    private final MatchService matchService;
    private final ScoreService scoreService;
    private final ComboService comboService;
    private final Random random = new Random();
    private boolean racing = false;
    private long raceStartTime;
    private int raceTarget;
    private int currentRaceCount;
    private static final long RACE_DURATION = 10000;
    private static final int RACE_REWARD = 100;
    private static final int RACE_PENALTY = 50;
    public SkillService(Board board,
                        MatchService matchService,
                        ScoreService scoreService,
                        ComboService comboService) {
        this.board = board;
        this.matchService = matchService;
        this.scoreService = scoreService;
        this.comboService = comboService;
    }

    public int getHintCount() {
        return hintCount;
    }

    public int getRaceCount() {
        return raceCount;
    }

    public int getShuffleCount() {
        return shuffleCount;
    }

    public int getSwapCount() {
        return swapCount;
    }

    //1.hint
    public Tile[] hint(){
        if (hintCount <= 0) {
            return null;
        }
        int rows=board.getRows();
        int cols=board.getCols();
        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                Tile a = board.getTile(r1, c1);
                if (a == null || a.isRemoved()) {
                    continue;
                }
                for (int r2 = 0; r2 < rows; r2++) {
                    for (int c2 = 0; c2 < cols; c2++) {
                        Tile b = board.getTile(r2, c2);
                        if (b == null || b.isRemoved()) {
                            continue;
                        }
                        if (a == b) {
                            continue;
                        }
                        if (a.getType() != b.getType()) {
                            continue;
                        }
                        if (matchService.canMatch(a, b)) {
                            hintCount--;
                            return new Tile[]{a, b};
                        }
                    }
                }
            }
        }
        return null;
    }
    //2.洗牌
    public void shuffle(){
        if(shuffleCount<=0){
            return;
        }
        List<Integer> types=new ArrayList<>();
        int rows = board.getRows();
        int cols = board.getCols();
        for (int r = 0; r < rows; r++) {//收集剩余 Tile 类型
            for (int c = 0; c < cols; c++) {
                Tile tile = board.getTile(r, c);
                if (tile != null && !tile.isRemoved()) {
                    types.add(tile.getType());
                }
            }
        }
        Collections.shuffle(types);//打乱
        int index=0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = board.getTile(r, c);
                if (tile != null && !tile.isRemoved()) {
                    tile.setType(types.get(index));
                    index++;//保持位置不变，只更换类型
                }
            }
        }
        shuffleCount--;
    }
    //3.竞速
    public void startRace(int targetCount) {
        if(raceCount<=0){
            return;
        }
        racing = true;
        raceStartTime = System.currentTimeMillis();
        raceTarget = targetCount;
        currentRaceCount = 0;
        raceCount--;
    }
    public void onRaceMatch(){
        if(!racing){
            return;
        }
        currentRaceCount++;
        checkRaceResult();
    }
    public void checkRaceResult(){
        if(!racing){
            return;
        }
        long currentTime=System.currentTimeMillis();
        if(currentRaceCount>=raceTarget){
            scoreService.addScore(RACE_REWARD);
            racing=false;
            return;
        }
        if(currentTime-raceStartTime>=RACE_DURATION){
            scoreService.subtractScore(RACE_PENALTY);
            racing=false;
        }
    }
    public boolean isRacing() {
        return racing;
    }
    public long getRemainingRaceTime() {
        if (!racing) {
            return 0;
        }
        long currentTime = System.currentTimeMillis();
        long remaining =
                RACE_DURATION - (currentTime - raceStartTime);
        return Math.max(remaining, 0);
    }
    //4.换位
    public void swap(Tile a, Tile b) {
        if(swapCount<=0){
            return;
        }
        if (a == null || b == null) {
            return;
        }
        if (a.isRemoved() || b.isRemoved()) {
            return;
        }
        int temp = a.getType();
        a.setType(b.getType());
        b.setType(temp);
        swapCount--;
    }
}
