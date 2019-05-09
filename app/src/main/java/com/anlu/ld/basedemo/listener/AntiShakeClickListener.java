package com.anlu.ld.basedemo.listener;

import android.view.View;

import com.anlu.ld.basedemo.utlis.AntiShakeUtils;


public abstract class AntiShakeClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        if (!AntiShakeUtils.isInvalidClick(v)) {
            click(v);
        }
    }

    public abstract void click(View v);
}