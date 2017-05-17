package com.cstructor.android310;

import dagger.Component;

@Component(
        modules = {MyModule.class}
)
public interface MyComponent {
    void inject(MainActivity activity);

    MyService myService();
}
