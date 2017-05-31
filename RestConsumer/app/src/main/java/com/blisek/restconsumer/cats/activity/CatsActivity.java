package com.blisek.restconsumer.cats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.blisek.restconsumer.R;
import com.blisek.restconsumer.cats.structs.CatsPictures;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatsActivity extends AppCompatActivity {
    private static final String TAG = "CatsActivity";
    public static final String ARG_CATS_PICTURES = "cats_pictures";
    private CatsPictures catsPictures;

    @BindView(R.id.images_gridview)
    GridView imagesGridView;

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

    }


    private static class CatsImagesAdapter extends BaseAdapter {
        private CatsPictures pictures;
        private List<URL> picturesUrls;

        public CatsImagesAdapter(CatsPictures pictures) {
            this.pictures = Objects.requireNonNull(pictures);
            picturesUrls = pictures.getPicturesUrls();
        }

        @Override
        public int getCount() {
            return pictures.getPicturesUrls().size();
        }

        @Override
        public Object getItem(int position) {
            return picturesUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
