package com.yantwin.zeekitdemo.service;


import com.yantwin.zeekitdemo.model.BlogsResponse;

import java.util.List;

import rx.Observable;


public interface BlogsViewInterface {

    void onCompleted();

    void onError(String message);

    void onBlogss(List<BlogsResponse> BlogsResponses);

    Observable<List<BlogsResponse>> getBlogss();
}
