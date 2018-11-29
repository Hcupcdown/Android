package com.example.admin.algorithm_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在加载布局文件前判断是否登陆过
        sprfMain= getSharedPreferences("inform",MODE_PRIVATE);
        editorMain=sprfMain.edit();
        Intent intent0=getIntent();
        String inform="  ";
        inform=intent0.getStringExtra("inform");
        String stringcomp="true";
        Log.d(TAG, "onCreate:iehfquefhnqefnwe "+inform);
        if(inform !=stringcomp){
            Log.d(TAG, "onCreate: "+"点击了这里");
            editorMain.putBoolean("main",false);
            editorMain.apply();
        }
        //getBoolean("main",false)；当找不到"main"所对应的键值是默认返回false
        if(sprfMain.getBoolean("main",false)){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        setContentView(R.layout.login);
        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=findViewById(R.id.name).toString();
                String password=findViewById(R.id.password).toString();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                editorMain.putBoolean("main",true);
                editorMain.putString("name",name);
                editorMain.putString("password",password);
                editorMain.apply();
                Log.d(TAG, "onClick: "+name);
                intent.putExtra("name",name);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

}
