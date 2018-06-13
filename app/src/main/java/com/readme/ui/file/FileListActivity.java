package com.readme.ui.file;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.base.utils.ToolbarUtil;
import com.readme.R;
import com.readme.app.activity.BaseCompatActivity;
import com.readme.config.Contant;
import com.readme.config.Extra;
import com.readme.ui.file.adapter.FileListAdapter;
import com.readme.ui.file.bean.FileInfo;
import com.readme.ui.file.utils.FileUtil;


import java.util.List;

import butterknife.BindView;


public class FileListActivity extends BaseCompatActivity {
    public final static String TAG = "FileListActivity";

    @BindView(R.id.main_view)
    ListView mainView;
    @BindView(R.id.main_path)
    TextView mainPath;

    private String mPath = Contant.DEFAULT_PATH;
    private Context mContext;

    @Override
    protected int setContentView() {
        return R.layout.acitivty_file_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtil.setToolbarLeft(toolbar, "File", null, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mPath = bundle.getString(Extra.FILE_PATH, Contant.DEFAULT_PATH);
        }

        mainPath.setText(mPath);
        initAppView(mPath);
    }

    private void initAppView(String path) {
        List<FileInfo> allInfo = FileUtil.getFiles(path);
        Log.d(TAG, "allInfo:" + allInfo.size());
        FileListAdapter mAdapter = new FileListAdapter(mContext, allInfo);
        mainView.setAdapter(mAdapter);
    }
}