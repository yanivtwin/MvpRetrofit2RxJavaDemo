package com.yantwin.zeekitdemo.service;


import com.yantwin.zeekitdemo.model.BlogsResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface BlogsService {   // the call to get the json interface using retrofit

    @GET("/post_1.json")
    Observable<List<BlogsResponse>> getBlogss();
}
