package com.yantwin.zeekitdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yantwin.zeekitdemo.R;
import com.yantwin.zeekitdemo.model.Products;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    @Bind(R.id.imageView)
    ImageView img;
    @Bind(R.id.textView)
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {    //second activity , will get the parcelable data and put the text and image on the screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Products prod= getIntent().getExtras().getParcelable(MainActivity.EXTRA_MESSAGE);
        ButterKnife.bind(Main2Activity.this);

        Log.e("clicked",""+prod.getName());
        Glide.with(this).load( prod.getDemo_image()).into(img);
        txt.setText(prod.getName());

    }
}
