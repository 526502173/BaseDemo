package com.anlu.ld.basedemo.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;



public class LoadDialog extends ProgressDialog {
    private AnimationDrawable anim;
    private @DrawableRes
    int res;

    public LoadDialog(Context context) {
        super(context);
        //super(context, R.style.AnimLoadingDialog);
       // this.res = R.drawable.anim_loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.dialog_loading);
       /* setCanceledOnTouchOutside(false);
        ImageView iv_loading = (ImageView) findViewById(R.id.iv_loading);
        iv_loading.setImageResource(res);
        anim = (AnimationDrawable) iv_loading.getDrawable();*/
    }

    @Override
    public void onStart() {
        super.onStart();
        if (anim != null){
            anim.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (anim != null) {
            anim.stop();
        }
    }
}
