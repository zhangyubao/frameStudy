package com.zbao.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.EditText;

import com.orhanobut.logger.Logger;
import com.zbao.news.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangYB on 2016/5/23.
 * <p/>
 * 该界面主要演示指纹验证
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    @Bind(R.id.et_username)
    public EditText userName;
    @Bind(R.id.et_password)
    public EditText password;

    private FingerprintManagerCompat mFingerprintManager;

    //重启sensor的标识
    private static final int Message = 0x01;

    /**
     * 重新启动sensor监听
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logger.i(TAG, "重启指纹验证");
            mFingerprintManager.authenticate(null, 0, null, new CallBack(), null);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFingerprintManager = FingerprintManagerCompat.from(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * 登录
     */
    @OnClick(R.id.btn_login)
    public void login() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 指纹验证
     */
    @OnClick(R.id.btn_finger)
    public void fingerAuthenticate() {
        mFingerprintManager.authenticate(null, 0, null, new CallBack(), null);
    }


    /**
     * 注意：sensor被关闭后，android允许我们在30s之后重新打开Sensor授权监听
     */
    private class CallBack extends FingerprintManagerCompat.AuthenticationCallback {
        /**
         * 指纹验证出现错误回调
         *
         * @param errMsgId
         * @param errString
         */
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            Logger.i(TAG, "指纹验证出现错误");
        }

        /**
         * 指纹衍生成功回调
         * <p/>
         * 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
         *
         * @param result
         */
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Logger.i(TAG, "指纹验证成功");
            mHandler.sendEmptyMessageDelayed(Message, 1000 * 30);
        }

        /**
         * 指纹验证失败回调
         * <p/>
         * 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
         */
        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Logger.i(TAG, "指纹验证失败");
            mHandler.sendEmptyMessageDelayed(Message, 1000 * 30);
        }
    }

}
