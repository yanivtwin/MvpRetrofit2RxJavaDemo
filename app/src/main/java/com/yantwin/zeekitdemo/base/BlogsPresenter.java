package com.yantwin.zeekitdemo.base;


import com.yantwin.zeekitdemo.model.BlogsResponse;
import com.yantwin.zeekitdemo.model.Products;
import com.yantwin.zeekitdemo.service.BlogsViewInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


public class BlogsPresenter extends BasePresenter implements Observer<List<BlogsResponse>> {

    private BlogsViewInterface mViewInterface;

    public BlogsPresenter(BlogsViewInterface viewInterface) {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() { // report to activity on complete
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }// report to activity on error

    @Override
    public void onNext(List<BlogsResponse> BlogsResponses) {//after getting the json arrange the list and the sublists

        arrangeList(BlogsResponses);
    }

    private void arrangeList(List<BlogsResponse> blogsResponses) {   //will arrange the list (and the sublist by priority)
        Observable.from(blogsResponses)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .toSortedList(new Func2<BlogsResponse, BlogsResponse, Integer>() {
                    @Override
                    public Integer call(BlogsResponse response, BlogsResponse response2) {
                        return Long.compare( response2.getPriority(),response.getPriority());  //will arrange the list
                    }
                })
                .flatMap(new Func1<List<BlogsResponse>, Observable<List<BlogsResponse>>>() {
                    @Override
                    public Observable<List<BlogsResponse>> call(List<BlogsResponse> blogs) {
                        // will arrange the sublists
                        List<List<BlogsResponse>> allBlogs = new ArrayList<>();
                        List<BlogsResponse> singleBlog = new ArrayList<>();
                        int counter=0;
                        for (BlogsResponse blog : blogs) {

                            blog.setProductsList(arrangeBlog(blog.getProductsList()));

                            singleBlog.add(blog);
                            counter++;

                        }
                        allBlogs.add(singleBlog);
                        return Observable.from(allBlogs);
                    }
                })
                .subscribe(new Action1<List<BlogsResponse>>() {
                    @Override
                    public void call(List<BlogsResponse> BlogsResponses) {
                        mViewInterface.onBlogss(BlogsResponses);
                    }
                });
    }

    private List<Products> arrangeBlog(List<Products> products) { //will arrange
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products products, Products t1) {
                return t1.getPriority()-products.getPriority();
            }


        });
        return products;
    }

    public void fetchBlogss() {
        unSubscribeAll();
        subscribe(mViewInterface.getBlogss(), BlogsPresenter.this);
    }
}
