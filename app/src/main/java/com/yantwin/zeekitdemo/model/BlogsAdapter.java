package com.yantwin.zeekitdemo.model;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yantwin.zeekitdemo.R;

import java.util.ArrayList;
import java.util.List;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.Holder> {

    private final LayoutInflater mInflater;
    private  Context mContext;
    private List<BlogsResponse> mBlogsList;
    private BlogsClickListener mListener;


    public BlogsAdapter(BlogsClickListener listener, LayoutInflater inflater, Context context) {
        mContext = context;
        mListener = listener;
        mInflater = inflater;
        mBlogsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_blogs, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        BlogsResponse currBlogs = mBlogsList.get(position);

        holder.mName.setText(currBlogs.getName());
//        holder.mPrice.setText(String.format("$%.2f", currBlogs.getPrice()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                mContext,
                LinearLayoutManager.HORIZONTAL,
                false
        );

        holder.productRecyclerView.setLayoutManager(linearLayoutManager);
        holder.productRecyclerView.setAdapter(new SecondaryAdapter(currBlogs.getProductsList(),mListener,mContext));
        Glide.with(holder.itemView.getContext()).load( currBlogs.getImage_url()).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mBlogsList.size();
    }

    public void addBlogss(List<BlogsResponse> BlogsResponses) {
        mBlogsList.addAll(BlogsResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView mPhoto;
        private TextView mName;
        private RecyclerView productRecyclerView;

        public Holder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.BlogsPhoto);
            mName = (TextView) itemView.findViewById(R.id.BlogsName);
            productRecyclerView= (RecyclerView)itemView.findViewById(R.id.productRecyclerView);


        }


    }

    public interface BlogsClickListener {

        void onClick(int position, Products mProd);
    }



}
