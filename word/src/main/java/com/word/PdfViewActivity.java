package com.word;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.base.utils.LogUtil;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.hint.utils.DialogUtils;
import com.hint.utils.ToastUtils;
import com.word.app.WordBaseCompatActivity;

import butterknife.BindView;


public class PdfViewActivity extends WordBaseCompatActivity {
    private static final String TAG = "PdfViewActivity";

    @BindView(R2.id.pdfView)
    PDFView pdfView;

    private Context mContext;

    @Override
    protected boolean setToolbar() {
        return false;
    }

    @Override
    protected int setContentView() {
        return R.layout.word_pdf_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Intent intent = getIntent();
        Uri uri = intent.getData();
        initView(uri);
    }

    private void initView(Uri uri) {
        DialogUtils.showLoading(mContext);
        pdfView.fromUri(uri)
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
