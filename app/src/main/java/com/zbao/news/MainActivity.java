package com.zbao.news;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zbao.news.base.BaseActivity;
import com.zbao.news.entity.MessageEvent;
import com.zbao.news.main.joke.widget.JokesFragment;
import com.zbao.news.main.mine.widget.MineFragment;
import com.zbao.news.main.news.widget.NewsFragment;
import com.zbao.news.main.video.widget.VideoFragment;
import com.zbao.news.utils.CommonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.Bind;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainActivity extends BaseActivity implements OnTabItemSelectListener {

    private static final String TAG = "LogSampleActivity";// logt
    //Log.d(TAG, "addMenuTable() called with: " + ""); //logm
    //Log.d(TAG, "addMenuTable() returned: " + );   logr
    @Bind(R.id.rbtn_news)
    RadioButton btnNew;
    @Bind(R.id.rbtn_jokes)
    RadioButton btnJoke;
    @Bind(R.id.rbtn_videos)
    RadioButton btnVideo;
    @Bind(R.id.rbtn_mine)
    RadioButton btnMine;

    @Bind(R.id.rg_menu)
    RadioGroup mGroupMenu;

    @Bind(R.id.menu_tab)
    PagerBottomTabLayout menuTab;
    //当前正在显示的Fragment界面
    private Fragment currentVisiable;
    //底部导航栏的控制
    private Controller mController;

    private static final int Menu_news = 0x00;
    private static final int Menu_joke = 0x01;
    private static final int Menu_video = 0x02;
    private static final int Menu_minne = 0x03;

    private FragmentManager mManager;
    private long mExitTime;

    private HashMap<String, Fragment> mFragments = new HashMap<String, Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager = getSupportFragmentManager();
        EventBus.getDefault().register(this);
        addMenuTable();
    }

    /**
     * 设置底部导航栏
     */
    private void addMenuTable() {
        mController = menuTab.builder()
                .addTabItem(R.drawable.menu_new_selector, getResources().getString(R.string.news))
                .addTabItem(R.drawable.menu_joke_selector, getResources().getString(R.string.jokes))
                .addTabItem(R.drawable.menu_video_selector, getResources().getString(R.string.video))
                .addTabItem(R.drawable.menu_mine_selector, getResources().getString(R.string.mine))
                .build();
        mController.addTabItemClickListener(this);
        //设置消息红点
        mController.setDisplayOval(3, true);
        //设置消息数量
        mController.setMessageNumber(0, 10);
        //默认选中那个模块
        mController.setSelect(0);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 创建并添加Fragment
     *
     * @param key
     * @param clazz
     */
    private void generateFragment(String key, Class clazz) {
        //通过show()与hide()方法来做界面切换
        try {
            FragmentTransaction transaction = mManager.beginTransaction();
            Fragment fragment = mFragments.get(key);
            if (fragment == null) {
                Logger.d("generate fragment~~~~~~~~~~~~~~~~");
                fragment = (Fragment) clazz.newInstance();
                mFragments.put(key, fragment);
                transaction.add(R.id.ll_container, fragment);
            }
            if (currentVisiable != null) {
                transaction.hide(currentVisiable).show(fragment);
            } else {
                transaction.show(fragment);
            }
            //transaction.replace(R.id.ll_container, fragment);
            transaction.commit();
            currentVisiable = fragment;
            Logger.d("current visiable fragment is ~~~~~~~~~~" + currentVisiable);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSelected(int index, Object tag) {
        switch (index) {
            case Menu_news:
                generateFragment("news", NewsFragment.class);
                break;
            case Menu_joke:
                generateFragment("jokes", JokesFragment.class);
                break;
            case Menu_video:
                generateFragment("videos", VideoFragment.class);
                break;
            case Menu_minne:
                generateFragment("mine", MineFragment.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRepeatClick(int index, Object tag) {
        Logger.d("重复点击~~~~~~~~~~~~~~");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onReceive(MessageEvent msg) {
        Logger.e("receive  message ================" + msg.getMessage());
    }
    /*************************************** Android6.0 动态权限申请开始*****************************/
    /**
     * Android 6.0 动态申请权限
     */
    private void requestPermission() {
        //判断当前Activity是否已经获取了该权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 当前应用申请权限时曾经被用户拒绝过,在此要做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                CommonUtil.showToast(this, "");
            } else {
                //申请用户权限
                ActivityCompat.requestPermissions(this
                        , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , EXTERNAL_STORAGE_REQ_CODE);
            }
        }
    }

    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQ_CODE: {
                // 如果请求被拒绝，那么通常grantResults数组为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //申请成功，进行相应操作
                } else {
                    //申请失败，可以继续向用户解释。
                }
                return;
            }
        }
    }
    /***************************************Android6.0 动态权限申请结束*****************************/
}
