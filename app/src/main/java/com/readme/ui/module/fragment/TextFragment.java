package com.readme.ui.module.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.utils.LogUtil;
import com.hint.utils.DialogUtils;
import com.readme.R;
import com.readme.app.activity.BaseCompatFragment;
import com.readme.ui.module.file.utils.FileUtil;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TextFragment extends BaseCompatFragment {
    public final static String TAG = "TextFragment";
    @BindView(R.id.main_view)
    TextView mainView;

    private Context mContext;

    @Override
    protected int setContentView() {
        return R.layout.fragment_text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        mContext = this.getContext();
        initView();

        return containerView;
    }

    private void initView() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                LogUtil.d(TAG, "loadContent");
                loadContent(emitter);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d(TAG, "subscribe");
                        DialogUtils.showLoading(mContext);
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtil.d(TAG, "onNext");
                        mainView.setText(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d(TAG, "onComplete");
                        DialogUtils.dismissLoading();
                    }
                });
    }

    private void loadContent(ObservableEmitter<String> emitter){
        String content = FileUtil.getFileContent(myUri.toString());
        LogUtil.d(TAG, "myUri:" + myUri);
        if (TextUtils.isEmpty(content) || content.length() == 0) {
            emitter.onNext("null");
        } else {
            emitter.onNext(content);
        }
        emitter.onComplete();
    }
}
