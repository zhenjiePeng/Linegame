package service;
import model.Board;
import java.io.*;
import java.io.Serializable;

public class SaveLoadService {
    public void saveGame(Board board,int score,String path){
        try{
            GameData data = new GameData(board, score);
            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(path)
                    );
            out.writeObject(data);
            out.close();
            System.out.println("游戏保存成功");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("游戏保存失败");
        }
    }
    public GameData loadGame(String path){
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(path));
            GameData data=(GameData) in.readObject();
            in.close();
            System.out.println("游戏读取成功");
            return data;
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("游戏读取失败");
            return null;
        }
    }
}

