package service;
import model.MatchRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ReplayService implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<MatchRecord> records;
    public ReplayService() {
        records = new ArrayList<>();
    }
    public void addRecord(MatchRecord record) {
        if (record == null) {
            return;
        }
        records.add(record);
    }
    //获取所有记录
    public List<MatchRecord> getRecords() {
        return records;
    }
    //获取指定记录
    public MatchRecord getRecord(int index) {
        if (index < 0 || index >= records.size()) {
            return null;
        }
        return records.get(index);
    }
    public int size() {
        return records.size();
    }
    public boolean isEmpty() {
        return records.isEmpty();
    }
    public void clear() {
        records.clear();
    }
    public void replay() {
        if (records.isEmpty()) {
            System.out.println("没有回放记录");
            return;
        }
        System.out.println("===== 开始回放 =====");
        long startTime =
                records.get(0).getTimestamp();
        for (MatchRecord record : records) {
            long currentTime =
                    record.getTimestamp();
            long delay =
                    currentTime - startTime;
            try {
                Thread.sleep(
                        Math.max(delay, 0)
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(record);
            startTime = currentTime;
        }
        System.out.println("===== 回放结束 =====");
    }
    public void printRecords() {
        if (records.isEmpty()) {
            System.out.println("没有记录");
            return;
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println(
                    (i + 1) + ". "
                            + records.get(i)
            );
        }
    }

}
