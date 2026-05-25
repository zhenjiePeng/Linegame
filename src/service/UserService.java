package service;
import model.User;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class UserService {
    private Map<String,User> users;//搞一个二维表，存用户和用户名
    private static final String USER_FILE = "data/users.dat"; // 用户数据文件路径
    public UserService() {
        users = new HashMap<>();//（key，value），key唯一，value可以重复
        loadUsers();
    }
    public boolean register(String username,String password){//注册
        if(users.containsKey(username)){
            return false;
        }
        User user=new User(username,password);
        users.put(username,user);//put方法没有key则新建一个key，有该key就覆盖该key的值
        saveUsers();
        return true;
    }
    public User login(String username, String password){//登录
        if(!users.containsKey(username)){
            return null;
        }
        User user=users.get(username);//找到那个用户的用户名
        if(user.checkPassword(password)){
            return user;
        }
        return null;
    }
    public boolean userExists(String username) {//判断用户名是否存在
        return users.containsKey(username);
    }
    public void updateUser(User user) {//更新用户信息
        users.put(user.getUsername(), user);
        saveUsers();
    }
    public Map<String, User> getAllUsers() {//getter
        return users;
    }
    private void saveUsers(){
        try {
            // 确保 data 文件夹存在
            File file = new File(USER_FILE);
            File parent = file.getParentFile();//获取user文件的父文件夹
            if (parent != null && !parent.exists()) {
                parent.mkdirs();//用来创建文件夹
            }

            // 对象输出流，绑定文件，写入对象，简要地说就是用来把数据传到文件里的方法
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(USER_FILE));

            // 写入整个 users Map
            oos.writeObject(users);

            // 关闭流
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();//如果IO异常（文件找不到、权限不够、磁盘满了、流关闭失败等），告诉你哪一行错了，为什么错了
        }
    }
    @SuppressWarnings("unchecked")//我知道这个强转有风险，但我保证没问题，编译器你别报警告了
    private void loadUsers() {
        File file = new File(USER_FILE);

        // 文件不存在，说明第一次运行，直接返回
        if (!file.exists()) {
            return;
        }

        try {
            // 对象输入流
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(USER_FILE));//把二进制数据读回对象

            // 读取并转换为 Map<String, User>
            users = (Map<String, User>) ois.readObject();//对象=（要转成的格式）流的名字.readObject()

            // 关闭流
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            // 文件损坏时重新创建空集合，避免程序崩溃
            users = new HashMap<>();
            e.printStackTrace();
        }
    }
}

