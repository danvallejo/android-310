package com.cstructor.android310;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class MyApplication extends Application {
    private MyComponent mMyComponent;
    private static MyApplication mMyApplication;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mMyApplication = this;

        // Dagger%COMPONENT_NAME%
        mMyComponent = DaggerMyComponent.builder()
                // list of modules that are part of this component need to be created here too
                // This also corresponds to the name of your module: %component_name%Module
                .myModule(new MyModule())
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public static MyComponent getMyComponent() {
        return mMyApplication.mMyComponent;
    }
}
