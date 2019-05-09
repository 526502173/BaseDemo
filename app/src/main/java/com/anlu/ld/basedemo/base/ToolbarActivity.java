package com.anlu.ld.basedemo.base;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


import com.anlu.ld.basedemo.R;

import butterknife.BindView;

/**
 * Created by maoqi on 2018/6/4.
 */
public abstract class ToolbarActivity extends BaseActivity {

    @BindView(R.id.tb_toolbar)
    protected Toolbar tb_toolbar;
    @Nullable
    @BindView(R.id.tv_title)
    protected TextView tv_title;


    @Override
    protected void initView() {
        initToolBar();
    }

    public void initToolBar() {
        setSupportActionBar(tb_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isEnableBackArrow());
        actionBar.setDisplayShowHomeEnabled(isEnableBackArrow());
        actionBar.setDisplayShowTitleEnabled(false);
        if (tv_title != null) {
            tv_title.setText(setToolbarTitle());
        }
    }

    abstract protected String setToolbarTitle();

    protected boolean isEnableBackArrow() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitleColor(int color) {
        if (tv_title != null) {
            tv_title.setTextColor(color);
        }
    }
}
