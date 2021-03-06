package com.anlu.ld.basedemo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.anlu.ld.basedemo.AndroidApplication;
import com.anlu.ld.basedemo.module.MainActivity;
import com.anlu.ld.basedemo.R;
import com.anlu.ld.basedemo.cons.Cons;
import com.anlu.ld.basedemo.dialog.LoadDialog;
import com.anlu.ld.basedemo.utlis.LogUtils;
import com.anlu.ld.basedemo.utlis.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    //toast类型
    private static final int TYPE_SUCC = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_ALERT = 2;

    protected String TAG;
    private Unbinder unbinder;
    private View mToastView;
    protected Context mContext;
    protected FragmentManager mFragmentManager;
    private LoadDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        TAG = getClass().getSimpleName();
        AndroidApplication.getInstance().getmActivityManager().addActivity(this);
        initData();
        setContentView(attachLayoutRes());
        setStatusBar();
        unbinder = ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        if (enableEventBus()) {
            EventBus.getDefault().register(this);
        }
        LogUtils.d(TAG,"onCreate");
        initInjector();
        initView();
        loadData();
    }

    @LayoutRes
    protected abstract int attachLayoutRes();

    protected void initInjector() {
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void loadData();

    /**
     * 跳到登录页并登录成功
     */
    protected void loginSucc() {
    }

    /**
     * 跳到登录页并取消登录
     */
    protected void loginCancel() {
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBar() {
        //StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
    }

    /***
     *
     * @return 是否注册EventBus
     */
    protected boolean enableEventBus() {
        return false;
    }

    protected boolean isLogin() {
        return AndroidApplication.getInstance().isLogin();
    }

    @Override
    public void tokenTimeout() {
        /*if (!LoginActivity.isRunning) {
            LoginActivity.start(this);
            AndroidApplication.getInstance().loginout();
            EventBus.getDefault().post(new MessageEvent(EventTag.loginout, null));
        }*/
    }

    public void addFragment(@IdRes int contentViewId, Fragment fragment) {
        if (contentViewId == Cons.INT_ZERO) {
            mFragmentManager.beginTransaction().add(fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().add(contentViewId, fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    public void addFragment(Fragment fragment) {
        addFragment(Cons.INT_ZERO, fragment);

    }

    public void removeFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    @Override
    public void showProgress() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isDestroyed()) {
                return;
            }
        }
        if (!isDestroyed() && !isFinishing()) {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadDialog(this);
            }
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
        }
    }
    @Override
    public void removeProgress() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing() && !isDestroyed() && !isFinishing()) {
            mLoadingDialog.dismiss();
        }
    }
    @Override
    public void toastAlert(String info) {
        showToast(info, TYPE_ALERT);
    }
    @Override
    public void toastSucc(String info) {
        showToast(info, TYPE_SUCC);
    }
    @Override
    public void toastFailed(String info) {
        showToast(info, TYPE_FAILED);
    }
    @Override
    public void toastNetError() {
        showToast(getString(R.string.net_error), TYPE_FAILED);
    }

    private void showToast(String info, int type) {
        if (info != null && !info.isEmpty()) {
            if (mToastView == null) {
                mToastView = getLayoutInflater().inflate(R.layout.toast, null);
            }
            ImageView mIv_toast_order_bid = mToastView.findViewById(R.id.iv_toast_order_bid);
            TextView mTv_toast_order_bid = mToastView.findViewById(R.id.tv_toast_order_bid);
            if (type == TYPE_SUCC) {
                mIv_toast_order_bid.setImageResource(R.drawable.chenggong);
            } else if (type == TYPE_FAILED) {
                mIv_toast_order_bid.setImageResource(R.drawable.shibai);
            } else if (type == TYPE_ALERT) {
                mIv_toast_order_bid.setImageResource(R.drawable.jingshi1);
            }
            mTv_toast_order_bid.setText(info);
            ToastUtil.toastCustomView(mToastView);
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> initNetLifecycler() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Cons.INTENT_LOGIN_REQUEST) {
            if (resultCode == Cons.INTENT_RESULT_OK) {
                //登录成功
                loginSucc();
            } else {
                loginCancel();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if ((!(this instanceof MainActivity)) && AndroidApplication.getInstance().getmActivityManager().getListSize() <= 1) {
            MainActivity.start(this);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        AndroidApplication.getInstance().getmActivityManager().removeActivity(this);
        if (enableEventBus()) {
            EventBus.getDefault().unregister(this);
        }

    }
}
