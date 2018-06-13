package com.readme.app.activity;

import android.os.Bundle;

import com.base.app.activity.BaseAppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseCompatActivity extends BaseAppCompatActivity {

    protected int pageIndex = 0;
    protected int pageSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);
    }

    protected abstract int setContentView();
}
