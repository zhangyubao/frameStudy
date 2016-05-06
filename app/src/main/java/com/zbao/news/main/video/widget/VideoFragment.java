package com.zbao.news.main.video.widget;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zbao.news.R;
import com.zbao.news.base.BaseFragment;

/**
 * 视频.
 */
public class VideoFragment extends BaseFragment {

    private static final String TAG = "VideoFragment";

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    public void loadLayout() {
        if (isPrepared && isVisiable) {
            //初始化界面控件等
            Logger.d("video fragment  add");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void createPresenter() {

    }

}
