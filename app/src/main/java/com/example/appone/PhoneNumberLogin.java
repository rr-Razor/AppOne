package com.example.appone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class PhoneNumberLogin extends AppCompatActivity {

    private EditText mEtPhone;
    private EditText mEtPassword;
    private CheckBox mCBPsd;
    private String TAG = "PhoneNumberLogin";
    private String SP_PHONE = "sp_phone";
    private String SP_PASSWD = "sp_passwd";
    private String SP_IS_REMEMBER_PSD = "sp_is_remember_psd";
    private SharedPreferences sharedPreferences;
    private boolean mIsChecked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initUI();

        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        if (sharedPreferences == null) {
            sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        mEtPhone.setText(sharedPreferences.getString(SP_PHONE, ""));
        mEtPassword.setText(sharedPreferences.getString(SP_PASSWD, ""));

        mIsChecked = sharedPreferences.getBoolean(SP_IS_REMEMBER_PSD, false);

        mCBPsd.setChecked(mIsChecked);
    }

    private void initUI() {
        mEtPhone = findViewById(R.id.et_phone);
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIsChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_PHONE, mEtPhone.getText().toString());
                    edit.commit();
                }
            }
        });

        mEtPassword = findViewById(R.id.et_password);
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIsChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_PASSWD, mEtPassword.getText().toString());
                    edit.commit();
                }
            }
        });

        mCBPsd = findViewById(R.id.cb_remember_password);
        mCBPsd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "状态为：" + isChecked);
                mIsChecked = isChecked;
                if (isChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }

                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_PHONE, mEtPhone.getText().toString());
                    edit.putString(SP_PASSWD, mEtPassword.getText().toString());
                    edit.putBoolean(SP_IS_REMEMBER_PSD, isChecked);
                    edit.commit();
                }
            }
        });
    }
}
