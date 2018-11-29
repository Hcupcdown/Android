package com.example.admin.algorithm_2;

import android.util.Log;
import java.math.*;


public class NumberKeyHash {
    //存放联系人信息的数组
    private static int total=16;
    //选择哈希函数
    private static  int HN=0;
    //选择解决冲突的方法
    private static  int SN=0;
    //线性取中值时的移位量，初始为2
    private static  int AND=4;
    private static LinkMan[] linkMan=new LinkMan[total];
    //已经占用个数
    private static int m=0;

    //构造函数
    public NumberKeyHash(){
        this.total=16;
        this.m=0;
        linkMan=new LinkMan[total];
    }


    //设置要使用的函数
    public static void changeHN_SN(int H,int S){
        HN=H;
        SN=S;
    }

    //放入键值对
    public static void push(String key, LinkMan linkman) {
        int flag=0;
        //调用哈希函数
        int loc=HashFaction(key);
        while(linkMan[loc]!=null){
            flag++;
            //调用解决冲突函数
            Sloution(key,loc,flag);
        }
        linkMan[loc]=linkman;
        //如果已经快满了，就对哈希表扩容
        if(m>(int)4*total/5){
            dilatation();
        }
    }


    //通过号码查找
    public static LinkMan findByNumber(String key){
        int flag=0;
        //调用哈希函数
        int findTime=0;
        int loc=HashFaction(key);
        while(linkMan[loc]==null||!linkMan[loc].getNumber().equals(key)){
            flag++;
            if(findTime==total){
                return null;
            }
            findTime++;
            //调用解决冲突函数
            Sloution(key,loc,flag);
        }
        return linkMan[loc];
    }

    //删除
    public static void delete(String key){
        int flag=0;
        int loc=HashFaction(key);
        while(linkMan[loc].getNumber()!=key){
            flag++;
            Sloution(key,loc,flag);
        }
        linkMan[loc]=null;

        //占用个数减一
        m--;
    }


    //对哈希表扩容
    private static void dilatation(){
        int j;
        j=total;
        total=2*total;
        AND++;
        LinkMan[] oldlink=linkMan;
        linkMan=new LinkMan[total];
        m=0;
        for(int i=0;i<j;i++){
            if(oldlink[i]!=null){
                push(oldlink[i].getNumber(),oldlink[i]);
                oldlink[i]=null;
            }
        }
    }


    //散列函数
    private static int HashFaction(String key) {
        int sum=0;
        int loc=0;
        //将key的ascii码相加
        for(int i=0;i<key.length();i++) {
            sum=(sum<<6)+key.charAt(i);
        }
        switch(HN) {
            case 0:
                //除留余数法
                loc=sum%total;
                break;
            case 1:
                //平方其中法
                sum=sum*sum;
                loc=(sum&((total-1)<<AND/2))>>(AND/2);
                break;
            case 2:
                //充分混淆
                sum^=(sum<<key.length())^(sum>>12);
                sum^=(sum<<7)^(sum>>4);
                loc=(sum&((total-1)<<AND/2))>>(AND/2);
                break;
        }
        return loc;
    }


    //选择解决冲突的函数
    private static int Sloution (String key,int loc,int flag){
        switch(SN){
            case 0:
                //线性探查法
                loc+=(loc+1)%total;
                break;
            case 1:
                //二次探查法
                loc+=(loc+Math.pow(-1,flag)*flag*flag)%total;
                break;

        }
        return loc;
    }
}
