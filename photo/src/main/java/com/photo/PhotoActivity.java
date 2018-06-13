package com.photo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.base.utils.ToolbarUtil;
import com.photo.R;
import com.photo.app.PhotoBaseCompatActivity;
import com.photo.config.PhotoConfig;
import com.photo.ui.fragment.PicViewFragment;


public class PhotoActivity extends PhotoBaseCompatActivity {
    private final static String TAG = "PhotoActivity";

    private Uri urlStr;

    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_pic_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urlStr = getIntent().getData();
        initView();
    }

    private void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PicViewFragment fragment = new PicViewFragment();
        fragment.setPath(urlStr);
        transaction.replace(R.id.main_frame_layout, fragment);
        transaction.commit();
    }
}
