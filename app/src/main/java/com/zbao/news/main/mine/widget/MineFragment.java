package com.zbao.news.main.mine.widget;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zbao.news.R;
import com.zbao.news.base.BaseFragment;

/**
 * 我的.
 */
public class MineFragment extends BaseFragment {


    private static final String TAG = "MineFragment";

    public MineFragment() {
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
            Logger.d("mine fragment  add");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void createPresenter() {

    }

}
