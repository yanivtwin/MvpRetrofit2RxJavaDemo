package com.yantwin.zeekitdemo.dependencies;


import com.yantwin.zeekitdemo.service.BlogsService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ApiModule {

    @Provides
    @CustomScope
    BlogsService provideBlogsService(Retrofit retrofit) {
        return retrofit.create(BlogsService.class);
    }
}
