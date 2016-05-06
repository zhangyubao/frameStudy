package com.zbao.news.main.joke.widget;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zbao.news.R;
import com.zbao.news.base.BaseFragment;

/**
 * 娱乐模块.
 */
public class JokesFragment extends BaseFragment {

    private static final String TAG = "JokesFragment";

    public JokesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return mView;
    }

    @Override
    public void loadLayout() {
        if (isPrepared && isVisiable) {
            //初始化界面控件等
            Logger.d("joke fragment  add");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_jokes;
    }

    @Override
    public void createPresenter() {

    }

}
