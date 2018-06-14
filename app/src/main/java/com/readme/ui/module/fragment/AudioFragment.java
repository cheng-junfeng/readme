package com.readme.ui.module.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.utils.LogUtil;
import com.hint.utils.ToastUtils;
import com.readme.R;
import com.readme.app.activity.BaseCompatFragment;

import java.io.IOException;


public class AudioFragment extends BaseCompatFragment {
    private static final String TAG = "AudioFragment";

    private Context mContext;
    private MediaPlayer audioPlayer;

    @Override
    protected int setContentView() {
        return R.layout.fragment_audio;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        mContext = this.getContext();

        initView();
        return containerView;
    }

    private void initView() {
        audioPlayer = new MediaPlayer();
        audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            audioPlayer.reset();
            audioPlayer.setDataSource(mContext, myUri);
            audioPlayer.prepare();
            audioPlayer.start();

            audioPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    ToastUtils.showToast(mContext, "Error:" + extra);
                    return false;
                }
            });
            audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ToastUtils.showToast(mContext, "Over");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
        audioPlayer.stop();
        audioPlayer.release();
        audioPlayer = null;
    }
}