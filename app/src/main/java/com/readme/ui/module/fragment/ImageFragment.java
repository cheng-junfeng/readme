package com.readme.ui.module.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.readme.R;
import com.readme.app.activity.BaseCompatFragment;
import com.readme.ui.widget.PhotoView;

import butterknife.BindView;


public class ImageFragment extends BaseCompatFragment {
    private static final String TAG = "ImageFragment";
    @BindView(R.id.image)
    PhotoView image;

    @Override
    protected int setContentView() {
        return R.layout.fragment_image;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        initView();

        return containerView;
    }

    private void initView() {
        Glide.with(this)
                .load(myUri)
                .error(R.mipmap.ic_default_image)
                .into(image);
    }
}
