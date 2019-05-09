package com.anlu.ld.basedemo.module;

import android.content.Context;
import android.content.Intent;

import com.anlu.ld.basedemo.R;
import com.anlu.ld.basedemo.base.BaseActivity;

public class MainActivity extends BaseActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
