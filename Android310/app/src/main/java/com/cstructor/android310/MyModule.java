package com.cstructor.android310;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    @Provides
    MyService providesMyService() {
        return new MyService();
    }
}
