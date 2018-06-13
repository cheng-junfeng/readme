package com.readme.ui.file;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.readme.R;
import com.readme.app.activity.BaseCompatActivity;
import com.readme.ui.file.utils.FileUtil;

import butterknife.BindView;

public class FileActivity extends BaseCompatActivity {
    public final static String TAG = "FileActivity";
    @BindView(R.id.main_view)
    TextView mainView;

    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_file;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();

        mainView.setText(FileUtil.getFileContent(uri.toString()));
    }
}
