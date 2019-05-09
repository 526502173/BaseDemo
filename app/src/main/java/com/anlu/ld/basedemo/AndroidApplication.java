package com.anlu.ld.basedemo;

import android.app.Application;
import android.content.Context;

import com.anlu.ld.basedemo.cons.CacheKey;
import com.anlu.ld.basedemo.cons.SpKey;
import com.anlu.ld.basedemo.data.bean.PublicParamBean;
import com.anlu.ld.basedemo.data.source.Repository;
import com.anlu.ld.basedemo.injector.components.ApplicationComponent;
import com.anlu.ld.basedemo.injector.components.DaggerApplicationComponent;
import com.anlu.ld.basedemo.injector.modules.ApplicationModule;
import com.anlu.ld.basedemo.utlis.ActivityListManager;
import com.anlu.ld.basedemo.utlis.CacheUtils;
import com.anlu.ld.basedemo.utlis.HttpUtils;
import com.anlu.ld.basedemo.utlis.SpUtils;
import com.anlu.ld.basedemo.utlis.toast.ToastUtils;
import com.facebook.stetho.Stetho;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;



/**
 * Aplication
 */

public class AndroidApplication extends Application {
    private static AndroidApplication sApplication;
    private ApplicationComponent mAppComponent;
    public final boolean isDebug = BuildConfig.isDebug;
    private ActivityListManager mActivityManager;
    private PublicParamBean mPublicParamBean;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        sApplication = this;
        mActivityManager = new ActivityListManager();
        //初始化toast
        ToastUtils.init(this);
        /*//初始化渠道
        int channelId = Channel.getChannelId(this);*/

        //初始化Stetho
        if (isDebug) {
            Stetho.initializeWithDefaults(this);
        }

        mPublicParamBean = new PublicParamBean();
        HttpUtils.handleSSLHandshake();
    }

    public PublicParamBean getPublicParamBean() {
        if (mPublicParamBean == null) {
            return new PublicParamBean();
        }
        return mPublicParamBean;
    }

    public Map<String, String> getPublicParamMap() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            PublicParamBean publicParamBean = getPublicParamBean();
            Field[] fields = null;
            String clzName = publicParamBean.getClass().getSimpleName();
            fields = publicParamBean.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String proName = field.getName();
                Object proValue = field.get(publicParamBean);
                if (proName != null && proValue != null) {
                    map.put(proName, proValue.toString());
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return map;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //多dex
//        MultiDex.install(base);
    }

    /**
     * 是否debug
     *
     * @return
     */
    public static boolean isDebug() {
        return BuildConfig.isDebug;
    }

    private void initInjector() {
        mAppComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public static AndroidApplication getInstance() {
        return sApplication;
    }

    public ActivityListManager getmActivityManager() {
        return mActivityManager;
    }

    public Repository getRepository() {
        return mAppComponent.getRepository();
    }


   /* public void loginSucc(LoginBean data) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setAvatar(data.getAvatar());
        personalInfo.setNickname(data.getNickname());
        setUser(personalInfo);
        CacheUtils.putString(CacheKey.TOKEN, data.getToken());
    }

    public void setUser(PersonalInfo user) {
        mUser = user;
        CacheUtils.putBean(CacheKey.USER, mUser);
    }*/


   /* public PersonalInfo getUser() {
        if (mUser == null) {
            mUser = (PersonalInfo) CacheUtils.getBean(CacheKey.USER, PersonalInfo.class);
        }
        return mUser;
    }*/

    public String getDeviceId() {
        return SpUtils.getInstance().getString(SpKey.DEVICE);
    }

    public boolean isLogin() {
        String token = CacheUtils.getString(CacheKey.TOKEN);
        return token != null && !token.isEmpty();
    }

   /* public void loginout() {
        mUser = null;
        CacheUtils.remove(CacheKey.USER);
        CacheUtils.remove(CacheKey.TOKEN);
    }

    public Observable<H5PagerBean> getH5PagerBean() {
        if (mH5PagerBean == null) {
            mH5PagerBean = (H5PagerBean) CacheUtils.getBean(CacheKey.H5_PAGER, H5PagerBean.class);
        }
        Observable<H5PagerBean> remoteObservable = mAppComponent.getRepository().getH5Pagers().map(new SimpleWrapperFunction<H5PagerBean>(null));
        return mH5PagerBean == null ? remoteObservable : Observable.just(mH5PagerBean);
    }

    public void setH5PagerBean(H5PagerBean h5PagerBean) {
        mH5PagerBean = h5PagerBean;
        CacheUtils.putBean(CacheKey.H5_PAGER, h5PagerBean);
    }*/


}
