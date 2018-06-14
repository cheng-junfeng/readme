package com.readme.ui.module.fragment;

import com.readme.app.activity.BaseCompatFragment;
import com.readme.config.Types;


public class BaseFragmentFactory {
    private static BaseFragmentFactory mInstance;
    private TextFragment mTextFragment;
    private ImageFragment mImageFragment;
    private VideoFragment mVideoFragment;
    private AudioFragment mAudioFragment;
    private PdfFragment mPdfFragment;

    private BaseFragmentFactory() {
    }

    public static BaseFragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (BaseFragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new BaseFragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private BaseCompatFragment getTextFragment() {
        if (mTextFragment == null) {
            synchronized (BaseFragmentFactory.class) {
                if (mTextFragment == null) {
                    mTextFragment = new TextFragment();
                }
            }
        }
        return mTextFragment;
    }

    private BaseCompatFragment getImageFragment() {
        if (mImageFragment == null) {
            synchronized (BaseFragmentFactory.class) {
                if (mImageFragment == null) {
                    mImageFragment = new ImageFragment();
                }
            }
        }
        return mImageFragment;
    }

    private BaseCompatFragment getVideoFragment() {
        if (mVideoFragment == null) {
            synchronized (BaseFragmentFactory.class) {
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                }
            }
        }
        return mVideoFragment;
    }

    private BaseCompatFragment getAudioFragment() {
        if (mAudioFragment == null) {
            synchronized (BaseFragmentFactory.class) {
                if (mAudioFragment == null) {
                    mAudioFragment = new AudioFragment();
                }
            }
        }
        return mAudioFragment;
    }

    private BaseCompatFragment getPdfFragment() {
        if (mPdfFragment == null) {
            synchronized (BaseFragmentFactory.class) {
                if (mPdfFragment == null) {
                    mPdfFragment = new PdfFragment();
                }
            }
        }
        return mPdfFragment;
    }

    public BaseCompatFragment getFragment(Types type) {
        BaseCompatFragment fragment = null;
        switch (type) {
            case text:
                fragment = getTextFragment();
                break;
            case image:
                fragment = getImageFragment();
                break;
            case video:
                fragment = getVideoFragment();
                break;
            case audio:
                fragment = getAudioFragment();
                break;
            case pdf:
                fragment = getPdfFragment();
                break;
        }
        return fragment;
    }
}
