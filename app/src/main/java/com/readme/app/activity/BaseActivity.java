package com.readme.app.activity;

import android.os.Bundle;

import com.base.app.activity.BaseAppActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends BaseAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);
    }

    protected abstract int setContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
