package picodiploma.dicoding.moviesubmission3.rest;


import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularResponse;
import picodiploma.dicoding.moviesubmission3.utils.Config;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvShowApiService {
    @GET("tv/popular")
    Call<TvShowPopularResponse> getTvShowPopular(@Query("api_key") String apiKey);
}
