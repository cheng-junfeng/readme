package com.readme.ui.module;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.base.utils.LogUtil;
import com.readme.R;
import com.readme.app.activity.BaseCompatActivity;
import com.readme.app.activity.BaseCompatFragment;
import com.readme.config.Types;
import com.readme.ui.module.fragment.BaseFragmentFactory;

public class ReadActivity extends BaseCompatActivity {
    private final static String TAG = "ReadActivity";
    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_read;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null){
            Uri theUri = intent.getData();
            String theType = intent.getType();
            LogUtil.d(TAG, "uri"+theUri+":"+theType);
            initView(theUri, theType);
        }else{
            LogUtil.d(TAG, "intent:null");
            finish();
        }
    }

    private void initView(Uri uri, String type) {
        if(uri == null || type == null){
            finish();
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseCompatFragment fragment = BaseFragmentFactory.getInstance().getFragment(Types.value(type));
        fragment.setUri(uri);
        transaction.replace(R.id.main_frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
