package com.zbao.news.main.mine.widget;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zbao.news.R;
import com.zbao.news.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的.
 */
public class MineFragment extends BaseFragment {

    private static final String TAG = "MineFragment";

    @Bind(R.id.iv_user_image)
    ImageView mIvUserImage;   //用户头像
    @Bind(R.id.tv_setting)
    TextView mTvSetting;     //设置
    @Bind(R.id.tv_password)
    TextView mTvPassword;   //密码设置

    private TestThread mTestThread;

    /**
     * 结束线程的方法：
     * <p/>
     * 1、给线程设置标记当当前Activity或者Fragment销毁时结束线程
     * <p/>
     * 2、初始化的时候定义启动的线程为守护线程,这样当主线程消亡的时候,其他线程也会被终止
     * <p/>
     * 3、if (myThread != null) {
     * 　　   Thread dummy = myThread;
     * 　　   myThread = null;
     * 　　   dummy.interrupt();
     * 　　}
     */
    private boolean isTrue = true;

    public MineFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mView);
//        mTestThread = new TestThread();
//        mTestThread.start();
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

    private class TestThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (isTrue)
                Log.e("TAG", "当前线程~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isTrue = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isTrue = false;
    }
}
