package service;
import model.RankingEntry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class RankingService implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<RankingEntry> rankings;
    public RankingService() {
        rankings = new ArrayList<>();
    }
    public void addRecord(RankingEntry entry) {
        if (entry == null) {
            return;
        }
        rankings.add(entry);
        sortRankings();
    }
    public void sortRankings() {
        //java工具类，用来给 List 排序。
        Collections.sort(rankings,new Comparator<RankingEntry>() {
                    @Override
                    public int compare(RankingEntry a,
                                       RankingEntry b) {
                        // 分数降序
                        if (a.getScore() != b.getScore()) {
                            return b.getScore()
                                    - a.getScore();
                        }
                        // 时间升序
                        return a.getTimeUsed()
                                - b.getTimeUsed();
                    }
                });
    }
    public List<RankingEntry> getRankings() {
        return rankings;
    }
    //输出前n名
    public List<RankingEntry> getTopN(int n) {
        List<RankingEntry> result =
                new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        int limit =
                Math.min(n, rankings.size());
        for (int i = 0; i < limit; i++) {
            result.add(rankings.get(i));
        }
        return result;
    }
    public RankingEntry getBestRecord() {
        if (rankings.isEmpty()) {
            return null;
        }
        return rankings.get(0);
    }
    public void clear() {
        rankings.clear();
    }
    public boolean isEmpty() {
        return rankings.isEmpty();
    }
    public int size() {
        return rankings.size();
    }
    public void printRankings() {
        if (rankings.isEmpty()) {
            System.out.println("排行榜为空");
            return;
        }
        System.out.println("===== 排行榜 =====");
        for (int i = 0; i < rankings.size(); i++) {
            System.out.println(
                    (i + 1) + ". "
                            + rankings.get(i)
            );
        }
    }

}
