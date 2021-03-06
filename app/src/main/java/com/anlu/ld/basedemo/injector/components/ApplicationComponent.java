package com.anlu.ld.basedemo.injector.components;

import android.content.Context;

import com.anlu.ld.basedemo.data.source.Repository;
import com.anlu.ld.basedemo.injector.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MAO on 2018/6/2.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getApplicationContext();

    Repository getRepository();
}
