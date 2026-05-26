package service;

public class ComboService {
    private static final long COMBO_INTERVAL=3000;//combo时间2秒
    private int combo;
    private long lastMatchTime;
    public ComboService(){
        reset();
    }
    public void reset(){
        combo=0;
        lastMatchTime=0;
    }
    public int onMatch(){
        long currentTime=System.currentTimeMillis();
        if(lastMatchTime==0){
            combo=1;//第一次消除
        }
        else{
            long delta=currentTime-lastMatchTime;
            if(delta<=COMBO_INTERVAL){
                combo++;
            }
            else{
                combo=1;
            }
        }
        lastMatchTime=currentTime;
        return combo;
    }
    public boolean isComboActive() {
        if(combo<=0){
            return false;
        }
        long currentTime=System.currentTimeMillis();
        return currentTime-lastMatchTime<=COMBO_INTERVAL;
    }
    public int getCombo(){
        if(!isComboActive()){
            return 0;
        }
        return combo;
    }
    public long getComboInterval() {
        return COMBO_INTERVAL;
    }
    public long getRemainingComboTime(){
        if(!isComboActive()){
            return 0;
        }
        long currentTime=System.currentTimeMillis();
        long remaining=COMBO_INTERVAL-(currentTime-lastMatchTime);
        return Math.max(remaining,0);
    }
    public long getLastMatchTime(){
        return lastMatchTime;
    }
}
