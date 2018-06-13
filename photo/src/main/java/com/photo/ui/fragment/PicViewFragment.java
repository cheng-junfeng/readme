package com.photo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hint.utils.ToastUtils;
import com.photo.R;
import com.photo.R2;
import com.photo.app.PhotoBaseCompatFragment;
import com.photo.ui.widget.PhotoView;

import butterknife.BindView;


public class PicViewFragment extends PhotoBaseCompatFragment {
    private static final String TAG = "PicViewFragment";
    @BindView(R2.id.image)
    PhotoView image;

    @Override
    protected int setContentView() {
        return R.layout.fragment_show_big_image;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        initView();//初始化View

        return containerView;
    }

    private void initView() {
        if(myUri == null){
            ToastUtils.showToast(getActivity(), "地址为空");
            image.setImageResource(default_res);
        }else{
            Glide.with(this)
                    .load(myUri)
                    .error(default_res)
                    .into(image);
        }
    }
}
