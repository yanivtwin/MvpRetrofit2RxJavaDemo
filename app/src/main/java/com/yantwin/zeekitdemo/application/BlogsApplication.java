package com.yantwin.zeekitdemo.application;

import android.app.Application;

import com.yantwin.zeekitdemo.dependencies.ApiComponent;
import com.yantwin.zeekitdemo.dependencies.DaggerApiComponent;
import com.yantwin.zeekitdemo.dependencies.DaggerNetworkComponent;
import com.yantwin.zeekitdemo.dependencies.NetworkComponent;
import com.yantwin.zeekitdemo.dependencies.NetworkModule;
import com.yantwin.zeekitdemo.model.Constant;


/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 3/19/2016
 */
public class BlogsApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
