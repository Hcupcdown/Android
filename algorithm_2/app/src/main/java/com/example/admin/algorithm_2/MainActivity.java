package com.example.admin.algorithm_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    private LinkMan searchMan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //onCreateOptionsMenu(R.menu.menu_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //选择哈希表的哈希函数与解决冲突方式
        NameKeyHash.changeHN_SN(1,1);
        NumberKeyHash.changeHN_SN(1,1);
        final ImageButton delete=findViewById(R.id.delete);
        //隐藏删除按钮
        delete.setVisibility(View.INVISIBLE);
        //添加添加联系人按钮的点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到添加联系人界面
                Intent addLinkman=new Intent(MainActivity.this,AddLinkmanActivity.class);
                startActivity(addLinkman);
            }
        });

        //设置查找按钮的点击事件
        ImageButton serchButton=findViewById(R.id.search);
        serchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //获取输入框的输入内容
                EditText searchKey=findViewById(R.id.search_key);
                //按名字在哈希表中查找
                searchMan=NameKeyHash.findByName(searchKey.getText().toString());
                //如果未找到，再按号码查找
                if(searchMan==null){
                    searchMan=NumberKeyHash.findByNumber(searchKey.getText().toString());
                }
                //将找到的信息显示出来
                TextView showname=findViewById(R.id.showname);
                TextView shownumber=findViewById(R.id.shownumber);
                TextView showaddress=findViewById(R.id.showaddress);
                if(searchMan!=null){
                    showname.setText("姓名:"+searchMan.getName());
                    shownumber.setText("号码:"+searchMan.getNumber());
                    showaddress.setText("地址:"+searchMan.getAddress());
                    delete.setVisibility(View.VISIBLE);
                }else{
                    showname.setText("查无此人");
                    shownumber.setText("");
                    showaddress.setText("");
                    delete.setVisibility(View.INVISIBLE);
                }

            }
        });
        //添加删除按钮的点击事件
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameKeyHash.delete(searchMan.getName());
                NumberKeyHash.delete(searchMan.getNumber());
                TextView showname=findViewById(R.id.showname);
                TextView shownumber=findViewById(R.id.shownumber);
                TextView showaddress=findViewById(R.id.showaddress);
                showname.setText("");
                shownumber.setText("");
                showaddress.setText("");
                delete.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"已删除",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建菜单栏
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    //设置菜单栏点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
        loginIntent.putExtra("inform","true");
        startActivity(loginIntent);
        MainActivity.this.finish();
        return true;
        //return super.onOptionsItemSelected(item);
    }
}
