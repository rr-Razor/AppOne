package com.example.appone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        findViewById(R.id.bt_app_login).setOnClickListener(this);
        findViewById(R.id.bt_app_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_app_login:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), PhoneNumberLogin.class);
                this.startActivity(intent);
                break;
            case R.id.bt_app_register:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), Register.class);
                this.startActivity(intent2);
                break;
        }
    }
}
