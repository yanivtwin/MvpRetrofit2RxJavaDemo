package com.yantwin.zeekitdemo.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yantwin.zeekitdemo.R;

import java.util.List;


class SecondaryAdapter extends RecyclerView.Adapter<SecondaryAdapter.SecondaryViewHolder>  {
    private List<Products> mProducts;
    private Context mContext;
    private BlogsAdapter.BlogsClickListener mListener;

    /**
     * this is the second adapter will get a onclick call and pass it to the activity along with the data
     *
     */


    public SecondaryAdapter(List<Products> products,BlogsAdapter.BlogsClickListener listener,Context context) {
        mContext=context;
        mProducts = products;
        mListener = listener;

    }



    @Override
    public SecondaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_products, parent, false);
        return new SecondaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SecondaryViewHolder holder, int position) {
        Products currProduct= mProducts.get(position);
        holder.bindView(currProduct);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class SecondaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName;
        private ImageView mImage;
        private TextView mPriority;

        public SecondaryViewHolder(View view) {
            super(view);
            mName = (TextView) itemView.findViewById(R.id.productName);
            mPriority = (TextView) itemView.findViewById(R.id.productPriority);
            mImage = (ImageView) itemView.findViewById(R.id.productPhoto);
            view.setOnClickListener(this);

        }

        public void bindView(Products prod) {
            mName.setText(prod.getName());
            mPriority.setText(String.valueOf(prod.getPriority()));
            Glide.with(mContext).load( prod.getBase_image()).into(mImage);

        }
        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mProducts.get(getAdapterPosition()));

        }
    }
}

