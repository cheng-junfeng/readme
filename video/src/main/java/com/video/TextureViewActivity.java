package com.video;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.widget.Button;

import com.base.utils.LogUtil;
import com.base.utils.ToolbarUtil;
import com.video.R;
import com.video.R2;
import com.video.app.VideoBaseCompatActivity;
import com.video.config.VideoStat;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class TextureViewActivity extends VideoBaseCompatActivity implements SurfaceTextureListener,
        OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener,
        OnVideoSizeChangedListener {
    private static final String TAG = "TextureViewActivity";

    @BindView(R2.id.textureview)
    TextureView textureview;

    private MediaPlayer mediaPlayer;
    private Uri urlStr;
    private Context mContext;

    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.video_texture_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        textureview.setSurfaceTextureListener(this);
        urlStr = getIntent().getData();
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
            mediaPlayer.setDataSource(mContext, urlStr);
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
        //停止
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}