package com.blisek.restconsumer.main.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blisek.restconsumer.R;
import com.blisek.restconsumer.cats.activity.CatsActivity;
import com.blisek.restconsumer.cats.services.generators.CatsServiceGenerator;
import com.blisek.restconsumer.cats.structs.CatsPictures;
import com.blisek.restconsumer.icndb.activity.ICNDbActivity;
import com.blisek.restconsumer.icndb.async.RetrofitCall;
import com.blisek.restconsumer.icndb.services.generators.ICNDbServiceGenerator;
import com.blisek.restconsumer.icndb.structs.Joke;
import com.blisek.restconsumer.icndb.structs.Jokes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initProgressDialog();
    }

    private void initProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.main_progress_dialog_title));
        progressDialog.setMessage(getString(R.string.main_progress_dialog_message));
        progressDialog.setCancelable(false);
        this.progressDialog = progressDialog;
    }

    @OnClick(R.id.icndbButton)
    public void onICNDbButtonClicked(View view) {
        progressDialog.show();
        Call<Jokes> jokesCall = ICNDbServiceGenerator.getInstance().getRandomJokes(5);
        new RetrofitCall<>(jokes2JokeConverter, jokesReceiveCallback).execute(jokesCall);
    }

    @OnClick(R.id.catsButton)
    public void onCatsButtonClicked(View view) {
        progressDialog.show();
        Call<CatsPictures> catsPicturesCall = CatsServiceGenerator.getInstance().getRandomCatsPictures();
        new RetrofitCall<>(catsPicturesConverter, catsPicturesCallback).execute(catsPicturesCall);
    }


    private final RetrofitCall.Callback<Joke> jokesReceiveCallback = new RetrofitCall.Callback<Joke>() {
        @Override
        public void onResponseReceived(Collection<Joke> jokes) {
            Intent intent = new Intent(MainActivity.this, ICNDbActivity.class);
            Bundle jokesBundle = new Bundle();
            jokesBundle.putSerializable(ICNDbActivity.ARG_JOKE_LIST, (ArrayList<Joke>)jokes);
            intent.putExtras(jokesBundle);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            startActivity(intent);
        }
    };

    private final RetrofitCall.Converter<Response<Jokes>, Joke> jokes2JokeConverter = new RetrofitCall.Converter<Response<Jokes>, Joke>() {
        @Override
        public void convert(Response<Jokes> response, Collection<Joke> outputCollection) {
            Jokes joke = response.body();
            if("success".equals(joke.getType()))
                outputCollection.addAll(response.body().getValue());
        }
    };

    private final RetrofitCall.Callback<CatsPictures> catsPicturesCallback = new RetrofitCall.Callback<CatsPictures>() {
        @Override
        public void onResponseReceived(Collection<CatsPictures> responses) {
            CatsPictures catsPictures = responses.iterator().next();
            Intent intent = new Intent(MainActivity.this, CatsActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable(CatsActivity.ARG_CATS_PICTURES, catsPictures);
            intent.putExtras(extras);
            progressDialog.dismiss();
            startActivity(intent);
        }
    };

    private final RetrofitCall.Converter<Response<CatsPictures>, CatsPictures> catsPicturesConverter = new RetrofitCall.Converter<Response<CatsPictures>, CatsPictures>() {
        @Override
        public void convert(Response<CatsPictures> inputObj, Collection<CatsPictures> outputCollection) {
            if(inputObj.isSuccessful())
                outputCollection.add(inputObj.body());
        }
    };
}
