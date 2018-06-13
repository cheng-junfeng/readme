package com.readme.ui.module.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.readme.R;
import com.readme.app.activity.BaseCompatFragment;
import com.readme.ui.module.file.utils.FileUtil;

import butterknife.BindView;

public class TextFragment extends BaseCompatFragment {
    public final static String TAG = "TextFragment";
    @BindView(R.id.main_view)
    TextView mainView;

    @Override
    protected int setContentView() {
        return R.layout.fragment_text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        initView();

        return containerView;
    }

    private void initView(){
        mainView.setText(FileUtil.getFileContent(myUri.toString()));
    }
}
