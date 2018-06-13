package com.readme.ui.module.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.utils.LogUtil;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.hint.utils.DialogUtils;
import com.hint.utils.ToastUtils;
import com.readme.R;
import com.readme.app.activity.BaseCompatFragment;


import butterknife.BindView;


public class PdfFragment extends BaseCompatFragment {
    private static final String TAG = "PdfFragment";

    @BindView(R.id.pdfView)
    PDFView pdfView;

    private Context mContext;

    @Override
    protected int setContentView() {
        return R.layout.fragment_pdf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = super.onCreateView(inflater, container, savedInstanceState);
        mContext = this.getContext();
        initView();

        return containerView;
    }

    private void initView() {
        DialogUtils.showLoading(mContext);
        pdfView.fromUri(myUri)
//                .pages(0, 1) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onLoad(loadCompleteListener) // called after document is loaded and starts to be rendered
                .onError(errorListener)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .spacing(0)
                .load();
    }

    OnLoadCompleteListener loadCompleteListener = new OnLoadCompleteListener() {
        @Override
        public void loadComplete(int nbPages) {
            DialogUtils.dismissLoading();
        }
    };

    OnErrorListener errorListener = new OnErrorListener() {
        @Override
        public void onError(Throwable t) {
            LogUtil.d(TAG, "error:" + t.getMessage());
            DialogUtils.dismissLoading();
            ToastUtils.showToast(mContext, "Load Failed.");
        }
    };
}
