package picodiploma.dicoding.moviesubmission3.ui.main.MovieViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MoviePopulerData;

public class MoviePopularViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MoviePopulerData>> listMoviePopular = new MutableLiveData<>();


    public void setMoviePopular(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MoviePopulerData> listItemMp = new ArrayList<>();
        String API_KEY = "848001472ba79b519655a14b11681993";
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listMp = responseObject.getJSONArray("results");

                    for (int i = 0; i < listMp.length(); i++) {
                        JSONObject movieP = listMp.getJSONObject(i);
                        MoviePopulerData mpData = new MoviePopulerData(movieP);
                        listItemMp.add(mpData);
                    }
                    listMoviePopular.postValue(listItemMp);
                } catch (Exception e) {
                    Log.d("Exeption", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<MoviePopulerData>> getMoviePopular(){
        return listMoviePopular;
    }
}
