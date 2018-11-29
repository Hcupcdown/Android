package com.example.admin.algorithm_2;


public class NameKeyHash {
    //存放联系人信息的数组
    private static int total = 16;
    //选择哈希函数
    private static int HN = 0;
    //选择解决冲突的方法
    private static int SN = 0;
    //线性取中值时的移位量，初始为2
    private static int AND = 4;
    private static LinkMan[] linkMan = new LinkMan[total];
    //已经占用个数
    private static int m = 0;
    //构造函数
    public NameKeyHash();
    //设置要使用的函数
    public static void changeHN_SN(int H, int S);
    //放入键值对
    public static void push(String key, LinkMan linkman);
    //通过姓名查找
    public static LinkMan findByName(String key);
    //删除
    public static void delete(String key);
    //对哈希表扩容
    private static void dilatation();
    //散列函数
    private static int HashFaction(String key);
    //选择解决冲突的函数
    private static int Sloution(String key, int loc, int flag);
}
