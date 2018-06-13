package com.readme;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.base.config.GlobalConfig;
import com.blankj.utilcode.util.Utils;
import com.base.utils.LogUtil;
import com.readme.ui.upgrade.UpgradeActvity;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.upgrade.UpgradeListener;

public class ReadmeApplication extends MultiDexApplication {
    public static final String TAG = "ReadmeApplication";
    private Context mContext;
    private static ReadmeApplication instance;

    public static synchronized ReadmeApplication getInstance() {
        if (null == instance) {
            instance = new ReadmeApplication();
        }
        return instance;
    }

    public ReadmeApplication(){}

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
        LogUtil.d(TAG, "onCreate");

        //util
        Utils.init(mContext);
        //upgrade
        initUpgrade();
    }

    private void initUpgrade(){
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.initDelay = 1 * 1000;
        Beta.largeIconId = R.mipmap.ic_launcher;
        Beta.smallIconId = R.mipmap.ic_launcher;
        Beta.defaultBannerId = R.mipmap.ic_launcher;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Beta.showInterruptedStrategy = false;
        Beta.upgradeListener = new UpgradeListener() {
            @Override
            public void onUpgrade(int ret, UpgradeInfo strategy, boolean isManual, boolean isSilence) {
                LogUtil.d(TAG, "onUpgrade:"+isManual);
                if (strategy != null) {
                    Intent i = new Intent();
                    i.setClass(getApplicationContext(), UpgradeActvity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                } else {
                    //Toast.makeText(getApplicationContext(), "没有更新", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Bugly.init(getApplicationContext(), "acc6f699ef", GlobalConfig.IS_DEBUG);
    }
}
