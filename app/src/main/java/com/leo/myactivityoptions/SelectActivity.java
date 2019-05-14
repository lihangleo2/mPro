package com.leo.myactivityoptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.leo.myactivityoptions.likewx.LikeWxActivity;
import com.leo.myactivityoptions.localdemo.LocalMainActivity;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {
    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        mImmersionBar = ImmersionBar.with(this)
                .fitsSystemWindows(false);
        mImmersionBar.init();
        findViewById(R.id.buttonPanel).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.buttonLuck).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImmersionBar.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPanel:
                LocalMainActivity.actionStart(this);
                break;
            case R.id.button:
                MainActivity.actionStart(this);
                break;
            case R.id.buttonLuck:
                startActivity(new Intent(SelectActivity.this, LikeWxActivity.class));
                break;
        }
    }
}
