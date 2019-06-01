package picodiploma.dicoding.moviesubmission3.viewmodel.tvshowviewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularData;
import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularResponse;
import picodiploma.dicoding.moviesubmission3.rest.TvShowApiService;
import picodiploma.dicoding.moviesubmission3.utils.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowPopularViewModel extends ViewModel {

    private MutableLiveData<List<TvShowPopularData>> listTvShowPopular;


    public LiveData<List<TvShowPopularData>> getTvShowPopular() {
        if (listTvShowPopular == null) {
            listTvShowPopular = new MutableLiveData<>();

            loadPopularTV();
        }
        return listTvShowPopular;
    }

    private void loadPopularTV() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TvShowApiService tvShowApiService = retrofit.create(TvShowApiService.class);

        Call<TvShowPopularResponse> call = tvShowApiService.getTvShowPopular(Config.API_KEY);
        call.enqueue(new Callback<TvShowPopularResponse>() {
            @Override
            public void onResponse(Call<TvShowPopularResponse> call, Response<TvShowPopularResponse> response) {
                listTvShowPopular.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvShowPopularResponse> call, Throwable t) {
                Log.e("Failure", t.getMessage());
            }
        });

    }
}
