package com.yantwin.zeekitdemo.dependencies;


import com.yantwin.zeekitdemo.ui.MainActivity;

import dagger.Component;

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity activity);
}
