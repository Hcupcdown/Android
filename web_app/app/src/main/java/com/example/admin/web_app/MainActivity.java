package com.example.admin.web_app;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //如果sdk版本大于22
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                //函数参数为(Activity实例，string数组，表示申请的权限，请求码)
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA},1);
            }
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.RECORD_AUDIO},1);
            }
        }
        WebView webview=(WebView)findViewById(R.id.webview);
        //新建webview设置类
        WebSettings webSetting= webview.getSettings();
        //设置对JavaScript的支持
        webSetting.setJavaScriptEnabled(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //显示的网页地址
        webview.loadUrl("file:///android_asset/loginAndChatting.html");
        webSetting.setMediaPlaybackRequiresUserGesture(false);
        webview.setWebChromeClient(new WebChromeClient() {
            public void onPermissionRequest(final PermissionRequest request) {
                MainActivity.this.runOnUiThread(new Runnable(){
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }// run
                });// MainActivity

            }// onPermissionRequest
        });
    }
}
