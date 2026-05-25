package service;
import model.Board;
import model.Tile;

import java.util.Random;
public class ComputerService {
    private final Board board;
    private final MatchService matchService;
    private final SkillService skillService;
    private final Random random = new Random();
    private int minThinkTime=500;
    private int maxThinkTime=3000;
    private double skillProbability=0.2;
    public ComputerService(Board board,
                     MatchService matchService,
                     SkillService skillService) {
        this.board = board;
        this.matchService = matchService;
        this.skillService = skillService;
    }
    public boolean makeMove(){
        think();
        if(random.nextDouble()<skillProbability){//利用随机数构建可能性
            boolean usedSkill=useRandomSkill();
            if(usedSkill){
                return true;
            }
        }
        Tile[] move=findMove();
        if (move == null) {
            return false;
        }
        return matchService.match(move[0], move[1]);
    }
    public Tile[] findMove() {
        int rows = board.getRows();
        int cols = board.getCols();
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
                        if (matchService.canMatch(a, b)) {
                            return new Tile[]{a, b};
                        }
                    }
                }
            }
        }
        return null;
    }
    public boolean useRandomSkill(){
        int choice=random.nextInt(4);//随机选择一个技能
        switch (choice){
            case 0:
                Tile[] hint=skillService.hint();
                return hint!=null;
            case 1:
                skillService.shuffle();
                return true;
            case 2:
                if(!skillService.isRacing()){
                    skillService.startRace(5);
                    return true;
                }
            case 3:
                Tile a = getRandomTile();
                Tile b = getRandomTile();
                if (a != null && b != null && a != b) {
                    skillService.swap(a, b);
                    return true;
                }
                break;
        }
        return false;
    }
    private Tile getRandomTile() {
        int rows = board.getRows();
        int cols = board.getCols();
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            Tile tile = board.getTile(r, c);
            if (tile != null && !tile.isRemoved()) {
                return tile;
            }
        }
        return null;
    }
    private void think() {
        int delay = minThinkTime + random.nextInt(maxThinkTime - minThinkTime + 1);
        try {
            Thread.sleep(delay);//让当前线程暂停一段时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setThinkTime(int min, int max) {
        if (min < 0 || max < min) {
            return;
        }
        minThinkTime = min;
        maxThinkTime = max;
    }
    public void setSkillProbability(double probability) {
        if (probability < 0 || probability > 1) {
            return;
        }
        skillProbability = probability;
    }
}
