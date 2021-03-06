package com.readme.ui.module.upgrade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.utils.FileUtil;
import com.base.utils.TimeUtil;

import com.readme.R;
import com.readme.app.activity.BaseActivity;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;

import butterknife.BindView;
import butterknife.OnClick;


public class UpgradeActvity extends BaseActivity {

    @BindView(R.id.dialog_title)
    TextView title;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.start)
    Button start;

    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.dialog_my_upgrade;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateBtn(Beta.getStrategyTask());
        tv.setText("Progress:" + getRate(Beta.getStrategyTask().getSavedLength(), Beta.getStrategyTask().getTotalLength()));
        title.setText(Beta.getUpgradeInfo().title);
        version.setText("Version:" + Beta.getUpgradeInfo().versionName);
        size.setText("Size:" + FileUtil.formetFileSize(Beta.getUpgradeInfo().fileSize));
        time.setText("Time:" + TimeUtil.milliseconds2String(Beta.getUpgradeInfo().publishTime));
        content.setText(Beta.getUpgradeInfo().newFeature);

        Beta.registerDownloadListener(new DownloadListener() {
            @Override
            public void onReceive(DownloadTask task) {
                updateBtn(task);
                tv.setText("Progress:" + getRate(task.getSavedLength(), task.getTotalLength()));
            }

            @Override
            public void onCompleted(DownloadTask task) {
                updateBtn(task);
                tv.setText("Progress:" + getRate(task.getSavedLength(), task.getTotalLength()));
            }

            @Override
            public void onFailed(DownloadTask task, int code, String extMsg) {
                updateBtn(task);
                tv.setText("Fail to download");
            }
        });
    }

    public void updateBtn(DownloadTask task) {
        switch (task.getStatus()) {
            case DownloadTask.INIT:
            case DownloadTask.DELETED:
            case DownloadTask.FAILED: {
                start.setText("Start");
            }
            break;
            case DownloadTask.COMPLETE: {
                start.setText("Install");
            }
            break;
            case DownloadTask.DOWNLOADING: {
                start.setText("Pause");
            }
            break;
            case DownloadTask.PAUSED: {
                start.setText("Continue");
            }
            break;
        }
    }

    @OnClick({R.id.cancel, R.id.start})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.cancel) {
            Beta.cancelDownload();
            finish();
        } else if (viewId == R.id.start) {
            DownloadTask task = Beta.startDownload();
            updateBtn(task);
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Beta.unregisterDownloadListener();
    }

    private String getRate(long saved, long total) {
        if (total <= 0) {
            return "0%";
        } else {
            if (saved == total) {
                return "100%";
            } else {
                double curSize = (double) saved;
                double totalSize = (double) total;
                double rate = (curSize / totalSize) * 100;
                return (long) rate + "%";
            }
        }
    }
}
