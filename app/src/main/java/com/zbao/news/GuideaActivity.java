package com.zbao.news;

import android.content.Intent;
import android.os.Bundle;

import com.chyrta.onboarder.OnboarderActivity;
import com.chyrta.onboarder.OnboarderPage;
import com.zbao.news.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangYB on 2016/5/3.
 */
public class GuideaActivity extends OnboarderActivity {
    List<OnboarderPage> onboarderPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboarderPages = new ArrayList<OnboarderPage>();

        // Create your first page
        OnboarderPage pageOne = new OnboarderPage("Title 1", "Description 1");
        OnboarderPage pageTwo = new OnboarderPage(R.string.app_name, R.string.app_description, R.mipmap.guide_first);

        // You can define title and description colors (by default white)
        pageOne.setTitleColor(R.color.black);
        pageOne.setDescriptionColor(R.color.white);

        // Don't forget to set background color for your page
        pageOne.setBackgroundColor(R.color.guide_bg);
        pageTwo.setBackgroundColor(R.color.guide_bg);

        // Add your pages to the list
        onboarderPages.add(pageOne);
        onboarderPages.add(pageTwo);

        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);

    }

    @Override
    public void onSkipButtonPressed() {
        super.onSkipButtonPressed();
        enterMain();
    }

    @Override
    public void onFinishButtonPressed() {
        enterMain();
    }

    /**
     * 进入程序主页面
     */
    private void enterMain() {
        SharedPreferenceUtils.saveInstallState(this, true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
