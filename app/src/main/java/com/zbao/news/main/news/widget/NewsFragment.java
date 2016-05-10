package com.zbao.news.main.news.widget;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zbao.news.R;
import com.zbao.news.base.BaseFragment;
import com.zbao.news.entity.JokeInf;
import com.zbao.news.network.InternetService;
import com.zbao.news.main.news.adapter.JokeRectclerAdapter;
import com.zbao.news.main.news.presenter.NewsFragmentPresenterImpl;
import com.zbao.news.main.news.view.NewFragmentView;
import com.zbao.news.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 新闻.
 */
public class NewsFragment extends BaseFragment implements NewFragmentView, JokeRectclerAdapter.OnItemClickListener {
    private static final String TAG = "NewsFragment";

    @Bind(R.id.recy_news_list)
    RecyclerView mNewList;

    private List<JokeInf.ResultBean.DataBean> mJokeInfs;
    private JokeRectclerAdapter mJokeAdapter;
    private InternetService mService;
    private NewsFragmentPresenterImpl mFragmentPresenter;

    public NewsFragment() {
    }


    @Override
    public void loadLayout() {
        if (isPrepared && isVisiable) {
            //初始化界面控件等
            mNewList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mNewList.addItemDecoration(new SpaceItemDecoration(CommonUtil.dip2px(getActivity(), 5)));
            mNewList.setHasFixedSize(true);
            mJokeAdapter = new JokeRectclerAdapter();
            mNewList.setAdapter(mJokeAdapter);
            mJokeAdapter.setItemClickListener(this);
            Logger.d("news fragment  add");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void createPresenter() {
        mFragmentPresenter = new NewsFragmentPresenterImpl();
        mFragmentPresenter.setNewFragmentView(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    public void onItemClick(int position) {
        CommonUtil.showToast(getActivity(), position + "");
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildCount() != 0) {
                outRect.top = space;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentPresenter.getNewsOnInternet();
    }

    @Override
    public void setDataForList(List<JokeInf.ResultBean.DataBean> news) {
        if (mJokeInfs != null) {
        } else {
            mJokeInfs = new ArrayList<JokeInf.ResultBean.DataBean>();
        }
        mJokeAdapter.setData(news);
    }

    @Override
    public void showProgress() {
        Logger.d("show loading dialog ~~~~~~");
    }

    @Override
    public void hideProgress() {
        Logger.d("hide loading dialog ~~~~~~");
    }
}
