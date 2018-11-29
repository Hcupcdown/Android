package com.example.admin.algorithm_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button transButton=findViewById(R.id.trans);
        transButton.setOnClickListener(new View.OnClickListener(){

            public static final String TAG = "";

            @Override
            public void onClick(View v) {
                EditText experssiontext=findViewById(R.id.expression);
                String experssion=experssiontext.getText().toString();
                Log.d(TAG, "onClick: "+experssion);
                InToPo inToPo=new InToPo(experssion);
                //将中缀转换为后缀
                inToPo.translation();
                TextView result=findViewById(R.id.result);
                result.setText("后缀表达式为:"+inToPo.getResult());
                TextView comp=findViewById(R.id.textView2);
                comp.setText("计算结果为:"+inToPo.comp());
            }
        });
    }
}
