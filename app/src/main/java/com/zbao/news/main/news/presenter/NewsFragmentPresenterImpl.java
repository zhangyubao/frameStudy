package com.zbao.news.main.news.presenter;

import com.zbao.news.R;
import com.zbao.news.app.NewsApplication;
import com.zbao.news.entity.JokeInf;
import com.zbao.news.main.news.model.NewsFragmentModel;
import com.zbao.news.main.news.model.NewsFragmentModelImpl;
import com.zbao.news.main.news.view.NewFragmentView;
import com.zbao.news.utils.CommonUtil;

/**
 * Created by zhangYB on 2016/5/3.
 */
public class NewsFragmentPresenterImpl implements NewsFragmentPresenter, NewsFragmentModelImpl.OnNewsLoadFinishedListener {


    private NewFragmentView mNewFragmentView;

    private NewsFragmentModel mNewsFragmentModel;

    public NewsFragmentPresenterImpl() {
        this.mNewsFragmentModel = new NewsFragmentModelImpl();
    }


    @Override
    public void getNewsOnInternet() {
        mNewsFragmentModel.getNewsList(this);
    }

    @Override
    public void onSucess(JokeInf jokeInf) {
        mNewFragmentView.setDataForList(jokeInf.getResult().getData());
    }

    @Override
    public void onFailure(String tag, Throwable throwable) {
        CommonUtil.showToast(NewsApplication.getContext(), NewsApplication.getContext().getResources().getString(R.string.load_failure));
    }


    public NewFragmentView getNewFragmentView() {
        return mNewFragmentView;
    }

    public void setNewFragmentView(NewFragmentView newFragmentView) {
        mNewFragmentView = newFragmentView;
    }
}
