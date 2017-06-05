package com.yantwin.zeekitdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yantwin.zeekitdemo.R;
import com.yantwin.zeekitdemo.application.BlogsApplication;
import com.yantwin.zeekitdemo.base.BlogsPresenter;
import com.yantwin.zeekitdemo.dependencies.DividerItemDecoration;
import com.yantwin.zeekitdemo.model.BlogsAdapter;
import com.yantwin.zeekitdemo.model.BlogsResponse;
import com.yantwin.zeekitdemo.model.Products;
import com.yantwin.zeekitdemo.service.BlogsService;
import com.yantwin.zeekitdemo.service.BlogsViewInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,BlogsViewInterface, BlogsAdapter.BlogsClickListener {
    public static final String EXTRA_MESSAGE = "Prod";

    @Inject
    BlogsService mService;

    private BlogsPresenter mPresenter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.downloadButton)
    Button downloadButton;
    private BlogsAdapter mAdapter;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        ButterKnife.bind(MainActivity.this);

        configViews();
        mPresenter = new BlogsPresenter(MainActivity.this);
        mPresenter.onCreate();
        downloadButton.setOnClickListener(this);
    }

    private void resolveDependency() {    // inject dependencies
        ((BlogsApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void configViews() {  // set the main blog list
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new BlogsAdapter(this, getLayoutInflater(),this);
        mRecyclerView.setAdapter(mAdapter);
    }



    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }//listener for the result oncomplere

    @Override
    public void onError(String message) {//listener for the result error
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBlogss(List<BlogsResponse> BlogsResponses) {
        mAdapter.addBlogss(BlogsResponses);
    }   //listener for the result done

    @Override
    public Observable<List<BlogsResponse>> getBlogss() {
        return mService.getBlogss();
    } // will get the json using Retrofit



    @Override
    public void onClick(View view) {   //on download button clicked
        mPresenter.onResume();
        mPresenter.fetchBlogss();
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    public void onClick(int position, Products mProd) {  //on click on product will move to Main2Activity
        Log.e("THIS IS CLICKED ",position+"     "+mProd.getName());
        Intent intent = new Intent(this, Main2Activity.class);

        intent.putExtra(EXTRA_MESSAGE, (Parcelable) mProd);
        startActivity(intent);
    }
}
