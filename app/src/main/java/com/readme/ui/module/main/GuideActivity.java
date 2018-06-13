package com.readme.ui.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.readme.R;
import com.readme.app.activity.BaseCompatActivity;
import com.readme.ui.module.file.FileListActivity;
import com.readme.ui.module.scan.ScanActivity;

import butterknife.OnClick;

public class GuideActivity extends BaseCompatActivity {
    private final static String TAG = "GuideActivity";

    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.scan_view, R.id.find_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan_view:
                readGo(ScanActivity.class);
                break;
            case R.id.find_view:
                readGo(FileListActivity.class);
                break;
        }
    }
}
