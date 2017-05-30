package com.blisek.restconsumer.cats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.blisek.restconsumer.R;
import com.blisek.restconsumer.cats.structs.CatsPictures;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatsActivity extends AppCompatActivity {
    private static final String TAG = "CatsActivity";
    public static final String ARG_CATS_PICTURES = "cats_pictures";

    private CatsPictures catsPictures;

    @BindView(R.id.urls_view)
    TextView urlsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        ButterKnife.bind(this);

        catsPictures = (CatsPictures)getIntent().getExtras().getSerializable(ARG_CATS_PICTURES);
        init();
    }

    private void init() {
        List<URL> urls = catsPictures.getPicturesUrls();
        StringBuilder builder = new StringBuilder(urls.size() * 2);
        for(URL url : urls) {
            builder.append(url.toString());
            builder.append('\n');
        }

        urlsView.setText(builder.toString());
    }
}
