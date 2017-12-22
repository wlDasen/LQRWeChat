package com.lqr.wechat.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.lqr.wechat.R;
import com.lqr.wechat.model.cache.UserCache;
import com.lqr.wechat.ui.base.BaseActivity;
import com.lqr.wechat.ui.base.BasePresenter;
import com.lqr.wechat.util.UIUtils;

import butterknife.Bind;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * @创建者 CSDN_LQR
 * @描述 微信闪屏页
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = "jpd-SPAc";
    @Bind(R.id.rlButton)
    RelativeLayout mRlButton;
    @Bind(R.id.btnLogin)
    Button mBtnLogin;
    @Bind(R.id.btnRegister)
    Button mBtnRegister;

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        //电话通讯录
                        Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.READ_PHONE_STATE,
                        //位置
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        //相机、麦克风
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.CAMERA,
                        //存储空间
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_SETTINGS
                )
                .request();
        if (!TextUtils.isEmpty(UserCache.getToken())) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            jumpToActivity(intent);
            finish();
        }
    }

    @Override
    public void initView() {
        Log.d(TAG, "initView: ");
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.black));

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        mRlButton.startAnimation(alphaAnimation);
    }

    @Override
    public void initListener() {
        Log.d(TAG, "initListener: ");
        mBtnLogin.setOnClickListener(v -> {
            jumpToActivity(LoginActivity.class);
            finish();
        });
        mBtnRegister.setOnClickListener(v -> {
            jumpToActivity(RegisterActivity.class);
            finish();
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        Log.d(TAG, "createPresenter: ");
        return null;
    }

    @Override
    protected int provideContentViewId() {
        Log.d(TAG, "provideContentViewId: ");
        return R.layout.activity_splash;
    }
}
