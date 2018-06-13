package com.readme.ui.module.fragment;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.ViewGroup;

import com.base.utils.LogUtil;
import com.readme.R;
import com.readme.app.activity.BaseCompatFragment;

import java.io.IOException;

import butterknife.BindView;

public class VideoFragment extends BaseCompatFragment implements SurfaceTextureListener,
        OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener,
        OnVideoSizeChangedListener {
    private static final String TAG = "VideoFragment";

    @BindView(R.id.textureview)
    TextureView textureview;

    private MediaPlayer mediaPlayer;
    private Context mContext;


    @Override
    protected int setContentView() {
        return R.layout.fragment_video;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        mContext = this.getContext();
        textureview.setSurfaceTextureListener(this);

        return containerView;
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {

    }

    @Override
    public void onPrepared(MediaPlayer arg0) {

    }

    @Override
    public void onCompletion(MediaPlayer arg0) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer arg0, int arg1) {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int arg1,
                                          int arg2) {
        Surface s = new Surface(surface);
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(mContext, myUri);
            mediaPlayer.setSurface(s);
            mediaPlayer.prepare();
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
        mediaPlayer.stop();
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
                                            int arg2) {
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}