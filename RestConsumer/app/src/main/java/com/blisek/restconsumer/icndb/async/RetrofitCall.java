package com.blisek.restconsumer.icndb.async;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public class RetrofitCall<T, R> extends AsyncTask<Call<T>, Void, List<R>> {
    private static final String TAG = "RetrofitCall";

    public interface Callback<T> {
        void onResponseReceived(Collection<T> responses);
    }

    public interface Converter<T, R> {
        void convert(T inputObj, Collection<R> outputCollection);
    }

    private final Converter<Response<T>, R> converter;
    private final Callback<R> callback;

    public RetrofitCall(Converter<Response<T>, R> converter, Callback<R> callback) {
        this.converter = Objects.requireNonNull(converter);
        this.callback = Objects.requireNonNull(callback);
    }


    @Override
    protected List<R> doInBackground(Call<T>... calls) {
        final List<Response<T>> responses = new ArrayList<>(calls.length);
        for(Call<T> retrofitCall : calls) {
            try {
                Response<T> response = retrofitCall.execute();
                responses.add(response);
            }
            catch (IOException ioe) {
                Log.e(TAG, "Error while communicating over network interface", ioe);
            }
        }

        final List<R> convertedResponses = new ArrayList<>(responses.size());
        for(Response<T> response : responses) {
            if(response.isSuccessful())
                converter.convert(response, convertedResponses);
        }

        return convertedResponses;
    }

    @Override
    protected void onPostExecute(List<R> rs) {
        callback.onResponseReceived(rs);
    }
}
