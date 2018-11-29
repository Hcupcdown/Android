package com.example.admin.algorithm_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//添加联系人界面
public class AddLinkmanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlinkman);
        Button preser=findViewById(R.id.Preservation);

        //设置保存按钮点击事件
        preser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //获取输入的姓名
                String name,number,address;
                EditText textName=findViewById(R.id.name);
                name=textName.getText().toString();
                //获取输入的号码
                EditText textNumber=findViewById(R.id.number);
                number=textNumber.getText().toString();
                //获取输入的地址
                EditText textAddress=findViewById(R.id.address);
                address=textAddress.getText().toString();

                //建立联系人对象
                LinkMan lk=new LinkMan(name,number,address);
                //将联系人信息放入以名字为关键字的哈希表中
                NameKeyHash.push(name,lk);
                //将联系人信息放入以号码为关键字的哈希表中
                NumberKeyHash.push(number,lk);
                //提示信息已保存
                Toast.makeText(AddLinkmanActivity.this,"已保存",Toast.LENGTH_LONG).show();
                //回到主界面
                AddLinkmanActivity.this.finish();
            }
        });
    }
}
